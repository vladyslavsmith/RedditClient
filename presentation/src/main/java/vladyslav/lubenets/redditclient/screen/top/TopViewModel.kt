package vladyslav.lubenets.redditclient.screen.top

import com.trello.rxlifecycle2.LifecycleTransformer
import vladyslav.lubenets.domain.entity.TopListModel
import vladyslav.lubenets.domain.interactor.FetchTopCase
import vladyslav.lubenets.domain.rx.SimpleObserver
import vladyslav.lubenets.redditclient.components.base.BaseViewModelImpl
import javax.inject.Inject

/**
 * Created by Lubenets Vladyslav on 9/23/18.
 */
class TopViewModel @Inject constructor(
        private val fetchTopCase: FetchTopCase) : TopContract.ViewModel, BaseViewModelImpl() {

    override fun loadFirstPage(
            observer: SimpleObserver<TopListModel>,
            lifecycleTransformer: LifecycleTransformer<TopListModel>?) {
        fetchTopCase.execute(observer, null, lifecycleTransformer)
    }

    override fun nextPage(observer: SimpleObserver<TopListModel>,
                          nextPageToken: String?,
                          lifecycleTransformer: LifecycleTransformer<TopListModel>?) {
        fetchTopCase.execute(observer, nextPageToken, lifecycleTransformer)
    }

    override fun onDestroy() {
        fetchTopCase.dispose()
    }

}