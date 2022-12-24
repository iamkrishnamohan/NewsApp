package `in`.newtel.newsapp.ui

import `in`.newtel.newsapp.R
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_article.*

class ArticleFragment : Fragment(R.layout.fragment_article) {
    val args: ArticleFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val article = args.article
        Log.e("TAG", "onViewCreated: Article is ${article.title} ")
        webView.apply {
            webViewClient = WebViewClient()
            loadUrl(article.url)
        }


    }
}