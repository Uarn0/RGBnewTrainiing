package com.example.dekan_training

import android.graphics.Color

interface InterfaceHex {
    fun getHex(): String
}
interface InterfaceRGB {
    fun getRGB(): String
}

data class MyColors(var codeColor: String) : InterfaceHex, InterfaceRGB {

    override fun getHex(): String {
        return if (codeColor.startsWith("#")) codeColor else rgbToHex(parseRgbToColor(codeColor))
    }

    override fun getRGB(): String {
        return if (codeColor.startsWith("#")) hexToRgb(codeColor) else codeColor
    }

    fun isHex(): Boolean = codeColor.startsWith("#")

    companion object {
        fun rgbToHex(color: Int): String {
            val r = Color.red(color)
            val g = Color.green(color)
            val b = Color.blue(color)
            return String.format("#%02X%02X%02X", r, g, b)
        }

        fun hexToRgb(hex: String): String {
            val color = Color.parseColor(hex)
            return "${Color.red(color)}, ${Color.green(color)}, ${Color.blue(color)}"
        }

        fun parseRgbToColor(rgbString: String): Int {
            val regex = """(\d+),\s*(\d+),\s*(\d+)""".toRegex()
            val matchResult = regex.find(rgbString)

            return if (matchResult != null) {
                val (r, g, b) = matchResult.destructured
                Color.rgb(r.toInt(), g.toInt(), b.toInt())
            } else {
                Color.GRAY
            }
        }
    }
}
