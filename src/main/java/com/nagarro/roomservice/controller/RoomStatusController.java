package com.nagarro.roomservice.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.roomservice.model.RoomStatus;
import com.nagarro.roomservice.service.RoomStatusService;

@RestController
@RequestMapping("room-api/roomsStatus")
public class RoomStatusController {

	Logger logger = LoggerFactory.getLogger(RoomStatusController.class);
	@Autowired
	RoomStatusService roomStatusService;

	@GetMapping
	public ResponseEntity<List<RoomStatus>> getAllRoomsStatus() {
		logger.debug("Entering getAllRoomsStatus method");

		Optional<List<RoomStatus>> rooms = roomStatusService.getAllRoomStatuses();
		if (!rooms.isPresent()) {
			logger.debug("No room statuses found");
			return new ResponseEntity<List<RoomStatus>>(HttpStatus.NO_CONTENT);
		}

		logger.debug("Returning room statuses: {}", rooms.get());
		return ResponseEntity.ok(rooms.get());
	}
}
