package com.example.treker_fefu

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.treker_fefu.databinding.FragmentProfileInfoBinding


class FragmentProfileInfo : Fragment() {
    private var _binding: FragmentProfileInfoBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileInfoBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.includeToolbar.myToolbar.title = "Профиль"
  binding.changePassword.setOnClickListener {
          findNavController().navigate(R.id.action_fragmentProfileInfo_to_fragmentChangePassword)
        }
        binding.buttonOut.setOnClickListener {
      findNavController().navigate(R.id.action_fragmentProfileInfo_to_mainScreenActivity)
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}