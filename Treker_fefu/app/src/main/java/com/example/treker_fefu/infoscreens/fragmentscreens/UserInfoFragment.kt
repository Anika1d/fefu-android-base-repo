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
import com.example.treker_fefu.databinding.FragmentUserInfoBinding
import com.example.treker_fefu.mapscreens.activityscreens.MapsActivity
import com.example.treker_fefu.model.arrival.Arrival
import com.example.treker_fefu.model.arrival.ArrivalService
import com.example.treker_fefu.model.arrival.ArrivalsListener
import com.example.treker_fefu.model.arrival.AdapterArrival
import com.example.treker_fefu.model.arrival.ArrivalActionListener
import java.util.ArrayList

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class UserInfoFragment : Fragment() {
    private var _binding: FragmentUserInfoBinding? = null
    private val binding get() = _binding!!
    val arrivalService = ArrivalService()
    private var adapter = AdapterArrival(object : ArrivalActionListener {
        override fun onArrivalDetails(arrival: Arrival) {
            activity!!.supportFragmentManager.beginTransaction().apply {
                val visibleFragment =
                    activity!!.supportFragmentManager.fragments.firstOrNull { !isHidden }
                visibleFragment?.let {
                    hide(visibleFragment)
                }
                add(
                    R.id.fragmentContainerView,
                        FragmentFull_InfoItemArrival(arrival, "user_data"),
                        "user_arrival_details"
                    )
                commit()
            }
        }

        override fun onArrivalDelete(arrival: Arrival) {
            arrivalService.removeArrival(arrival)
        }
    }, "user_data")


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
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserInfoBinding.inflate(inflater, container, false)
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
        arrivalService.addListener(arrivalListener)

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putAll(outState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        arrivalService.removeListener(arrivalListener)
        _binding = null
    }

    private val arrivalListener: ArrivalsListener = {
        adapter.arrivals = it as ArrayList<Arrival>
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            UserInfoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}