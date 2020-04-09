package br.com.compasso.poll.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.compasso.poll.enumeration.VoteSessionStatus;
import br.com.compasso.poll.model.VoteSession;

public interface VoteSessionRepository extends JpaRepository<VoteSession, Long> {

	List<VoteSession> findByStatus(VoteSessionStatus open);

}
