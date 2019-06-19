package br.com.eletronicsystem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.eletronicsystem.entities.DrawerTerminal;

@Repository
public interface DrawerTerminalRepository extends JpaRepository<DrawerTerminal, Long>
{
	public List<DrawerTerminal> findAll();
	public Optional<DrawerTerminal> findByNoteType(int noteType);
	
}