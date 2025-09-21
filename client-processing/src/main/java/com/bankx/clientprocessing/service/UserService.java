package com.bankx.clientprocessing.service;

import com.bankx.clientprocessing.Dto.UserDto;
import com.bankx.clientprocessing.entity.UserEntity;
import com.bankx.clientprocessing.mapper.UserMapper;
import com.bankx.clientprocessing.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    public UserDto createUser(UserDto userDto) {
        log.info("Called: createUser - Controller layer");
        UserEntity userEntity = userMapper.toUserEntity(userDto);
        userRepository.save(userEntity);
        return userDto;
    }
}
