package com.example.treker_fefu.model.arrival

import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.treker_fefu.R
import com.example.treker_fefu.databinding.ItemArrivalBinding
import com.example.treker_fefu.databinding.ItemDateBinding


class AdapterArrival(
    private val actionListener: ArrivalActionListener, private var tagParentFragment: String,
) : ListAdapter<ListArrival, RecyclerView.ViewHolder>(ListItemCallback()), View.OnClickListener {

    override fun onClick(v: View) {
        val arrival: ListArrival.Arrival = v.tag as ListArrival.Arrival
        actionListener.onArrivalDetails(arrival)
    }

    override fun getItemCount() = currentList.size

    override fun getItemViewType(position: Int): Int {
        return when (currentList[position]) {
            is ListArrival.Date -> R.layout.item_date
            is ListArrival.Arrival -> R.layout.item_arrival
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.item_date -> DateArrivalHolder(
                ItemDateBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            R.layout.item_arrival -> {

                val inflater = LayoutInflater.from(parent.context)
                val binding = ItemArrivalBinding.inflate(inflater, parent, false)
                binding.root.setOnClickListener(this)
                ArrivalHolder(binding)

            }
            else -> throw IllegalArgumentException("Invalid viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is DateArrivalHolder -> {
                val date = currentList[position] as ListArrival.Date
                holder.binding.textvItemDate.text = date.date
            }
            is ArrivalHolder -> {
                val arrival = currentList[position] as ListArrival.Arrival
                holder.itemView.tag = arrival
                with(holder.binding) {
                    if (tagParentFragment == "friends_data") {
                        nickName.setTextColor(
                            ContextCompat.getColor(
                                holder.binding.root.context,
                                R.color.purple_200
                            )
                        )

                    }
                    arrivalName.text = arrival.name_arrival
                    arrivalDistance.text = arrival.distance
                    arrivalLittleTime.text = arrival.time
                    arrivalTimeAgo.text = arrival.date
                }
            }
        }
    }

    class ArrivalHolder(val binding: ItemArrivalBinding) : RecyclerView.ViewHolder(binding.root)
    class DateArrivalHolder(val binding: ItemDateBinding) : RecyclerView.ViewHolder(binding.root)

    class ListItemCallback() : DiffUtil.ItemCallback<ListArrival>() {
        override fun areItemsTheSame(
            old_arrival: ListArrival,
            new_arrival: ListArrival,
        ): Boolean {
            return when {
                old_arrival is ListArrival.Date && new_arrival is ListArrival.Date ->
                    old_arrival.date == new_arrival.date
                old_arrival is ListArrival.Arrival && new_arrival is ListArrival.Arrival ->
                    old_arrival.id == new_arrival.id
                else -> false
            }
        }

        override fun areContentsTheSame(
            old_arrival: ListArrival,
            new_arrival: ListArrival,
        ): Boolean {
            return when {
                old_arrival is ListArrival.Date && new_arrival is ListArrival.Date ->
                    areItemsTheSame(old_arrival, new_arrival)
                old_arrival is ListArrival.Arrival && new_arrival is ListArrival.Arrival ->
                    old_arrival == new_arrival
                else -> false
            }

        }


    }
}

interface ArrivalActionListener {


    fun onArrivalDetails(arrival: ListArrival.Arrival) {

    }

    fun onArrivalDelete(arrival: ListArrival.Arrival) {

    }
}