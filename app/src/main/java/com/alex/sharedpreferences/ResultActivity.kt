package com.alex.sharedpreferences

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.alex.sharedpreferences.UserVipAplication.Companion.prefs
import com.alex.sharedpreferences.databinding.ActivityResultBinding
import com.google.gson.Gson
import org.json.JSONObject


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

        if (!prefs.getName().isNullOrBlank()){
            val userName = prefs.getName()
            binding.tvName.text = "Bienvenido $userName"

            if (prefs.getVip()){
                setVipColorBackground()
            }
        }else{
            val user = prefs.getObject()
            val gson = Gson()
            val userObject = gson.fromJson(user, User::class.java)
            val name = userObject.name
            val edad = userObject.age

            binding.tvName.text = "$name $edad"
        }

    }

    fun setVipColorBackground(){
        binding.container.setBackgroundColor(ContextCompat.getColor(this, R.color.teal_200))
    }
}