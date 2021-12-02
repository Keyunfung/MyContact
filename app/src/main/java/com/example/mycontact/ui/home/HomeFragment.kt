package com.example.mycontact.ui.home

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mycontact.MainActivity
import com.example.mycontact.R
import com.example.mycontact.SecondActivity
import com.example.mycontact.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textViewWelcome
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        binding.buttonSecond.setOnClickListener{
            val intent = Intent(context, SecondActivity::class.java)
            startActivity(intent)
        }

        val profilePreference : SharedPreferences = activity?.getPreferences(Context.MODE_PRIVATE)!!
        val name : String? = profilePreference.getString(MainActivity.NAME, "")
        binding.textViewWelcome.text = String.format("%s %s", getString(R.string.welcome), name)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}