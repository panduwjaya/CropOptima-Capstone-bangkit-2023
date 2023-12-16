package com.cropoptima.cropoptima.auth.forgotpassword

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.cropoptima.cropoptima.R
import com.cropoptima.cropoptima.databinding.FragmentForgotBinding
import com.google.firebase.auth.FirebaseAuth

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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()

        binding.btnReset.setOnClickListener {
            val email = binding.edEmail.text.toString()
            val edEmail = binding.edEmail


            // validasi email kosong
            if (email.isEmpty()){
                edEmail.error = "Email Tidak Boleh Kosong"
                edEmail.requestFocus()
                return@setOnClickListener
            }

            // validasi penulisan email
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                edEmail.error = "Email Tidak Valid"
                edEmail.requestFocus()
                return@setOnClickListener
            }

            FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnCompleteListener{
                if (it.isSuccessful){
                    Toast.makeText(requireContext(),"Email Reset Password Dikirim", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_forgotFragment_to_loginFragment)
                }else {
                    Toast.makeText(requireContext(),"${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}