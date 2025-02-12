import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.dekan_training.R

class HexInput : Fragment() {
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_hex_input, container, false)

        val inputHex = view.findViewById<EditText>(R.id.input_hex)
        val btnApply = view.findViewById<Button>(R.id.btn_apply_color)
        val outputHex = view.findViewById<TextView>(R.id.output_hex)
        val outputRgb = view.findViewById<TextView>(R.id.output_rgb)
        val colorPreview = view.findViewById<View>(R.id.color_preview)

        btnApply.setOnClickListener {
            val hex = inputHex.text.toString().trim()
            if (hex.matches(Regex("^#?[0-9A-Fa-f]{6}$"))) {
                val cleanHex = if (hex.startsWith("#")) hex else "#$hex"
                val color = Color.parseColor(cleanHex)

                val r = Color.red(color)
                val g = Color.green(color)
                val b = Color.blue(color)

                outputHex.text = "HEX: $cleanHex"
                outputRgb.text = "RGB: ($r, $g, $b)"
                colorPreview.setBackgroundColor(color)

                sharedViewModel.addColor("HEX: $cleanHex  |  RGB: ($r, $g, $b)")
            } else {
                outputHex.text = "Invalid HEX!"
                outputRgb.text = ""
            }
        }

        return view
    }
}
