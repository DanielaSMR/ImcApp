package com.example.imcapp

import android.content.Intent
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

    private lateinit var viewBotonReCalc : AppCompatButton
    private lateinit var viewTextoImc : TextView
    private lateinit var viewDescImc : TextView
    private lateinit var viewImc : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_imc_result)
        initComponents()
        initListeners()

        val imc = intent.getDoubleExtra(ImcCalculatorActivity.IMC_KEY, 0.0)
        val imcCategory = intent.getStringExtra(ImcCalculatorActivity.IMC_CATEGORY_KEY)
        val imcDesc = intent.getStringExtra(ImcCalculatorActivity.IMC_DESC_KEY)

        viewImc.text = "%.2f".format(imc)
        viewTextoImc.text = "Categoria: $imcCategory"
        viewDescImc.text = "$imcDesc"
    }

    private fun initComponents() {
        viewBotonReCalc = findViewById(R.id.botonReCalc)
        viewTextoImc = findViewById(R.id.texto_imc)
        viewDescImc = findViewById(R.id.desc_imc)
        viewImc = findViewById(R.id.imc)
    }

    private fun initListeners() {
        viewBotonReCalc.setOnClickListener {
            val intent = Intent(this, ImcCalculatorActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish() // Cierra la actividad actual
        }
    }

}