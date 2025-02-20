
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dekan_training.MyAdapter
import com.example.dekan_training.R

class HistoryFragment : Fragment() {
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_history, container, false)
        val customAdapter = MyAdapter()

        val recyclerView: RecyclerView = view.findViewById(R.id.recycleViewHistory)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = customAdapter
        sharedViewModel.colors.observe(viewLifecycleOwner) {
            sharedViewModel.colors.value?.let { it1 -> customAdapter.updateList(it1) }
        }
        return view
    }
}
