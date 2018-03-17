package com.WeekendWarrior.WeekendWarrior.models.data;

import com.WeekendWarrior.WeekendWarrior.models.Wrestler;
import org.springframework.data.repository.CrudRepository;

public interface WrestlerDAO extends CrudRepository<Wrestler, Integer>{
}
