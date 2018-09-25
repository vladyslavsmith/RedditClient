package vladyslav.lubenets.data.entity.mapper;

import org.mapstruct.Mapper;

import vladyslav.lubenets.data.entity.TopListApiModel;
import vladyslav.lubenets.domain.entity.TopListModel;

/**
 * Created by Lubenets Vladyslav on 9/22/18.
 */
@Mapper
public interface ItemMapper {
    TopListModel toDomainList(TopListApiModel topListApiModel);
}
