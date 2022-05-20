package com.example.gymnote.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gymnote.data.CwiczeniaREPO
import java.lang.IllegalArgumentException

class CwiczenieViewFactory(private val repo: CwiczeniaREPO): ViewModelProvider.Factory
{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(CwiczenieView::class.java))
        {
            return CwiczenieView(repo) as T
        }
        throw IllegalArgumentException("Nieznana klasa View Model")
    }
}