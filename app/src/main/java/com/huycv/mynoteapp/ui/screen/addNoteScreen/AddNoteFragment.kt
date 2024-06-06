package com.huycv.mynoteapp.ui.screen.addNoteScreen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.huycv.mynoteapp.ui.activity.MainActivity
import com.huycv.mynoteapp.R
import com.huycv.mynoteapp.data.model.Note
import com.huycv.mynoteapp.databinding.FragmentAddNoteBinding

class AddNoteFragment : Fragment() {
    final val TAG = AddNoteViewModel::class.java.getName()
    private lateinit var binding: FragmentAddNoteBinding
    private lateinit var viewModel: AddNoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddNoteBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUI()
    }

    private fun listener() {
        viewModel.allNotes.observe(requireActivity()) {
            Log.d(TAG, "listener: " + it.size + AddNoteViewModel::class.java.getName())

        }
        binding.btnAddNoteSaveData.setOnClickListener {
            insertNote()
        }
    }

    private fun insertNote() {
        val noteTitle = binding.addNoteTitle.text.toString()
        val noteDesc = binding.addNoteDesc.text.toString()

        if (noteTitle.isEmpty()) {
            binding.addNoteTitle.error = "Title is not blank !"
            Toast.makeText(requireContext(), "Title is not blank !", Toast.LENGTH_SHORT).show()
        } else if (noteDesc.isEmpty()) {
            binding.addNoteDesc.error = "Description is not blank !"
            Toast.makeText(requireContext(), "Description is not blank !", Toast.LENGTH_SHORT)
                .show()
        } else {
            Log.d(TAG, "listener11111: " + noteDesc + noteTitle + viewModel.allNotes)
            viewModel.addNote(Note(0, noteTitle, noteDesc))
            Toast.makeText(requireContext(), "Save Note Success !", Toast.LENGTH_SHORT).show()
            this@AddNoteFragment.findNavController().popBackStack(R.id.homeFragment, false)
        }
    }

    private fun setUpUI() {
        viewModel = ViewModelProvider(
            this, (activity as MainActivity).viewModelFactory
        )[AddNoteViewModel::class.java]
        listener()
    }
}