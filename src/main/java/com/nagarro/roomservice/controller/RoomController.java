package com.nagarro.roomservice.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.roomservice.model.Room;
import com.nagarro.roomservice.service.RoomService;

@RestController
@RequestMapping("room-api/rooms")
public class RoomController {

	Logger logger = LoggerFactory.getLogger(RoomController.class);

	@Autowired
	RoomService roomService;

	/**
	 * Retrieves all rooms in the system.
	 * 
	 * @return A ResponseEntity containing a list of all rooms.
	 */
	@GetMapping
	public ResponseEntity<List<Room>> getAllRooms() {
		logger.debug("Entering getAllRooms method");
		Optional<List<Room>> rooms = roomService.findAllRooms();
		if (!rooms.isPresent()) {
			logger.warn("No rooms found");
			return new ResponseEntity<List<Room>>(HttpStatus.NO_CONTENT);
		}
		logger.debug("Returning {} rooms", rooms.get().size());
		return ResponseEntity.ok(rooms.get());
	}

	/**
	 * Deletes a room from the system.
	 * 
	 * @param roomNumber The room number of the room to delete.
	 * @return A ResponseEntity indicating success or failure.
	 */
	@GetMapping("/{roomNumber}")
	public ResponseEntity<Room> getRoom(@PathVariable(value = "roomNumber", required = true) String roomNumber) {
		logger.debug("Entering getRoom method with roomNumber={}", roomNumber);
		Optional<Room> room = roomService.getRoomByRoomNumber(roomNumber);
		if (!room.isPresent()) {
			logger.warn("No room found with roomNumber={}", roomNumber);
			return ResponseEntity.notFound().build();
		}
		logger.debug("Returning room with roomNumber={}", roomNumber);
		return ResponseEntity.ok(room.get());
	}

	/**
	 * Adds a new room to the system.
	 * 
	 * @param room The new room to add.
	 * @return A ResponseEntity indicating success or failure.
	 */
	@PostMapping
	public ResponseEntity<?> addRoom(@RequestBody Room room) {
		logger.debug("Entering addRoom method with room={}", room);
		try {
			Room savedRoom = roomService.addRoom(room);
			if (savedRoom == null) {
				logger.warn("Failed to save room: {}", room);
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		} catch (Exception ex) {
			logger.error("Failed to add room: {}", room, ex);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		logger.debug("Successfully added room: {}", room);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	/**
	 * Updates an existing room's details.
	 * 
	 * @param roomNumber The room number of the room to update.
	 * @param room       The updated room details.
	 * @return A ResponseEntity indicating success or failure.
	 */
	@PutMapping("/{roomNumber}")
	public ResponseEntity<?> updateRoom(@PathVariable(value = "roomNumber", required = true) String roomNumber,
			@RequestBody Room room) {
		logger.debug("Entering updateRoom method with roomNumber={} and room={}", roomNumber, room);
		Optional<Room> updatedRoom = roomService.updateRoomDetails(roomNumber, room);
		if (!updatedRoom.isPresent()) {
			logger.warn("Failed to update room with roomNumber={}", roomNumber);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		logger.debug("Successfully updated room with roomNumber={}", roomNumber);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	/**
	 * Deletes a room from the system.
	 * 
	 * @param roomNumber The room number of the room to delete.
	 * @return A ResponseEntity indicating success or failure.
	 */
	@DeleteMapping("/{roomNumber}")
	public ResponseEntity<?> deleteRoom(@PathVariable(value = "roomNumber", required = true) String roomNumber) {
		logger.debug("Entering deleteRoom method with roomNumber={}", roomNumber);
		ResponseEntity<?> response = roomService.deleteRoom(roomNumber);
		logger.debug("Returned from deleteRoom method with response={}", response);
		return response;
	}

}
