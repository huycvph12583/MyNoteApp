package com.huycv.mynoteapp.data.repository

import androidx.lifecycle.LiveData
import com.huycv.mynoteapp.data.model.Note
import com.huycv.mynoteapp.data.source.local.AppDatabase

class NoteRepository(private val appDatabase: AppDatabase) {
    suspend fun insertNote(note: Note) = appDatabase.noteDao().insertNote(note)
    suspend fun editNote(note: Note) = appDatabase.noteDao().updateNote(note)
    suspend fun deleteNote(note: Note) = appDatabase.noteDao().deleteNote(note)
    fun getAllNote(): LiveData<List<Note>> = appDatabase.noteDao().getAllNote()

}