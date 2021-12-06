package com.example.treker_fefu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.navigation.fragment.findNavController
import com.example.treker_fefu.databinding.FragmentChangePasswordBinding

class FragmentChangePassword : Fragment() {
    private var _binding: FragmentChangePasswordBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.includeToolbarChP.myToolbar.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_fragmentChangePassword_to_fragmentProfileInfo)
        }
        binding.buttonChPass.setOnClickListener{
            makeText(this.context,"Пароль успешно изменен",Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_fragmentChangePassword_to_fragmentProfileInfo)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChangePasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.includeToolbarChP.myToolbar.title = "Изменить пароль"
        binding.includeToolbarChP.myToolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}