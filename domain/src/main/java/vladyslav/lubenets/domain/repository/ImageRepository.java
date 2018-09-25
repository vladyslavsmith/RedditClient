package vladyslav.lubenets.domain.repository;

import io.reactivex.Single;

/**
 * Created by Lubenets Vladyslav on 9/24/18.
 */
public interface ImageRepository {
    Single<String> provideLocalImagePath(String url);
}
