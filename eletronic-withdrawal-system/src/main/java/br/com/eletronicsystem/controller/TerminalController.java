package br.com.eletronicsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.eletronicsystem.entities.Terminal;
import br.com.eletronicsystem.repository.TerminalRepository;
import br.com.eletronicsystem.services.TerminalService;

@CrossOrigin
@RestController
@RequestMapping("terminal")
public class TerminalController 
{
	@Autowired
	TerminalRepository terminalRepository;
	
	@Autowired
	TerminalService terminalService;
	
	@GetMapping
	public ResponseEntity<?> listAllTerminals()
	{
		return new ResponseEntity<Iterable<Terminal>>(terminalRepository.findAll(), HttpStatus.OK);
	}
	
	
	@PutMapping(path = "/withdraw/{valueToWithdraw}")
	public ResponseEntity<?> update(@PathVariable int valueToWithdraw) throws Exception
	{
		return new ResponseEntity<Object>(terminalService.doWithdraw(valueToWithdraw),HttpStatus.OK);
	}
	

}
