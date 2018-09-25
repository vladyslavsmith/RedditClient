package vladyslav.lubenets.data.repository.datasource;

import android.content.Context;

import javax.inject.Inject;
import javax.inject.Singleton;

import vladyslav.lubenets.data.executor.BackgroundExecutor;

/**
 * Created by Lubenets Vladyslav on 9/24/18.
 */
@Singleton
public class ImageDataStoreFactory {

    private final Context context;
    private final BackgroundExecutor backgroundExecutor;

    @Inject
    ImageDataStoreFactory(Context context, BackgroundExecutor backgroundExecutor) {
        this.context = context;
        this.backgroundExecutor = backgroundExecutor;
    }

    public ImageDataStore create() {
        return new LocalImageDataStore(context, backgroundExecutor);
    }
}
