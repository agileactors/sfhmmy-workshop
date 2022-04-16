package com.sfhmmy.api;

import com.sfhmmy.domain.User;
import com.sfhmmy.dto.CreateUserRequestDto;
import com.sfhmmy.dto.CreateUserResponseDto;
import com.sfhmmy.dto.GetUserResponseDto;
import com.sfhmmy.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserApi {

    private UserService userService;

    public UserApi(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public CreateUserResponseDto create(@RequestBody CreateUserRequestDto createUserRequestDto) {
        User newUser = userService.save(createUserRequestDto);

        return new CreateUserResponseDto(
                newUser.getId(),
                newUser.getFirstName(),
                newUser.getLastName(),
                newUser.getEmail()
        );
    }

    @GetMapping
    public List<GetUserResponseDto> findAll() {
        return userService.findAll().
                parallelStream()
                .map(user -> new GetUserResponseDto(
                        user.getId(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getEmail()
                ))
                .toList();
    }

}
