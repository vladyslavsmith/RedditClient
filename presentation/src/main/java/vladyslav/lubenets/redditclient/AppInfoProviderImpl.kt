package vladyslav.lubenets.redditclient

import vladyslav.lubenets.domain.AppInfoProvider
import javax.inject.Inject

/**
 * Created by Lubenets Vladyslav on 9/22/18.
 */
class AppInfoProviderImpl @Inject constructor() : AppInfoProvider {

    override fun provideBaseEndpoint(): String {
        return BuildConfig.ENDPOINT
    }
}