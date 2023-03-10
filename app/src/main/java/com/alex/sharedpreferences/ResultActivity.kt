package com.alex.sharedpreferences

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.alex.sharedpreferences.UserVipAplication.Companion.prefs
import com.alex.sharedpreferences.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding:ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUi()
    }

    fun initUi(){
        binding.btnBack.setOnClickListener {
            prefs.remove()
            onBackPressed()
        }
        val userName = prefs.getName()
        binding.tvName.text = "Bienvenido $userName"

        if (prefs.getVip()){
            setVipColorBackground()
        }
    }

    fun setVipColorBackground(){
        binding.container.setBackgroundColor(ContextCompat.getColor(this, R.color.teal_200))
    }
}