package vladyslav.lubenets.redditclient

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import vladyslav.lubenets.domain.executor.PostExecutionThread
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Lubenets Vladyslav on 9/23/18.
 */
@Singleton
class MainThread @Inject
internal constructor() : PostExecutionThread {
    override fun getScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }
}
