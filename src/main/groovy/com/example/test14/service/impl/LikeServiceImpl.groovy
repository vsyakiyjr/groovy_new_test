package com.example.test14.service.impl

import com.example.test14.dto.AuthorDto
import com.example.test14.model.Note
import com.example.test14.service.LikeService
import com.example.test14.service.NoteService
import com.example.test14.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service

@Service
class LikeServiceImpl implements LikeService {

    @Autowired
    private NoteService noteService
    @Autowired
    private UserService userService
//    @Value("${app.base.url}")
    private String baseUrl = "http://localhost:8080"

    @Override
    Note likeNote(String noteId, Authentication authentication) {
        def likedNote = noteService.findNoteById(noteId)
        def username = authentication.getName()
        def profileLink = baseUrl + "/user/" + username
        AuthorDto author = new AuthorDto(username, profileLink)
        def liked = likedNote.getLiked()
        liked.add(author)
        noteService.saveNote(likedNote)
        return likedNote
    }

    @Override
    Note removeLikeFromNote(String noteId, Authentication authentication) {
        def unlikedNote = noteService.findNoteById(noteId)
        def username = authentication.getName()
        def liked = unlikedNote.getLiked()
        liked.removeIf { (it.username == username) }
        noteService.saveNote(unlikedNote)
        return unlikedNote
    }
}
