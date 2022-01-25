package com.example.treker_fefu.mainscreens.fragmentscreens

import android.content.Intent
import android.os.Bundle
import android.text.Spannable
import android.text.style.ClickableSpan
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.treker_fefu.App
import com.example.treker_fefu.R
import com.example.treker_fefu.api.MyResult
import com.example.treker_fefu.api.model.GenderType
import com.example.treker_fefu.api.model.Token
import com.example.treker_fefu.databinding.FragmentRegisterScreenBinding
import com.example.treker_fefu.infoscreens.activityscreens.ActivityInfoTreker
import com.example.treker_fefu.mainscreens.fragmentscreens.viewmodel.RegisterViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


class RegisterScreenFragment : Fragment() {
    private var _binding: FragmentRegisterScreenBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: RegisterViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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
        binding.agreeRul.movementMethod = LinkMovementMethod.getInstance()

        binding.agreeRul.text = s_r
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cliker_rules()
        binding.includeToolbar.myToolbar.title = "Регистрация"
        binding.includeToolbar.myToolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)

        binding.includeToolbar.myToolbar.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_RegisterScreenFragment_to_MainScreenFragment)
        }
        binding.checkBox.setOnClickListener {
            binding.checkBox2.setChecked(false)
            binding.checkBox3.setChecked(false)
        }
        binding.checkBox2.setOnClickListener {
            binding.checkBox.setChecked(false)
            binding.checkBox3.setChecked(false)
        }
        binding.checkBox3.setOnClickListener {
            binding.checkBox.setChecked(false)
            binding.checkBox2.setChecked(false)
        }

        viewModel = ViewModelProvider(this)[RegisterViewModel::class.java]

        viewModel.dataFlow
            .onEach {
                if (it is MyResult.Success<Token>) {
                    App.INSTANCE.sharedPreferences.edit().putString("token", it.result.token)
                        .apply()
                    val intent = Intent(this.context, ActivityInfoTreker::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                } else if (it is MyResult.Error<Token>) {
                    makeText(this.context, it.e.toString(), Toast.LENGTH_LONG).show()
                }
            }
            .launchIn(lifecycleScope)

        binding.buttonSecond.setOnClickListener {
            //     findNavController().navigate(R.id.action_RegisterScreenFragment_to_siginScreenFragment)
            val login = binding.loginEdit.text.toString()
            val pass = binding.passEditReg.text.toString()
            val pass_copy = binding.passEditReg2.text.toString()
            val nickname = binding.nickEdit.text.toString()
            var genderOrdinal = 0
            val gender = when {
                binding.checkBox.isChecked -> {
                    "Мужской"
                }
                binding.checkBox2.isChecked -> {
                    "Женский"
                }
                binding.checkBox3.isChecked -> {
                    "Другое"
                }
                else -> "Другое"
            }
            for (i in enumValues<GenderType>()) {
                if (i.type == gender) {
                    genderOrdinal = i.ordinal
                }
            }
            if (pass == pass_copy) {
                viewModel.register(login, pass, nickname, genderOrdinal)
            } else {
                makeText(this.context, "Пароли не совпадают", Toast.LENGTH_SHORT).show()
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}


