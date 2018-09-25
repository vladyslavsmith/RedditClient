package vladyslav.lubenets.redditclient.components.base

import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlin.reflect.KProperty
/**
 * Created by Lubenets Vladyslav on 9/23/18.
 */
typealias ViewFactory = ((LayoutInflater, ViewGroup) -> View)

class ViewFactoryDelegate(@LayoutRes val layout: Int) {

    operator fun getValue(thisRef: BaseController<*>, prop: KProperty<*>): ViewFactory =
            { layoutInflater, viewGroup -> layoutInflater.inflate(layout, viewGroup, false) }
}

fun viewFactory(@LayoutRes layout: Int) = ViewFactoryDelegate(layout)