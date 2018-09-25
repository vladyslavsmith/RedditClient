package vladyslav.lubenets.redditclient.components.list.pagination

/**
 * Created by Lubenets Vladyslav on 9/23/18.
 */
interface PaginationContainer {

    var paginationListener: () -> Unit

    fun notifyLoaded()
}