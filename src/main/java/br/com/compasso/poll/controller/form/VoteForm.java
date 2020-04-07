package br.com.compasso.poll.controller.form;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.compasso.poll.enumeration.OptionVote;
import br.com.compasso.poll.model.User;
import br.com.compasso.poll.model.Vote;

public class VoteForm {
	@NotNull @DecimalMin(value = "1")
	private Long idUser;
	@NotNull @DecimalMin(value = "1")
	private Long idSession;
	@NotBlank
	private String vote;
	
	public Long getIdUser() {
		return idUser;
	}
	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
	public Long getIdSession() {
		return idSession;
	}
	public void setIdSession(Long idSession) {
		this.idSession = idSession;
	}
	public String getVote() {
		return vote.toUpperCase();
	}
	public void setVote(String vote) {
		this.vote = vote;
	}
	
	public Vote convert(User user) {
		return new Vote(user, OptionVote.valueOf(getVote()));
	}
}
