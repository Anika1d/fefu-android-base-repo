package com.example.treker_fefu.model.map
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.treker_fefu.R

class MapItemAdapter (
    private val mapItems: List<MapItem>
) : RecyclerView.Adapter<MapItemAdapter.MapItemViewHolder>() {

    private var items = mutableListOf<View>()

    private var itemClickListener: (Int, MapItemType) -> Unit = {
            _, _ ->
    }
    fun setItemClickListener(listener: (Int, MapItemType) -> Unit) {
        itemClickListener = listener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MapItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_in_map, parent, false)
        items.add(view)
        return MapItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: MapItemViewHolder, position: Int) {
        holder.bind(mapItems[position])
    }

    override fun getItemCount(): Int = mapItems.size

    inner class MapItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvMapItemName: TextView = itemView.findViewById(R.id.tvMapItemName)
        private lateinit var type: MapItemType
        init {
            itemView.setOnClickListener {
                items.forEach { views ->
                    views.isSelected = it == views
                    if (absoluteAdapterPosition != RecyclerView.NO_POSITION)
                        itemClickListener.invoke(adapterPosition, type)
                }
            }
        }

        fun bind(item: MapItem) {
            tvMapItemName.text = MapItemType.values()[item.type].type
            type = MapItemType.values()[item.type]
        }
    }
}
