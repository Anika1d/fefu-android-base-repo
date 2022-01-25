package com.example.treker_fefu.infoscreens.fragmentscreens

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
import com.example.treker_fefu.App
import com.example.treker_fefu.R
import com.example.treker_fefu.api.MyResult
import com.example.treker_fefu.api.model.User
import com.example.treker_fefu.databinding.FragmentProfileInfoBinding
import com.example.treker_fefu.infoscreens.fragmentscreens.viewmodel.ProfileViewModel
import com.example.treker_fefu.mainscreens.activityscreens.MainScreenActivity
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


class FragmentProfileInfo : Fragment() {
    private var _binding: FragmentProfileInfoBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileInfoBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[ProfileViewModel::class.java]

        viewModel.logoutUser
            .onEach {
                if (it is MyResult.Success<Unit>) {
                    App.INSTANCE.sharedPreferences.edit().remove("token").apply()
                    val intent = Intent(activity, MainScreenActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }
                else if (it is MyResult.Error<Unit>) {
                    makeText(requireContext(), it.e.toString(), Toast.LENGTH_LONG).show()
                }
            }
            .launchIn(lifecycleScope)

        viewModel.profile
            .onEach {
                if (it is MyResult.Success<User>) {
                    binding.loginEdit.setText(it.result.login)
                    binding.nickEdit.setText(it.result.name)
                }
                else if (it is MyResult.Error<User>) {
                    makeText(requireContext(), it.e.toString(), Toast.LENGTH_LONG).show()
                }
            }
            .launchIn(lifecycleScope)
        viewModel.getProfile()

        fun makeCurrentFragment(fragment: Fragment) {
            parentFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContainerView, fragment)
                commit()
            }
        }
        binding.includeToolbarPr.myToolbar.inflateMenu(R.menu.profile_menu)
        binding.includeToolbarPr.myToolbar.menu.findItem(R.id.save_button)
            .setOnMenuItemClickListener {
                makeText(this@FragmentProfileInfo.context, "Сохранено", Toast.LENGTH_LONG).show()
                true
            }

        binding.includeToolbarPr.myToolbar.title = "Профиль"
        binding.changePassword.setOnClickListener {
            makeCurrentFragment(FragmentChangePassword())
        }
        binding.buttonOut.setOnClickListener {
            viewModel.logout()
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}