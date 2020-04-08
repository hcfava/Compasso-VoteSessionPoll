package br.com.compasso.poll.controller;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.compasso.poll.controller.dto.VoteSessionDto;
import br.com.compasso.poll.controller.form.VoteSessionForm;
import br.com.compasso.poll.controller.service.PollService;
import br.com.compasso.poll.model.VoteSession;
import br.com.compasso.poll.service.VoteSessionService;

@RestController
@RequestMapping("/votesession")
public class VoteSessionController {
	
	@Autowired
	private VoteSessionService voteSessionService;
	@Autowired
	private PollService pollService;
	
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
	
	@PostMapping
	@Transactional
	public ResponseEntity<VoteSessionDto> register(@RequestBody @Valid VoteSessionForm form, UriComponentsBuilder uriBuilder) {
		VoteSession voteSession = form.convert(pollService);
		voteSessionService.save(voteSession);
		
		URI uri = uriBuilder.path("/votesession/{id}").buildAndExpand(voteSession.getId()).toUri();
		return ResponseEntity.created(uri).body(new VoteSessionDto(voteSession));
	}
}
