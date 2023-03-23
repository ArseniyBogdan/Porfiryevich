package com.example.porfiryevich.ui.main

import android.view.View
import com.example.porfiryevich.ui.main.interfaces.Snackbars
import com.google.android.material.snackbar.Snackbar

class SnackbarCaller(private val view: View): Snackbars {
    override fun callSnackbar(message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
    }
}