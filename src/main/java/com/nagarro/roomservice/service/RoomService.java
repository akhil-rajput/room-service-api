package com.nagarro.roomservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.nagarro.roomservice.dao.RoomRepository;
import com.nagarro.roomservice.exception.RoomNotFoundException;
import com.nagarro.roomservice.model.Room;

@Component
public class RoomService {

	Logger logger = LoggerFactory.getLogger(RoomService.class);
	@Autowired
	RoomRepository repository;

	public Optional<Room> getRoomByRoomNumber(String roomNumber) {
		logger.debug("Entering getRoomByRoomNumber method with roomNumber: {}", roomNumber);
		Optional<Room> room = repository.findByRoomNumber(roomNumber);
		logger.debug("Returning room: {}", room);
		return room;
	}

	public Room addRoom(Room room) {
		logger.debug("Adding room: {}", room);
		Room savedRoom = repository.save(room);
		logger.debug("Returning saved room: {}", savedRoom);
		return savedRoom;
	}

	public Optional<Room> updateRoomDetails(String roomNumber, Room updatedRoomDetails) {
		logger.debug("Entering updateRoomDetails method with roomNumber: {} and updatedRoomDetails: {}", roomNumber,
				updatedRoomDetails);
		Optional<Room> room = repository.findByRoomNumber(roomNumber);
		if (!room.isPresent()) {
			logger.debug("Room not found for roomNumber: {}", roomNumber);
			throw new RoomNotFoundException(HttpStatus.NOT_FOUND.value(), "Could not find room!");
		}
		Room updatedRoom = room.get();
		updatedRoom.setRoomNumber(updatedRoomDetails.getRoomNumber());
		updatedRoom.setCapacity(updatedRoomDetails.getCapacity());
		updatedRoom.setIsAvailable(updatedRoomDetails.getIsAvailable());
		updatedRoom.setRoomType(updatedRoomDetails.getRoomType());
		updatedRoom.setPricePerNight(updatedRoomDetails.getPricePerNight());
		try {
			Room updated = repository.save(updatedRoom);
			logger.debug("Returning updated room: {}", updated);
			return Optional.ofNullable(updated);
		} catch (Exception e) {
			logger.error("Error while updating room: {}", e.getMessage());
			return Optional.ofNullable(null);
		}
	}

	public ResponseEntity<String> deleteRoom(String roomNumber) {
		logger.debug("Entering deleteRoom method with roomNumber: {}", roomNumber);
		Optional<Room> room = repository.findByRoomNumber(roomNumber);
		if (!room.isPresent()) {
			logger.debug("Room not found for roomNumber: {}", roomNumber);
			throw new RoomNotFoundException(HttpStatus.NOT_FOUND.value(), "Could not find room!");
		}
		try {
			repository.delete(room.get());
			logger.debug("Room deleted successfully");
		} catch (Exception ex) {
			logger.error("Error while deleting room: {}", ex.getMessage());
			return new ResponseEntity<>("Could not delete room", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	public Optional<List<Room>> findAllRooms() {
		logger.debug("Entering findAllRooms method");
		List<Room> rooms = new ArrayList<>();
		repository.findAll().forEach(rooms::add);
		logger.debug("Returning rooms: {}", rooms);
		return Optional.ofNullable(rooms.isEmpty() ? null : rooms);
	}

}
