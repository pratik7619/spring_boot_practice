package com.example.spring_boot_practice.database.repository

import com.example.spring_boot_practice.database.model.Note
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface NoteRepository : MongoRepository<Note, ObjectId> {

    fun findNotesByOwnerId(ownerId: ObjectId) : List<Note>
}