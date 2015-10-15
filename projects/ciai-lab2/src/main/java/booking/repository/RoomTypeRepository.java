package booking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import booking.model.Room;
import booking.model.RoomType;


public interface RoomTypeRepository extends CrudRepository<RoomType, Long> {
//	RoomType findByName(String name);

}

