package com.example.cinema.login

import android.content.Intent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.os.Bundle
import android.util.Log

import androidx.appcompat.app.AppCompatActivity
import com.example.cinema.*
import kotlinx.android.synthetic.main.login_activity.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity() {


    //private lateinit var token: String


    val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://api.themoviedb.org/3/").build()


    val logClient = retrofit.create(AvtorizeApi::class.java)

    var a = ""
    var b = ""

    var login = ""
    var pass = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)



//        searchLogin.addTextChangedListener { editableText: Editable? ->
//            if (editableText != null) {
//                a = editableText.toString()
//            }
//        }
//        searchPass.addTextChangedListener {
//            if (it != null) {
//                b = it.toString()
//            }
//        }



        enter_welcome2.setOnClickListener {
//            login = a
//            pass = b
            login = "WSRtest"
            pass = "qwerty123"

            firstToken(login,pass)
        }

    }

    private fun firstToken(login:String,pass:String) {

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

                    logClien(LoginAutData(login, pass, item!!))

                }
            }
        })
    }

    private fun logClien(loginAutData: LoginAutData) {


        logClient.loginAutorizeApi(API_KEY, loginAutData)
            .enqueue(object : Callback<CreateRequestTokenData> {
                override fun onFailure(call: Call<CreateRequestTokenData>, t: Throwable) {
                }

                override fun onResponse(
                    call: Call<CreateRequestTokenData>,
                    response: Response<CreateRequestTokenData>
                ) {
                    val item = response.body()?.request_token
                    if (item != null) {
                        var tok = RecvestToken(item)
                        recvTocen(tok)
                    }
                }
            }
            )
    }


    private fun recvTocen(token: RecvestToken) {

        logClient.sessionNew(API_KEY, token).enqueue(object : Callback<SessionNewData> {
            override fun onFailure(call: Call<SessionNewData>, t: Throwable) {
                println()
            }

            override fun onResponse(
                call: Call<SessionNewData>,
                response: Response<SessionNewData>
            ) {

                if (response.body() != null) {
                    SessionId.id = response.body()!!.session_id
                   startActivity(Intent(this@LoginActivity, ProfileActivity::class.java))
                }
            }

        })
    }
}