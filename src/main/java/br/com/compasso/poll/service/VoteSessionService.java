package br.com.compasso.poll.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compasso.poll.model.VoteSession;
import br.com.compasso.poll.repository.VoteSessionRepository;

@Service
public class VoteSessionService {
	
	@Autowired
	private VoteSessionRepository voteSessionRepository;
	
	public List<VoteSession> getAll(){
		return voteSessionRepository.findAll();
	}
	
	public VoteSession findById(Long id) {
		Optional<VoteSession> voteSession = voteSessionRepository.findById(id);
		if(voteSession.isPresent())
			return voteSession.get();
		return null;
	}
}
