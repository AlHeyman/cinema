package com.example.cinema

import android.content.Intent

import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.city_item_rv.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity() {

    val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
    .baseUrl("https://www.themoviedb.org").build()

    val logClient = retrofit.create(LogInAut :: class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        var list : MutableList<LogInAut> = ArrayList()

        logClient.createToken(api_key).enqueue(object: Callback <List<>)



    }

}