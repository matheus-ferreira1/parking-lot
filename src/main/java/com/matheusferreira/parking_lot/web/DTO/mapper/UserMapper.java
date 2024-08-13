package com.matheusferreira.parking_lot.web.DTO.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

import com.matheusferreira.parking_lot.entity.User;
import com.matheusferreira.parking_lot.web.DTO.UserCreateDTO;
import com.matheusferreira.parking_lot.web.DTO.UserResponseDTO;

public class UserMapper {
    public static User toUser(UserCreateDTO userCreateDTO) {
        return new ModelMapper().map(userCreateDTO, User.class);
    }

    public static List<UserResponseDTO> toListDTO(List<User> users) {
        return users.stream().map(user -> toDTO(user)).collect(Collectors.toList());
    }

    public static UserResponseDTO toDTO(User user) {
        String role = user.getRole().name().substring("ROLE_".length());
        ModelMapper mapperMain = new ModelMapper();
        TypeMap<User, UserResponseDTO> propertyMapper = mapperMain.createTypeMap(User.class, UserResponseDTO.class);
        propertyMapper.addMappings(
                mapper -> mapper.map(src -> role, UserResponseDTO::setRole));
        return mapperMain.map(user, UserResponseDTO.class);
    }
}
