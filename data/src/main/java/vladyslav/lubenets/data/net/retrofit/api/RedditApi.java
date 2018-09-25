package vladyslav.lubenets.data.net.retrofit.api;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import vladyslav.lubenets.data.entity.TopListApiModel;

/**
 * Created by Lubenets Vladyslav on 9/22/18.
 */
public interface RedditApi {

    @GET(NetworkContract.Feed.TOP)
    Observable<TopListApiModel> fetchTopItems(@Query("after") String nextPageToken,
                                              @Query("limit") Integer limit);

}
