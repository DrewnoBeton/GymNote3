package com.example.gymnote.data

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CwiczeniaDAO {

    @Insert
    suspend fun wstawCwiczenie(cwiczenie: Cwiczenie): Long

    @Update
    suspend fun updateCwiczenie(cwiczenie: Cwiczenie): Int

    @Delete
    suspend fun usunCwiczenie(cwiczenie: Cwiczenie): Int

    //@Query("SELECT * FROM cwiczenia ORDER BY nazwa")
    @Query("SELECT * FROM cwiczenia")
    fun getCwiczenia(): Flow<List<Cwiczenie>>

    @Query("DELETE FROM cwiczenia")
    suspend fun usunCwiczenia(): Int

    //@Insert(onConflict = OnConflictStrategy.REPLACE)
    //fun wstawCwiczenia(cwiczenia: List<Cwiczenie>)

    //@Query("SELECT * FROM cwiczenia WHERE id = :cw_id")
    //fun getCwiczenie(cw_id: String): LiveData<Cwiczenie>


}