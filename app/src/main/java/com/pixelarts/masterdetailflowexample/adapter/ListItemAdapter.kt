package com.pixelarts.masterdetailflowexample.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pixelarts.masterdetailflowexample.R
import com.pixelarts.masterdetailflowexample.common.GlideApp
import com.pixelarts.masterdetailflowexample.model.Collection

class ListItemAdapter(private val listener: OnItemClickedListener):
    ListAdapter<Collection, ListItemAdapter.ViewHolder>(DIFF_CALLBACK) {
    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemAdapter.ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_list_content, parent, false)
        return ViewHolder(view, context)
    }

    override fun onBindViewHolder(holder: ListItemAdapter.ViewHolder, position: Int) {
        val collection = getItem(position)
        holder.apply {
            setContent(collection)
        }

        holder.itemView.setOnClickListener {
            listener.itemClickedListener(position)
        }
    }

    class ViewHolder(itemView: View, private val context: Context): RecyclerView.ViewHolder(itemView){
        val headLine: TextView = itemView.findViewById(R.id.tvHeadline)
        val description: TextView = itemView.findViewById(R.id.tvDescrption)
        val author: TextView = itemView.findViewById(R.id.tvAuthor)
        val ratingBar: RatingBar = itemView.findViewById(R.id.ratingBar)
        val articleImage: ImageView = itemView.findViewById(R.id.ivArticleImage)

        fun setContent(collection: Collection){
            headLine.text = collection.headline
            description.text = collection.description
            author.text = collection.author.name
            ratingBar.rating = collection.ratings.toFloat()

            GlideApp.with(context).load(collection.pictureUrl)
                .centerCrop()
                .override(500, 380)
                //.optionalCenterCrop()
                .into(articleImage)
        }
    }

    interface OnItemClickedListener{
        fun itemClickedListener(position: Int)
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<Collection> = object : DiffUtil.ItemCallback<Collection>(){
            override fun areItemsTheSame(oldItem: Collection, newItem: Collection): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Collection, newItem: Collection): Boolean {
                return oldItem == newItem
            }
        }
    }
}