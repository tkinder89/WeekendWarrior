package com.WeekendWarrior.WeekendWarrior.models.data;


import com.WeekendWarrior.WeekendWarrior.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Integer>{
    User findByEmail(String email);
}
