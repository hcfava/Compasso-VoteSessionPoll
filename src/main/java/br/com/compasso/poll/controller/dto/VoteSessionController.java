package br.com.compasso.poll.controller.dto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.compasso.poll.model.VoteSession;
import br.com.compasso.poll.repository.VoteSessionRepository;

@RestController
@RequestMapping("/votesession")
public class VoteSessionController {
	
	@Autowired
	private VoteSessionRepository voteSessionRepository;
	
	@GetMapping
	public List<VoteSessionDto> list(){
		System.out.println("entrou");
		List<VoteSession> voteSessions = voteSessionRepository.findAll();
		return VoteSessionDto.convert(voteSessions);
	}
}
