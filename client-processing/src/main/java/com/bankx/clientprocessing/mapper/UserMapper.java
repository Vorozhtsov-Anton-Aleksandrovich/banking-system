package com.bankx.clientprocessing.mapper;

import com.bankx.clientprocessing.Dto.UserDto;
import com.bankx.clientprocessing.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDto toUserDto(UserEntity userEntity) {
        return new UserDto(
                userEntity.getId(),
                userEntity.getLogin(),
                userEntity.getEmail(),
                userEntity.getPassword()
        );
    }

    public UserEntity toUserEntity(UserDto userDto) {
        return UserEntity.builder()
                .id(userDto.id())
                .login(userDto.login())
                .email(userDto.email())
                .password(userDto.password())
                .build();
    }

}
