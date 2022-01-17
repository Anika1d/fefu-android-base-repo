package com.example.treker_fefu.model.arrival


import com.github.javafaker.Faker
import kotlin.Comparator


typealias ArrivalsListener = (arrivals: List<Arrival>) -> Unit

class ArrivalService {
    private var arrivals = mutableListOf<Arrival>()
    private var listeners = mutableListOf<ArrivalsListener>()

    init {
        val faker = Faker.instance()


        arrivals = (5..25).map {
            Arrival(
                id = it.toLong(),
                name_arrival = faker.job().title(),
                distance = faker.number().numberBetween(1, 3500).toLong(), // в метрах
                full_info_date = Triple(
                    faker.number().numberBetween(1, 31),
                    faker.number().numberBetween(1, 12),
                    faker.number().numberBetween(2000, 2020),
                ), time_start = Triple(
                    faker.number().numberBetween(1, 60),
                    faker.number().numberBetween(1, 60),
                    faker.number().numberBetween(1, 24)
                ),
                time_finish = Triple(
                    faker.number().numberBetween(1, 60),
                    faker.number().numberBetween(1, 60),
                    faker.number().numberBetween(1, 24)
                ),
                comment = faker.lorem().paragraph(2),
                nick_user = faker.name().username()
            )
        }.toMutableList()
        val CompareObjects = Comparator<Arrival> { a, b ->
            when {
                a.full_info_date.third != b.full_info_date.third -> a.full_info_date.third - b.full_info_date.third
                a.full_info_date.second != b.full_info_date.second -> a.full_info_date.second - b.full_info_date.second
                else -> a.full_info_date.first - b.full_info_date.first
            }
        }
        arrivals.sortedWith(CompareObjects)


    }



    fun removeArrival(arrival: Arrival) {
        val indexToDelete = arrivals.indexOfFirst { it.id == arrival.id }
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
