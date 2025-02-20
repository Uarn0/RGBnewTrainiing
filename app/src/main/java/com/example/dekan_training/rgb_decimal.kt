package com.example.dekan_training

import SharedViewModel
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.dekan_training.databinding.FragmentRgbDecimalBinding

class RGBInput : Fragment() {
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private var _binding : FragmentRgbDecimalBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentRgbDecimalBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val inputR = binding.inputR
        val inputG = binding.inputG
        val inputB = binding.inputB
        val btnApply = binding.btnApplyRgb
        val outputRgb = binding.outputRgb
        val outputHex = binding.outputHex
        val colorPreview = binding.colorPreview

        btnApply.setOnClickListener {
            val r = inputR.text.toString().toIntOrNull() ?: -1
            val g = inputG.text.toString().toIntOrNull() ?: -1
            val b = inputB.text.toString().toIntOrNull() ?: -1

            if (r in 0..255 && g in 0..255 && b in 0..255) {
                val hexColor = String.format("#%02X%02X%02X", r, g, b)
                val rgbString = "RGB: ($r, $g, $b)"
                val rgb = "$r,$g,$b"
                outputRgb.text = rgbString
                outputHex.text = "HEX: $hexColor"
                colorPreview.setBackgroundColor(Color.rgb(r, g, b))

                sharedViewModel.addColor(rgb)
            } else {
                outputRgb.text = "Invalid RGB values!"
                outputHex.text = ""
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
