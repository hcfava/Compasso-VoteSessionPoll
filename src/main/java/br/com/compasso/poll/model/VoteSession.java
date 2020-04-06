package br.com.compasso.poll.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import br.com.compasso.poll.enumeration.OptionVote;

@Entity
public class VoteSession {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDateTime startTime = LocalDateTime.now();
	private boolean sessionOpen = true;
	@OneToMany
	private Set<Vote> votes = new HashSet<Vote>();
	@OneToOne
	private Poll poll;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isSessionOpen() {
		return sessionOpen;
	}

	public void closeSessionOpen() {
		this.sessionOpen = false;
	}

	public Set<Vote> getVotes() {
		return votes;
	}

	public void setVotes(Set<Vote> votes) {
		this.votes = votes;
	}

	public Poll getPoll() {
		return poll;
	}

	public void setPoll(Poll poll) {
		this.poll = poll;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public Long countYesVotes() {

		long count = votes.stream().filter(vote -> vote.getOptionVote().equals(OptionVote.SIM)).count();

		return count;
	}

	public long countNoVotes() {

		long count = votes.stream().filter(vote -> vote.getOptionVote().equals(OptionVote.NAO)).count();

		return count;
	}

}
