package com.nagarro.roomservice.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.nagarro.roomservice.model.Room;

@SpringBootTest
class RoomRepositoryTest {

	@Autowired
	RoomRepository repo;

	//@Test
	void findByRoomNumber() {
		Optional<Room> room = repo.findByRoomNumber("209");
		assertEquals(true, room.isPresent());
	}

}
