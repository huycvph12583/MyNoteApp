package com.huycv.mynoteapp.ui.screen.HomeScreen

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.huycv.mynoteapp.data.model.Note
import com.huycv.mynoteapp.data.repository.NoteRepository
import com.huycv.mynoteapp.data.source.local.AppDatabase

class HomeViewModel(application: Application) : ViewModel() {
    val noteRepository: NoteRepository
    val allNotes: LiveData<List<Note>>

    init {
        val noteDao = AppDatabase.invoke(application)
        noteRepository = NoteRepository(noteDao)
        allNotes = noteRepository.getAllNote()
    }
}
