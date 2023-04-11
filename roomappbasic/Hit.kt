package com.example.roomappbasic

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hits")
class Hit (@PrimaryKey(autoGenerate = true) val id: Long, val song: String, val artist: String, val year: Int, var chart: Int) {
    override fun toString(): String {
        return "$id: $song by $artist, year $year, chart $chart"
    }
}
