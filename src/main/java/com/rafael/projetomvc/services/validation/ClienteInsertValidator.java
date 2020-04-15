package com.rafael.projetomvc.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.function.ObjDoubleConsumer;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.rafael.projetomvc.DTO.ClienteNewDTO;
import com.rafael.projetomvc.dominio.Cliente;
import com.rafael.projetomvc.dominio.enums.TipoCliente;
import com.rafael.projetomvc.repository.ClienteRepository;
import com.rafael.projetomvc.resources.exception.FieldMessage;
import com.rafael.projetomvc.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public void initialize(ClienteInsert ann) {
	}
	
	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		if(objDto.getTipoCliente().equals(TipoCliente.PessoaFisica.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj" , "Cpf Invalido"));
		}
		
		if(objDto.getTipoCliente().equals(TipoCliente.PessoaJuridica.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj" , "CNPJ Invalido"));
		}
		// inclua os testes aqui, inserindo erros na lista
		
		Cliente aux = clienteRepository.findByEmail(objDto.getEmail());
		if(aux != null) {
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