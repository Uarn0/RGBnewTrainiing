package com.example.dekan_training

import android.annotation.SuppressLint
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
import com.example.dekan_training.MyColors.Companion.parseRgbToColor

class MyAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var colorList: List<MyColors> = emptyList()

    companion object {
        private const val TYPE_HEX = 0
        private const val TYPE_RGB = 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (colorList[position].isHex()) TYPE_HEX else TYPE_RGB
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_HEX -> MyViewHolderHEX(inflater.inflate(R.layout.list_item_hex, parent, false))
            TYPE_RGB -> MyViewHolderRGB(inflater.inflate(R.layout.list_item_rgb, parent, false))
            else -> throw IllegalArgumentException("Unknown view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val colorItem = colorList[position]

        when (holder) {
            is MyViewHolderHEX -> {
                holder.titleColorNameHEX.text = (colorItem as InterfaceHex).getHex()
                val color = Color.parseColor(colorItem.getHex())
                holder.colorBlockHEX.setBackgroundColor(color)
                holder.copyHexButtonHEX.setOnClickListener {
                    copyToClipBoard(holder.itemView.context, colorItem.getHex())
                }
            }
            is MyViewHolderRGB -> {
                holder.titleColorNameRGB.text = (colorItem as InterfaceRGB).getRGB()
                val color = parseRgbToColor(colorItem.getRGB())
                holder.colorBlockRGB.setBackgroundColor(color)
                holder.copyRGBButtonRGB.setOnClickListener {
                    copyToClipBoard(holder.itemView.context, colorItem.getRGB())
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return colorList.size
    }

    class MyViewHolderHEX(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleColorNameHEX: TextView = itemView.findViewById(R.id.output_color)
        val colorBlockHEX: View = itemView.findViewById(R.id.color_prev)
        val copyHexButtonHEX: Button = itemView.findViewById(R.id.btn_copy_Hex)
    }

    class MyViewHolderRGB(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleColorNameRGB: TextView = itemView.findViewById(R.id.output_color_rgb)
        val colorBlockRGB: View = itemView.findViewById(R.id.color_prev_rgb)
        val copyRGBButtonRGB: Button = itemView.findViewById(R.id.btn_copy_RGB)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<MyColors>) {
        colorList = newList
        notifyDataSetChanged()
    }

    private fun copyToClipBoard(context: Context, text: String){
        val clipBoard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("Copied Color", text)
        clipBoard.setPrimaryClip(clip)
        Toast.makeText(context, "Copied: $text", Toast.LENGTH_SHORT).show()
    }
}