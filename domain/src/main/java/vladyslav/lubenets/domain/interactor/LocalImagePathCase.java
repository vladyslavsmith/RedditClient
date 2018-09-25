package vladyslav.lubenets.domain.interactor;

import javax.inject.Inject;

import io.reactivex.Observable;
import vladyslav.lubenets.domain.executor.PostExecutionThread;
import vladyslav.lubenets.domain.executor.ThreadExecutor;
import vladyslav.lubenets.domain.repository.ImageRepository;

/**
 * Created by Lubenets Vladyslav on 9/24/18.
 */
public class LocalImagePathCase extends UseCase<String, String> {


    private final ImageRepository imageRepository;

    @Inject
    LocalImagePathCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread,
                       ImageRepository imageRepository) {
        super(threadExecutor, postExecutionThread);
        this.imageRepository = imageRepository;
    }

    @Override
    Observable<String> buildUseCaseObservable(String s) {
        return imageRepository.provideLocalImagePath(s).toObservable();
    }
}
