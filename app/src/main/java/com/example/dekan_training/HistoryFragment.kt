import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.dekan_training.R

class HistoryFragment : Fragment() {
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_history, container, false)
        val historyText = view.findViewById<TextView>(R.id.history_text)

        sharedViewModel.colors.observe(viewLifecycleOwner) { colorsList ->
            historyText.text = colorsList.joinToString("\n")
        }

        return view
    }
}
