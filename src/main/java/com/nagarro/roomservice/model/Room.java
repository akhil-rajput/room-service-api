package com.nagarro.roomservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostPersist;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Type;
import org.springframework.beans.factory.annotation.Autowired;

import com.nagarro.roomservice.dao.RoomStatusRepository;

@Entity
@Table(name = "rooms")
public class Room {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, nullable = false)
	private String roomNumber;

	@Column(nullable = false)
	private String capacity;

	@Column(nullable = false)
	private Double pricePerNight;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private RoomType roomType;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private RoomAvailability isAvailable;

	public Room() {
	}

	
	public Room(Long id, String roomNumber, String capacity, Double pricePerNight, RoomType roomType,
			RoomAvailability isAvailable) {
		this.id = id;
		this.roomNumber = roomNumber;
		this.capacity = capacity;
		this.pricePerNight = pricePerNight;
		this.roomType = roomType;
		this.isAvailable = isAvailable;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getRoomNumber() {
		return roomNumber;
	}


	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}


	public String getCapacity() {
		return capacity;
	}


	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}


	public Double getPricePerNight() {
		return pricePerNight;
	}


	public void setPricePerNight(Double pricePerNight) {
		this.pricePerNight = pricePerNight;
	}


	public RoomType getRoomType() {
		return roomType;
	}


	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}


	public RoomAvailability getIsAvailable() {
		return isAvailable;
	}


	public void setIsAvailable(RoomAvailability isAvailable) {
		this.isAvailable = isAvailable;
	}


	@Override
	public String toString() {
		return "Room [id=" + id + ", roomNumber=" + roomNumber + ", capacity=" + capacity + ", pricePerNight="
				+ pricePerNight + ", roomType=" + roomType + ", isAvailable=" + isAvailable + "]";
	}
}