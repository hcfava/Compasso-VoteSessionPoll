package br.com.compasso.poll.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.compasso.poll.model.Poll;

public class PollForm {

	@NotNull
	@NotEmpty
	private String subject;
	@NotNull
	@NotEmpty
	private String description;

	public String getSubject() {
		return subject;
	}

	public String getDescription() {
		return description;
	}
	
	public Poll toPoll() {
		return new Poll(subject, description);
	}

}
