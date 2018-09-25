package vladyslav.lubenets.data.executor;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import android.support.annotation.NonNull;

import javax.inject.Inject;
import javax.inject.Singleton;

import vladyslav.lubenets.domain.executor.ThreadExecutor;

@Singleton
public class BackgroundExecutor implements ThreadExecutor {
    private final ThreadPoolExecutor threadPoolExecutor;

    @Inject
    BackgroundExecutor() {
        this.threadPoolExecutor = new ThreadPoolExecutor(3, 5, 10, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(), new JobThreadFactory());
    }

    @Override
    public void execute(@NonNull Runnable runnable) {
        this.threadPoolExecutor.execute(runnable);
    }

    private static class JobThreadFactory implements ThreadFactory {
        private int counter = 0;

        @Override
        public Thread newThread(@NonNull Runnable runnable) {
            return new Thread(runnable, "android_job_" + counter++);
        }
    }
}
