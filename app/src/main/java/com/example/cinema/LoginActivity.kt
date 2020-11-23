package com.example.cinema

import android.content.Intent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.os.Bundle
import android.text.Editable
import android.util.Log

import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import kotlinx.android.synthetic.main.login_activity.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity() {


    private lateinit var token: String


    val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://www.themoviedb.org").build()

    val logClient = retrofit.create(AvtorizeApi::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)


        enter_welcome2.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    ProfileActivity::class.java
                )
            )
        }

        logClient.createToken().enqueue(object : Callback<CreateRequestTokenData> {
            override fun onFailure(call: Call<CreateRequestTokenData>, t: Throwable) {
                Log.e("login_activity", "Error!")
            }

            override fun onResponse(
                call: Call<CreateRequestTokenData>,
                response: Response<CreateRequestTokenData>
            ) {
                val item = response.body()?.request_token
                if (item != null) {

                    logClien(LoginAutData("WSRtest", "qwerty123", item))
                }
            }
            //WSRtest qwerty123
        })
        searchLogin.addTextChangedListener { editableText: Editable? ->
            if (editableText != null) {
                var login: String = editableText.toString()
            }
        }
        searchPass.addTextChangedListener {
            if (it != null) {
                var pass: String = it.toString()
            }
        }


    }

    private fun recvTocen() {
        var recvestToken = RecvestToken(request_token = "")// token)
        logClient.sessionNew(API_KEY, recvestToken).enqueue(object : Callback<SessionNewData> {
            override fun onFailure(call: Call<SessionNewData>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<SessionNewData>,
                response: Response<SessionNewData>
            ) {

                val item = response.body()
                if (item != null) {

                }
            }

        })
    }

    private fun logClien(loginAutData: LoginAutData) {
       var loginAutData = LoginAutData(password="", username = "", request_token = "")//token)

        logClient.loginAutorizeApi(API_KEY, loginAutData)
            .enqueue(object : Callback<CreateRequestTokenData> {

                override fun onFailure(call: Call<CreateRequestTokenData>, t: Throwable) {
                }

                override fun onResponse(
                    call: Call<CreateRequestTokenData>,
                    response: Response<CreateRequestTokenData>
                ) {
                    val item = response.body()
                    if (item != null) {

                        token = item.request_token

                    }
                }
                //  var sessionNewData = SessionNewData (success , ession_id = "")


            }
            )
    }

}