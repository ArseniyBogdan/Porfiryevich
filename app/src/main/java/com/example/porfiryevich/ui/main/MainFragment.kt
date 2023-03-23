package com.example.porfiryevich.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.lifecycleScope
import com.example.porfiryevich.R
import com.example.porfiryevich.ui.main.interfaces.DialogPicker
import com.example.porfiryevich.ui.main.interfaces.Snackbars
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var mainView: ConstraintLayout
    private lateinit var editText: EditText
    private lateinit var bGenerate: Button
    private lateinit var cGenerate: Button
    private lateinit var viewModel: MainViewModel
    private lateinit var settings: ImageView
    private lateinit var snackbar: Snackbars
    private lateinit var dialog: DialogPicker

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainView = view.findViewById(R.id.main)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        editText = view.findViewById(R.id.message)
        snackbar = SnackbarCaller(mainView)
        dialog = activity?.let { DialogCaller(it) }!!
        bGenerate = view.findViewById(R.id.generate_button)
        cGenerate = view.findViewById(R.id.clear_button)
        settings = view.findViewById(R.id.ic_settings)
        initTextViewObserver()
        initClickListeners()
    }

    private fun initTextViewObserver() {
        lifecycleScope.launch(Dispatchers.Main){
            viewModel.message.collect { value ->
                editText.setText(editText.text.toString().plus(value))
            }
        }
        lifecycleScope.launch(Dispatchers.Main){
            viewModel.failedSend.collect { value ->
                if (viewModel.failedSend.value){
                    snackbar.callSnackbar("Превышено время ожидания ответа")
                    viewModel.failedSend.value = false
                }
            }
        }
        lifecycleScope.launch(Dispatchers.Main){
            viewModel.validateFailure.collect { value ->
                if (viewModel.validateFailure.value){
                    snackbar.callSnackbar("Введены некорректные значения")
                    viewModel.validateFailure.value = false
                }
            }
        }
    }

    private fun initClickListeners(){
        bGenerate.setOnClickListener {viewModel.sendAMessage(editText.text.toString()) }
        cGenerate.setOnClickListener {editText.setText("")}
        settings.setOnClickListener{showAlertDialog()}
    }

    private fun showAlertDialog(){
        lifecycleScope.launch(Dispatchers.Main){
            viewModel.resetCountOfWords(dialog.callDialog())
        }
    }

}