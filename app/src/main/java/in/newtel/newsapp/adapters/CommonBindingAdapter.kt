package `in`.newtel.newsapp.adapters

import `in`.newtel.newsapp.R
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("imageUrlCache", "placeholder")
fun loadImageCache(view: AppCompatImageView, imageUrlCache: String?, placeholder: Int) {

    Glide.with(view.context)
        .load(imageUrlCache)
        .apply(
            RequestOptions()
                .placeholder(placeholder)
                .fallback(placeholder)
                .error(placeholder)
                .centerInside()
        )
        .into(view)
}

@BindingAdapter("app:loadImageUrlData")
fun loadImageUrlData(view: AppCompatImageView, url: String?) {
    url?.let {
        Glide.with(view.context).load(url)
            .centerInside()
            .placeholder(R.drawable.poster_placeholder)
            .into(view)
    }

}