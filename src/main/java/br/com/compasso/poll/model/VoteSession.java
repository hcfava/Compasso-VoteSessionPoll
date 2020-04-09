package br.com.compasso.poll.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.ValidationException;

import br.com.compasso.poll.enumeration.OptionVote;
import br.com.compasso.poll.enumeration.VoteSessionStatus;
import br.com.compasso.poll.repository.VoteRepository;

@Entity
public class VoteSession {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDateTime startTime = LocalDateTime.now();
	private LocalDateTime endTime;
	private final Long DEFAULT_TIME = 1l;
	@Enumerated
	private VoteSessionStatus status = VoteSessionStatus.OPEN;
	@OneToMany
	private Set<Vote> votes = new HashSet<Vote>();
	@OneToOne
	private Poll poll;

	public VoteSession() {
		
	}
	
	public VoteSession(Poll poll2, Long voteSessionTime) {
		this.poll = poll2;
		this.endTime = startTime.plusMinutes(Optional.ofNullable(voteSessionTime).orElse(DEFAULT_TIME));
	}
	
	public LocalDateTime getEndTime() {
		return endTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public VoteSessionStatus getStatus() {
		return status;
	}

	public void CloseVoteSession() {
		this.status = VoteSessionStatus.CLOSED;
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

	public boolean addVote(Vote vote, VoteRepository voteRepository) {
		if (votes.add(vote)) {
			voteRepository.save(vote);
			return true;
		} else
			throw new ValidationException("Esse associado já votou nesta pauta");

	}

}
