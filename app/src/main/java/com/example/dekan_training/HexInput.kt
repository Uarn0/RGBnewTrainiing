package com.example.dekan_training

import SharedViewModel
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.dekan_training.databinding.FragmentHexInputBinding

class HexInput : Fragment() {
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private var _binding: FragmentHexInputBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHexInputBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val inputHex = binding.inputHex
        val btnApply = binding.btnApplyColor
        val outputHex = binding.outputHex
        val outputRgb = binding.outputRgb
        val colorPreview = binding.colorPreview

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

                sharedViewModel.addColor(cleanHex)
            } else {
                outputHex.text = "Invalid HEX!"
                outputRgb.text = ""
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
