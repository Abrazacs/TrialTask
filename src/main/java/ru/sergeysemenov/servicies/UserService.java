package ru.sergeysemenov.servicies;

import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.sergeysemenov.converters.UserConverter;
import ru.sergeysemenov.dtos.UserDto;
import ru.sergeysemenov.entities.User;
import ru.sergeysemenov.exceptions.BadRequestException;
import ru.sergeysemenov.repositories.UserRepository;

import java.util.Optional;
import java.util.UUID;


@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserConverter userConverter;

    public void save(UserDto userDto){
        UUID trace = UUID.randomUUID();
        log.info("Start create new user userDto={}, trace={}", userDto,  trace);
        if (userRepository.findByUsername(userDto.getUsername()).isPresent()) {
            log.error("Error create user, username={} is occupied, trace={}", userDto.getUsername(), trace);
            throw new BadRequestException("This username is occupied. Try to use another one");
        }
        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            log.error("Error create user, email={} is occupied, trace={}", userDto.getEmail(), trace);
            throw new BadRequestException("This email is occupied. Try to use another one");
        }
        userRepository.save(userConverter.convert(userDto));
        log.info("User successfully saved, traceId={}", trace);
    }

    public Optional<User> findByUsername(String username){
        return userRepository.findByUsername(username);
    }






}
