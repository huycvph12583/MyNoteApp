package com.huycv.mynoteapp.ui.screen.EditNoteScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.huycv.mynoteapp.R
import com.huycv.mynoteapp.data.model.Note
import com.huycv.mynoteapp.databinding.FragmentEditNoteBinding
import com.huycv.mynoteapp.ui.activity.MainActivity

class EditNoteFragment : Fragment() {
    private lateinit var binding: FragmentEditNoteBinding
    private lateinit var viewModel: EditNoteViewModel
    private lateinit var noteModel: Note
    private val args: EditNoteFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditNoteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUI()
    }

    private fun listener() {
        binding.btnEditNoteSaveData.setOnClickListener {
            insertNote()
        }
        binding.btnEditNoteDeleteData.setOnClickListener {
            confirmDelete(noteModel)
        }
    }

    private fun insertNote() {
        val noteTitle = binding.edtEditNoteTitle.text.toString()
        val noteDesc = binding.edtEditNoteDesc.text.toString()
        if (noteTitle.isEmpty()) {
            binding.edtEditNoteTitle.error = "Title is not blank !"
            Toast.makeText(requireContext(), "Title is not blank !", Toast.LENGTH_SHORT).show()
        } else if (noteDesc.isEmpty()) {
            binding.edtEditNoteDesc.error = "Description is not blank !"
            Toast.makeText(requireContext(), "Description is not blank !", Toast.LENGTH_SHORT)
                .show()
        } else {
            viewModel.updateNote(Note(noteModel.id, noteTitle, noteDesc))
            Toast.makeText(requireContext(), "Save Note Success !", Toast.LENGTH_SHORT).show()
            this@EditNoteFragment.findNavController().popBackStack(R.id.homeFragment, false)
        }
    }

    private fun setUpUI() {
        viewModel = ViewModelProvider(
            this, (activity as MainActivity).viewModelFactory
        )[EditNoteViewModel::class.java]
        noteModel = args.note!!
        binding.edtEditNoteTitle.setText(noteModel.noteTitle)
        binding.edtEditNoteDesc.setText(noteModel.noteDesc)
        listener()
    }

    private fun confirmDelete(note: Note) {
        AlertDialog.Builder(requireActivity()).apply {
            setTitle("Delete This Note!")
            setMessage("Do you want to delete this note ?")
            setPositiveButton("Yes") { _, _ ->
                viewModel.deleteNote(note)
                Toast.makeText(requireContext(), "Delete Note Success !", Toast.LENGTH_SHORT).show()
                this@EditNoteFragment.findNavController().popBackStack(R.id.homeFragment, false)
            }
            setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }.create().show()
        }
    }
}