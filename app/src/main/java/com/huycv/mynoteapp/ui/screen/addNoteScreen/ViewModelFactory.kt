package com.huycv.mynoteapp.ui.screen.addNoteScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class ViewModelFactory(
    private val creators: Map<Class<out ViewModel>, () -> ViewModel>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val creator = creators[modelClass] ?: creators.entries.firstOrNull {
            modelClass.isAssignableFrom(it.key)
        }?.value ?: throw IllegalArgumentException("Unknown ViewModel class: $modelClass")

        return try {
            @Suppress("UNCHECKED_CAST")
            creator() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}