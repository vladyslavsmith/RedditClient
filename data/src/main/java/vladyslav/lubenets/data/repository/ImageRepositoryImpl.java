package vladyslav.lubenets.data.repository;

import javax.inject.Inject;

import io.reactivex.Single;
import vladyslav.lubenets.data.repository.datasource.ImageDataStoreFactory;
import vladyslav.lubenets.domain.repository.ImageRepository;

/**
 * Created by Lubenets Vladyslav on 9/24/18.
 */
public class ImageRepositoryImpl implements ImageRepository {

    private final ImageDataStoreFactory imageDataStoreFactory;

    @Inject
    ImageRepositoryImpl(ImageDataStoreFactory imageDataStoreFactory) {
        this.imageDataStoreFactory = imageDataStoreFactory;
    }

    @Override
    public Single<String> provideLocalImagePath(String url) {
        return imageDataStoreFactory.create().fetchLocalImagePath(url);
    }
}
