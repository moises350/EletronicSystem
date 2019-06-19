package br.com.bankapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bankapi.entities.Terminal;

public interface TerminalRepository extends JpaRepository<Terminal, Long>
{
	
}
