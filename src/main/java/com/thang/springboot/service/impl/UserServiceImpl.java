package com.thang.springboot.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.thang.springboot.dto.UserDTO;
import com.thang.springboot.entity.User;
import com.thang.springboot.repository.UserRepository;
import com.thang.springboot.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	  @Autowired
	  private UserRepository userRepository;

	@Override
	public void addUser(User user) {
		userRepository.save(user);
		
	}

	@Override
	public List<User> getUsers() {
		return userRepository.findAll();
	}

	@Override
	public User getUser(Integer id) {
		User user = userRepository
                .findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Invalid user id "+ id));

        return user;
	}

	@Override
	public void updateUser(Integer id, User user) {
		userRepository
        .findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid user Id:" + id));
		user.setId(id);
		userRepository.save(user);
		
	}

	@Override
	public void deleteUser(Integer id) {
		User user = userRepository
		.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid user Id:" + id));
		
        userRepository.delete(user);
		
	}

	@Override
	public void updateName(Integer id, UserDTO userDTO) {
		User user = userRepository
		.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid user Id:" + id));
        user.setName(userDTO.getName());
        userRepository.save(user);
		
	}

	@Override
	public Boolean checkLogin(String name, Integer password) {
		Optional<User> optionalUser = userRepository.findByName(name);
		if(optionalUser.isPresent()&& optionalUser.get().getPassword().equals(password)) {
			return true;
		}
		return null;
	}

}
