package com.example.dekan_training

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment

class rgb_input : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_rgb_decimal, container, false)

        val inputR = view.findViewById<EditText>(R.id.input_r)
        val inputG = view.findViewById<EditText>(R.id.input_g)
        val inputB = view.findViewById<EditText>(R.id.input_b)
        val btnApply = view.findViewById<Button>(R.id.btn_apply_rgb)
        val outputRgb = view.findViewById<TextView>(R.id.output_rgb)
        val outputHex = view.findViewById<TextView>(R.id.output_hex)
        val colorPreview = view.findViewById<View>(R.id.color_preview)

        btnApply.setOnClickListener {
            val r = inputR.text.toString().toIntOrNull() ?: -1
            val g = inputG.text.toString().toIntOrNull() ?: -1
            val b = inputB.text.toString().toIntOrNull() ?: -1

            if (r in 0..255 && g in 0..255 && b in 0..255) {
                val color = Color.rgb(r, g, b)
                val hexColor = String.format("#%02X%02X%02X", r, g, b)

                outputRgb.text = "RGB: ($r, $g, $b)"
                outputHex.text = "HEX: $hexColor"

                colorPreview.setBackgroundColor(color)
            } else {
                outputRgb.text = "Invalid RGB values!"
                outputHex.text = ""
            }
        }

        return view
    }
}
