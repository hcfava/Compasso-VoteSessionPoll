package br.com.compasso.poll.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.compasso.poll.model.Poll;

public interface PollRepository extends JpaRepository<Poll, Long> {

}
