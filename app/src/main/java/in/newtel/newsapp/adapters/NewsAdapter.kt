package `in`.newtel.newsapp.adapters

import `in`.newtel.newsapp.databinding.ItemArticleRowLayoutBinding
import `in`.newtel.newsapp.models.Article
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.MyViewHolder>() {


    class MyViewHolder(val binding: ItemArticleRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(result: Article) {
            binding.news = result

            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemArticleRowLayoutBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(binding)
            }

        }

    }

    private val differCallback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentRecipe = differ.currentList[position]
        holder.bind(currentRecipe)

        holder.binding.clRoot.setOnClickListener {
            onItemClickListener?.let {
                it(currentRecipe)
            }
        }

    }


    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun setOnItemClickListener(listen: (Article) -> Unit) {
        onItemClickListener = listen
    }

    private var onItemClickListener: ((Article) -> Unit)? = null

}