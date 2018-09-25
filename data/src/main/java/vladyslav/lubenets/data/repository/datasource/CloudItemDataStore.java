package vladyslav.lubenets.data.repository.datasource;

import io.reactivex.Observable;
import vladyslav.lubenets.data.entity.TopListApiModel;
import vladyslav.lubenets.data.net.retrofit.api.RedditApi;

/**
 * Created by Lubenets Vladyslav on 9/22/18.
 */
public class CloudItemDataStore implements ItemDataStore {

    private static final Integer DEFAULT_LIMIT = 10;
    private final RedditApi redditApi;

    CloudItemDataStore(RedditApi redditApi) {
        this.redditApi = redditApi;
    }

    @Override
    public Observable<TopListApiModel> topList(String nextPageToken) {
        return redditApi.fetchTopItems(nextPageToken, DEFAULT_LIMIT);
    }
}
