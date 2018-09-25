package vladyslav.lubenets.redditclient.screen.top

import com.bluelinelabs.conductor.RouterTransaction
import vladyslav.lubenets.domain.entity.TopItemModel
import vladyslav.lubenets.redditclient.screen.gallery.GalleryController
import java.util.ArrayList
import javax.inject.Inject

/**
 * Created by Lubenets Vladyslav on 9/23/18.
 */
class TopNavigator @Inject constructor() : TopContract.Navigator {

    override fun toGallery(controller: TopController, images: List<TopItemModel.Preview.Images>) {
        val imageUrls = images
                .filter { it.source != null && it.source.url != null }
                .map {
                    it.source.url
                }
        val imageUrlsList = ArrayList(imageUrls)
        controller.router.pushController(
                RouterTransaction.with(GalleryController.newInstance(imageUrlsList)))
    }

}