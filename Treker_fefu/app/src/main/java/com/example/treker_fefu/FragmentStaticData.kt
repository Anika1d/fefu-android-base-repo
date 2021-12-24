package com.example.treker_fefu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.treker_fefu.databinding.FragmentStaticDataBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class FragmentStaticData : Fragment() {

    private var _binding: FragmentStaticDataBinding? = null
    lateinit var adapter: ScreenAdapter
    private val tabsNames: Array<String> = arrayOf("Мои", "Пользователей");
    lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStaticDataBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) {
            adapter = ScreenAdapter(this)
            viewPager = binding.pager
            viewPager.adapter = adapter
            tabLayout = view.findViewById(R.id.tabs)
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = tabsNames[position]
            }.attach()
        }

    }

    companion object {
        @JvmStatic
        fun newInstance() = FragmentStaticData()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}