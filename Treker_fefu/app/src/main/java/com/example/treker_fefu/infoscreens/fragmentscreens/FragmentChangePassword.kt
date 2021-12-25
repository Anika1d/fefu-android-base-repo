package com.example.treker_fefu.infoscreens.fragmentscreens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.makeText
import com.example.treker_fefu.R
import com.example.treker_fefu.databinding.FragmentChangePasswordBinding

class FragmentChangePassword : Fragment() {
    private var _binding: FragmentChangePasswordBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChangePasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.includeToolbarChP.myToolbar.title = "Изменить пароль"
        binding.includeToolbarChP.myToolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
        fun makeCurrentFragment(fragment: Fragment, tag: String) {
            parentFragmentManager.beginTransaction().apply {
                val visibleFragment = parentFragmentManager.fragments.firstOrNull { !it.isHidden }
                val currentFragment = parentFragmentManager.findFragmentByTag(tag)

                if (visibleFragment == currentFragment) return@apply
                visibleFragment?.let { hide(it) }
                if (currentFragment != null) {
                    show(currentFragment)
                } else {
                    add(R.id.fragmentContainerView, fragment, tag)
                }
                commit()
            }
        }

        binding.includeToolbarChP.myToolbar.setNavigationOnClickListener {
            makeCurrentFragment(FragmentProfileInfo(), "profile")
        }
        binding.buttonChPass.setOnClickListener {
            makeText(this.context, "Пароль успешно изменен", Toast.LENGTH_SHORT).show()
            makeCurrentFragment(FragmentProfileInfo(), "profile")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}