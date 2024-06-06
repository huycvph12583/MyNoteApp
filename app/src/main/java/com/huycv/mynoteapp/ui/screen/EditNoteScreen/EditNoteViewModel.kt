package com.huycv.mynoteapp.ui.screen.EditNoteScreen

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.huycv.mynoteapp.data.model.Note
import com.huycv.mynoteapp.data.repository.NoteRepository
import com.huycv.mynoteapp.data.source.local.AppDatabase
import kotlinx.coroutines.launch

class EditNoteViewModel(application: Application) : ViewModel() {
    val noteRepository: NoteRepository

    init {
        val noteDao = AppDatabase.invoke(application)
        noteRepository = NoteRepository(noteDao)
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