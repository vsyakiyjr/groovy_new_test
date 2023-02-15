package com.example.test14.controller

import com.example.test14.dto.JwtRequest
import com.example.test14.dto.JwtResponse
import com.example.test14.dto.UserDto
import com.example.test14.model.User
import com.example.test14.service.UserService
import com.example.test14.utils.security.JwtUtil
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid
import groovy.transform.Immutable

@Immutable
class Test {
    String name
    String occupation
}

@RestController
class UserController {

    def test = [
            new Test('John Doe', 'gardener'),
            new Test('Roger Roe', 'driver'),
            new Test('Kim Smith', 'teacher')
    ]

    @Autowired
    private JwtUtil jwtUtil
    @Autowired
    private AuthenticationManager authenticationManager
    @Autowired
    private PasswordEncoder passwordEncoder
    @Autowired
    private UserService userService

    @GetMapping("/test")
    String test() {
        return 'test'
    }

    @GetMapping
    List<User> welcome() {
        List<User> users = userService.findAll()
        return users
    }

    @GetMapping("/users")
    List<User> users() {
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

    @PostMapping("/addorUpdate")
    public ResponseEntity<User> addorUpdate(@RequestBody @Valid User, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return 'error';
        }
        ResponseEntity.ok().body(test)
    }

    @PostMapping("/registration")
    ResponseEntity registration(@Valid @RequestBody UserDto userDto, BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Name should not be empty")
        }
        if (userService.findUserByUsername(userDto.getUsername()) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This user already exists")
        }
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()))
        return ResponseEntity.status(200).body(userService.saveUser(userDto))
    }
}
