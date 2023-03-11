package com.alex.sharedpreferences

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.alex.sharedpreferences.databinding.ActivityResultBinding
import com.google.gson.Gson


class ResultActivity : AppCompatActivity() {
    private lateinit var binding:ActivityResultBinding
    var sharePref: Prefs? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharePref = Prefs(this)
        initUi()
    }

    fun initUi(){
        binding.btnBack.setOnClickListener {
            sharePref?.remove()
            onBackPressed()
        }

        if (!sharePref?.getName().isNullOrBlank()){
            val userName = sharePref?.getName()
            binding.tvName.text = "Bienvenido $userName"

            if (sharePref?.getVip()!!){
                setVipColorBackground()
            }
        }else{
            getObjectUndefined()
        }

    }

    fun getObjectUndefined(){
        val user = sharePref?.getObjectUndefined(userShare)
        val product = sharePref?.getObjectUndefined(productShare)
        val gson = Gson()
        val userObject = gson.fromJson(user, User::class.java)
        val productObject = gson.fromJson(product, Product::class.java)

        binding.tvName.text = "${userObject.name} ${productObject.name}"
    }

    fun getUserObject(){
        val user = sharePref?.getObject()
        val gson = Gson()
        val userObject = gson.fromJson(user, User::class.java)
        val name = userObject.name
        val edad = userObject.age

        binding.tvName.text = "$name $edad"
    }

    fun setVipColorBackground(){
        binding.container.setBackgroundColor(ContextCompat.getColor(this, R.color.teal_200))
    }
}