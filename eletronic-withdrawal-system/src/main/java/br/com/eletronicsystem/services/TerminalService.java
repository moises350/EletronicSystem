package br.com.eletronicsystem.services;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.eletronicsystem.dto.WithdrawDto;
import br.com.eletronicsystem.entities.DrawerTerminal;
import br.com.eletronicsystem.entities.Terminal;
import br.com.eletronicsystem.error.ResourceNotFoundException;
import br.com.eletronicsystem.repository.DrawerTerminalRepository;
import br.com.eletronicsystem.repository.TerminalRepository;

@Service
public class TerminalService {
	
	@Autowired
	DrawerTerminalRepository drawerTerminalRepository;
	
	@Autowired
	TerminalRepository terminalRepository;
	
	@Value("${api.url_base}")
	String url_base;
	
	@Value("${api_published_key}")
	String key;
	
	
	public void callBankToSupply(int noteType, int valueToSupply)
	{
		RestTemplate rest = new RestTemplate();	
		String url = url_base+"bank/supply/"+noteType+"/"+valueToSupply;
		
		RequestEntity<?> request = RequestEntity				
				.put(URI.create(url))
				.contentLength(0)
				.header("Authorization", key)
				.body("");
		
		rest.exchange(request, String.class);
	}
	
	
	
	public WithdrawDto doWithdraw(Integer value) throws Exception
	{
		//
		List<DrawerTerminal> list = drawerTerminalRepository.findAll();
		
		for(int i = 0; i<list.size(); i++)
		{
			if(list.get(i).getQttNote() == 0)
			{
				callBankToSupply(list.get(i).getNoteType(),250);// quando o saldo de determinada nota estiver zerada, chama a bank-api pra abastecer
			}
			
		}
		
		Optional<Terminal> terminal = terminalRepository.findById(new Long(1)); // Foi inserido hardcoded devido à falta de tempo e terá somente um terminal
		
		WithdrawDto withdrawDto = new WithdrawDto();
		
		
		if(terminal.get().getIdStatus() == Terminal.TERMINAL_STATUS_ACTIVE)
		{
			if(value % 10 == 0 && value > 0)
			{
				int valueAux = value;
				for (DrawerTerminal drawerTerminal : list) 
				{
					while(valueAux >= drawerTerminal.getNoteType())
					{
						valueAux -= drawerTerminal.getNoteType();
						
						switch(drawerTerminal.getNoteType()) 
						{
						
							case 10:
								withdrawDto.setQttNotes10(withdrawDto.getQttNotes10() + 1);
							break;
							
							case 20:
								withdrawDto.setQttNotes20(withdrawDto.getQttNotes20() + 1);
							break;
							
							case 50:
								withdrawDto.setQttNotes50(withdrawDto.getQttNotes50() + 1);
							break;
							
							case 100:
								withdrawDto.setQttNotes100(withdrawDto.getQttNotes100() + 1);
							break;
						}
						
					}
				}
			}
			else
			{
				throw new ResourceNotFoundException("Value Invalid");
			}
			
			withdrawDto.setValueToWithdraw(value);
			
			updateQttNotes(withdrawDto);
		
		}
		else
		{
			throw new Exception("Terminal is temporary inactive to supply");
		}
		return withdrawDto;
	}
	
	public void updateQttNotes(WithdrawDto dto)
	{
		if(dto.getQttNotes10() > 0)
		{
			Optional<DrawerTerminal> drawerOptional10 = drawerTerminalRepository.findByNoteType(10);
			drawerOptional10.get().setQttNote(drawerOptional10.get().getQttNote() - dto.getQttNotes10());
			drawerTerminalRepository.save(drawerOptional10.get());
		}
		
		if(dto.getQttNotes20() > 0)
		{
			Optional<DrawerTerminal> drawerOptional20 = drawerTerminalRepository.findByNoteType(20);
			drawerOptional20.get().setQttNote(drawerOptional20.get().getQttNote() - dto.getQttNotes20());
			drawerTerminalRepository.save(drawerOptional20.get());
		}
		
		if(dto.getQttNotes50() > 0)
		{
			Optional<DrawerTerminal> drawerOptional50 = drawerTerminalRepository.findByNoteType(50);
			drawerOptional50.get().setQttNote(drawerOptional50.get().getQttNote() - dto.getQttNotes50());
			drawerTerminalRepository.save(drawerOptional50.get());
		}
		
		if(dto.getQttNotes100() > 0)
		{
			Optional<DrawerTerminal> drawerOptional100 = drawerTerminalRepository.findByNoteType(100);
			drawerOptional100.get().setQttNote(drawerOptional100.get().getQttNote() - dto.getQttNotes100());
			drawerTerminalRepository.save(drawerOptional100.get());
		}
		
	}
}
