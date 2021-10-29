package com.example.treker_fefu

import android.content.Context

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.fragment.app.Fragment
import com.example.treker_fefu.databinding.ActivityInfoTrekerBinding

class ActivityInfoTreker : AppCompatActivity() {
    private lateinit var binding: ActivityInfoTrekerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoTrekerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        makeCurrentFragment(FragmentStaticData())
        binding.btnNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.staticData -> makeCurrentFragment(FragmentStaticData())
                R.id.fragmentProfileInfo -> makeCurrentFragment(FragmentProfileInfo())
            }
            true
        }

    }

    private fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainerView, fragment)
            commit()
        }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        return super.onCreateView(name, context, attrs)
    }


}
