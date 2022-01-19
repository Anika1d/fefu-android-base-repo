package com.example.treker_fefu.infoscreens.fragmentscreens

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.treker_fefu.R
import com.example.treker_fefu.databinding.FragmentFriendsBinding
import com.example.treker_fefu.mapscreens.activityscreens.MapsActivity
import com.example.treker_fefu.model.arrival.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FriendsFragment : Fragment() {
    private var _binding: FragmentFriendsBinding? = null
    private val adapter = AdapterArrival(
        object : ArrivalActionListener {
            override fun onArrivalDetails(arrival: ListArrival.Arrival) {
                activity!!.supportFragmentManager.beginTransaction().apply {
                    val visibleFragment =
                        activity!!.supportFragmentManager.fragments.firstOrNull { !isHidden }
                    visibleFragment?.let {
                        hide(visibleFragment)
                    }
                    add(
                        R.id.fragmentContainerView,
                        FragmentFull_InfoItemArrival(arrival, "friends_data"),
                        "friends_arrival_details"
                    )
                    commit()
                }
            }
        }, "friends_data"

    )
    private val binding get() = _binding!!

    private var param1: String? = null
    private var param2: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFriendsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initArrival()
        binding.fab.setColorFilter(Color.WHITE)
        binding.fab.setOnClickListener {
            val intent = Intent(activity, MapsActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initArrival() {
        val recyclerView: RecyclerView = binding.includeRVArrivalFullInfo.rVArrivalFullInfo
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.adapter = adapter
        //TODO ну пока что  могу лишь черновиками заполнить
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }



}