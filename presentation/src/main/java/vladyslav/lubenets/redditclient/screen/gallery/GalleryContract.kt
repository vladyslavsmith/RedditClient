package vladyslav.lubenets.redditclient.screen.gallery

import com.trello.rxlifecycle2.LifecycleTransformer
import vladyslav.lubenets.domain.rx.SimpleObserver
import vladyslav.lubenets.redditclient.components.base.BaseViewModel

/**
 * Created by Lubenets Vladyslav on 9/24/18.
 */
class GalleryContract {

    interface ViewModel : BaseViewModel {
        fun provideLocalImagePath(url: String,
                                  observer: SimpleObserver<String>,
                                  lifecycleTransformer: LifecycleTransformer<String>?)
    }

    interface Navigator


}