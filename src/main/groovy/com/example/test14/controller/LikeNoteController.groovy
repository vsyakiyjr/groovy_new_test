package com.example.test14.controller

import com.example.test14.model.Note
import com.example.test14.service.LikeService
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/like/notes")
@SecurityRequirement(name = "JWT Authentication")
class LikeNoteController {

    @Autowired
    private LikeService likeService

    @PostMapping("/{noteId}")
    Note likeNote(@PathVariable(name = "noteId") String noteId, Authentication authentication) {
        likeService.likeNote(noteId, authentication)
    }

    @PostMapping("/remove/{noteId}")
    Note removeLikeFromNote(@PathVariable(name = "noteId") String noteId, Authentication authentication) {
        likeService.removeLikeFromNote(noteId, authentication)
    }

}
