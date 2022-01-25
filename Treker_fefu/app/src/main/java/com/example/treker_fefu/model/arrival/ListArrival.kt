package com.example.treker_fefu.model.arrival

import com.google.android.gms.maps.model.LatLng

sealed class ListArrival {
    data class Date(
        val date: String
    ) : ListArrival()

    data class Arrival(
        val id: Int,
        val name_arrival: String,
        val nickname: String,
        val distance: String,
        val date: String,
        val time: String,
        val time_start: String,
        val time_finish: String,
        // val coords:Pair<LatLng,LatLng>
        // val comment:String
    ) : ListArrival()
}
