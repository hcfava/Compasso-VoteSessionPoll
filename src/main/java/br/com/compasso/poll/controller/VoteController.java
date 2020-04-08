package br.com.compasso.poll.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.compasso.poll.controller.form.VoteForm;
import br.com.compasso.poll.model.VoteSession;
import br.com.compasso.poll.service.VoteService;
import br.com.compasso.poll.service.VoteSessionService;

@RestController
@RequestMapping("/vote")
public class VoteController {
	
	@Autowired
	private VoteSessionService voteSessionService;
	@Autowired
	private VoteService voteService;
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> vote(@RequestBody @Valid VoteForm voteForm){
		VoteSession voteSession = voteSessionService.findById(voteForm.getIdSession());
		if(voteSession != null) {
			voteSession = voteService.tryVote(voteSession, voteForm);
			voteSessionService.save(voteSession);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}
}
