package com.example.gymnote.data

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

/**
 * Interfejs DAO (Data Access Object)
 *
 */
@Dao
interface CwiczeniaDAO {


    /**
     * Wstawia podane ćwiczenie do bazy
     *
     * @param cwiczenie Ćwiczenie do wstawienia
     * @return id dodanego wiersza
     */
    @Insert
    suspend fun wstawCwiczenie(cwiczenie: Cwiczenie): Long

    /**
     * Aktualizuje podane ćwiczenie z bazy
     *
     * @param cwiczenie Ćwiczenie do aktualizacji
     * @return Ilość zmienionych wierszy
     */
    @Update
    suspend fun updateCwiczenie(cwiczenie: Cwiczenie): Int

    /**
     * Usuwa podane ćwiczenie z bazy
     *
     * @param cwiczenie Ćwiczenie do usunięcia
     * @return Ilość zmienionych wierszy
     */
    @Delete
    suspend fun usunCwiczenie(cwiczenie: Cwiczenie): Int

    /**
     * Pobiera listę ćwiczeń z bazy
     *
     * @return
     */
    @Query("SELECT * FROM cwiczenia")
    fun getCwiczenia(): Flow<List<Cwiczenie>>

    /**
     * Usuwa zawartośc bazy
     *
     * @return
     */
    @Query("DELETE FROM cwiczenia")
    suspend fun usunCwiczenia(): Int

    //@Insert(onConflict = OnConflictStrategy.REPLACE)
    //fun wstawCwiczenia(cwiczenia: List<Cwiczenie>)

    //@Query("SELECT * FROM cwiczenia WHERE id = :cw_id")
    //fun getCwiczenie(cw_id: String): LiveData<Cwiczenie>


}