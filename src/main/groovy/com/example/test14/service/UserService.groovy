package com.example.test14.service

import com.example.test14.dto.UserDto
import com.example.test14.model.Note
import com.example.test14.model.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException

interface UserService {

    List<User> findAllNotes()

    saveUser(UserDto userDto)

    User findUserByUsername(String username)

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException

}