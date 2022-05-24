package com.example.gymnote.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.gymnote.R
import com.example.gymnote.data.Cwiczenie
import com.example.gymnote.databinding.ItemListaBinding
import com.example.gymnote.generated.callback.OnClickListener

/**
 * Adapter do RecycleView
 *
 * @property clickListener Zaczep
 */
class myRecylcerViewAdapter(private val clickListener: (Cwiczenie) -> Unit): RecyclerView.Adapter<myViewHolder>()
{
    private val listaCwiczen = ArrayList<Cwiczenie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemListaBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_lista, parent, false)
        return myViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listaCwiczen.size
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        holder.bind(listaCwiczen[position], clickListener)
    }

    /**
     * Ustawia listę ćwiczeń
     *
     * @param cwiczenia
     */
    fun ustawListe(cwiczenia: List<Cwiczenie>)
    {
        listaCwiczen.clear()
        listaCwiczen.addAll(cwiczenia)
    }
}


/**
 * Klasa ViewHolder
 *
 * @property binding Powiązanie danych
 */
class myViewHolder(val binding: ItemListaBinding): RecyclerView.ViewHolder(binding.root) {
    /**
     * Funkcja obsługująca powiązanie danych
     *
     * @param cwiczenie Ćwiczenie
     * @param clickListener Zmienna do nasłuchu kliknięć
     */
    fun bind(cwiczenie: Cwiczenie, clickListener: (Cwiczenie) -> Unit)
    {
        binding.cwNazwaText.text = cwiczenie.nazwa
        binding.cwOpisText.text = cwiczenie.opis
        binding.cwCiezarText.text = cwiczenie.ciezar.toString()
        binding.iloscText.text = cwiczenie.ilosc.toString()
        binding.listaCwiczen.setOnClickListener{clickListener(cwiczenie)}
   }
}