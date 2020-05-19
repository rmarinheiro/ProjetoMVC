package com.rafael.projetomvc.services;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.rafael.projetomvc.DTO.ClienteDTO;
import com.rafael.projetomvc.DTO.ClienteNewDTO;
import com.rafael.projetomvc.dominio.Categoria;
import com.rafael.projetomvc.dominio.Cidade;
import com.rafael.projetomvc.dominio.Cliente;
import com.rafael.projetomvc.dominio.Endereco;
import com.rafael.projetomvc.dominio.enums.Perfil;
import com.rafael.projetomvc.dominio.enums.TipoCliente;
import com.rafael.projetomvc.repository.ClienteRepository;
import com.rafael.projetomvc.repository.EnderecoRepository;
import com.rafael.projetomvc.security.UserSS;
import com.rafael.projetomvc.services.exception.AuthorizationException;
import com.rafael.projetomvc.services.exception.DataIntegrityException;
import com.rafael.projetomvc.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private ImageService imageService;
	
	
	@Autowired
	private S3Service s3Service;
	
	@Value("${img.prefix.client.profile}")
	private String prefix;

	
	public Cliente find(Integer cod) {
		UserSS user = UserService.authenticated();
		if(user == null || !user.hasRole(Perfil.ADMIN) && !cod.equals(user.getId())) {
			throw new AuthorizationException("Acesso Negado");
			
		}
		
		Optional<Cliente> obj = clienteRepository.findById(cod);
		return obj.orElseThrow(()-> new ObjectNotFoundException("Cliente Id não encontrado id:" + cod + Cliente.class));
	}
	
	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj,obj);
		return clienteRepository.save(newObj);
	}

	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
		
	}

	public void delete(Integer id) {
		 find(id);
		try {
			clienteRepository.deleteById(id);
			
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é Possível excluir um cliente que tenha pedidos");
		}
		
	}

	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}
	
	public Page<Cliente> findPage(Integer page , Integer linesPage, String orderBy,String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPage, Direction.valueOf(direction), orderBy);
		return clienteRepository.findAll(pageRequest);
	}
	
	public Cliente fromDTO(ClienteDTO clienteDTO) {
		return new Cliente(clienteDTO.getId(),clienteDTO.getNome(),clienteDTO.getEmail(),null,null,null);
	}
	
	
	public Cliente fromDTO(ClienteNewDTO clienteDTO) {
	  Cliente cli = new Cliente(null, clienteDTO.getNome(), clienteDTO.getEmail(), clienteDTO.getCpfOuCnpj(),TipoCliente.toEnum(clienteDTO.getTipoCliente()),pe.encode(clienteDTO.getSenha()));
	  Cidade cid = new Cidade(clienteDTO.getCidadeID(), null,null);
	  Endereco end = new Endereco(null, clienteDTO.getLogradouro(), clienteDTO.getNumero(), clienteDTO.getComplemento(), clienteDTO.getBairro(), clienteDTO.getCep(), cli, cid);
	  cli.getEnderecos().add(end);
	  cli.getTelefones().add(clienteDTO.getTelefone1());
	  if(clienteDTO.getTelefone2() != null) {
		  cli.getTelefones().add(clienteDTO.getTelefone2());
	  }
	  
	  if(clienteDTO.getTelefone3() != null) {
		  cli.getTelefones().add(clienteDTO.getTelefone3());
	  }
	  
	  return cli;
	}

	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = clienteRepository.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}
	
	public URI uploadProfilePicture(MultipartFile multipartfile) {
		UserSS user = UserService.authenticated();
		if(user == null) {
			throw new AuthorizationException("Acesso Negado");
		}
		
		
		BufferedImage jpgImage = imageService.getJpgImageFromFile(multipartfile);
		String fileName = prefix + user.getId() + ".jpg";
		return s3Service.uploadFile(imageService.getInputStream(jpgImage,"jpg"), fileName, "image");
		
		
		
		
		
	}
	
	

}
