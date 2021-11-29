package com.example.mycontact.ui.profile

import android.app.AlertDialog
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import com.example.mycontact.MainActivity
import com.example.mycontact.R
import com.example.mycontact.databinding.FragmentProfileBinding
import com.example.mycontact.model.Contact

class ProfileFragment : Fragment() {
    //View Binding
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Enable menu in this fragment
        setHasOptionsMenu(true)
        Log.d("Profile Fragment", "OnCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val view = binding.root
        Log.d("Profile Fragment", "OnCreateView")
        val profilePreference : SharedPreferences = activity?.getPreferences(MODE_PRIVATE)!!
        val name : String? = profilePreference.getString(MainActivity.NAME, "")
        val phone : String? = profilePreference.getString(MainActivity.PHONE, "")
        binding.editTextPersonName.setText(name)
        binding.editTextPhone.setText(phone)
        return view //or binding root
    }

    //TODO: Insert code to display and handle Menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.fragment_profile_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.action_save -> {
                //TODO: Save profile data
                //Log.d("Profile Fragment", "Profile Saved")
                if(binding.editTextPersonName.text.isEmpty()) {
                    binding.editTextPersonName.error = getString(R.string.error_value_required)
                    return false
                }
                if(binding.editTextPhone.text.isEmpty()) {
                    binding.editTextPhone.error = getString(R.string.error_value_required)
                    return false
                }

                val contact = Contact(binding.editTextPersonName.text.toString(), binding.editTextPhone.text.toString())
                //Snackbar.make(binding.frameLayout, R.string.record_saved, Snackbar.LENGTH_SHORT).show()

                val builder = AlertDialog.Builder(context)

                builder.setMessage("Save profile?")
                    .setPositiveButton("Save", { dialog, id ->
                        //TODO: Save profile record
                        saveProfilePreference(contact)
                        Toast.makeText(context, R.string.record_saved, Toast.LENGTH_SHORT).show()
                    })
                    .setNegativeButton("Cancel", { dialog, id ->
                        //TODO: Cancel the save event
                    })
                builder.create().show()

                /*AlertDialog.Builder(context)
                    .setTitle("Titile of the dialog")
                    .setMessage("Message")
                    .setPositiveButton("OK", {dialog, id ->
                        //TODO:Positive action
                    })
                    .setNegativeButton("Cancel", {dialog, id ->
                    })
                    .create().show()*/
                true // return
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Profile Fragment", "OnDestroy")
        _binding = null
    }

    override fun onPause() {
        super.onPause()
        Log.d("Profile Fragment", "OnPause")
    }

    fun saveProfilePreference(contact: Contact) {
        val profilePreference : SharedPreferences = activity?.getPreferences(MODE_PRIVATE)!!
        profilePreference.edit().apply {
            putString(MainActivity.NAME, contact.name)
            putString(MainActivity.PHONE, contact.phone)
            commit()
        }
    }
}