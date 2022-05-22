package com.example.gymnote.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.gymnote.R
import com.example.gymnote.data.Cwiczenie
import com.example.gymnote.databinding.ItemListaBinding
import com.example.gymnote.generated.callback.OnClickListener


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
    fun ustawListe(cwiczenia: List<Cwiczenie>)
    {
        listaCwiczen.clear()
        listaCwiczen.addAll(cwiczenia)
    }
}



class myViewHolder(val binding: ItemListaBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(cwiczenie: Cwiczenie, clickListener: (Cwiczenie) -> Unit)
    {
        binding.cwNazwaText.text = cwiczenie.nazwa
        binding.cwOpisText.text = cwiczenie.opis
        //todo dorobic ciezar ilosc
        binding.listaCwiczen.setOnClickListener{clickListener(cwiczenie)}
   }
}