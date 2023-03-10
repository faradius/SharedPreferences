package com.alex.sharedpreferences

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import com.alex.sharedpreferences.UserVipAplication.Companion.prefs
import com.alex.sharedpreferences.databinding.ActivityMainBinding
import com.google.gson.Gson
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
        checkUserValues()


    }

    fun initUI(){
        binding.btnContinue.setOnClickListener {  accessToDetail() }
    }

    fun accessToDetail(){
        if (binding.etName.text.toString().isNotEmpty()){
            prefs.save(binding.etName.text.toString())
            prefs.saveVip(binding.cbVip.isChecked)
            goToDetail()
        }else{
//            Toast.makeText(this, "Debes poner tu nombre", Toast.LENGTH_SHORT).show()
            val jsonUser = JSONObject()
            jsonUser.put("name", "Victor")
            jsonUser.put("age", 25)

            val gson = Gson()
            val user = gson.fromJson(jsonUser.toString(),User::class.java)
//            Log.d("TAG", "onCreate: $user")
            prefs.saveObject(user)
            goToDetail()

        }
    }

    fun checkUserValues(){
        if (prefs.getName().isNotEmpty()){
            goToDetail()
        }else if (prefs.getObject().isNotEmpty()){
            goToDetail()
        }
    }

    fun goToDetail(){
        startActivity(Intent(this, ResultActivity::class.java))
    }
}