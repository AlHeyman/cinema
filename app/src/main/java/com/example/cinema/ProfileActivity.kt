package com.example.cinema

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cinema.film.FilmAdapter
import com.example.cinema.film.RaterMOvData
import com.example.cinema.film.Result
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

        rv.layoutManager = LinearLayoutManager(this)


        // var account = RaterMOvData.id.toString()
//       var lang =SessionId.iso_639_1.toString()
//        var sort = SessionId.sort_by.toString()
//        var pag = SessionId.page.toString().toInt()


        var list: MutableList<Result> = ArrayList()

//        var filmAd: FilmAdapter = FilmAdapter(list)
//
//        rv.adapter = filmAd




        logClient.accountDetals(API_KEY, a).enqueue(object : Callback<GetAccountDetals> {
            override fun onFailure(call: Call<GetAccountDetals>, t: Throwable) {
                println()
            }

            override fun onResponse(
                call: Call<GetAccountDetals>,
                response: Response<GetAccountDetals>
            ) {
                if (response.body() != null) {
                    var bf = response.body()!!.id

                    username.text = response.body()!!.name
                    id_dult.text = response.body()!!.include_adult.toString()
                    langu.text = response.body()!!.iso_3166_1
                    coun.text = response.body()!!.iso_639_1

                    if (bf != null) xyu(bf, API_KEY, a)


                }
            }


        })//возможно нужно добавть сессион айди, в autorize тоже

//9847177
    }

    private fun xyu(id: Int, API_KEY: String, a: String) {
        logClient.raterMovie(id, API_KEY, a).enqueue(object : Callback<RaterMOvData> {
            override fun onFailure(call: Call<RaterMOvData>, t: Throwable) {
                println()
            }

            override fun onResponse(call: Call<RaterMOvData>, response: Response<RaterMOvData>) {
                if (response.body() != null) {

                    rv.adapter = FilmAdapter(response.body()!!.results.toMutableList())

//
//                  FilmId.idi = response.body()!!.results[0].id.toString().toInt()


//                    title_film.text = response.body()!!.results[0].original_title
//
//
//
//                    date_film.text = response.body()!!.results[0].release_date
                    println()
                }
            }


//            override fun onFailure(call: Call<RaterMOvData>, t: Throwable) {
//                Log.e("logClient", "onFailurew")
//                println()
//            }
//
//            override fun onResponse(call: Call<RaterMOvData>, response: Response<Result>) {
//                if (response.body() != null) {
////
////                  FilmId.idi = response.body()!!.id.toString().toInt()
//                    title_film.text = response.body()!!.original_title
//                    date_film.text = response.body()!!.release_date
//                    println()
//                }


        })
    }


}




