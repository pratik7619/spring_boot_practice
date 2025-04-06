package com.example.spring_boot_practice.controller

import com.example.spring_boot_practice.database.model.Note
import com.example.spring_boot_practice.database.repository.NoteRepository
import org.bson.types.ObjectId
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.Instant

//POST http://localhost:8085/notes
//GET http://localhost:8085/notes?ownerId=123

@RestController
@RequestMapping("/notes")
class NoteController(
    private val noteRepository: NoteRepository
) {

    data class NoteRequest(
        val id: String?,
        val title: String,
        val content: String,
        val color: Long,
        val ownerId: String
    )

    data class NoteResponse(
        val id: String,
        val title: String,
        val content: String,
        val color: Long,
        val createdAt: Instant
    )

    @PostMapping
    fun save(body: NoteRequest): NoteResponse {
        val note = noteRepository.save(
            Note(
                id = body.id?.let { ObjectId(it) } ?: ObjectId.get(),
                title = body.title,
                color = body.color,
                content = body.content,
                createdAt = Instant.now(),
                ownerId = ObjectId(body.ownerId)
            )
        )
        return note.toResponse()
    }

    @GetMapping
    fun findByOwnerId(
        @RequestParam(required = true) ownerId: String
    ) : List<NoteResponse> {
        return noteRepository.findNotesByOwnerId(ObjectId(ownerId)).map {
           it.toResponse()
        }
    }

    private fun Note.toResponse() : NoteController.NoteResponse {
        return NoteResponse(
            id = id.toHexString(),
            title = title,
            color = color,
            content = content,
            createdAt = createdAt,
        )
    }
}