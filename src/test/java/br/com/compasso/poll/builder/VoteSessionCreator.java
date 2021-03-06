package br.com.compasso.poll.builder;

import br.com.compasso.poll.enumeration.OptionVote;
import br.com.compasso.poll.model.Poll;
import br.com.compasso.poll.model.User;
import br.com.compasso.poll.model.Vote;
import br.com.compasso.poll.model.VoteSession;
import br.com.compasso.poll.repository.VoteRepository;

public class VoteSessionCreator {
	
	private VoteSession voteSession;
	
	public VoteSessionCreator poll(String subject, String description) {
		this.voteSession = new VoteSession(new Poll(subject, description), null); 
		return this;
	}
	
	public VoteSession create() {
		return voteSession;
	}
	
	public VoteSessionCreator vote(User user, OptionVote vote, VoteRepository voteRepository) {
		this.voteSession.addVote(new Vote(user, vote), voteRepository);
		return this;
	}
}
