package vladyslav.lubenets.data.repository.datasource;

import io.reactivex.Single;

/**
 * Created by Lubenets Vladyslav on 9/24/18.
 */
public interface ImageDataStore {
    Single<String> fetchLocalImagePath(String url);
    String provideImageName(Long currentTimeStamp);
}
