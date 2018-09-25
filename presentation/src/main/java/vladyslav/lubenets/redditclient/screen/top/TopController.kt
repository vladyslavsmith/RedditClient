package vladyslav.lubenets.redditclient.screen.top

import android.os.Bundle
import android.os.Parcelable
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.Toast
import com.trello.rxlifecycle2.LifecycleTransformer
import vladyslav.lubenets.domain.entity.TopItemModel
import vladyslav.lubenets.domain.entity.TopListModel
import vladyslav.lubenets.domain.rx.SimpleObserver
import vladyslav.lubenets.redditclient.R
import vladyslav.lubenets.redditclient.components.base.BaseController
import vladyslav.lubenets.redditclient.components.base.viewFactory
import kotlinx.android.synthetic.main.top_controller.view.*
import vladyslav.lubenets.redditclient.components.controller.setToolbar
import vladyslav.lubenets.redditclient.components.list.RecyclerItemsView
import vladyslav.lubenets.redditclient.di.component.DaggerTopScreenComponent
import javax.inject.Inject

/**
 * Created by Lubenets Vladyslav on 9/23/18.
 */
class TopController(bundle: Bundle? = Bundle()) : BaseController<TopContract.ViewModel>(bundle) {

    @Inject
    override lateinit var viewModel: TopContract.ViewModel
    @Inject
    lateinit var navigator: TopContract.Navigator

    private var adapter: TopAdapter? = null
    private var currentPageNumber = 0
    private var nextPageToken: String? = null
    override val controllerView by viewFactory(R.layout.top_controller)

    override fun initializeInjector() {
        DaggerTopScreenComponent.builder()
                .applicationComponent(provideApplicationComponent())
                .build()
                .inject(this)
    }

    override fun onViewCreated(view: View) {
        var requestFirstPager = false
        if (adapter === null) {
            adapter = TopAdapter()
            adapter!!.thumbnailClickListener = { checkAndToGallery(it) }
            requestFirstPager = true
        }
        initToolbar(view.toolbar)
        view.rvItems.adapter = adapter

        val lifecycleTransformer = provideLifecycleTransformer<TopListModel>()
        initPagination(view.rvItems, lifecycleTransformer)
        if (requestFirstPager) {
            viewModel.loadFirstPage(provideAdapterObserver(), lifecycleTransformer)
        }
    }

    private fun initPagination(
            rvItems: RecyclerItemsView,
            lifecycleTransformer: LifecycleTransformer<TopListModel>?) {
        rvItems.paginationListener = {
            if (currentPageNumber >= TOP_ENTRIES_LIMIT) {
                Toast.makeText(rvItems.context, R.string.page_limit_reached, Toast.LENGTH_LONG).show()
            } else {
                viewModel.nextPage(provideAdapterObserver(), nextPageToken, lifecycleTransformer)
            }
        }
    }

    private fun provideAdapterObserver(): SimpleObserver<TopListModel> {
        return object : SimpleObserver<TopListModel>() {
            override fun onNext(t: TopListModel) {
                nextPageToken = t.data.after
                adapter!!.addItems(t.data.children)
                currentPageNumber++
            }
        }
    }

    private fun initToolbar(toolbar: Toolbar) {
        toolbar.title = applicationContext?.getString(R.string.top_list)
        setToolbar(toolbar)
    }

    override fun onSaveViewState(view: View, outState: Bundle) {
        super.onSaveViewState(view, outState)
        outState.putParcelable(BUNDLE_RECYCLER_LAYOUT,
                view.rvItems.layoutManager.onSaveInstanceState())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val parcelable = savedInstanceState.getParcelable<Parcelable>(BUNDLE_RECYCLER_LAYOUT)
        view?.rvItems?.layoutManager?.onRestoreInstanceState(parcelable)
    }

    private fun checkAndToGallery(it: List<TopItemModel.Preview.Images>) {
        if (it.isEmpty()) {
            Toast.makeText(applicationContext, R.string.no_images, Toast.LENGTH_LONG).show()
        } else {
            navigator.toGallery(this, it)
        }
    }

    companion object {
        private const val TOP_ENTRIES_LIMIT = 5
        private const val BUNDLE_RECYCLER_LAYOUT = "bundle_recycler_layout"
    }

}