package br.com.bankapi.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WithdrawDto 
{
	@NotNull(message = "Cannot be null")
	public int valueToWithdraw;
	
	public int qttNotes10;
	
	public int qttNotes20;
	
	public int qttNotes50;

	public int qttNotes100;

}
