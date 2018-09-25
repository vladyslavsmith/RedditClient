package vladyslav.lubenets.redditclient.components.base

import android.os.Bundle

/**
 * Created by Lubenets Vladyslav on 9/23/18.
 */


interface BaseViewModel {
    fun onCreate(savedInstanceState: Bundle?)
    fun onDestroy()
}

open class BaseViewModelImpl : BaseViewModel {
    override fun onDestroy() {}
    override fun onCreate(savedInstanceState: Bundle?) {}
}