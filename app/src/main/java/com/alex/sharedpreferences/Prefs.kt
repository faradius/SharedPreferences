package com.alex.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson

class Prefs(context: Context) {
    val SHARED_NAME = "MyPref"
    val SHARED_USER_NAME = "user"
    val SHARED_USER_OBJECT = "userObject"
    val SHARED_VIP = "vip"

    private var prefs: SharedPreferences? = null

    init {
        prefs = context.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE)
    }



    fun save(name:String){
        prefs?.edit()?.putString(SHARED_USER_NAME,name)?.apply()
    }

    fun saveObject(objeto:Any){
        val gson = Gson()
        val json = gson.toJson(objeto)
        prefs?.edit()?.putString(SHARED_USER_OBJECT,json)?.apply()
    }

    fun saveObjectUndefined(key: String, objeto: Any){
        val gson = Gson()
        val json = gson.toJson(objeto)
        prefs?.edit()?.putString(key, json)?.commit()
    }
    fun saveObjectUser(key: String, objeto: Any){
        val gson = Gson()
        val json = gson.toJson(objeto)
        prefs?.edit()?.putString(key, json)?.commit()
    }
    fun saveObjectProduct(key: String, objeto: Any){
        val gson = Gson()
        val json = gson.toJson(objeto)
        prefs?.edit()?.putString(key, json)?.commit()
    }

    fun getObjectUndefined(key: String):String{
        return prefs?.getString(key, "")!!
    }
    fun getObjectUser(key: String):String{
        return prefs?.getString(key, "")!!
    }

    fun getObjectProduct(key: String):String{
        return prefs?.getString(key, "")!!
    }

    fun saveVip(vip:Boolean){
        prefs?.edit()?.putBoolean(SHARED_VIP, vip)?.apply()
    }

    fun getName():String{
        return prefs?.getString(SHARED_USER_NAME, "")!!
    }

    fun getObject():String{
        return prefs?.getString(SHARED_USER_OBJECT, "")!!
    }

    fun getVip(): Boolean? {
        return prefs?.getBoolean(SHARED_VIP, false)
    }

    fun remove(){
        prefs?.edit()?.clear()?.apply()
    }
}