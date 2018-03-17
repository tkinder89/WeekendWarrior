package com.WeekendWarrior.WeekendWarrior;


import com.WeekendWarrior.WeekendWarrior.models.User;

public interface UserService {
    public User findUserByEmail(String email);
    public void saveUser(User user);
}
