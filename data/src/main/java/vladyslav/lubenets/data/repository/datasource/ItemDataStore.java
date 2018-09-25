package vladyslav.lubenets.data.repository.datasource;

import io.reactivex.Observable;
import vladyslav.lubenets.data.entity.TopListApiModel;

/**
 * Created by Lubenets Vladyslav on 9/22/18.
 */
public interface ItemDataStore {
    Observable<TopListApiModel> topList(String nextPageToken);
}
