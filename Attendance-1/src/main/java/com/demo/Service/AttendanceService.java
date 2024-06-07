package com.demo.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.dao.AttendanceRepository;
import com.demo.dao.UserRepository;
import com.demo.model.Attendance;
import com.demo.model.User;

@Service
@Transactional
public class AttendanceService {

	@Autowired
    private final AttendanceRepository attendanceRepository;

    @Autowired
    private final UserRepository userRepository;

    public AttendanceService(AttendanceRepository attendanceRepository, UserRepository userRepository) {
        this.attendanceRepository = attendanceRepository;
        this.userRepository = userRepository;
    }

    public Attendance signIn(User user) {
        User foundUser = userRepository.findByUsername(user.getUsername());
        LocalDate today = LocalDate.now();
        Optional<Attendance> existingAttendance = attendanceRepository.findByUserAndDate(foundUser, today);

        if (existingAttendance.isPresent()) {
            throw new IllegalStateException("User has already signed in today");
        }

        Attendance attendance = new Attendance();
        attendance.setDate(today);
        attendance.setSignInTime(LocalTime.now());
        attendance.setUser(foundUser);
        attendance.setAbsent(false);

        return attendanceRepository.save(attendance);
    }

    public Attendance signOut(User user) {
        User foundUser = userRepository.findByUsername(user.getUsername());
        LocalDate today = LocalDate.now();
        Optional<Attendance> existingAttendance = attendanceRepository.findByUserAndDate(foundUser, today);

        if (existingAttendance.isEmpty() || existingAttendance.get().getSignOutTime() != null) {
            throw new IllegalStateException("User has not signed in today or has already signed out");
        }

        Attendance attendance = existingAttendance.get();
        attendance.setSignOutTime(LocalTime.now());

        return attendanceRepository.save(attendance);
    }



	    public List<Attendance> getUserAttendance(Long userId) {
	        return attendanceRepository.findByUserId(userId);
	    }

	    public List<Attendance> getAllAttendance() {
	        return attendanceRepository.findAll();
	    }

	    public List<User> getUsersAbsentOnDate(LocalDate date) {
	        List<User> allUsers = userRepository.findAll();
	        List<User> presentUsers = attendanceRepository.findUsersByDate(date);

	        allUsers.removeAll(presentUsers);

	        return allUsers;
	    }
	    
	    public boolean isSignedIn(User user) {
	        LocalDate today = LocalDate.now();
	        Optional<Attendance> existingAttendance = attendanceRepository.findByUserAndDate(user, today);
	        return existingAttendance.isPresent() && existingAttendance.get().getSignOutTime() == null;
	    }
	}


