package com.example.cinema

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cinema.film.FilmId
import com.example.cinema.film.RaterMOvData
import kotlinx.android.synthetic.main.profile_activity.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProfileActivity : AppCompatActivity() {

    val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
    .baseUrl("https://api.themoviedb.org/3/").build()

    val logClient = retrofit.create(AvtorizeApi::class.java)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_activity)
        //session.text=SessionId.id

        //val profile = AccountItemDetals.accountItem?.userName

        var a = SessionId.id.toString()
        var b = FilmId.id.toString()

           logClient.accountDetals(API_KEY,a).enqueue(object : Callback<GetAccountDetals>{
               override fun onFailure(call: Call<GetAccountDetals>, t: Throwable) {
               }

               override fun onResponse(
                   call: Call<GetAccountDetals>,
                   response: Response<GetAccountDetals>
               ) {
                   if (response.body()!=null){

                       username.text = response.body()!!.name
                       id_dult.text = response.body()!!.include_adult.toString()
                       lang.text=response.body()!!.iso_3166_1
                       coun.text=response.body()!!.iso_639_1
                   }
               }



           })//возможно нужно добавть сессион айди, в autorize тоже

        logClient.raterMovie(API_KEY,b)

        }


    }

