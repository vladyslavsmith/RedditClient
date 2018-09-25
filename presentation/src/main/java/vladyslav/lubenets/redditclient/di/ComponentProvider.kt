package vladyslav.lubenets.redditclient.di

/**
 * Created by Lubenets Vladyslav on 9/23/18.
 */
interface ComponentProvider<C> {
    fun provideComponent() : C
}