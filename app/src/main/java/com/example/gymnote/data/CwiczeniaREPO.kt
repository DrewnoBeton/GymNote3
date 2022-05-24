package com.example.gymnote.data

/**
 * Repozytorium slużące do komunikacji z DAO
 *
 * @property cwiczeniaDAO Używany interfejs DAO
 */
class CwiczeniaREPO(private val cwiczeniaDAO: CwiczeniaDAO) {

    /**
     * Lista ćwiczeń
     */
    val cwiczenia = cwiczeniaDAO.getCwiczenia()

    /**
     * Wywoływacz funkcji [wstawCwiczenie][CwiczeniaDAO.wstawCwiczenie] z [CwiczeniaDAO]
     *
     * @param cwiczenie Ćwiczenie do wstawienia
     * @return id wstawionego ćwiczenia
     */
    suspend fun wstaw(cwiczenie: Cwiczenie): Long
    {
        return cwiczeniaDAO.wstawCwiczenie(cwiczenie)
    }

    /**
     * Wywoływacz funkcji [updateCwiczenie][CwiczeniaDAO.updateCwiczenie] z [CwiczeniaDAO]
     *
     * @param cwiczenie Ćwiczenie do aktualizacji
     * @return Ilość zmienionych wierszy
     */
    suspend fun update(cwiczenie: Cwiczenie): Int
    {
        return cwiczeniaDAO.updateCwiczenie(cwiczenie)
    }

    /**
     * Wywoływacz funkcji [usunCwiczenie][CwiczeniaDAO.usunCwiczenie] z [CwiczeniaDAO]
     *
     * @param cwiczenie Ćwiczenie do usunięcia
     * @return Ilość zmienionych wierszy
     */
    suspend fun usun(cwiczenie: Cwiczenie): Int
    {
        return cwiczeniaDAO.usunCwiczenie(cwiczenie)
    }

    /**
     * Wywoływacz funkcji [usunCwiczenia][CwiczeniaDAO.usunCwiczenia] z [CwiczeniaDAO]
     *
     * @return Ilość zmienionych wierszy
     */
    suspend fun usunCwiczenia(): Int
    {
        return cwiczeniaDAO.usunCwiczenia()
    }
}