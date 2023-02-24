package com.nagarro.roomservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.roomservice.model.RoomStatus;

@Repository
public interface RoomStatusRepository extends JpaRepository<RoomStatus, Long> {
}
