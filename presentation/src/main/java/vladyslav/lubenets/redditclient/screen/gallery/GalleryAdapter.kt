package vladyslav.lubenets.redditclient.screen.gallery

import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_gallery_page.view.*
import vladyslav.lubenets.redditclient.R
import java.util.*

/**
 * Created by Lubenets Vladyslav on 9/24/18.
 */
class GalleryAdapter(private val images: ArrayList<String>) : PagerAdapter() {

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun getCount(): Int {
        return images.size
    }

    override fun destroyItem(container: ViewGroup, position: Int, view: Any) {
        container.removeView(view as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(container.context)
        val view = inflater.inflate(R.layout.item_gallery_page, container, false)
        Glide
                .with(container.context)
                .load(images[position])
                .apply(RequestOptions()
                        .error(android.R.drawable.stat_notify_error))
                .into(view.ivImage)
        container.addView(view)
        return view
    }
}