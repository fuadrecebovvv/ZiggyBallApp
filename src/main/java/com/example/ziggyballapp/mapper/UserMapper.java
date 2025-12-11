package com.example.ziggyballapp.mapper;

import com.example.ziggyballapp.dao.entity.UserEntity;
import com.example.ziggyballapp.model.UpdatedUserDto;
import com.example.ziggyballapp.model.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserEntity toEntity(UserDto userDto);

    UserDto toDto(UserEntity userEntity);

    @Mapping(target = "id", ignore = true)
    void toEntity(UpdatedUserDto userDto, @MappingTarget UserEntity userEntity);
}
