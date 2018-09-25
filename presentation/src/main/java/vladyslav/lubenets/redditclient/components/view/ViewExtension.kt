package vladyslav.lubenets.redditclient.components.view

import android.view.View

/**
 * Created by Lubenets Vladyslav on 9/23/18.
 */
inline fun View.goneIf(crossinline predicate: () -> Boolean) {
    visibility = if (predicate.invoke()) View.GONE else View.VISIBLE
}

inline fun View.visibleIf(crossinline predicate: () -> Boolean) {
    visibility = if (predicate.invoke()) View.VISIBLE else View.GONE
}
