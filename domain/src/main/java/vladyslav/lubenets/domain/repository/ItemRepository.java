package vladyslav.lubenets.domain.repository;

import io.reactivex.Observable;
import vladyslav.lubenets.domain.entity.TopListModel;

/**
 * Created by Lubenets Vladyslav on 9/22/18.
 */
public interface ItemRepository {
    Observable<TopListModel> topList(String nextPageToken);
}
