package vladyslav.lubenets.redditclient.screen.top

import android.support.v7.widget.RecyclerView
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.github.marlonlom.utilities.timeago.TimeAgo
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_top_list.*
import vladyslav.lubenets.domain.entity.TopItemModel
import vladyslav.lubenets.domain.entity.TopItemWrapperModel
import vladyslav.lubenets.redditclient.R
import vladyslav.lubenets.domain.entity.TopItemModel.Preview.Images
import java.util.concurrent.TimeUnit

/**
 * Created by Lubenets Vladyslav on 9/23/18.
 */
class TopAdapter : RecyclerView.Adapter<TopAdapter.Holder>() {

    private val items: MutableList<TopItemWrapperModel> = arrayListOf()

    var thumbnailClickListener = { _: List<Images> -> }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_top_list, parent,
                false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(items[position])
    }

    fun addItems(children: List<TopItemWrapperModel>) {
        items.addAll(children)
        notifyDataSetChanged()
    }

    inner class Holder(override val containerView: View) :
            RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(itemWrapper: TopItemWrapperModel) {
            val context = containerView.context
            val item = itemWrapper.data
            tvAuthor.text = item.author
            tvCreatedDate.text = TimeAgo.using( TimeUnit.MILLISECONDS.convert(item.createdUtc,
                    TimeUnit.SECONDS))
            tvTitle.text = item.title
            tvCommentsCounter.text = item.numComments.toString()
            ivThumbnail.setOnClickListener { thumbnailClickListener.invoke(item.preview.images) }
            val imageUrl = provideImageUrl(item)
            if (imageUrl === null) {
                ivThumbnail.visibility = GONE
            } else {
                ivThumbnail.visibility = VISIBLE
                Glide.with(context)
                        .load(imageUrl)
                        .into(ivThumbnail)
            }
        }

        private fun provideImageUrl(item: TopItemModel): String? {
            val thumbnail = item.thumbnail
            val isValidUrl = Patterns.WEB_URL.matcher(thumbnail).matches()
            if (isValidUrl) {
                return thumbnail
            }
            val imagesPreview = item.preview
            if (imagesPreview != null &&
                    imagesPreview.images != null &&
                    imagesPreview.images.isNotEmpty()) {
                return imagesPreview.images[0].source.url
            }
            return null
        }
    }
}