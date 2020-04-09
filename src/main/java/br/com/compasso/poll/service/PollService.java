package br.com.compasso.poll.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compasso.poll.model.Poll;
import br.com.compasso.poll.repository.PollRepository;

@Service
public class PollService {
	@Autowired
	private PollRepository pollRepository;
	
	public void save(Poll poll) {
		pollRepository.save(poll);
	}
}
