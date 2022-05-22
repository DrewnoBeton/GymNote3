package com.example.gymnote.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName =  "cwiczenia")
data class Cwiczenie(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val cw_id: Int,
    @ColumnInfo(name = "nazwa") var nazwa: String,
    @ColumnInfo(name = "opis") var opis: String,
    @ColumnInfo(name = "pic_url") val imageUrl: String = "",
    @ColumnInfo(name = "ilosc") val ilosc: Int = 0,
    @ColumnInfo(name = "ceizar") val ciezar: Int = 0
    //TODO czy trening

)
