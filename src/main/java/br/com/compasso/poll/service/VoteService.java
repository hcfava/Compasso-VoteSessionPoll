package br.com.compasso.poll.service;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compasso.poll.controller.form.VoteForm;
import br.com.compasso.poll.enumeration.VoteSessionStatus;
import br.com.compasso.poll.model.User;
import br.com.compasso.poll.model.Vote;
import br.com.compasso.poll.model.VoteSession;
import br.com.compasso.poll.repository.UserRepository;
import br.com.compasso.poll.repository.VoteRepository;

@Service
public class VoteService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired	
	private VoteRepository voteRepository;
	
	public VoteSession tryVote(VoteSession voteSession, @Valid VoteForm voteForm) {
		if(sessionIsClosed(voteSession))
			throw new ValidationException("Esta sessão já foi finalizada");
		Optional<User> user = userRepository.findById(voteForm.getIdUser());
		if(user.isPresent()) {
			Vote vote = voteForm.convert(user.get());
			voteSession.addVote(vote, voteRepository);
		}
		return voteSession;
	}
	
	private boolean sessionIsClosed(VoteSession voteSession) {
		return (voteSession.getEndTime().isBefore(LocalDateTime.now()) || voteSession.getStatus() == VoteSessionStatus.CLOSED);
	}
}
