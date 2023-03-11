package com.alex.sharedpreferences

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.alex.sharedpreferences.databinding.ActivityMainBinding
import com.google.gson.Gson
import org.json.JSONObject

const val userShare = "userPreference"
const val productShare = "productPreference"
class MainActivity : AppCompatActivity() {

    var sharePref: Prefs? = null


    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharePref = Prefs(this)
        initUI()
        checkUserValues()


    }

    fun initUI(){
        binding.btnContinue.setOnClickListener {  accessToDetail() }
    }

    fun accessToDetail(){
        if (binding.etName.text.toString().isNotEmpty()){
            sharePref?.save(binding.etName.text.toString())
            sharePref?.saveVip(binding.cbVip.isChecked)
            goToDetail()
        }else{
//            Toast.makeText(this, "Debes poner tu nombre", Toast.LENGTH_SHORT).show()
            saveObjectUndefined()

        }
    }

    fun saveObjectUndefined(){
        val jsonUser = JSONObject()
        jsonUser.put("name", "Pepe")
        jsonUser.put("age", 30)

        val jsonProduct = JSONObject()
        jsonProduct.put("name", "Coca Cola")
        jsonProduct.put("price", 8.50)

        val gson = Gson()
        val user = gson.fromJson(jsonUser.toString(),User::class.java)
        val product = gson.fromJson(jsonProduct.toString(), Product::class.java)

        sharePref?.saveObjectUndefined(userShare,user)
        sharePref?.saveObjectUndefined(productShare, product)
        goToDetail()
    }

    fun saveObjectUser(){
        val jsonUser = JSONObject()
        jsonUser.put("name", "Victor")
        jsonUser.put("age", 25)

        val gson = Gson()
        val user = gson.fromJson(jsonUser.toString(),User::class.java)
//            Log.d("TAG", "onCreate: $user")
        sharePref?.saveObject(user)
        goToDetail()
    }

    fun checkUserValues(){
        if (sharePref?.getName()?.isNotEmpty()!!){
            goToDetail()
        }else if (sharePref?.getObject()?.isNotEmpty()!!){
            goToDetail()
        }else if (sharePref?.getObjectUser(userShare)?.isNotEmpty()!!){
            goToDetail()
        }
    }

    fun goToDetail(){
        startActivity(Intent(this, ResultActivity::class.java))
    }
}