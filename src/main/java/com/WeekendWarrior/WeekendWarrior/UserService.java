package com.WeekendWarrior.WeekendWarrior;


import com.WeekendWarrior.WeekendWarrior.models.User;

public interface UserService {
    public User findUserByEmail(String emaail);
    public void saveUser(User user);
}
