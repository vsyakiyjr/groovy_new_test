package com.example.test14.service.impl

import com.example.test14.dto.UserDto
import com.example.test14.model.User
import com.example.test14.repository.UserRepository
import com.example.test14.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository

    @Override
    void saveUser(UserDto userDto) {
        User userToSave = new User()
        userToSave.setUsername(userDto.getUsername())
        userToSave.setPassword(userDto.getPassword())
        userRepository.save(userToSave)
    }

    @Override
    User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username)
    }

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userFromDb = userRepository.findUserByUsername(username)
        return new org.springframework.security.core.userdetails.User(userFromDb.getUsername(),userFromDb.getPassword(),new ArrayList<>())
    }
}
