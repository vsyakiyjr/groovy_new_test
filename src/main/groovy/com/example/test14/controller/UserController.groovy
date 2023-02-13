package com.example.test14.controller

import com.example.test14.dto.JwtRequest
import com.example.test14.dto.JwtResponse
import com.example.test14.dto.UserDto
import com.example.test14.model.User
import com.example.test14.service.UserService
import com.example.test14.utils.security.JwtUtil
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController {

    @Autowired
    private JwtUtil jwtUtil
    @Autowired
    private AuthenticationManager authenticationManager
    @Autowired
    private PasswordEncoder passwordEncoder
    @Autowired
    private UserService userService

    @GetMapping
    List<User> welcome() {
        List<User> users = userService.findAll()
        return users
    }

    @GetMapping("/whoami")
    @SecurityRequirement(name = "JWT Authentication")
    String whoAmI(Authentication authentication) {
        return authentication.getName()
    }

    @PostMapping("/auth")
    JwtResponse authenticate(@RequestBody JwtRequest jwtRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getUsername(),
                            jwtRequest.getPassword()
                    )
            )
        } catch (Exception e) {
            throw new Exception("INVALID_CREDENTIALS", e)
        }

        final UserDetails userDetails = userService.loadUserByUsername(jwtRequest.getUsername())
        final String token = jwtUtil.generateToken(userDetails)

        return new JwtResponse(token)
    }

    @PostMapping("/registration")
    ResponseEntity registration(@RequestBody UserDto userDto) throws Exception {
        if (userService.findUserByUsername(userDto.getUsername()) != null) {
            throw new Exception("This user already exists")
        }
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()))
        userService.saveUser(userDto)
    }
}
