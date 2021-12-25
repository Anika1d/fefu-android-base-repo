package com.example.treker_fefu.model.arrival

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.treker_fefu.databinding.ItemArrivalBinding
import java.util.*


class AdapterArrival(
    private val actionListener: ArrivalActionListener, private var tagParentFragment: String
) : RecyclerView.Adapter<AdapterArrival.ArrivalViewHolder>(), View.OnClickListener {
    var arrivals = ArrayList<Arrival>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()

        }

    // val arrivalService = ArrivalService()
    override fun onClick(v: View) {
        val arrival: Arrival = v.tag as Arrival
        actionListener.onArrivalDetails(arrival)
    }

    override fun getItemCount(): Int = arrivals.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArrivalViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemArrivalBinding.inflate(inflater, parent, false)
        binding.root.setOnClickListener(this)
        return ArrivalViewHolder(binding)
    }


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ArrivalViewHolder, position: Int) {
        val arrival = arrivals[position]

        with(holder.binding) {
            fun numberLessTеn(string: String): String {
                return if (string.toInt() < 10) {
                    "0$string"
                } else string
            }
            holder.itemView.tag = arrival
            arrivalName.text = arrival.name_arrival
            ////ЕСЛИ БОЛЬШЕ ТЫСЯЧИ ДЕЛИТЬ
            if (arrival.distance > 1000) {
                val tmp_distance: Double = arrival.distance.toDouble() / 1000
                arrivalDistance.text = "$tmp_distance  км"
            } else {
                arrivalDistance.text = "${arrival.distance}  м"
            }//////  не забыть СРАВНИть ГОД. МЕСЯЦ. И ДНИ НЕДЕЛИ И НАШЛИ ТАЙМ АГОУ c cегодня
            arrivalTimeAgo.text =
                numberLessTеn(arrival.full_info_date.first.toString()) + '.' +
                        numberLessTеn(arrival.full_info_date.second.toString()) + '.' +
                        arrival.full_info_date.third.toString()
            if (tagParentFragment == "user_data") nickName.text = ""
            else nickName.text = "@${arrival.nick_user}"
            var timeH = arrival.time_finish.third - arrival.time_start.third
            var timeM = arrival.time_finish.second - arrival.time_start.second

            if (timeH < 0) timeH += 24
            if (timeM < 0) timeM += 60

            when {
                timeH == 11 -> arrivalLittleTime.text = "$timeH часов"
                timeH == 0 -> arrivalLittleTime.text = ""
                timeH % 10 == 1 -> arrivalLittleTime.text = "$timeH час"
                timeH < 5 -> arrivalLittleTime.text = "$timeH часа"
                timeH < 21 -> arrivalLittleTime.text = "$timeH часов"
                timeH % 10 < 5 -> arrivalLittleTime.text = "$timeH часа"
                else -> arrivalLittleTime.text = "$timeH часов"
            }

            when {
                timeM == 11 -> arrivalLittleTime.text =
                    "${arrivalLittleTime.text} $timeM минут"
                timeM == 0 -> arrivalLittleTime.text =
                    "${arrivalLittleTime.text}"
                timeM % 10 == 1 -> arrivalLittleTime.text =
                    "${arrivalLittleTime.text} $timeM минута"
                timeM < 5 -> arrivalLittleTime.text = "${arrivalLittleTime.text} $timeM минуты"
                timeM < 21 -> arrivalLittleTime.text = "${arrivalLittleTime.text} $timeM минут"
                timeM % 10 < 5 -> arrivalLittleTime.text = "${arrivalLittleTime.text} $timeM минуты"
                else -> arrivalLittleTime.text = "${arrivalLittleTime.text} $timeM минут"
            }
            dataTimeInfoToday.text = "Ранее в " + arrival.full_info_date.third.toString() + " году"

        }

    }

    class ArrivalViewHolder(
        val binding: ItemArrivalBinding
    ) : RecyclerView.ViewHolder(binding.root)
}

interface ArrivalActionListener {
    fun onArrivalDetails(arrival: Arrival) {

    }

    fun onArrivalDelete(arrival: Arrival) {

    }
}