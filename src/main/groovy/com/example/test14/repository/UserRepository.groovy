package com.example.test14.repository

import com.example.test14.model.User
import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository extends MongoRepository<User, String> {
    User findUserByUsername(String username)
}