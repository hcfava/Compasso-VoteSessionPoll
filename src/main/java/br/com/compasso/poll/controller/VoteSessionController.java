package br.com.compasso.poll.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.compasso.poll.controller.dto.VoteSessionDto;
import br.com.compasso.poll.model.VoteSession;
import br.com.compasso.poll.service.VoteSessionService;

@RestController
@RequestMapping("/votesession")
public class VoteSessionController {
	
	@Autowired
	private VoteSessionService voteSessionService;
	
	@GetMapping
	public List<VoteSessionDto> list(){
		List<VoteSession> voteSessions = voteSessionService.getAll();
		return VoteSessionDto.convert(voteSessions);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<VoteSessionDto> voteSessionDetail(@PathVariable Long id){
		VoteSession voteSession = voteSessionService.findById(id);
		if(voteSession != null)
			return ResponseEntity.ok(new VoteSessionDto(voteSession));
		return ResponseEntity.notFound().build();
	}
}
