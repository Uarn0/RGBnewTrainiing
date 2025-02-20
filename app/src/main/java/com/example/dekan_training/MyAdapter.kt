package com.example.dekan_training

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class MyAdapter : RecyclerView.Adapter<MyAdapter.MyViewHolder1>() {
    private var colorList: List<MyColors> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder1 {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return MyViewHolder1(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder1, position: Int) {
        val colorItem = colorList[position]
        holder.titleColorName.text = colorItem.codeColor

        try {
            val color = if (colorItem.codeColor.startsWith("#")) {
                Color.parseColor(colorItem.codeColor)
            } else {
                parseRgbToColor(colorItem.codeColor)
            }
            holder.colorBlock.setBackgroundColor(color)
        } catch (e: IllegalArgumentException) {
            holder.colorBlock.setBackgroundColor(Color.GRAY)
        }

        holder.copyHexButton.setOnClickListener {
            val hexColor = if (colorItem.codeColor.startsWith("#")){
                colorItem.codeColor
            }else{
                rgbToHex(parseRgbToColor(colorItem.codeColor))
            }
            copyToClipBoard(holder.itemView.context, hexColor)
        }

        holder.copyRGBButton.setOnClickListener {
            val rgbColor = if (colorItem.codeColor.startsWith("#")) {
                hexToRgb(colorItem.codeColor)
            } else {
                colorItem.codeColor
            }
            copyToClipBoard(holder.itemView.context, rgbColor)
        }
    }

    private fun hexToRgb(hex: String): String {
        val color = Color.parseColor(hex)
        return "${Color.red(color)}, ${Color.green(color)}, ${Color.blue(color)}"
    }

    override fun getItemCount(): Int {
        return colorList.size
    }

    class MyViewHolder1(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleColorName: TextView = itemView.findViewById(R.id.output_color)
        val colorBlock: View = itemView.findViewById(R.id.color_prev)
        val copyHexButton: Button = itemView.findViewById(R.id.btn_copy_Hex)
        val copyRGBButton: Button = itemView.findViewById(R.id.btn_copy_RGB)
    }

    fun updateList(newList: List<MyColors>) {
        colorList = newList
        notifyDataSetChanged()
    }

    private fun parseRgbToColor(rgbString: String): Int {
        val regex = """(\d+),\s*(\d+),\s*(\d+)""".toRegex()
        val matchResult = regex.find(rgbString)

        return if (matchResult != null) {
            val (r, g, b) = matchResult.destructured
            Color.rgb(r.toInt(), g.toInt(), b.toInt())
        } else {
            Color.GRAY
        }
    }
    private fun rgbToHex(color: Int): String {
        val r = Color.red(color)
        val g = Color.green(color)
        val b = Color.blue(color)
        return String.format("#%02X%02X%02X", r, g, b)
    }
    private fun copyToClipBoard(context: Context, text: String){
        val clipBoard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("Copied Color", text)
        clipBoard.setPrimaryClip(clip)
        Toast.makeText(context, "Copied: $text", Toast.LENGTH_SHORT).show()
    }
}