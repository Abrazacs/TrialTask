package ru.sergeysemenov.converters;

import org.springframework.stereotype.Component;
import ru.sergeysemenov.dtos.UserDto;
import ru.sergeysemenov.entities.User;

@Component
public class UserConverter {

    public User convert(UserDto userDto){
        return User.builder()
                .username(userDto.getUsername())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .build();
    }
}
