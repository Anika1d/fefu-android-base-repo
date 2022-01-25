package com.example.treker_fefu.mainscreens.fragmentscreens

import android.content.Intent
import android.os.Bundle
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
import com.example.treker_fefu.api.model.Token
import com.example.treker_fefu.databinding.FragmentSiginScreenBinding
import com.example.treker_fefu.infoscreens.activityscreens.ActivityInfoTreker
import com.example.treker_fefu.mainscreens.fragmentscreens.viewmodel.SiginViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


class SiginScreenFragment : Fragment() {
    private var _binding: FragmentSiginScreenBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: SiginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSiginScreenBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[SiginViewModel::class.java]
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.includeToolbar.myToolbar.title = "Войти"
        binding.includeToolbar.myToolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)


        //TODO ВХОД
        binding.buttonInUser.setOnClickListener {
            val login = binding.loginEdit.text.toString()
            val password = binding.passEdit.text.toString()
            viewModel.login(login, password)
        }

        //TODO ВЫХОД
        binding.includeToolbar.myToolbar.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_siginScreenFragment_to_MainScreenFragment)
        }


        viewModel.dataFlow
            .onEach {
                if (it is MyResult.Success<Token>) {
                    App.INSTANCE.sharedPreferences.edit().putString("token", it.result.token).apply()
                    val intent = Intent(activity, ActivityInfoTreker::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }
                else if (it is MyResult.Error<Token>) {
                    makeText(requireContext(), it.e.toString(), Toast.LENGTH_LONG).show()
                }
            }
            .launchIn(lifecycleScope)


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}