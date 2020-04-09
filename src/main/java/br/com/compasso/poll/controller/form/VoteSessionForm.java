package br.com.compasso.poll.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.compasso.poll.model.Poll;
import br.com.compasso.poll.model.VoteSession;
import br.com.compasso.poll.service.PollService;

public class VoteSessionForm {

	@NotNull
	@NotEmpty
	private String subject;
	@NotNull
	@NotEmpty
	private String description;
	private Long voteSessionTime;

	public Long getVoteSessionTime() {
		return voteSessionTime;
	}

	public String getSubject() {
		return subject;
	}

	public String getDescription() {
		return description;
	}

	public VoteSession convert(PollService pollService){
		Poll poll = toPoll();
		pollService.save(poll);
		
		VoteSession voteSession = new VoteSession(poll, voteSessionTime);
		return voteSession;
	}
	
	private Poll toPoll() {
		return new Poll(subject, description);
	}

}
