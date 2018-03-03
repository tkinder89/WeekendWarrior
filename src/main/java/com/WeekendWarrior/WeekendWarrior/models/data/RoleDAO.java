package com.WeekendWarrior.WeekendWarrior.models.data;

import org.springframework.data.repository.CrudRepository;

import javax.management.relation.Role;

public interface RoleDAO extends CrudRepository <Role, Integer> {
}
