package vladyslav.lubenets.domain.rx;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by Lubenets Vladyslav on 9/23/18.
 */
public class SimpleObserver<T> extends DisposableObserver<T> {
    @Override
    public void onNext(T value) {
    }

    @Override
    public void onError(Throwable e) {
        //todo: use DI and ErrorHandler to intercept and show error to user
        e.printStackTrace();
    }

    @Override
    public void onComplete() {

    }
}
