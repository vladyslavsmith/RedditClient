package vladyslav.lubenets.domain.interactor;

import javax.inject.Inject;

import io.reactivex.Observable;
import vladyslav.lubenets.domain.entity.TopListModel;
import vladyslav.lubenets.domain.executor.PostExecutionThread;
import vladyslav.lubenets.domain.executor.ThreadExecutor;
import vladyslav.lubenets.domain.repository.ItemRepository;

/**
 * Created by Lubenets Vladyslav on 9/22/18.
 */
public class FetchTopCase extends UseCase<TopListModel, String> {

    private final ItemRepository itemRepository;

    @Inject
    public FetchTopCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread,
                        ItemRepository itemRepository) {
        super(threadExecutor, postExecutionThread);
        this.itemRepository = itemRepository;
    }

    @Override
    public Observable<TopListModel> buildUseCaseObservable(String nextPageToken) {
        return itemRepository.topList(nextPageToken);
    }
}
