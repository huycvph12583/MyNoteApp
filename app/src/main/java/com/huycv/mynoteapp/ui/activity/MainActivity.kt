package com.huycv.mynoteapp.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.huycv.mynoteapp.databinding.ActivityMainBinding
import com.huycv.mynoteapp.ui.screen.EditNoteScreen.EditNoteViewModel
import com.huycv.mynoteapp.ui.screen.HomeScreen.HomeViewModel
import com.huycv.mynoteapp.ui.screen.addNoteScreen.AddNoteViewModel
import com.huycv.mynoteapp.ui.screen.addNoteScreen.ViewModelFactory

class MainActivity : AppCompatActivity() {
    var binding: ActivityMainBinding? = null
    lateinit var viewModelFactory: ViewModelFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(getLayoutInflater())
        setContentView(binding?.root)
        viewModelFactory = ViewModelFactory(
            mapOf(
                AddNoteViewModel::class.java to { AddNoteViewModel(application) },
                HomeViewModel::class.java to { HomeViewModel(application) },
                EditNoteViewModel::class.java to { EditNoteViewModel(application) },
            )
        )
    }
}