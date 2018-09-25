package vladyslav.lubenets.data.repository.datasource;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;

import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import vladyslav.lubenets.data.executor.BackgroundExecutor;

/**
 * Created by Lubenets Vladyslav on 9/24/18.
 */
public class LocalImageDataStore implements ImageDataStore {

    private final Context context;
    private final Handler mainThreadHandler;
    private final BackgroundExecutor backgroundExecutor;

    public LocalImageDataStore(Context context, BackgroundExecutor backgroundExecutor) {
        this.context = context;
        this.backgroundExecutor = backgroundExecutor;
        mainThreadHandler = new Handler();
    }

    @Override
    public Single<String> fetchLocalImagePath(String url) {
        return Single.create(emitter -> mainThreadHandler.post(() ->
                Glide
                        .with(context)
                        .asBitmap()
                        .load(url)
                        .into(new SimpleTarget<Bitmap>() {
                            @Override
                            public void onResourceReady(@NonNull Bitmap resource,
                                                        @Nullable Transition<? super Bitmap> transition) {
                                backgroundExecutor.execute(() -> saveAndProcessBitmap(emitter, resource));
                            }

                            private void saveAndProcessBitmap(SingleEmitter<String> emitter,
                                                              Bitmap resource) {
                                String imagePath = null;
                                try {
                                    imagePath = saveImage(resource);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                emitter.onSuccess(imagePath);
                            }

                            private String saveImage(Bitmap resource) throws IOException {
                                String imagePath = null;
                                String imageName = provideImageName(System.currentTimeMillis());
                                String storageDirectoryString = Environment
                                        .getExternalStoragePublicDirectory(Environment
                                                .DIRECTORY_PICTURES) + "/RedditClient";
                                File storageDirectory = new File(storageDirectoryString);
                                Boolean errorHappened = false;
                                if (!storageDirectory.exists()) {
                                    errorHappened = !storageDirectory.mkdirs();
                                }
                                if (!errorHappened) {
                                    File imageFile = new File(storageDirectory, imageName);
                                    imagePath = imageFile.getAbsolutePath();
                                    FileOutputStream fileStream = new FileOutputStream
                                            (imageFile);
                                    resource.compress(Bitmap.CompressFormat.JPEG, 100,
                                            fileStream);
                                    fileStream.close();
                                }
                                return imagePath;
                            }

                        }))
        );
    }

    @Override
    public String provideImageName(Long currentTimeStamp) {
        return String.format(Locale.getDefault(), "%d.jpg", currentTimeStamp);
    }

}
