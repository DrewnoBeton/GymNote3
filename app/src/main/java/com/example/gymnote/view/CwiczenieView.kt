package com.example.gymnote.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymnote.data.CwiczeniaREPO
import com.example.gymnote.data.Cwiczenie
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect

class CwiczenieView(private val repo: CwiczeniaREPO) : ViewModel()
{
    val inputNazwa = MutableLiveData<String>()
    val inputOpis= MutableLiveData<String>()
    //TODO przekminic ilosc i ciezar
    val zlu_btn_text = MutableLiveData<String>()
    val usun_btn_text = MutableLiveData<String>()

    //wiadomosci
    private val statusMessage = MutableLiveData<Event<String>>()
    val message: LiveData<Event<String>>
    get() = statusMessage

    init {
        zlu_btn_text.value = "Zapisz"
        usun_btn_text.value = "Usuń wszystkie"
    }

    fun zapisz_lub_update()
    {
        if(inputNazwa.value == null)
        {
            statusMessage.value = Event("Podaj nazwę ćwiczenia")
        }
        else if (inputOpis.value == null)
        {
            statusMessage.value = Event("Podaj opis ćwiczenia")
        }
        else {
            val nazwa = inputNazwa.value!!
            val opis = inputOpis.value!!
            wstawCwiczenie(Cwiczenie(0, nazwa, opis, "", 0, 0))
        }
    }
    private fun wstawCwiczenie(cwiczenie: Cwiczenie) = viewModelScope.launch{
        val nowe_id = repo.wstaw(cwiczenie)
        if (nowe_id > -1)
        {
            statusMessage.value = Event("Pomyślnie dodano ćwiczenie nr: $nowe_id")
        }
        else
        {
            statusMessage.value = Event("Bład przy dodawaniu ćwiczenia")
        }
    }

    fun usun_lub_usunCwiczenia()
    {

    }
}