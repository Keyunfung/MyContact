package com.example.mycontact.ui.insert

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mycontact.R
import com.example.mycontact.databinding.FragmentInsertBinding

class InsertFragment : Fragment() {

    private var _binding : FragmentInsertBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentInsertBinding.inflate(inflater, container, false)
        return binding.root
    }

}