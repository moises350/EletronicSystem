package br.com.eletronicsystem.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "drawer_terminal")
public class DrawerTerminal extends AbstractEntity
{
	private static final long serialVersionUID = 1L;
	
	@NotNull
	@Column(name = "ID_TERMINAL")
	private String idTerminal;
	
	@NotNull
	@Column(name = "NOTE_TYPE")
	private Integer noteType;
	
	@NotNull
	@Column(name = "QTT_NOTE")
	private Integer qttNote;


	public String getIdTerminal() {
		return idTerminal;
	}

	public void setIdTerminal(String idTerminal) {
		this.idTerminal = idTerminal;
	}

	public Integer getNoteType() {
		return noteType;
	}

	public void setNoteType(Integer noteType) {
		this.noteType = noteType;
	}
	
	public Integer getQttNote() {
		return qttNote;
	}

	public void setQttNote(Integer qttNote) {
		this.qttNote = qttNote;
	}

}
