package com.example.imcapp

import android.icu.text.DecimalFormat
import android.os.Bundle
import android.view.TextureView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.imcapp.ImcCalculatorActivity.Companion.IMC_KEY

class ImcResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_imc_result)
        getImc()
        initComponents()
        initListeners()
    }

    private fun initListeners() {

    }

    private fun initComponents() {
        viewBotonReCalc = findViewById(R.id.botonReCalc)
        viewTextoImc = findViewById(R.id.texto_imc)
        viewDescImc = findViewById(R.id.desc_imc)
        viewImc = findViewById(R.id.imc)
    }

    private fun initUI(){
        setImc(getImc())
        setTextoImc()
    }

    private fun setTextoImc(){
        when (getImc()){
            in 0.0 .. 18.5 -> "Infrapeso"
            in 18.6 .. 24.9 -> "Normal"
            in 25.0 .. 29.9 -> "Sobrepeso"
            in 30.0 .. 40.0 -> "Obeso"
        }
    }

    private fun getImc() : Double{
        val imc = intent.extras?.getDouble(IMC_KEY) ?: -1.0
        return imc
    }

    private fun setImc(imc: Double){
        viewImc.text = imc.toString()
    }

    private lateinit var viewBotonReCalc : AppCompatButton
    private lateinit var viewTextoImc : TextView
    private lateinit var viewDescImc : TextView
    private lateinit var viewImc : TextView
}