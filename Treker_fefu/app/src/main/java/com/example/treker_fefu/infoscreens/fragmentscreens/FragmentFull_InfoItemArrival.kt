package com.example.treker_fefu.infoscreens.fragmentscreens

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.treker_fefu.App
import com.example.treker_fefu.R
import com.example.treker_fefu.databinding.FragmentFullInfoItemArrivalBinding
import com.example.treker_fefu.model.arrival.*
import com.example.treker_fefu.room.math.toDateSeparator
import java.util.ArrayList

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FragmentFull_InfoItemArrival :
    Fragment() {
    private var _binding: FragmentFullInfoItemArrivalBinding? = null
    private val binding get() = _binding!!
    lateinit var arrival_id: String
    lateinit var arrival: ListArrival.Arrival
    lateinit var tagParentFragment: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val a = arguments?.getStringArrayList("myArg")
        tagParentFragment = a?.get(1).toString()
        arrival_id = a?.get(0).toString()

    }

    fun searchArrival() {
        arrival = App.INSTANCE.db.myActivityDao().getById(arrival_id.toInt()).toArrival()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFullInfoItemArrivalBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchArrival()
        fun BackToScreenHome() {
            parentFragmentManager.beginTransaction().apply {
                val currentFragment =
                    parentFragmentManager.findFragmentByTag("static_data")!!
                hide(this@FragmentFull_InfoItemArrival)
                show(currentFragment)
                commit()
            }
        }
        binding.toolbar.title = arrival.name_arrival
        with(binding) {
            arrivalDistance.text = arrival.distance
            if (tagParentFragment == "user_data") nickName.text = ""
            else {
                commentEditInput.apply {
                    setTextColor(Color.GRAY)
                    setBackgroundColor(Color.LTGRAY)
                    //   setText(arrival.comment)
                    setText("Комментарий")
                    isEnabled = false
                    isCursorVisible = false
                    isFocusable = false
                    isClickable = false

                }
                nickName.text = "@${arrival.nickname}"
            }
            arrivalTimeAgo.text = arrival.date
            timeStartAndFinish.text =
                "Старт ${arrival.time_start} |  Финиш ${arrival.time_finish}"

            toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
            toolbar.setNavigationOnClickListener {
                BackToScreenHome()
            }


            if (tagParentFragment == "user_data") {
                toolbar.inflateMenu(R.menu.toolbar_item_menu)
                toolbar.menu.findItem(R.id.trash).setOnMenuItemClickListener {
                   // arrivalService.removeArrival(arrival)
                    BackToScreenHome()
                    Toast.makeText(context, "Удалено", Toast.LENGTH_LONG).show()
                    true
                }
                toolbar.menu.findItem(R.id.repost).setOnMenuItemClickListener {
                    Toast.makeText(context, "Вы успешно поделились", Toast.LENGTH_LONG).show()
                    true
                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}