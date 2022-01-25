package com.example.treker_fefu.mapscreens.fragmentscreens

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.treker_fefu.App
import com.example.treker_fefu.R
import com.example.treker_fefu.databinding.FragmentStartTrackingBinding
import com.example.treker_fefu.model.map.MapItem
import com.example.treker_fefu.model.map.MapItemAdapter
import com.example.treker_fefu.model.map.MapItemType
import com.example.treker_fefu.room.db.ArrivalRoom
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset


class StartTrackingFragment : Fragment() {

    private val dataList = listOf(
        MapItem(MapItemType.BICYCLE.ordinal),
        MapItem(MapItemType.RUNNING.ordinal),
        MapItem(MapItemType.CAR.ordinal),
    )
    private var _binding: FragmentStartTrackingBinding? = null
    private val binding get() = _binding!!

    private var selectedActivity: MapItemType? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentStartTrackingBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SimpleDateFormat")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = MapItemAdapter(dataList)
        binding.rvMapItems.adapter = adapter

        adapter.setItemClickListener { position, activityType ->
            selectedActivity = activityType
            for (i in 0..binding.rvMapItems.layoutManager?.itemCount!!) {
                binding.rvMapItems.layoutManager?.findViewByPosition(i)?.isSelected = false
            }
            binding.rvMapItems.layoutManager?.findViewByPosition(position)?.isSelected = true
        }

        binding.btnStart.setOnClickListener {
            val instant: Instant = Instant.now()
            val systemZone: ZoneId = ZoneId.systemDefault()
            val currentOffsetForMyZone: ZoneOffset = systemZone.rules.getOffset(instant)
            val curDateTime = LocalDateTime.now(currentOffsetForMyZone)
            selectedActivity?.let {
                App.INSTANCE.db.myActivityDao().insert(
                    ArrivalRoom(
                        id = 0,
                        name_arrival = selectedActivity!!.ordinal,
                        time_start = curDateTime.minusHours(2),
                        time_finish = curDateTime,
                        coords = listOf(Pair(25.0, 25.0)),
                        nickname = "anika"

                    )
                )
                selectedActivity?.let {
                    parentFragmentManager
                        .beginTransaction().apply {
                            replace(
                                R.id.containerTracking,
                                RunnedFragment.newInstance(),
                                "runned_fragment"
                            )
                            addToBackStack(null)
                            commit()
                        }
                }
            }
        }
    }
}