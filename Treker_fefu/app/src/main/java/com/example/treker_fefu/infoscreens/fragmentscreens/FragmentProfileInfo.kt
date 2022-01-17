package com.example.treker_fefu.infoscreens.fragmentscreens

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.makeText
import com.example.treker_fefu.R
import com.example.treker_fefu.databinding.FragmentProfileInfoBinding
import com.example.treker_fefu.mainscreens.activityscreens.MainScreenActivity


class FragmentProfileInfo : Fragment() {
    private var _binding: FragmentProfileInfoBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileInfoBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
            val intent = Intent(activity, MainScreenActivity::class.java)
            startActivity(intent)
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}