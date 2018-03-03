package com.WeekendWarrior.WeekendWarrior.models.data;

import org.apache.catalina.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDAO extends CrudRepository<User, Integer>{
}
