package com.example.treker_fefu.mainscreens.fragmentscreens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.treker_fefu.R
import com.example.treker_fefu.databinding.FragmentMainScreenBinding

class MainScreenFragment : Fragment() {
    private var _binding: FragmentMainScreenBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMainScreenBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_MainScreenFragment_to_RegisterScreenFragment)
        }
        binding.haveAc.setOnClickListener {
            findNavController().navigate(R.id.action_MainScreenFragment_to_siginScreenFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}