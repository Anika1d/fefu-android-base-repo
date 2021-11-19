package com.example.treker_fefu

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.treker_fefu.databinding.ItemArrivalBinding
import com.example.treker_fefu.model.Arrival
import com.example.treker_fefu.model.ArrivalService
import com.github.javafaker.Faker
import java.util.*
class AdapterArrival : RecyclerView.Adapter<AdapterArrival.ArrivalViewHolder>() {
    var arrivals=ArrayList<Arrival>()
        set(value) {
            field = value
            notifyDataSetChanged()

        }
    val arrivalService = ArrivalService()
    override fun getItemCount(): Int = arrivals.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArrivalViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemArrivalBinding.inflate(inflater, parent, false);
        return ArrivalViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ArrivalViewHolder, position: Int) {
        val arrival = arrivals[position]
        with(holder.binding) {
            arrivalName.text = arrival.name_arrival
            ////ЕСЛИ БОЛЬШЕ ТЫСЯЧИ ДЕЛИТЬ
            if (arrival.distance > 1000) {
                val tmp_distance: Double = arrival.distance.toDouble() / 1000
                arrivalDistance.text = "$tmp_distance  км"
            } else {
                arrivalDistance.text = "${arrival.distance}  м"
            }//////  не забыть СРАВНИть ГОД. МЕСЯЦ. И ДНИ НЕДЕЛИ И НАШЛИ ТАЙМ АГОУ c cегодня
            arrivalTimeAgo.text =
                arrival.full_info_date.first.toString() + '.' +
                        arrival.full_info_date.second.toString() + '.' +
                        arrival.full_info_date.third.toString()


            var timeH = arrival.time_finish.third - arrival.time_start.third
            var timeM = arrival.time_finish.second - arrival.time_start.second

            if (timeH < 0) timeH += 24
            if (timeM < 0) timeM += 60

            arrivalLittleTime.text = "$timeH : $timeM"
        }

    }
    fun addArrivals(arrival: Arrival){
        arrivals.add(arrival)
        notifyDataSetChanged()
    }


    class ArrivalViewHolder(val binding: ItemArrivalBinding) : RecyclerView.ViewHolder(binding.root){

    }

}