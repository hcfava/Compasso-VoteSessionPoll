package br.com.compasso.poll;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.Set;

import javax.validation.ValidationException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.compasso.poll.builder.VoteSessionCreator;
import br.com.compasso.poll.enumeration.OptionVote;
import br.com.compasso.poll.model.User;
import br.com.compasso.poll.model.Vote;
import br.com.compasso.poll.model.VoteSession;
import br.com.compasso.poll.repository.VoteRepository;

@SpringBootTest
public class PollApplicationTests {

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
	public void AceitaUmVoto() {
		VoteRepository voteRepository = mock(VoteRepository.class);
		VoteSession voteSession = new VoteSessionCreator().poll("subject", "description").vote(joao, OptionVote.SIM, voteRepository).create();
		
		Set<Vote> votes = voteSession.getVotes();
		assertEquals(1, votes.size());
	}
	
	@Test(expected = ValidationException.class)
	public void NãoAceitaVotosDoMesmoUsuárioNaMesmaSessao() {
		VoteRepository voteRepository = mock(VoteRepository.class);
		VoteSession voteSession = new VoteSessionCreator().poll("subject", "description").vote(maria, OptionVote.SIM, voteRepository).create();
		voteSession.addVote(new Vote(maria, OptionVote.SIM), voteRepository);
		
	}

}
