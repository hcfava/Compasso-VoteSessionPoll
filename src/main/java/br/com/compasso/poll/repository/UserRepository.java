package br.com.compasso.poll.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.compasso.poll.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
