package br.com.compasso.poll.service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compasso.poll.controller.form.VoteForm;
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
		Optional<User> user = userRepository.findById(voteForm.getIdUser());
		if(user.isPresent()) {
			Vote vote = voteForm.convert(user.get());
			voteSession.addVote(vote, voteRepository);
		}
		return voteSession;
	}
}
