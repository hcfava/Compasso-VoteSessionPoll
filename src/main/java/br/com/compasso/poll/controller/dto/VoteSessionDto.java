package br.com.compasso.poll.controller.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import br.com.compasso.poll.enumeration.VoteSessionResult;
import br.com.compasso.poll.enumeration.VoteSessionStatus;
import br.com.compasso.poll.model.VoteSession;

public class VoteSessionDto {

	private String pollSubject;
	private String pollDescription;
	private Integer totalPollVotes;
	private Long yesPollVotes;
	private Long noPollVotes;
	private LocalDateTime creationDate;
	private LocalDateTime finishDate;
	private VoteSessionStatus status;
	private VoteSessionResult result;

	public VoteSessionDto(VoteSession voteSession) {
		this.pollSubject = voteSession.getPoll().getSubject();
		this.pollDescription = voteSession.getPoll().getDescription();
		this.totalPollVotes = voteSession.getVotes().size();
		this.yesPollVotes = voteSession.countYesVotes();
		this.noPollVotes = voteSession.countNoVotes();
		this.creationDate = voteSession.getStartTime();
		this.finishDate = voteSession.getEndTime();
		this.status = voteSession.getStatus();
		if (this.status == VoteSessionStatus.OPEN) {
			setResult(VoteSessionResult.VOTING);
		} else if (this.noPollVotes > this.yesPollVotes) {
			this.result = VoteSessionResult.DENIED;
		} else if (this.noPollVotes < this.yesPollVotes) {
			this.result = VoteSessionResult.APROVED;
		} else
			this.result = VoteSessionResult.DRAW;

	}

	public VoteSessionResult getResult() {
		return result;
	}

	public void setResult(VoteSessionResult result) {
		this.result = result;
	}

	public String getFinishDate() {
		return finishDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
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

	public String getCreationDate() {
		return creationDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
	}

	public VoteSessionStatus getStatus() {
		return status;
	}

	public static List<VoteSessionDto> convert(List<VoteSession> voteSessions) {
		return voteSessions.stream().map(VoteSessionDto::new).collect(Collectors.toList());
	}

}
