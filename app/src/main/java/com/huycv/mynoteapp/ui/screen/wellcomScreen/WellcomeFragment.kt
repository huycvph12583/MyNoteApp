package com.huycv.mynoteapp.ui.screen.wellcomScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.huycv.mynoteapp.R
import com.huycv.mynoteapp.databinding.FragmentWellcomeBinding

class WellcomeFragment : Fragment() {
    private lateinit var biding: FragmentWellcomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        biding = FragmentWellcomeBinding.inflate(inflater, container, false)
        return biding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUI()
    }

    private fun setUpUI() {
        listener()
    }

    private fun listener() {

        biding.btnStart.setOnClickListener {
            this@WellcomeFragment.findNavController()
                .navigate(R.id.action_wellcomeFragment_to_homeFragment)
        }
    }

}