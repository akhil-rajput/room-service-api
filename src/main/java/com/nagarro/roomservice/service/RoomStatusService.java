package com.nagarro.roomservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.roomservice.dao.RoomStatusRepository;
import com.nagarro.roomservice.model.RoomStatus;

@Service
public class RoomStatusService {

	Logger logger = LoggerFactory.getLogger(RoomStatusService.class);
	@Autowired
	private RoomStatusRepository roomStatusRepository;

	public Optional<List<RoomStatus>> getAllRoomStatuses() {
		logger.info("Getting all room statuses");
		List<RoomStatus> rooms = new ArrayList<>();
		roomStatusRepository.findAll().forEach(rooms::add);
		Optional<List<RoomStatus>> optionalRooms = Optional.ofNullable(rooms.isEmpty() ? null : rooms);
		if (optionalRooms.isPresent()) {
			logger.info("Found {} room statuses", optionalRooms.get().size());
		} else {
			logger.info("No room statuses found");
		}
		return optionalRooms;
	}

}