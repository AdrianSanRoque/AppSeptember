package es.usj.section_1.adrian.appseptember.Movies.helpers

import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import es.usj.section_1.adrian.appseptember.Models.Movie

class MoviesAdapter(
    val clickListener: ArrayList<Movie>,
    fragmentActivity: FragmentActivity


):  androidx.recyclerview.widget.ListAdapter<Movie,
        RecyclerView.ViewHolder>(MoviesDiffCallBack()) {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return MoviesViewHolder.from(parent)
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
       holder.bind(getItem(position)!!,clickListener)



    /*when(holder){
           is MoviesViewHolder -> {
               val item = getItem(position) as Movie

               holder.bind(item, clickListener)
           }
       }

          */

    }

    class MoviesViewHolder private constructor(val binding:) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Movie, clickListener: es.usj.section_1.adrian.appseptember.Movies.helpers.MoviesListener)
        {
            binding.Cli
        }


    }


    class MoviesDiffCallBack: DiffUtil.ItemCallback<Movie>(){

        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.rank == newItem.rank
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return newItem.equals(oldItem)
        }
    }

    class MoviesListener(val clickListener: (movieId: String) -> Unit) {
        fun onclick(movieDetails: Movie) = clickListener(movieDetails.rank.toString())
    }



}









/**/
