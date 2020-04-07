package br.com.compasso.poll.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.compasso.poll.model.Vote;

public interface VoteRepository extends JpaRepository<Vote, Long>{

}
