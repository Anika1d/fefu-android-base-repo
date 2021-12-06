package com.example.treker_fefu

import android.os.Bundle
import android.text.Spannable
import android.text.style.ClickableSpan
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.findNavController
import com.example.treker_fefu.databinding.FragmentRegisterScreenBinding


class RegisterScreenFragment : Fragment() {

    private var _binding: FragmentRegisterScreenBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var myToolbar: Toolbar
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterScreenBinding.inflate(inflater, container, false)

        return binding.root

    }


    private fun cliker_rules() {
        val text_Rules = binding.agreeRul.text
        val s_r = SpannableString(text_Rules);
        val clickableSpanCon = object : ClickableSpan() {
            override fun onClick(widget: View) {
                makeText(requireActivity(), "Config", Toast.LENGTH_LONG).show()

            }
        }
        val clickableSpanRules = object : ClickableSpan() {
            override fun onClick(widget: View) {
                makeText(requireActivity(), "Rules", Toast.LENGTH_LONG).show()

            }
        }
        s_r.setSpan(clickableSpanCon, 39, 70, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        s_r.setSpan(clickableSpanRules, 125, 152, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.agreeRul.setMovementMethod(LinkMovementMethod.getInstance())

        binding.agreeRul.setText(s_r)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cliker_rules()
        binding.includeToolbar.myToolbar.title="Регистрация"
        binding.includeToolbar.myToolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_RegisterScreenFragment_to_siginScreenFragment)
        }
        binding.includeToolbar.myToolbar.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_RegisterScreenFragment_to_MainScreenFragment)
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}


