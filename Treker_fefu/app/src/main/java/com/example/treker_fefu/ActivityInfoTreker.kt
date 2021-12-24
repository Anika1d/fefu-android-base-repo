package com.example.treker_fefu

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.example.treker_fefu.databinding.ActivityInfoTrekerBinding
import com.mifmif.common.regex.Main

class ActivityInfoTreker : AppCompatActivity() {
    private lateinit var binding: ActivityInfoTrekerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fun makeCurrentFragment(fragment: Fragment, tag: String) {
            supportFragmentManager.beginTransaction().apply {
                val visibleFragment = supportFragmentManager.fragments.firstOrNull { !it.isHidden }
                val currentFragment = supportFragmentManager.findFragmentByTag(tag)
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



        binding = ActivityInfoTrekerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContainerView, FragmentStaticData(), "static_data")
                commit()
            }
        } else {
            supportFragmentManager.beginTransaction().apply {
                add(R.id.fragmentContainerView, FragmentStaticData(), "static_data")
                commit()
            }
        }
        binding.btnNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.staticData -> makeCurrentFragment(FragmentStaticData(), "static_data")
                R.id.fragmentProfileInfo -> makeCurrentFragment(FragmentProfileInfo(), "profile")
            }
            true
        }


    }

    fun outPerson() {
        val intentt = Intent(this, MainScreenActivity::class.java)
        startActivity(intentt)
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        return super.onCreateView(name, context, attrs)

    }


}
