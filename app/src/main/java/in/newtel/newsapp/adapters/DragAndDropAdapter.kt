import `in`.newtel.newsapp.R
import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.layout_drag_item.view.*
import java.util.*

class DragAndDropAdapter(
    val context: Context,
    var stringList: MutableList<Uri?>,
    var listener: OnStartDragListener
) : RecyclerView.Adapter<DragAndDropAdapter.MyDragAndDropViewHolder>(), ItemTouchHelperAdapter {
    inner class MyDragAndDropViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyDragAndDropViewHolder {
        return MyDragAndDropViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_drag_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyDragAndDropViewHolder, position: Int) {
        val article = stringList[position]
        holder.itemView.apply {
            Glide.with(this).load(article)
                .placeholder(R.drawable.poster_placeholder)
                .into(imageView)
            item.setOnLongClickListener {
                listener.onStartDrag(holder)
                false
            }
        }
    }

    override fun getItemCount(): Int {
        return stringList.size
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
        Collections.swap(stringList, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
        return true
    }

    override fun onItemDismiss(position: Int) {
        stringList.removeAt(position)
        notifyItemRemoved(position)
    }


}