package vladyslav.lubenets.redditclient.screen.gallery

import com.trello.rxlifecycle2.LifecycleTransformer
import vladyslav.lubenets.domain.interactor.LocalImagePathCase
import vladyslav.lubenets.domain.rx.SimpleObserver
import vladyslav.lubenets.redditclient.components.base.BaseViewModelImpl
import javax.inject.Inject

/**
 * Created by Lubenets Vladyslav on 9/24/18.
 */
class GalleryViewModel @Inject constructor(
        private val localImagePathCase: LocalImagePathCase) : GalleryContract.ViewModel, BaseViewModelImpl() {

    override fun provideLocalImagePath(url: String,
                                       observer: SimpleObserver<String>,
                                       lifecycleTransformer: LifecycleTransformer<String>?) {
        localImagePathCase.execute(observer, url, lifecycleTransformer)
    }

    override fun onDestroy() {
        localImagePathCase.dispose()
    }

}