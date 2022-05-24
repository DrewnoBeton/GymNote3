package com.example.gymnote.data

/**
 * test
 *
 * @property cwiczeniaDAO
 */
class CwiczeniaREPO(private val cwiczeniaDAO: CwiczeniaDAO) {

    val cwiczenia = cwiczeniaDAO.getCwiczenia()
    suspend fun wstaw(cwiczenie: Cwiczenie): Long
    {
        return cwiczeniaDAO.wstawCwiczenie(cwiczenie)
    }
    suspend fun update(cwiczenie: Cwiczenie): Int
    {
        return cwiczeniaDAO.updateCwiczenie(cwiczenie)
    }
    suspend fun usun(cwiczenie: Cwiczenie): Int
    {
        return cwiczeniaDAO.usunCwiczenie(cwiczenie)
    }
    suspend fun usunCwiczenia(): Int
    {
        return cwiczeniaDAO.usunCwiczenia()
    }
}