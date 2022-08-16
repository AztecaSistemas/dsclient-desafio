package com.devsuperior.dsclient.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dsclient.dto.ClientDTO;
import com.devsuperior.dsclient.entities.Client;
import com.devsuperior.dsclient.repositories.ClientRepository;
import com.devsuperior.dsclient.services.exceptions.EntityNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;
	
	@Transactional(readOnly = true)
	public List<ClientDTO> findAll() {
		List<Client> list = repository.findAll();
		
		// A linha abaixo é equivalente ao bloco de provessamento e return logo abaixo desta linha
		return list.stream().map(x -> new ClientDTO(x)).collect(Collectors.toList());
		
		/*
		List<ClientDTO> listDto = new ArrayList<>();
		for (Client cli : list) {
			listDto.add(new ClientDTO(cli));
		}
		return listDto;
		*/
		
	}

	@Transactional(readOnly = true)
	public ClientDTO findById(Long id) {
		Optional<Client> obj = repository.findById(id);
		//Client entity = obj.get();
		Client entity = obj.orElseThrow(() -> new EntityNotFoundException("ID não encontrado!"));
		return new ClientDTO(entity);
	}
	
}
