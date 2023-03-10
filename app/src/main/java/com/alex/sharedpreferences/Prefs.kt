package com.alex.sharedpreferences

import android.content.Context

class Prefs(val context: Context) {
    val SHARED_NAME = "MyPref"
    val SHARED_USER_NAME = "user"
    val SHARED_VIP = "vip"

    val storage = context.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE)

    fun save(name:String){
        storage.edit().putString(SHARED_USER_NAME,name).apply()
    }

    fun saveVip(vip:Boolean){
        storage.edit().putBoolean(SHARED_VIP, vip).apply()
    }

    fun getName():String{
        return storage.getString(SHARED_USER_NAME, "")!!
    }

    fun getVip():Boolean{
        return storage.getBoolean(SHARED_VIP, false)
    }

    fun remove(){
        storage.edit().clear().apply()
    }
}