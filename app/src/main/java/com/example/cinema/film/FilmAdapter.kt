package com.example.cinema.film

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cinema.R
import kotlinx.android.synthetic.main.film_recomend_item.view.*

class FilmAdapter(var list: MutableList<Result>) : RecyclerView.Adapter<FilmAdapter.RvViev>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvViev {
        Log.d("RvAdapter", "onCreateViewHolder")
        return RvViev(
            LayoutInflater.from(parent.context).inflate(R.layout.film_recomend_item, parent, false)
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RvViev, position: Int) {
        val film = list[position]
        holder.itemView.date_film.text = film.release_date
        holder.itemView.title_film.text = film.original_title
    }

    class RvViev(view: View) : RecyclerView.ViewHolder(view)
}
