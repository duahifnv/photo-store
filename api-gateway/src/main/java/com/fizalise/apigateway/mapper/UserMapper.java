package com.fizalise.apigateway.mapper;

import com.fizalise.apigateway.dto.RegistrationRequest;
import com.fizalise.apigateway.entity.Role;
import com.fizalise.apigateway.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring")
public abstract class UserMapper {
    @Autowired
    protected PasswordEncoder passwordEncoder;
    @Mapping(target = "password", qualifiedByName = "getEncodedPassword")
    public abstract User toUser(RegistrationRequest registrationRequest, Role role);
    @Named("getEncodedPassword")
    protected String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}
