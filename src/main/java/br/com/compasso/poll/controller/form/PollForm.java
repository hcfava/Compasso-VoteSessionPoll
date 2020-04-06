package br.com.compasso.poll.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class PollForm {

	@NotNull
	@NotEmpty
	@Length(min = 5)
	private String subject;
	@NotNull
	@NotEmpty
	@Length(min = 10)
	private String description;

	public String getSubject() {
		return subject;
	}

	public String getDescription() {
		return description;
	}

}
