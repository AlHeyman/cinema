package com.example.cinema

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.cinema.FilmId.id
import com.example.cinema.film.RaterMOvData
import kotlinx.android.synthetic.main.film_recomend_item.*
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
        var fil = FilmId.id.toString()
       // var account = RaterMOvData.id.toString()
//       var lang =SessionId.iso_639_1.toString()
//        var sort = SessionId.sort_by.toString()
//        var pag = SessionId.page.toString().toInt()




        logClient.accountDetals(API_KEY, a).enqueue(object : Callback<GetAccountDetals> {
            override fun onFailure(call: Call<GetAccountDetals>, t: Throwable) {
                println()
            }

            override fun onResponse(
                call: Call<GetAccountDetals>,
                response: Response<GetAccountDetals>
            ) {
                if (response.body() != null) {
                    id = response.body()!!.id.toString().toInt()
                    username.text = response.body()!!.name
                    id_dult.text = response.body()!!.include_adult.toString()
                    langu.text = response.body()!!.iso_3166_1
                    coun.text = response.body()!!.iso_639_1
                }
            }


        })//возможно нужно добавть сессион айди, в autorize тоже

        logClient.raterMovie(fil,API_KEY ,a ).enqueue(object : Callback<RaterMOvData> {
            override fun onResponse(call: Call<RaterMOvData>, response: Response<RaterMOvData>) {
                if (response.body() != null) {

                    FilmId.id = response.body()!!.id.toString().toInt()
                    title_film.text = response.body()!!.original_title
                    date_film.text = response.body()!!.release_date
                    println()
                }
            }

            override fun onFailure(call: Call<RaterMOvData>, t: Throwable) {
                Log.e("logClient", "onFailurew")
                println()
            }

        })

    }


}

