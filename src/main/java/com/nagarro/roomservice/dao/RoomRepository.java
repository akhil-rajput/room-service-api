package com.nagarro.roomservice.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nagarro.roomservice.model.Room;

@Repository
public interface RoomRepository extends CrudRepository<Room, Long> {
	@Query("FROM Room r WHERE r.roomNumber = :roomNumber")
	Optional<Room> findByRoomNumber(@Param("roomNumber") String roomNumber);

	@Modifying
	@Query("UPDATE Room r SET r.pricePerNight = :pricePerNight, r.roomType=:roomType,r.capacity = :capacity WHERE r.roomNumber = :roomNumber")
	int updateRoomDetails(@Param("pricePerNight") String pricePerNight,@Param("roomType") String roomType,@Param("capacity") int capacity,
			@Param("roomNumber") Long roomNumber);

}
