package vladyslav.lubenets.redditclient

import android.content.Intent
import android.os.Bundle
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import vladyslav.lubenets.redditclient.screen.top.TopController

/**
 * Created by Lubenets Vladyslav on 9/23/18.
 */
class MainActivity : RxAppCompatActivity() {

    private lateinit var router: Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        router = Conductor.attachRouter(this, container, savedInstanceState)
        //todo: use ivProgress and DI if we need to show/hide progress
    }

    override fun onResume() {
        super.onResume()
        if (!router.hasRootController()) {
            router.setRoot(RouterTransaction.with(TopController()))
        }
    }

    override fun onBackPressed() {
        if (!router.handleBack()) {
            super.onBackPressed()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        router.onActivityResult(requestCode, resultCode, data)
    }


}
