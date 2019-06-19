package br.com.bankapi.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bankapi.entities.DrawerTerminal;
import br.com.bankapi.repository.DrawerTerminalRepository;
import br.com.bankapi.services.TerminalService;

@RestController
@RequestMapping("bank")
public class TerminalController 
{
	@Autowired
	DrawerTerminalRepository drawerTerminalRepository;
	
	@Autowired
	TerminalService terminalService;
	
	@PutMapping(path = "/supply/{noteType}/{valueToSupply}")
	public ResponseEntity<?> update(@PathVariable int valueToSupply,@PathVariable int noteType) throws Exception
	{
		Optional<DrawerTerminal> drawerOptional = drawerTerminalRepository.findByNoteType(noteType);
		
		if (!drawerOptional.isPresent())
		{
			return ResponseEntity.notFound().build();
		}
		
		drawerOptional.get().setQttNote(valueToSupply);	
		drawerTerminalRepository.save(drawerOptional.get());
		
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

}
