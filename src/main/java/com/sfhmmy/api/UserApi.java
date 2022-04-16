package com.sfhmmy.api;

import com.sfhmmy.dto.CreateUserRequestDto;
import com.sfhmmy.dto.CreateUserResponseDto;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserApi {

    @GetMapping
    public String hello() {
        return "Hello World!";
    }

    @PostMapping
    public CreateUserResponseDto create(@RequestBody CreateUserRequestDto createUserRequestDto ){

        return new CreateUserResponseDto(UUID.randomUUID(), "Alex", "Panousis", "alexis.panousis@agileactors.com");
    }

}
