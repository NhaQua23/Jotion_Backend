package com.nhaqua23.jotion.service;

import com.nhaqua23.jotion.dto.AuthDTO;
import com.nhaqua23.jotion.dto.IntrospectDTO;
import com.nhaqua23.jotion.dto.UserDTO;
import com.nimbusds.jose.JOSEException;

import java.text.ParseException;

public interface AuthService {

	AuthDTO login(UserDTO dto);

	UserDTO signup(UserDTO dto);

	IntrospectDTO introspect(IntrospectDTO dto) throws JOSEException, ParseException;
}
