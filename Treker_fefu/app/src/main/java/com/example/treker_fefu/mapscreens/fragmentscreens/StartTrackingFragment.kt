package com.example.treker_fefu.mapscreens.fragmentscreens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.treker_fefu.R
import com.example.treker_fefu.databinding.FragmentStartTrackingBinding
import com.example.treker_fefu.model.map.MapItem
import com.example.treker_fefu.model.map.MapItemAdapter
import com.example.treker_fefu.model.map.MapItemType


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
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStartTrackingBinding.inflate(inflater, container, false)
        return binding.root
    }

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