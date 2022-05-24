package com.example.gymnote

import android.app.Application
import android.content.res.Resources

/**
 * Klasa potrzebna do przekazywania m.in. strings.xml do funkcji bez potrzeby przekazywania kontekstu
 *
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        appResources = resources
    }

    companion object {
        var appResources: Resources? = null
            private set
    }
}