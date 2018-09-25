package vladyslav.lubenets.data.repository.datasource;

import javax.inject.Inject;
import javax.inject.Singleton;

import vladyslav.lubenets.data.net.retrofit.api.RedditApi;

/**
 * Created by Lubenets Vladyslav on 9/22/18.
 */
@Singleton
public class ItemDataStoreFactory {

    private final RedditApi redditApi;

    @Inject
    ItemDataStoreFactory(RedditApi redditApi) {
        this.redditApi = redditApi;
    }

    public ItemDataStore create() {
        //todo: add local cache as store in future
        return createCloudStore();
    }

    private ItemDataStore createCloudStore() {
        return new CloudItemDataStore(redditApi);
    }

}
