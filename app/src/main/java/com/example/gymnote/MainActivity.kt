package com.example.gymnote

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gymnote.data.Baza
import com.example.gymnote.data.CwiczeniaREPO
import com.example.gymnote.data.Cwiczenie
import com.example.gymnote.databinding.ActivityMainBinding
import com.example.gymnote.view.CwiczenieView
import com.example.gymnote.view.CwiczenieViewFactory
import com.example.gymnote.view.myRecylcerViewAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var cwiczenieView: CwiczenieView
    private lateinit var adapter: myRecylcerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        val dao = Baza.getInstance(application).cwiczeniaDAO
        val repo = CwiczeniaREPO(dao)
        val factory = CwiczenieViewFactory(repo)
        cwiczenieView = ViewModelProvider(this, factory).get(CwiczenieView::class.java)
        binding.mojView = cwiczenieView
        binding.lifecycleOwner = this

        cwiczenieView.message.observe(this, Observer{
            it.getContent_niezrobione()?.let { Toast.makeText(this,it,Toast.LENGTH_LONG).show()}
        })
        initRecyclerView()
    }
    private fun initRecyclerView()
    {
        binding.cwiczeniaRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = myRecylcerViewAdapter({wybranyItem: Cwiczenie -> wybranyItemClick(wybranyItem)})
        binding.cwiczeniaRecyclerView.adapter = adapter
        wyswietlCwiczenia()
    }
    private fun wyswietlCwiczenia()
    {
        cwiczenieView.wczytajCwiczenia().observe(this, Observer {
            adapter.ustawListe(it)
            adapter.notifyDataSetChanged()
        })
    }
    private fun wybranyItemClick(cwiczenie: Cwiczenie){
        Toast.makeText(this,"Wybrano ćwiczenie ${cwiczenie.cw_id}", Toast.LENGTH_LONG).show()
    }
}