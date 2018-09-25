package vladyslav.lubenets.data.repository;

import org.mapstruct.factory.Mappers;

import javax.inject.Inject;

import io.reactivex.Observable;
import vladyslav.lubenets.data.entity.mapper.ItemMapper;
import vladyslav.lubenets.data.repository.datasource.ItemDataStoreFactory;
import vladyslav.lubenets.domain.entity.TopListModel;
import vladyslav.lubenets.domain.repository.ItemRepository;

/**
 * Created by Lubenets Vladyslav on 9/22/18.
 */
public class ItemRepositoryImpl implements ItemRepository {

    private final ItemDataStoreFactory itemDataStoreFactory;

    @Inject
    ItemRepositoryImpl(ItemDataStoreFactory itemDataStoreFactory) {
        this.itemDataStoreFactory = itemDataStoreFactory;
    }

    @Override
    public Observable<TopListModel> topList(String nextPageToken) {
        return itemDataStoreFactory.create().topList(nextPageToken)
                .map(topListApiModel -> Mappers.getMapper(ItemMapper.class).toDomainList
                        (topListApiModel));
    }
}
