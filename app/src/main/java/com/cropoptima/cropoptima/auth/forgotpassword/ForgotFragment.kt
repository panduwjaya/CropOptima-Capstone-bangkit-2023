package com.cropoptima.cropoptima.auth.forgotpassword

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.cropoptima.cropoptima.R
import com.cropoptima.cropoptima.databinding.FragmentForgotBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class ForgotFragment : Fragment() {

    private var _binding: FragmentForgotBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentForgotBinding.inflate(inflater, container, false)
        auth = Firebase.auth

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnReset.setOnClickListener {
            resetPassword()
        }
    }

    private fun resetPassword() {
        val email = binding.edEmail.text.toString()
        val context = binding.root.context
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener{
                if (it.isSuccessful){
                    Toast.makeText(context, "Check email to reset password", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(context, "Failed to reset password", Toast.LENGTH_SHORT).show()
                }
            }

    }
}