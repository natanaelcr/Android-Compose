package com.example.firstcomposeapp.Singleton

class DataStorage {
    companion object{
        private lateinit var dataDummy:HashMap<String,String>
    }

    fun fillData(){
        dataDummy = HashMap<String,String>()
        dataDummy.put("jokowi","amin maruf")
    }



}