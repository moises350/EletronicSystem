package br.com.bankapi.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bankapi.entities.Terminal;
import br.com.bankapi.repository.TerminalRepository;

@Service
public class TerminalService 
{
	@Autowired
	TerminalRepository terminalRepository;
	
	public void updateTerminalStatus(int idTerminal, int idStatus) throws Exception
	{
		Optional<Terminal> terminal = terminalRepository.findById((long) idTerminal);
		
		if (!terminal.isPresent())
		{
			throw new Exception("Terminal not Found");
		}
		
		terminal.get().setIdStatus(idStatus);;	
		terminalRepository.save(terminal.get());
	}
}
