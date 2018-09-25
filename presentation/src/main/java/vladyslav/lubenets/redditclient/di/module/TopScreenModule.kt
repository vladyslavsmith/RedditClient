package vladyslav.lubenets.redditclient.di.module

import dagger.Module
import dagger.Provides
import vladyslav.lubenets.redditclient.screen.top.TopContract
import vladyslav.lubenets.redditclient.screen.top.TopNavigator
import vladyslav.lubenets.redditclient.screen.top.TopViewModel

/**
 * Created by Lubenets Vladyslav on 9/23/18.
 */
@Module
class TopScreenModule {

    @Provides
    internal fun provideViewModel(topViewModel: TopViewModel): TopContract.ViewModel {
        return topViewModel
    }

    @Provides
    internal fun provideNavigator(topNavigator: TopNavigator): TopContract.Navigator {
        return topNavigator
    }

}