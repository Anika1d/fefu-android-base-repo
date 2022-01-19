package com.example.treker_fefu.model.arrival


typealias ArrivalsListener = (arrivals: List<ListArrival>) -> Unit

class ArrivalService {
    var arrivals = mutableListOf<ListArrival>()
    private var listeners = mutableListOf<ArrivalsListener>()
    fun removeArrival(arrival: ListArrival.Arrival) {
        val indexToDelete = arrivals.indexOfFirst {
            val ar = it as ListArrival.Arrival
            ar.id == arrival.id
        }
        if (indexToDelete != -1) {
            arrivals.removeAt(indexToDelete)
            notifyChanges()
        }
    }


    fun addListener(listener: ArrivalsListener) {
        listeners.add(listener)
        listener.invoke(arrivals)
    }

    fun removeListener(listener: ArrivalsListener) {
        listeners.remove(listener)
    }

    private fun notifyChanges() {
        listeners.forEach { it.invoke(arrivals) }
    }

}