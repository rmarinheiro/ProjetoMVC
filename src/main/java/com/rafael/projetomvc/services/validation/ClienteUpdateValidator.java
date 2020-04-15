package com.rafael.projetomvc.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.ObjDoubleConsumer;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.rafael.projetomvc.DTO.ClienteDTO;
import com.rafael.projetomvc.DTO.ClienteNewDTO;
import com.rafael.projetomvc.dominio.Cliente;
import com.rafael.projetomvc.dominio.enums.TipoCliente;
import com.rafael.projetomvc.repository.ClienteRepository;
import com.rafael.projetomvc.resources.exception.FieldMessage;
import com.rafael.projetomvc.services.validation.utils.BR;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {
	
	@Autowired
	private HttpServletRequest httpServletRequest;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public void initialize(ClienteUpdate ann) {
	}
	
	@Override
	public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {
		
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) httpServletRequest.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		map.get("id");
		Integer urlId = Integer.parseInt(map.get("id"));
		
		List<FieldMessage> list = new ArrayList<>();
		
		
		// inclua os testes aqui, inserindo erros na lista
		
		Cliente aux = clienteRepository.findByEmail(objDto.getEmail());
		if(aux != null && !aux.getId().equals(urlId)) {
			list.add(new FieldMessage("Email", "Email Já Existente"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
	

}