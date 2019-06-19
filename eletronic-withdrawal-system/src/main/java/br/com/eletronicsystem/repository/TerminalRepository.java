package br.com.eletronicsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.eletronicsystem.entities.Terminal;

public interface TerminalRepository extends JpaRepository<Terminal, Long>
{
	
}
