package com.demo.model;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Attendance {
	@Override
	public String toString() {
		return "Attendance [id=" + id + ", date=" + date + ", signInTime=" + signInTime + ", signOutTime=" + signOutTime
				+ ", absent=" + absent + ", user=" + user + "]";
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public LocalTime getSignInTime() {
		return signInTime;
	}
	public void setSignInTime(LocalTime signInTime) {
		this.signInTime = signInTime;
	}
	public LocalTime getSignOutTime() {
		return signOutTime;
	}
	public void setSignOutTime(LocalTime signOutTime) {
		this.signOutTime = signOutTime;
	}
	public boolean isAbsent() {
		return absent;
	}
	public void setAbsent(boolean absent) {
		this.absent = absent;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private LocalTime signInTime;
    private LocalTime signOutTime;
    private boolean absent;
    @ManyToOne
    private User user;
}
