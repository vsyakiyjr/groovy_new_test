package com.example.test14.service

import com.example.test14.model.Note
import org.springframework.security.core.Authentication

interface LikeService {

    Note likeNote(String noteId, Authentication authentication);

    Note removeLikeFromNote(String noteId, Authentication authentication)
}