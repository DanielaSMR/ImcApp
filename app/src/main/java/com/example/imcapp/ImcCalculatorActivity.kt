package com.example.imcapp

import android.content.Intent
import android.icu.text.DecimalFormat
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
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

    companion object {
        const val IMC_KEY = "EXTRA_IMC"
        const val IMC_CATEGORY_KEY = "EXTRA_IMC_CATEGORY"
        const val IMC_DESC_KEY = "EXTRA_IMC_DESC"
    }

    private var age : Int = 0
    private var m : Double = 1.2
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_imc_calculator)
        initComponents()
        initListeners()
        initUI()
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
            m = value / 100.0
        }
        viewBotonHeight.setOnClickListener(){
            setHeight(--kg)
        }
        viewBotonPlusHeight.setOnClickListener(){
            setHeight(++kg)
        }
        viewBotonAge.setOnClickListener(){
            setAge(--age)
        }
        viewBotonPlusAge.setOnClickListener(){
            setAge(++age)
        }
        viewBotonCalc.setOnClickListener(){
            if (kg > 0 &&  m > 0) {
                val imc = calculateIMC(kg, m)
                val imcCategory = getIMCCategory(imc)
                val imcDesc = getIMCDesc(imc)
                navigate2result(imc, imcCategory, imcDesc)
            } else {
                Toast.makeText(this, "Please enter valid values", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun calculateIMC(kg: Int, m: Double): Double {
        return kg / (m * m)
    }


    private fun navigate2result(imc: Double,imcCategory: String, imcDesc: String) {
        var intentIRA : Intent = Intent(this,ImcResultActivity::class.java)
        intentIRA.putExtra(IMC_KEY, imc)
        intentIRA.putExtra(IMC_CATEGORY_KEY, imcCategory)
        intentIRA.putExtra(IMC_DESC_KEY, imcDesc)
        startActivity(intentIRA)
    }

    private fun getIMCCategory(imc: Double): String {
        return when {
            imc < 18.5 -> "Infrapeso"
            imc < 24.9 -> "Normal"
            imc < 29.9 -> "Sobrepeso"
            else -> "Obesidad"
        }
    }

    private fun getIMCDesc(imc: Double): String {
        return when {
            imc < 18.5 -> "Estas por debajo de lo optimo para tu peso y altura"
            imc < 24.9 -> "Estas en un peso normal y bueno para tu peso y altura"
            imc < 29.9 -> "Estas por encima de los optimo para tu peso y altura"
            else -> "Estas por encima de lo optimo para tu salud"
        }
    }

    private fun setAge(age: Int){
        viewAge.text = age.toString()
    }
    private fun setHeight(kg: Int){
        viewKg.text = kg.toString()
    }

    private fun setWeight(m: Double) {
        viewCm.text = DecimalFormat("#.##").format(m * 100) + " cm"
    }

    private fun initUI(){
        setGenderColor(false)
        setAge(age)
        setHeight(kg)
        setWeight(m)
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


}