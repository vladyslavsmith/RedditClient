package vladyslav.lubenets.redditclient.di


import javax.inject.Scope
import kotlin.reflect.KClass

/**
 * Created by Lubenets Vladyslav on 9/23/18.
 */
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ScreenScope(val value: KClass<*>)
