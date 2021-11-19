package com.example.treker_fefu.model


import com.github.javafaker.Faker
import java.util.*
typealias ArrivalsListener=(arrivals:List<Arrival>)->Unit
class ArrivalService {
    private  var arrivals= mutableListOf<Arrival>()
    private  var listeners= mutableListOf<ArrivalsListener>()
  init {
        val faker = Faker.instance()
       arrivals = (5..25).map {
            Arrival(
                id = it.toLong(),
                name_arrival = faker.job().title(),
             //   nick_user = faker.name().username(),
                distance = faker.number().numberBetween(1, 2000).toLong(), // в метрах
                  full_info_date = Triple(
               faker.number().numberBetween(1, 60),
                    faker.number().numberBetween(1, 60),
                    faker.number().numberBetween(1, 24)
                ),
                time_start = Triple(
                    faker.number().numberBetween(1, 60),
                    faker.number().numberBetween(1, 60),
                    faker.number().numberBetween(1, 24)
                ),
                time_finish = Triple(
                    faker.number().numberBetween(1, 60),
                    faker.number().numberBetween(1, 60),
                    faker.number().numberBetween(1, 24)
                )
            ,
                //comment = faker.lorem().paragraph()
            )
        }.toMutableList()
    }

    fun getArrival():List<Arrival> {
        return arrivals
    }

    fun removeArrival(arrival: Arrival){
        val indexToDelete=arrivals.indexOfFirst { it.id==arrival.id  }
        if (indexToDelete!=-1){
            arrivals.removeAt(indexToDelete)
        }
    }
    fun moveArrival(arrival: Arrival,moveBy:Int){
        val oldIndex= arrivals.indexOfFirst{ it.id==arrival.id}
        if (oldIndex==-1) return
        val newIndex=oldIndex+moveBy
        if (newIndex<0 || newIndex>=arrivals.size) return
        Collections.swap(arrivals,oldIndex,newIndex)
        notifyChanges()

    }
    fun addListener(listener:ArrivalsListener){
        listeners.add(listener)
        listener.invoke(arrivals)
    }
    fun removeListener(listener:ArrivalsListener){
        listeners.remove(listener)
    }
    private  fun notifyChanges(){
         listeners.forEach{it.invoke(arrivals)}
    }

}
