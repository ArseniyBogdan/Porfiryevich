package com.example.porfiryevich.ui.main

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import com.example.porfiryevich.R
import com.example.porfiryevich.ui.main.interfaces.DialogPicker
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import java.io.Serializable
import java.util.*

class DialogCaller(private val context: Context): DialogPicker {
    override suspend fun callDialog(): String{
        return withContext(Dispatchers.Main) {
            return@withContext AlertDialog.Builder(context).setTitle("Количество автодополняемых слов от 1 до 50 слов")
                .awaitStringInput("Изменить", "Отменить",
                    context)
        }
    }


// расширение для приостанавливания диалогового окна
    private suspend fun AlertDialog.Builder.awaitStringInput(
        positiveText: String,
        negativeText: String,
        context: Context
    ) = suspendCancellableCoroutine { cont ->
        val inflater = LayoutInflater.from(context)
        val window_for_creation: View = inflater.inflate(R.layout.window_for_creation, null)
        setView(window_for_creation)

        val NameField = window_for_creation.findViewById<TextInputEditText>(R.id.creationField)

        val listener = DialogInterface.OnClickListener { _, which ->
            when(which){
                AlertDialog.BUTTON_POSITIVE -> cont.resume(Objects.requireNonNull(NameField.text).toString()) {}
                AlertDialog.BUTTON_NEGATIVE -> cont.resume("") {}
            }
        }

        setPositiveButton(positiveText, listener)
        setNegativeButton(negativeText, listener)

        setOnCancelListener { cont.cancel() }
        show()
    }
}