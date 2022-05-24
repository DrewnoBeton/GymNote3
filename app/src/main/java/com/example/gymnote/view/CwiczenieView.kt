package com.example.gymnote.view

import androidx.lifecycle.*
import com.example.gymnote.App
import com.example.gymnote.R
import com.example.gymnote.data.CwiczeniaREPO
import com.example.gymnote.data.Cwiczenie
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect


/**
 * Instancja ViewModelu
 *
 * @property repo Repozytorium
 */
class CwiczenieView(private val repo: CwiczeniaREPO) : ViewModel()
{
    private var czyWybrano = false
    private lateinit var cwiczenieDoZmiany: Cwiczenie
    val inputNazwa = MutableLiveData<String>()
    val inputOpis= MutableLiveData<String>()
    val inputIlosc = MutableLiveData<String>()
    val inputCiezar = MutableLiveData<String>()

    val zlu_btn_text = MutableLiveData<String>()
    val usun_btn_text = MutableLiveData<String>()

    //wiadomosci
    private val statusMessage = MutableLiveData<Event<String>>()
    val message: LiveData<Event<String>>
    get() = statusMessage

    init {
        //todo stringi w guziorach
        zlu_btn_text.value = "${App.appResources!!.getString(R.string.zapisz)}"
        usun_btn_text.value = "${App.appResources!!.getString(R.string.usun_wszytskie)}"
    }

    /**
     * Obsługa zapisu/aktualizacji
     *
     */
    fun zapisz_lub_update()
    {
        var ciezar = 0
        var ilosc = 0
        if(inputNazwa.value == null)
        {
            statusMessage.value = Event("${App.appResources!!.getString(R.string.brak_nazwy)}")
        }
        else if (inputOpis.value == null)
        {
            statusMessage.value = Event("${App.appResources!!.getString(R.string.brak_opisu)}")
        }
        else {
            if(czyWybrano)
            {
                cwiczenieDoZmiany.nazwa = inputNazwa.value!!
                cwiczenieDoZmiany.opis = inputOpis.value!!
                if(inputCiezar.value == null) {
                    ciezar = 0
                    cwiczenieDoZmiany.ciezar = ciezar
                }
                else { cwiczenieDoZmiany.ciezar = inputCiezar.value!!.toInt() }
                if(inputIlosc.value == null) {
                    ilosc = 0
                    cwiczenieDoZmiany.ilosc = ilosc
                }
                else { cwiczenieDoZmiany.ilosc = inputIlosc.value!!.toInt() }
                updateCwiczenie(cwiczenieDoZmiany)
            }
            else {
                val nazwa = inputNazwa.value!!
                val opis = inputOpis.value!!
                if(inputCiezar.value == null) { ciezar = 0 }
                else { ciezar = inputCiezar.value!!.toInt() }
                if(inputIlosc.value == null) { ilosc = 0 }
                else { ilosc = inputIlosc.value!!.toInt() }
                wstawCwiczenie(Cwiczenie(0, nazwa, opis, "", ilosc, ciezar))
                inputNazwa.value = ""
                inputOpis.value = ""
                inputCiezar.value = ""
                inputIlosc.value = ""
            }
        }
    }

    /**
     * Obsługa trybu aktualizacji i usuwania konkretnego ćwiczenia
     *
     * @param cwiczenie Ćwiczenie do edycji
     */
    fun init_update_lub_usun(cwiczenie: Cwiczenie)
    {
        inputNazwa.value = cwiczenie.nazwa
        inputOpis.value = cwiczenie.opis
        inputIlosc.value = cwiczenie.ilosc.toString()
        inputCiezar.value = cwiczenie.ciezar.toString()
        czyWybrano = true
        cwiczenieDoZmiany = cwiczenie
        zlu_btn_text.value = "${App.appResources!!.getString(R.string.zapisz_zmiany)}"
        usun_btn_text.value = "${App.appResources!!.getString(R.string.usun)}"
    }

    /**
     * Aktualizacja ćwiczenia
     *
     * @param cwiczenie Ćwiczenie do aktualizacji
     * @return
     */
    private fun updateCwiczenie(cwiczenie: Cwiczenie) = viewModelScope.launch {
        val wiersze = repo.update(cwiczenie)
        if (wiersze > 0)
        {
            inputNazwa.value = ""
            inputOpis.value = ""
            inputCiezar.value = ""
            inputIlosc.value = ""
            czyWybrano = false
            zlu_btn_text.value = "${App.appResources!!.getString(R.string.zapisz)}"
            usun_btn_text.value = "${App.appResources!!.getString(R.string.usun_wszytskie)}"
            statusMessage.value = Event("$wiersze ${App.appResources!!.getString(R.string.aktualizacja)}")
        }
        else
        {
            statusMessage.value = Event("${App.appResources!!.getString(R.string.blad_aktualizacja)}")
        }
    }
    /**
     * Wstawianie ćwiczenia
     *
     * @param cwiczenie Ćwiczenie do wstawienia
     * @return
     */
    private fun wstawCwiczenie(cwiczenie: Cwiczenie) = viewModelScope.launch{
        val nowe_id = repo.wstaw(cwiczenie)
        if (nowe_id > -1)
        {
            statusMessage.value = Event("${App.appResources!!.getString(R.string.wstaw)} $nowe_id")
        }
        else
        {
            statusMessage.value = Event("${App.appResources!!.getString(R.string.blad_wstaw)}")
        }
    }

    /**
     * Wybór między usuwaniem jednego ćwiczenia a całości
     *
     */
    fun usun_lub_usunCwiczenia()
    {
        if(czyWybrano)
        {
            usun(cwiczenieDoZmiany)
        }
        else {
            usunCwiczenia()
        }
    }

    /**
     * Usunięcie ćwiczenia
     *
     * @param cwiczenie Ćwiczenie do usunięcia
     * @return
     */
    private fun usun(cwiczenie: Cwiczenie) = viewModelScope.launch {
        val wiersze = repo.usun(cwiczenie)
        if(wiersze > 0)
        {
            inputNazwa.value = ""
            inputOpis.value = ""
            inputCiezar.value = ""
            inputIlosc.value = ""
            czyWybrano = false
            zlu_btn_text.value = "${App.appResources!!.getString(R.string.zapisz)}"
            usun_btn_text.value = "${App.appResources!!.getString(R.string.usun_wszytskie)}"
            statusMessage.value = Event("${App.appResources!!.getString(R.string.usun_git)} $wiersze")
        }
        else
        {
            statusMessage.value = Event("${App.appResources!!.getString(R.string.blad_usun)}")
        }
    }

    /**
     * Usunięcie wszystkich ćwiczeń
     *
     * @return
     */
    private fun usunCwiczenia() = viewModelScope.launch {
        val usuniete_wiersze = repo.usunCwiczenia()
        if(usuniete_wiersze > 0)
        {
            statusMessage.value = Event("${App.appResources!!.getString(R.string.usunieto)} ($usuniete_wiersze) ${App.appResources!!.getString(R.string.cwiczen)}")
        }
        else
        {
            statusMessage.value = Event("${App.appResources!!.getString(R.string.blad_usun_wszystkie)}")
        }
    }

    /**
     * Pobranie listy ćwiczeń
     *
     * @return
     */
    fun wczytajCwiczenia() = liveData{ repo.cwiczenia.collect{emit(it)} }
}