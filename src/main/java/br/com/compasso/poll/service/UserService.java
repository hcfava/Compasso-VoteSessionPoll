package br.com.compasso.poll.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compasso.poll.model.User;
import br.com.compasso.poll.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User findById(Long id) {
		Optional<User> user = userRepository.findById(id);
		if(user.isPresent())
			return user.get();
		return null;
	}
}
