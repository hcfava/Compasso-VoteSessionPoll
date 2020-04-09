package br.com.compasso.poll;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.lang.annotation.Retention;
import java.util.Set;

import javax.validation.ValidationException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.compasso.poll.builder.VoteSessionCreator;
import br.com.compasso.poll.enumeration.OptionVote;
import br.com.compasso.poll.model.User;
import br.com.compasso.poll.model.Vote;
import br.com.compasso.poll.model.VoteSession;

@SpringBootTest
class PollApplicationTests {

	private User henrique;
	private User maria;
	private User joao;

	@Before
	public void CreateUser() {
		henrique = new User("henrique", "12345678900");
		joao = new User("joao", "12345678901");
		maria = new User("maria", "12345678902");
	}
	
	@Test
	void AceitaUmVoto() {
		VoteSession voteSession = new VoteSessionCreator().poll("subject", "description").vote(joao, OptionVote.SIM).create();
		
		Set<Vote> votes = voteSession.getVotes();
		assertEquals(1, votes.size());
	}
	
	@Rule
	ExpectedException exception = ExpectedException.none();
	@Test
	void NãoAceitaVotosDoMesmoUsuário() {
		VoteSession voteSession = new VoteSessionCreator().poll("subject", "description").vote(maria, OptionVote.SIM).create();
		voteSession.addVote(new Vote(maria, OptionVote.SIM));
		
		
		exception.expect(ValidationException.class);
		
	}

}
