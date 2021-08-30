package es.usj.section_1.adrian.appseptember.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import es.usj.section_1.adrian.appseptember.Fragments.ActorsFragment
import es.usj.section_1.adrian.appseptember.Models.Genre
import es.usj.section_1.adrian.appseptember.R

class ActorsAdapter (
    private val itemList: ArrayList<Genre>,
    val context: Context,
):  RecyclerView.Adapter<ActorsAdapter.GenreViewHolder>() {

    class GenreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val name: TextView = itemView.findViewById(R.id.genre_name)
        val total_movies: TextView = itemView.findViewById(R.id.total_movies)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(
            R.layout.genre_item,
            parent,
            false
        )
        return GenreViewHolder(v)
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.name.setText(itemList.get(position).name)
        holder.total_movies.setText(itemList.get(position).number)

        /*holder.itemView.setOnClickListener {
            val it = Intent(context, ActorsFragment::class.java)
            it.putExtra("name", itemList.get(position).name)
            context.startActivity(it)
        }

         */
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}