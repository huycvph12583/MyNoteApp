package com.huycv.mynoteapp.ui.screen.addNoteScreen

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.huycv.mynoteapp.data.model.Note
import com.huycv.mynoteapp.data.repository.NoteRepository
import com.huycv.mynoteapp.data.source.local.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddNoteViewModel(application: Application) : ViewModel() {
    val noteRepository: NoteRepository
    val allNotes: LiveData<List<Note>>

    init {
        val noteDao = AppDatabase.invoke(application)
        noteRepository = NoteRepository(noteDao)
        allNotes = noteRepository.getAllNote()
    }

    fun addNote(note: Note) =
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.insertNote(note)

        }

    fun updateNote(note: Note) =
        viewModelScope.launch {
            noteRepository.editNote(note)
        }

    fun deleteNote(note: Note) =
        viewModelScope.launch {
            noteRepository.deleteNote(note)
        }


}