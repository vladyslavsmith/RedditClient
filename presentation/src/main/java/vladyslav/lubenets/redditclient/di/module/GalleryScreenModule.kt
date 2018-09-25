package vladyslav.lubenets.redditclient.di.module

import dagger.Module
import dagger.Provides
import vladyslav.lubenets.redditclient.screen.gallery.GalleryContract
import vladyslav.lubenets.redditclient.screen.gallery.GalleryNavigator
import vladyslav.lubenets.redditclient.screen.gallery.GalleryViewModel

/**
 * Created by Lubenets Vladyslav on 9/24/18.
 */
@Module
class GalleryScreenModule {

    @Provides
    internal fun provideViewModel(galleryViewModel: GalleryViewModel): GalleryContract.ViewModel {
        return galleryViewModel
    }

    @Provides
    internal fun provideNavigator(galleryNavigator: GalleryNavigator): GalleryContract.Navigator {
        return galleryNavigator
    }

}