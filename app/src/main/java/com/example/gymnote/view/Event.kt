package com.example.gymnote.view

/**
 * Klasa do tworzenia wiadomości systemowych
 *
 * @param T
 * @property content
 */
open class Event<out T>(private val content: T)
{
    var zrobione = false
    private set

    fun getContent_niezrobione(): T?{
        return if(zrobione){ null }
        else {
            zrobione = true
            content
        }
    }

    fun getContent(): T = content
}