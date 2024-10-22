package com.example.imcapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ImcCalculatorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        initComponents()
        initListeners()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_imc_calculator)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }

    private fun initListeners() {
        viewMale.setOnClickListener(){

        }
        viewFemale.setOnClickListener(){

        }
    }

    private fun getBackGroundColor(isMaleSelected:Boolean): Int {
        val colorReference = if(isMaleSelected) {
            R.color.bg_comp_sel
        } else {
            R.color.bg_comp
        }
        return ContextCompat.getColor(this,colorReference)
    }

    private fun setGenderColor(){
        viewMale.setCardBackgroundColor(getBackGroundColor(isMaleSelected))
        viewFemale.setCardBackgroundColor(getBackGroundColor(!isMaleSelected))
    }

    private fun initComponents() {
        viewMale = findViewById(R.id.viewMale)
        viewFemale = findViewById(R.id.viewFemale)
    }

    private lateinit var viewMale: CardView
    private lateinit var viewFemale: CardView
    private var isMaleSelected: Boolean = true;
}