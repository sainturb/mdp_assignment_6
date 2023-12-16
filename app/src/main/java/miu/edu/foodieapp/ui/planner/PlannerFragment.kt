package miu.edu.foodieapp.ui.planner

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.GridView
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import miu.edu.foodieapp.R
import miu.edu.foodieapp.databinding.FragmentPlannerBinding

class PlannerFragment : Fragment() {

    private var _binding: FragmentPlannerBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val weekDays = listOf<String>("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")

    private val r = Planner("my plan", R.drawable.wallemart, "Monday", "Bread & Butter", "Pizza", "Soup")
    private val list = mutableListOf<Planner>(r)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentPlannerBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val adapter = PlannerAdapter(list, root.context)
        val grid = root.findViewById<GridView>(R.id.plannerGrid)
        grid.adapter = adapter

        var available = getAvailableDays()

        val button = root.findViewById<FloatingActionButton>(R.id.plannerPlus)

        button.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            val inflaterDialog = layoutInflater
            builder.setTitle("Create a plan")
            builder.setMessage("Select your week day and plan it")
            val dialogLayout = inflaterDialog.inflate(R.layout.plan_add, null)
            builder.setView(dialogLayout)

            val titleText  = dialogLayout.findViewById<Spinner>(R.id.formDay)
            val labelText  = dialogLayout.findViewById<EditText>(R.id.formLabel)
            val breakfastText  = dialogLayout.findViewById<EditText>(R.id.formBreakfast)
            val lunchText  = dialogLayout.findViewById<EditText>(R.id.formLunch)
            val dinnerText  = dialogLayout.findViewById<EditText>(R.id.formDinner)

            val adapterMeasures = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item,
                available
            )
            titleText.adapter = adapterMeasures

            builder.setPositiveButton("Create") { _, _ ->
                val plan = Planner(labelText.text.toString(), R.drawable.wallemart, available[titleText.selectedItemPosition], breakfastText.text.toString(), lunchText.text.toString(), dinnerText.text.toString())
                list.add(plan)
                Toast.makeText(requireContext(), "Plan is successfully added", Toast.LENGTH_SHORT).show()
                available = getAvailableDays()
//                val success = blogListViewModel.insertBlog(titleText.text.toString(), contentText.text.toString(), tags, user, 0)
//                if (success) {
//                    titleText.text.clear()
//                    labelText.text.clear()
//                    breakfastText.text.clear()
//                    lunchText.text.clear()
//                    dinnerText.text.clear()
//                    Toast.makeText(requireContext(), "Plan is successfully added", Toast.LENGTH_SHORT).show()
//                } else {
//                    Toast.makeText(requireContext(), "Fill out required fields before you create a plan", Toast.LENGTH_SHORT).show()
//                }
            }
            builder.show()
        }

        return root
    }

    private fun getAvailableDays(): List<String> {
        val days = list.map { it.day }
        return weekDays.filter { !days.contains(it) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}