package ru.sergeysemenov.controllers;


import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.sergeysemenov.dtos.UserDto;
import ru.sergeysemenov.servicies.UserService;


@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void addNewUser(@RequestBody @Parameter(description = "User data", required = true) UserDto dto){
        userService.save(dto);
    }
}
