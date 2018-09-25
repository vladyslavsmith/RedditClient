package vladyslav.lubenets.domain.interator;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import vladyslav.lubenets.domain.entity.TopListModel;
import vladyslav.lubenets.domain.executor.PostExecutionThread;
import vladyslav.lubenets.domain.executor.ThreadExecutor;
import vladyslav.lubenets.domain.interactor.FetchTopCase;
import vladyslav.lubenets.domain.repository.ItemRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * Created by Lubenets Vladyslav on 9/25/18.
 */
@RunWith(MockitoJUnitRunner.class)
public class FetchTopCaseTest {

    private FetchTopCase fetchTopCase;

    @Mock
    private ThreadExecutor threadExecutor;
    @Mock
    private PostExecutionThread postExecutionThread;
    @Mock
    private ItemRepository itemRepository;
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private TestDisposableObserver<TopListModel> observer;

    @Before
    public void setUp() {
        fetchTopCase = new FetchTopCase(threadExecutor, postExecutionThread, itemRepository);
        when(fetchTopCase.buildUseCaseObservable(null)).thenReturn(Observable.<TopListModel>empty());
        when(postExecutionThread.getScheduler()).thenReturn(Schedulers.computation());
        this.observer = new TestDisposableObserver<>();
    }

    @Test
    public void testFetchWithNullObserver() {
        expectedException.expect(NullPointerException.class);
        fetchTopCase.execute(null, null, null);
    }

    @Test
    public void testZeroItemsEmitted() {
        fetchTopCase.execute(observer, null, null);
        assertThat(observer.valuesCount).isZero();
    }

    private static class TestDisposableObserver<T> extends DisposableObserver<T> {
        private int valuesCount = 0;

        @Override
        public void onNext(T value) {
            valuesCount++;
        }

        @Override
        public void onError(Throwable e) {
        }

        @Override
        public void onComplete() {
        }
    }


}
