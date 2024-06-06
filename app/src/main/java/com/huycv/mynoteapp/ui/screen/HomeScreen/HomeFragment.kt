package com.huycv.mynoteapp.ui.screen.HomeScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.huycv.mynoteapp.ui.activity.MainActivity
import com.huycv.mynoteapp.R
import com.huycv.mynoteapp.data.model.Note
import com.huycv.mynoteapp.databinding.FragmentHomeBinding
import com.huycv.mynoteapp.ui.adapter.NoteAdapter

class HomeFragment : Fragment() {
    private lateinit var noteAdapter: NoteAdapter
    private lateinit var biding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        biding = FragmentHomeBinding.inflate(inflater, container, false)
        return biding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUI()
    }

    private fun updateUi(lstNote: List<Note>) {
        if (lstNote.isNullOrEmpty()) {
            biding.emptyNotesImage.visibility = View.VISIBLE
            biding.homeRecyclerView.visibility = View.GONE

        } else {
            biding.emptyNotesImage.visibility = View.GONE
            biding.homeRecyclerView.visibility = View.VISIBLE
        }
    }

    private fun setUpListRecyclerView() {
        noteAdapter = NoteAdapter()
        biding.homeRecyclerView.apply {
            setHasFixedSize(true)
            adapter = noteAdapter
        }
        activity.let {

            viewModel.allNotes.observe(viewLifecycleOwner) { lstNote ->
                noteAdapter.diffUtil.submitList(lstNote)
                updateUi(lstNote)
            }
        }

    }

    private fun setUpUI() {
        viewModel = ViewModelProvider(
            this, (activity as MainActivity).viewModelFactory
        )[HomeViewModel::class.java]
        setUpListRecyclerView()
        listener()


    }

    private fun listener() {
        biding.addNoteFab.setOnClickListener {
            this@HomeFragment.findNavController()
                .navigate(R.id.action_homeFragment_to_addNoteFragment)
        }
    }
}