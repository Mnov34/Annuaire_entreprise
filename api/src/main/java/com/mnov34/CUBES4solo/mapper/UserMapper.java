package com.mnov34.CUBES4solo.mapper;

import com.mnov34.CUBES4solo.dto.UserDto;
import com.mnov34.CUBES4solo.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author Maël NOUVEL <br>
 * 02/2025
 **/
@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto toDto(User user);

    User toEntity(UserDto userDto);
}