package miu.edu.foodieapp.ui.planner

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import miu.edu.foodieapp.R

class PlannerAdapter(
    private val list: List<Planner>,
    private val context: Context
) :
    BaseAdapter() {
    private var layoutInflater: LayoutInflater? = null
    private lateinit var nameView: TextView
//    private lateinit var imageView: ImageView
    private lateinit var dayView: TextView
    private lateinit var breakfastView: TextView
    private lateinit var lunchView: TextView
    private lateinit var dinnerView: TextView

    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        if (layoutInflater == null) {
            layoutInflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        }
        if (view == null) {
            view = layoutInflater!!.inflate(R.layout.planner_view, null)
        }
        nameView = view!!.findViewById(R.id.label)
        dayView = view!!.findViewById(R.id.day)
        breakfastView = view!!.findViewById(R.id.breakfast)
        lunchView = view!!.findViewById(R.id.lunch)
        dinnerView = view!!.findViewById(R.id.dinner)
//        imageView = view.findViewById(R.id.image)
//        imageView.setImageResource(list[position].image)
        nameView.text = list[position].name
        dayView.text = list[position].day
        breakfastView.text = list[position].breakfast
        lunchView.text = list[position].lunch
        dinnerView.text = list[position].dinner

        return view
    }

}