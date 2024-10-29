package com.example.imcapp

import android.content.Intent
import android.icu.text.DecimalFormat
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.Slider

class ImcCalculatorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_imc_calculator)
        initComponents()
        initListeners()
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
        viewBotonHeight.setOnClickListener(){
            setHeight(kg--)
        }
        viewBotonPlusHeight.setOnClickListener(){
            setHeight(kg++)
        }
        viewBotonAge.setOnClickListener(){
            setAge(age--)
        }
        viewBotonPlusAge.setOnClickListener(){
            setAge(age++)
        }
        viewBotonCalc.setOnClickListener(){
            navigate2result(calculateIMC())
        }
    }


    companion object {
        const val IMC_KEY = "RESULT"
    }

    private fun navigate2result(imc: Double) {
        var intentIRA : Intent = Intent(this,ImcResultActivity::class.java)
        intentIRA.putExtra(IMC_KEY, imc)
        startActivity(intentIRA)
    }

    private fun calculateIMC(): Double{
        val resultado = kg/(m/2)
        return resultado
    }

    private fun setAge(age: Int){
        viewAge.text = age.toString()
    }
    private fun setHeight(kg: Int){
        viewKg.text = kg.toString()
    }


    private fun initUI(){
        setGenderColor(false)
        setAge(age)
        setHeight(kg)
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
        viewKg = findViewById(R.id.kg)
        viewAge = findViewById(R.id.age)
        viewBotonHeight = findViewById(R.id.btnSubtractKg)
        viewBotonAge = findViewById(R.id.btnSubtractAge)
        viewBotonPlusAge = findViewById(R.id.btnSubtractAgePlus)
        viewBotonPlusHeight = findViewById(R.id.btnSubtractKgPlus)
        viewBotonCalc = findViewById(R.id.botonCalc)
    }

    private var age : Int = 0
    private var m : Double = 0.00
    private var kg : Int = 0


    private lateinit var viewBotonCalc : AppCompatButton
    private lateinit var viewBotonAge : FloatingActionButton
    private lateinit var viewBotonPlusAge : FloatingActionButton
    private lateinit var viewBotonHeight : FloatingActionButton
    private lateinit var viewBotonPlusHeight : FloatingActionButton
    private lateinit var viewAge : TextView
    private lateinit var viewKg : TextView
    private lateinit var viewBarraCm : Slider
    private lateinit var viewCm : TextView
    private lateinit var viewMale: CardView
    private lateinit var viewFemale: CardView
    private var isMaleSelected: Boolean = true;
}