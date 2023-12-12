package com.cropoptima.cropoptima.auth.register

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.cropoptima.cropoptima.R
import com.cropoptima.cropoptima.databinding.FragmentRegisterBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.userProfileChangeRequest

class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        auth = FirebaseAuth.getInstance()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnRegister.setOnClickListener {
            register()
//            Snackbar.make(binding.root, "Register Successful", Snackbar.LENGTH_LONG).show()
        }


    }

    fun register() {
        val email = binding.edEmail.text.toString()
        val password = binding.edPassword.text.toString()
        val name = binding.edName.text.toString()

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Snackbar.make(binding.root, "Register Successful", Snackbar.LENGTH_LONG).show()
                val firebaseUser = auth.currentUser!!
                val profileUpdates = userProfileChangeRequest {
                    displayName = name
                    photoUri = Uri.parse("https://picsum.photos/800")
                }

                firebaseUser.updateProfile(profileUpdates)
            }
        }.addOnFailureListener { exception ->
            Toast.makeText(binding.root.context, exception.localizedMessage, Toast.LENGTH_LONG).show()
        }
    }

}