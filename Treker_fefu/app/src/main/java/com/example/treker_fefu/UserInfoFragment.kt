package com.example.treker_fefu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.treker_fefu.databinding.FragmentUserInfoBinding
import com.example.treker_fefu.model.Arrival


class UserInfoFragment : Fragment() {
    private var _binding: FragmentUserInfoBinding? = null
    private val binding get() = _binding!!
    private val adapter=AdapterArrival( object: ArrivalActionListener{
        override fun onArrivalDetails(arrival: Arrival){
            //findNavController().navigate(R.id.action_fragmentStaticData_to_fragmentFull_InfoItemArrival)
            makeText(this@UserInfoFragment.context,"ggggg",Toast.LENGTH_SHORT).show()
        }
    }

    )
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

    }

    private fun initArrival() {
        val recyclerView: RecyclerView = binding.includeRVArrivalFullInfo.rVArrivalFullInfo
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        val arrival = Arrival(
            1,
            "Сноубординг",
            1999,
            Triple(10, 11, 2002),
            Triple(11, 5, 5),
            Triple(11, 19, 23)
        )

        recyclerView.adapter = adapter
        adapter.addArrivals(arrival)
        adapter.addArrivals(arrival)
        adapter.addArrivals(arrival)
        adapter.addArrivals(arrival)
        adapter.addArrivals(arrival)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}