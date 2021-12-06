package com.example.treker_fefu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.treker_fefu.databinding.FragmentFullInfoItemArrivalBinding


class FragmentFull_InfoItemArrival : Fragment() {
    private var _binding: FragmentFullInfoItemArrivalBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFullInfoItemArrivalBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.includeToolbar.myToolbar?.title = "Cерфинг"
        binding.includeToolbar.myToolbar?.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
