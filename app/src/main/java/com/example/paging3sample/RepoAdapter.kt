package com.example.paging3sample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.paging3sample.model.Repo

class RepoAdapter(val itemClick: (html_url: String) -> Unit) :
    PagingDataAdapter<Repo, RepoAdapter.ViewHolder>(POST_COMPARATOR) {
    companion object {
        val POST_COMPARATOR = object : DiffUtil.ItemCallback<Repo>() {
            override fun areContentsTheSame(oldItem: Repo, newItem: Repo): Boolean =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: Repo, newItem: Repo): Boolean =
                oldItem.name == newItem.name
        }
    }

    class ViewHolder(itemView: View, var itemClick: (html_url: String) -> Unit) :
        RecyclerView.ViewHolder(itemView) {

        private val name: TextView = itemView.findViewById(R.id.name_text)
        private val description: TextView = itemView.findViewById(R.id.description_text)
        private val starCount: TextView = itemView.findViewById(R.id.star_count_text)
        private val language: TextView = itemView.findViewById(R.id.language_text)
        private var repo: Repo? = null

        init {
            itemView.setOnClickListener {
                repo?.html_url?.let { it1 -> itemClick(it1) }
            }
        }

        fun bind(repo: Repo?) {
            this.repo = repo
            if (repo != null) {
                name.text = repo.name
                description.text = repo.description
                starCount.text = repo.starCount.toString()
                language.text = repo.language
            }
        }

        fun update(repo: Repo?) {
            this.repo = repo
            starCount.text = repo?.starCount.toString()
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.repo_item, parent, false)


        return ViewHolder(view) {
            itemClick(it)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, payloads: MutableList<Any>) {
        if (payloads.isNotEmpty()) {
            holder.update(getItem(position))
        } else {
            onBindViewHolder(holder, position)
        }
    }

}