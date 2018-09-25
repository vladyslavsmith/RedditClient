package vladyslav.lubenets.redditclient.components.list

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.View
import vladyslav.lubenets.redditclient.components.list.RecyclerItemsView.Companion.DEFAULT_THRESHOLD
import vladyslav.lubenets.redditclient.components.list.RecyclerItemsView.Companion.FIRST_PAGE
import vladyslav.lubenets.redditclient.components.list.pagination.PaginationContainer
import vladyslav.lubenets.redditclient.components.view.goneIf
import vladyslav.lubenets.redditclient.components.view.visibleIf

/**
 * Created by Lubenets Vladyslav on 9/23/18.
 */
class RecyclerItemsView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyle: Int = 0
) : RecyclerView(context, attrs, defStyle), PaginationContainer {


    var placeholder: View? = null
        set(value) {
            field = value
            checkIfEmpty()
        }

    var threshold: Int = DEFAULT_THRESHOLD
        set(value) {
            field = value
            listener.visibleThreshold = value
        }

    override var paginationListener: () -> Unit = {}
        set(value) {
            listener.pageLoader = value
        }

    private val listener = EndlessRecyclerOnScrollListener(paginationListener)

    private val emptyObserver = object : AdapterDataObserver() {
        override fun onChanged() = checkIfEmpty()
        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) = checkIfEmpty()
        override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) = checkIfEmpty()
        override fun onItemRangeChanged(positionStart: Int, itemCount: Int) = checkIfEmpty()
    }

    override fun notifyLoaded() {
        listener.loading = true
    }


    fun reset() {
        listener.reset()
    }

    init {
        addOnScrollListener(listener)
    }

    override fun setAdapter(adapter: RecyclerView.Adapter<*>?) {
        super.setAdapter(adapter)

        adapter?.registerAdapterDataObserver(emptyObserver)
        checkIfEmpty()
    }

    private fun checkIfEmpty() {
        placeholder?.let { view ->
            adapter?.let {
                val emptyViewVisible = it.itemCount == 0
                view.visibleIf { emptyViewVisible }
                this.goneIf { emptyViewVisible }
            }
        }

    }

    companion object {
        const val DEFAULT_THRESHOLD = 5
        const val FIRST_PAGE = 1
    }
}


class EndlessRecyclerOnScrollListener(var pageLoader: ()-> Unit) :
        RecyclerView.OnScrollListener() {

    var loading = true
    var visibleThreshold = DEFAULT_THRESHOLD

    private var currentPage = FIRST_PAGE
    private var previousTotal = DEFAULT_TOTAL


    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        val layoutManager = recyclerView.layoutManager as? LinearLayoutManager
                ?: throw IllegalArgumentException(
                        "EndlessRecyclerOnScrollListener work only with LinearLayoutManager")
        val visibleItemCount = recyclerView.childCount
        val totalItemCount = recyclerView.layoutManager.itemCount
        val firstVisibleItem = layoutManager.findFirstVisibleItemPosition()

        if (loading && totalItemCount > previousTotal) {
            loading = false
            previousTotal = totalItemCount

        }
        if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
            currentPage++
            pageLoader.invoke()
            loading = true
        }
    }

    fun reset() {
        currentPage = FIRST_PAGE
        previousTotal = DEFAULT_TOTAL
        loading = true
    }

    companion object {
        private const val DEFAULT_TOTAL = 0
    }

}
