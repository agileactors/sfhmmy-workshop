package com.sfhmmy.service;

import com.sfhmmy.domain.User;
import com.sfhmmy.dto.CreateUserRequestDto;
import com.sfhmmy.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(CreateUserRequestDto createUserRequestDto) {

        User newUser = new User();
        newUser.setEmail(createUserRequestDto.getEmail());
        newUser.setFirstName(createUserRequestDto.getFirstName());
        newUser.setLastName(createUserRequestDto.getLastName());
        newUser.setId(UUID.randomUUID());

        return userRepository.save(newUser);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
