package br.com.bankapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bankapi.entities.DrawerTerminal;

@Repository
public interface DrawerTerminalRepository extends JpaRepository<DrawerTerminal, Long>
{	
	public Optional<DrawerTerminal> findByNoteType(int noteType);
}