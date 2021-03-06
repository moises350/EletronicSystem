package br.com.eletronicsystem.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;


@Entity
public class Terminal extends AbstractEntity
{
	private static final long serialVersionUID = 1L;
	
	public static final int TERMINAL_STATUS_ACTIVE = 1;
	public static final int TERMINAL_STATUS_INACTIVE = 2;
	
	@NotNull
	@Column(name = "DS_TERMINAL")
	private String dsTerminal;
	
	@NotNull
	@Column(name = "ID_STATUS")
	private int idStatus;

	public String getDsTerminal() {
		return dsTerminal;
	}

	public void setDsTerminal(String dsTerminal) {
		this.dsTerminal = dsTerminal;
	}

	public int getIdStatus() {
		return idStatus;
	}

	public void setIdStatus(int idStatus) {
		this.idStatus = idStatus;
	}

}
