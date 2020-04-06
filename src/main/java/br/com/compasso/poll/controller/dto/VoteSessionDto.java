package br.com.compasso.poll.controller.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.com.compasso.poll.model.VoteSession;

public class VoteSessionDto {

	private String pollSubject;
	private String pollDescription;
	private Integer totalPollVotes;
	private Long yesPollVotes;
	private Long noPollVotes;
	private LocalDateTime creationDate;

	public VoteSessionDto(VoteSession voteSession) {
		this.pollSubject = voteSession.getPoll().getSubject();
		this.pollDescription = voteSession.getPoll().getDescription();
		this.totalPollVotes = voteSession.getVotes().size();
		this.yesPollVotes = voteSession.countYesVotes();
		this.noPollVotes = voteSession.countNoVotes();
		this.creationDate = voteSession.getStartTime();
	}

	public String getPollSubject() {
		return pollSubject;
	}

	public String getPollDescription() {
		return pollDescription;
	}

	public Integer getTotalPollVotes() {
		return totalPollVotes;
	}

	public Long getYesPollVotes() {
		return yesPollVotes;
	}

	public Long getNoPollVotes() {
		return noPollVotes;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public static List<VoteSessionDto> convert(List<VoteSession> voteSessions) {
		return voteSessions.stream().map(VoteSessionDto::new).collect(Collectors.toList());
	}

}
