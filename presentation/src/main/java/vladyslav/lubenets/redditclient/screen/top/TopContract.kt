package vladyslav.lubenets.redditclient.screen.top

import com.trello.rxlifecycle2.LifecycleTransformer
import vladyslav.lubenets.domain.entity.TopItemModel.Preview.Images
import vladyslav.lubenets.domain.entity.TopListModel
import vladyslav.lubenets.domain.rx.SimpleObserver
import vladyslav.lubenets.redditclient.components.base.BaseViewModel

/**
 * Created by Lubenets Vladyslav on 9/23/18.
 */
interface TopContract {

    interface ViewModel : BaseViewModel {

        fun loadFirstPage(
                observer: SimpleObserver<TopListModel>,
                lifecycleTransformer: LifecycleTransformer<TopListModel>?)

        fun nextPage(observer: SimpleObserver<TopListModel>, nextPageToken: String?,
                     lifecycleTransformer: LifecycleTransformer<TopListModel>?)

    }

    interface Navigator {
        fun toGallery(controller: TopController, images: List<Images>)
    }

}