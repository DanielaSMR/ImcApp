package com.example.imcapp

import android.icu.text.DecimalFormat
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.slider.Slider

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
            setGenderColor(true)
        }
        viewFemale.setOnClickListener(){
            setGenderColor(false)
        }
        viewBarraCm.addOnChangeListener { _, value, _ ->
            //tvHeight.text = value.toString()
            viewCm.text = DecimalFormat("#.##").format(value) + " cm"
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

    private fun setGenderColor(isMaleSelected:Boolean){
        viewMale.setCardBackgroundColor(getBackGroundColor(isMaleSelected))
        viewFemale.setCardBackgroundColor(getBackGroundColor(!isMaleSelected))
    }

    private fun initComponents() {
        viewMale = findViewById(R.id.viewMale)
        viewFemale = findViewById(R.id.viewFemale)
        viewCm = findViewById(R.id.tvHeight)
        viewBarraCm = findViewById(R.id.rsHeight)
    }

    private lateinit var viewBarraCm : Slider
    private lateinit var viewCm : TextView
    private lateinit var viewMale: CardView
    private lateinit var viewFemale: CardView
    private var isMaleSelected: Boolean = true;
}