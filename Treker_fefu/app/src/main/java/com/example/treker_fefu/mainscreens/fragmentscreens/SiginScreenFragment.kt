package com.example.treker_fefu.mainscreens.fragmentscreens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.treker_fefu.R
import com.example.treker_fefu.databinding.FragmentSiginScreenBinding


class SiginScreenFragment : Fragment() {
    private var _binding: FragmentSiginScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSiginScreenBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.includeToolbar.myToolbar.title = "Войти"
        binding.includeToolbar.myToolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
        binding.buttonInUser.setOnClickListener {
            findNavController().navigate(R.id.action_siginScreenFragment_to_activity_info_treker)
        }
        binding.includeToolbar.myToolbar.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_siginScreenFragment_to_MainScreenFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}