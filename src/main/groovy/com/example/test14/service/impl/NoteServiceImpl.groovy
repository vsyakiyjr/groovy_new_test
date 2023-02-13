package com.example.test14.service.impl

import com.example.test14.dto.AuthorDto
import com.example.test14.model.Note
import com.example.test14.repository.NoteRepository
import com.example.test14.service.NoteService
import com.example.test14.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.domain.Sort
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service

import java.time.LocalDateTime

@Service
class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteRepository noteRepository
    @Autowired
    private UserService userService
//    @Value("${app.base.url}")
    private String baseUrl = "http://localhost:8080"

    @Override
    List<Note> findAllNotes() {
        noteRepository.findAll()
    }

    @Override
    Note findNoteById(String id) {
        noteRepository.findById(id).orElseThrow()
    }

    @Override
    List<Note> findAllNotesSorted() {
//        Sort sort = new Sort(Sort.Direction.ASC, "date")
        List<Note> notesByDate = noteRepository.findByOrderByTimestampDesc()
        notesByDate
    }

    @Override
    Note saveNote(Note note) {
        return noteRepository.save(note)
    }

    @Override
    Note saveNote(Note note, Authentication authentication) {
        String username
        String profileLink
        if (authentication != null) {
            username = authentication.getName()
            profileLink = baseUrl + "/user/" + username
        } else {
            username = "Anonym"
            profileLink = "No profile"
        }
        AuthorDto author = new AuthorDto(username, profileLink)
        LocalDateTime now = LocalDateTime.now()
        note.setAuthor(author)
        note.setTimestamp(now)
        return noteRepository.save(note)
    }

    @Override
    Note updateNote(Note note) {
        return noteRepository.save(note)
    }

    @Override
    void deleteNoteById(String id) {
        noteRepository.deleteById(id)
    }
}
