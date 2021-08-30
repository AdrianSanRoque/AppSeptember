package es.usj.section_1.adrian.appseptember.Movies.helpers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import es.usj.section_1.adrian.appseptember.Models.Movie
import es.usj.section_1.adrian.appseptember.R
import io.github.florent37.shapeofview.shapes.CircleView

class MoviesViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){

    val thumbImage: ImageView = itemView.findViewById(R.id.thumb_image)
    val title: TextView = itemView.findViewById(R.id.movieTitle)
    val movieYear: TextView = itemView.findViewById(R.id.yearText)
    val edit: ImageView = itemView.findViewById(R.id.edit_option)
    val editLayout: CircleView = itemView.findViewById(R.id.editLayout)



    fun bind(tastyOne: Movie, clickListener: MoviesListener){
        binding = tastyOne

        //pass the clicklistener from the adapter to the viewholder
        binding.cli = clickListener

        binding.executePendingBindings()
    }

    companion object{
        fun from(parent: ViewGroup): MoviesViewHolder {
            //make layoutinflater based on parent.context
            val layoutInflater = LayoutInflater.from(parent.context)

            //use binding obj to inflate layout
            //false so RV adds the item when needed
            val binding = ListItemHistoryBinding.inflate(layoutInflater, parent, false )

            //pass binding to viewholder
            return MoviesViewHolder(binding)
        }

}












/*class MoviesViewHolder(val binding: ListI)
    :RecyclerView.ViewHolder(binding.root){




}

}






/*



/*
* class HistoryViewHolder private constructor(val binding: ListItemHistoryBinding)
    :RecyclerView.ViewHolder(binding.root){

    fun bind(tastyOne: TastyOneUIModel, clickListener: HistoryListener){
        binding.tastyOne = tastyOne

        //pass the clicklistener from the adapter to the viewholder
        binding.clickListener = clickListener

        binding.executePendingBindings()
    }

    companion object{
        fun from(parent: ViewGroup): HistoryViewHolder {
            //make layoutinflater based on parent.context
            val layoutInflater = LayoutInflater.from(parent.context)

            //use binding obj to inflate layout
            //false so RV adds the item when needed
            val binding = ListItemHistoryBinding.inflate(layoutInflater, parent, false )

            //pass binding to viewholder
            return HistoryViewHolder(binding)
        }
    }
}*/*/