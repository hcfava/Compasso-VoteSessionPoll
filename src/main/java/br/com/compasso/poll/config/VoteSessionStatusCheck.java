package br.com.compasso.poll.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import br.com.compasso.poll.service.VoteSessionService;

@Configuration
@EnableScheduling
public class VoteSessionStatusCheck {

	@Autowired
	private VoteSessionService voteSessionService;

	@Scheduled(fixedRate = 10000)
	private void schedule() {
		voteSessionService.closeEndedVoteSession();
	}

}
