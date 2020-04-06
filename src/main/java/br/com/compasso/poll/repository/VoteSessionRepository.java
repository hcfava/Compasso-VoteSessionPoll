package br.com.compasso.poll.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.compasso.poll.model.VoteSession;

public interface VoteSessionRepository extends JpaRepository<VoteSession, Long> {

}
