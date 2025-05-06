package com.example.utsmobile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class AdapterList (private val itemList: kotlin.collections.List<ItemList>):RecyclerView.Adapter<AdapterList.ViewHolder>() {
    // Menyimpan referensi ke view pada layout item_data.xml
    class ViewHolder (@NonNull itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView. findViewById(R.id.item_image)
        val judul: TextView = itemView.findViewById(R.id.title)
        val subJudul: TextView = itemView.findViewById(R.id.sub_title)
    }
    // Membuat instance ViewHolder baru saat RecyclerView membutuhkan tampilan baru
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterList.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_data, parent, false)
        return ViewHolder (view)
    }
    // Mengikat (bind) data dari itemList[position] ke tampilan di holder.
    override fun onBindViewHolder(holder: AdapterList.ViewHolder, position: Int) {
        val item = itemList [position]
        holder. judul.text = item.judul
        holder.subJudul.text = item.subJudul
        Glide.with(holder.imageView.context).load(item.imageUrl).into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}