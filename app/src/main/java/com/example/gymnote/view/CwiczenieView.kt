package com.example.gymnote.view

import androidx.lifecycle.*
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
        //todo wypierdala apke xD i niczego nie usuwa
        usunCwiczenia()
    }

    private fun usunCwiczenia() = viewModelScope.launch {
        val usuniete_wiersze = repo.usunCwiczenia()
        if(usuniete_wiersze > 0)
        {
            statusMessage.value = Event("Usunięto ($usuniete_wiersze) ćwiczeń")
        }
        else
        {
            statusMessage.value = Event("Błąd przy usuwaniu ćwiczeń")
        }
    }


    fun wczytajCwiczenia() = liveData{ repo.cwiczenia.collect{emit(it)} }
}