package vladyslav.lubenets.redditclient.components.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.Controller
import com.trello.lifecycle2.android.lifecycle.AndroidLifecycle
import com.trello.rxlifecycle2.LifecycleTransformer
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import kotlinx.android.extensions.LayoutContainer
import vladyslav.lubenets.redditclient.RedditClientApplication
import vladyslav.lubenets.redditclient.di.component.ApplicationComponent

/**
 * Created by Lubenets Vladyslav on 9/23/18.
 */
abstract class BaseController<out VM : BaseViewModel> @JvmOverloads constructor(
        args: Bundle? = null) : Controller(args), LayoutContainer {

    abstract val viewModel: VM
    override val containerView: View? get() = view
    abstract val controllerView: ViewFactory

    init {
        addLifecycleListener(object : LifecycleListener() {

            override fun preCreateView(controller: Controller) {
                onCreated(null)
            }

            override fun postCreateView(controller: Controller, view: View) {
            }

        })
    }

    private fun onCreated(savedInstanceState: Bundle?) {
        initializeInjector()
        viewModel.onCreate(savedInstanceState)
    }

    override fun onDestroyView(view: View) {
        super.onDestroyView(view)
        viewModel.onDestroy()
    }

    abstract fun initializeInjector()

    fun provideApplicationComponent(): ApplicationComponent {
        val redditClientApplication = applicationContext as RedditClientApplication
        return redditClientApplication.provideComponent()
    }

    open fun onViewCreated(view: View) {
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = controllerView.invoke(inflater, container)
        onViewCreated(view)
        return view
    }

    fun <T> provideLifecycleTransformer(): LifecycleTransformer<T>? {
        if (activity is RxAppCompatActivity) {
            val rxActivity = activity as RxAppCompatActivity
            return AndroidLifecycle.createLifecycleProvider(rxActivity).bindToLifecycle<T>()
        }
        return null
    }
}