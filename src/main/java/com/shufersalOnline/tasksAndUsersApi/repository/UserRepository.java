package com.shufersalOnline.tasksAndUsersApi.repository;

import com.shufersalOnline.tasksAndUsersApi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);

}
