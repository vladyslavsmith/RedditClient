package vladyslav.lubenets.redditclient.components.controller

import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import vladyslav.lubenets.redditclient.components.base.BaseController

/**
 * Created by Lubenets Vladyslav on 9/24/18.
 */
fun BaseController<*>.setToolbar(toolbar: Toolbar) = (activity as AppCompatActivity)
        .setSupportActionBar(toolbar)

val BaseController<*>.actionBar: ActionBar?
    get() = (activity as AppCompatActivity).supportActionBar