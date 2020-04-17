package br.com.compasso.poll;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.Set;

import javax.validation.ValidationException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import br.com.compasso.poll.builder.VoteSessionCreator;
import br.com.compasso.poll.controller.form.VoteForm;
import br.com.compasso.poll.enumeration.OptionVote;
import br.com.compasso.poll.model.User;
import br.com.compasso.poll.model.Vote;
import br.com.compasso.poll.model.VoteSession;
import br.com.compasso.poll.repository.UserRepository;
import br.com.compasso.poll.repository.VoteRepository;
import br.com.compasso.poll.service.VoteService;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class PollApplicationTests {

	private User henrique;
	private User maria;
	private User joao;
	
	@InjectMocks
	private VoteService voteService;
	@Mock
	private VoteRepository voteRepository;
	@Mock
	private UserRepository userRepository;
	
	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

//	@Before
//	public void CreateUser() {
//		henrique = new User("henrique", "12345678900");
//		joao = new User("joao", "12345678901");
//		maria = new User("maria", "12345678902");
//		henrique.setId(1L);
//		joao.setId(2L);
//		maria.setId(3L);
//	}


	@Test
	public void AceitaUmVoto() {
		joao = new User("joao", "12345678901");
		VoteSession voteSession = new VoteSessionCreator().poll("subject", "description")
				.vote(joao, OptionVote.SIM, voteRepository).create();

		Set<Vote> votes = voteSession.getVotes();
		assertEquals(1, votes.size());
	}

	@Test(expected = ValidationException.class)
	public void NãoAceitaVotosDoMesmoUsuárioNaMesmaSessao() {
		maria = new User("maria", "12345678902");
		VoteSession voteSession = new VoteSessionCreator().poll("subject", "description")
				.vote(maria, OptionVote.SIM, voteRepository).create();
		voteSession.addVote(new Vote(maria, OptionVote.SIM), voteRepository);

	}

	@Test(expected = ValidationException.class)
	public void NaoDeixaVotarComASessaoFechada() {
		henrique = new User("henrique", "12345678900");
		henrique.setId(1L);
		VoteSession voteSession = new VoteSessionCreator().poll("subject", "description").create();
		Vote vote = new Vote(henrique, OptionVote.SIM);
		VoteForm voteForm = new VoteForm();
		voteForm.setVote("SIM");
		voteForm.setIdUser(henrique.getId());
		when(userRepository.findById(anyLong())).thenReturn(Optional.of(henrique));
		when(voteRepository.save(any(Vote.class))).thenReturn(vote);
		
		voteSession = voteService.tryVote(voteSession, voteForm);
		voteSession.CloseVoteSession();
		voteSession = voteService.tryVote(voteSession, voteForm);
	}

}
