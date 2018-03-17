package com.WeekendWarrior.WeekendWarrior.models.data;

import com.WeekendWarrior.WeekendWarrior.models.Booking;
import org.springframework.data.repository.CrudRepository;

public interface BookingDAO extends CrudRepository<Booking, Integer>{
}
