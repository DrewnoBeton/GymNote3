package com.example.gymnote

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.gymnote.data.Baza
import com.example.gymnote.data.CwiczeniaREPO
import com.example.gymnote.databinding.ActivityMainBinding
import com.example.gymnote.view.CwiczenieView
import com.example.gymnote.view.CwiczenieViewFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var cwiczenieView: CwiczenieView

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
    }
}