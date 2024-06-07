package com.demo.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.demo.model.Attendance;
import com.demo.model.User;

public interface AttendanceRepository extends JpaRepository<Attendance, Long>{
	List<Attendance> findByUserId(Long userId);
    Optional<Attendance> findByUserAndDate(User user, LocalDate date);

    @Query("SELECT a.user FROM Attendance a WHERE a.date = :date")
    List<User> findUsersByDate(@Param("date") LocalDate date);
}
