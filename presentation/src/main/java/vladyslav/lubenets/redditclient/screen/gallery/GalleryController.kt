package vladyslav.lubenets.redditclient.screen.gallery

import android.Manifest
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.tbruyelle.rxpermissions2.RxPermissions
import com.trello.rxlifecycle2.LifecycleTransformer
import kotlinx.android.synthetic.main.gallery_controller.view.*
import vladyslav.lubenets.domain.rx.SimpleObserver
import vladyslav.lubenets.redditclient.R
import vladyslav.lubenets.redditclient.components.base.BaseController
import vladyslav.lubenets.redditclient.components.base.viewFactory
import vladyslav.lubenets.redditclient.components.controller.actionBar
import vladyslav.lubenets.redditclient.components.controller.setToolbar
import vladyslav.lubenets.redditclient.di.component.DaggerGalleryScreenComponent
import java.io.File
import java.util.*
import javax.inject.Inject

/**
 * Created by Lubenets Vladyslav on 9/24/18.
 */
class GalleryController(bundle: Bundle? = Bundle()) :
        BaseController<GalleryContract.ViewModel>(bundle) {

    @Inject
    override lateinit var viewModel: GalleryContract.ViewModel

    override val controllerView by viewFactory(R.layout.gallery_controller)
    private lateinit var images: ArrayList<String>
    private var lifecycleTransformer: LifecycleTransformer<String>? = null

    init {
        setHasOptionsMenu(true)
    }

    override fun initializeInjector() {
        DaggerGalleryScreenComponent.builder()
                .applicationComponent(provideApplicationComponent())
                .build()
                .inject(this)
    }

    override fun onViewCreated(view: View) {
        lifecycleTransformer = provideLifecycleTransformer()
        images = args.getStringArrayList(IMAGE_URLS)!!
        initToolbar(view.toolbar)
        val adapter = GalleryAdapter(images)
        view.vpImages.adapter = adapter
    }

    private fun initToolbar(toolbar: Toolbar) {
        toolbar.title = applicationContext?.getString(R.string.gallery)
        setToolbar(toolbar)
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.gallery_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.save_to_phone_gallery -> {
                RxPermissions(activity as FragmentActivity)
                        .request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .subscribe {
                            if (it) {
                                saveCurrentPhoto()
                            } else {
                                Toast.makeText(activity, R.string
                                        .unable_save_image_to_gallery_no_permissions,
                                        Toast.LENGTH_LONG).show()
                            }
                        }
                true
            }
            android.R.id.home -> {
                router.handleBack()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    private fun saveCurrentPhoto() {
        view?.let {
            val context = it.context
            val currentImage = images[it.vpImages.currentItem]
            val observer = object : SimpleObserver<String>() {
                override fun onNext(value: String) {
                    if (value.isNullOrBlank()) {
                        Toast.makeText(context, R.string.unable_save_image_to_gallery,
                                Toast.LENGTH_LONG).show()
                    } else {
                        saveImageToGallery(context, value)
                        Toast.makeText(context, R.string.image_has_been_saved_to_gallery,
                                Toast.LENGTH_LONG).show()
                    }
                }
            }
            viewModel.provideLocalImagePath(currentImage, observer, lifecycleTransformer)
        }
    }

    private fun saveImageToGallery(context: Context, imagePath: String) {
        val scanIntent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
        val imageFile = File(imagePath)
        val imageUri = Uri.fromFile(imageFile)
        scanIntent.data = imageUri
        context.sendBroadcast(scanIntent)
    }

    override fun onSaveViewState(view: View, outState: Bundle) {
        super.onSaveViewState(view, outState)
        outState.putStringArrayList(IMAGE_URLS, images)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        images = savedInstanceState.getStringArrayList(IMAGE_URLS)!!
    }


    companion object {
        private const val IMAGE_URLS = "image_urls"

        fun newInstance(imageUrls: ArrayList<String>): GalleryController {
            val bundle = Bundle()
            bundle.putStringArrayList(GalleryController.IMAGE_URLS, ArrayList(imageUrls))
            return GalleryController(bundle)
        }
    }

}