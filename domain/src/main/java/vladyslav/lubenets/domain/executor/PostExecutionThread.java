package vladyslav.lubenets.domain.executor;

import io.reactivex.Scheduler;

public interface PostExecutionThread {
  Scheduler getScheduler();
}
