package es.usj.section_1.adrian.appseptember.Adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter


class ViewPagerAdapter(list: ArrayList<Fragment>, fm: FragmentManager, lifecycle: Lifecycle)
    : FragmentStateAdapter(fm,lifecycle) {

    val fragmentList= list
    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }

}

/*

// this is for fragment tabs
    override fun getItem(position: Int): Fragment {
        /*when (position) {
            0 -> {
                //  val homeFragment: HomeFragment = HomeFragment()
                return Movies()
            }
            1 -> {
                return Genre()
            }
            2 -> {
                // val movieFragment = MovieFragment()
                return Actors()
            }
            else ->
                return Movies()
        }
    }*/


    // this counts total number of tabs
    override fun getCount(): Int {
        return totalTabs
    }
}

 */