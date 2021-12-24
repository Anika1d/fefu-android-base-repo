package com.example.treker_fefu

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.marginBottom
import androidx.navigation.fragment.findNavController
import com.example.treker_fefu.databinding.FragmentFullInfoItemArrivalBinding
import com.example.treker_fefu.model.Arrival
import com.example.treker_fefu.model.ArrivalService
import com.example.treker_fefu.model.ArrivalsListener
import java.time.format.DateTimeFormatter
import java.util.ArrayList

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FragmentFull_InfoItemArrival(arrival: Arrival, WhatParentFragment: String) : Fragment() {
    private var _binding: FragmentFullInfoItemArrivalBinding? = null
    private val binding get() = _binding!!
    private var tagParentFragment = WhatParentFragment
    private var arrival = arrival

    private var param1: String? = null
    private var param2: String? = null


    private val arrivalService = ArrivalService()
    private var adapter = AdapterArrival(object : ArrivalActionListener {
    }, tagParentFragment)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFullInfoItemArrivalBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        fun BackToScreenHome() {
            parentFragmentManager.beginTransaction().apply {
                val currentFragment =
                    parentFragmentManager.findFragmentByTag("static_data")!!
                hide(this@FragmentFull_InfoItemArrival)
                show(currentFragment)
                commit()
            }
        }

        fun numberLessTеn(string: String): String {
            return if (string.toInt() < 10) {
                "0$string"
            } else string
        }
        binding.toolbar.title = arrival.name_arrival
        with(binding) {


            if (arrival.distance > 1000) {
                val tmp_distance: Double = arrival.distance.toDouble() / 1000
                arrivalDistance.text = "$tmp_distance  км"
            } else {
                arrivalDistance.text = "${arrival.distance}  м"

            }
            var timeH = arrival.time_finish.third - arrival.time_start.third
            var timeM = arrival.time_finish.second - arrival.time_start.second

            if (timeH < 0) timeH += 24
            if (timeM < 0) timeM += 60

            when {
                timeH == 11 -> arrivalLittleTime.text = "$timeH часов"
                timeH == 0 -> arrivalLittleTime.text =""
                timeH % 10 == 1 -> arrivalLittleTime.text = "$timeH час"
                timeH< 5 -> arrivalLittleTime.text = "$timeH часа"
                timeH < 21 -> arrivalLittleTime.text = "$timeH часов"
                timeH % 10 < 5 -> arrivalLittleTime.text = "$timeH часа"
                else -> arrivalLittleTime.text = "$timeH часов"
            }

            when {
                timeM  == 11 -> arrivalLittleTime.text =
                    "${arrivalLittleTime.text} $timeM минут"
                timeM == 0 -> arrivalLittleTime.text =
                    "${arrivalLittleTime.text}"
                timeM % 10 == 1 -> arrivalLittleTime.text =
                    "${arrivalLittleTime.text} $timeM минута"
                timeM< 5 -> arrivalLittleTime.text = "${arrivalLittleTime.text} $timeM минуты"
                timeM < 21 -> arrivalLittleTime.text = "${arrivalLittleTime.text} $timeM минут"
                timeM % 10 < 5 -> arrivalLittleTime.text = "${arrivalLittleTime.text} $timeM минуты"
                else -> arrivalLittleTime.text = "${arrivalLittleTime.text} $timeM минут"
            }

            if (tagParentFragment == "user_data") nickName.text = ""
            else {
                commentEditInput.apply {
                    setTextColor(Color.GRAY)
                    setBackgroundColor(Color.LTGRAY)
                    setText(arrival.comment)
                    isEnabled = false
                    isCursorVisible = false
                    isFocusable = false
                    isClickable = false

                }
                nickName.text = "@${arrival.nick_user}"
            }
            arrivalTimeAgo.text =
                numberLessTеn(arrival.full_info_date.first.toString()) + '.' +
                        numberLessTеn(arrival.full_info_date.second.toString()) + '.' +
                        arrival.full_info_date.third.toString()
            timeStartAndFinish.text =
                "Старт ${arrival.time_start.third}:${arrival.time_start.second} |  Финиш ${arrival.time_finish.third}:${arrival.time_start.second}"
            toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
            toolbar.setNavigationOnClickListener {
                BackToScreenHome()
            }
            if (tagParentFragment == "user_data") {
                toolbar.inflateMenu(R.menu.toolbar_item_menu)
                toolbar.menu.findItem(R.id.trash).setOnMenuItemClickListener {
                    arrivalService.removeArrival(arrival)
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

        arrivalService.addListener(arrivalListener)

    }


    override fun onDestroyView() {
        super.onDestroyView()
        arrivalService.removeListener(arrivalListener)
        _binding = null
    }

    private val arrivalListener: ArrivalsListener = {
        adapter.arrivals = it as ArrayList<Arrival>
    }

}
