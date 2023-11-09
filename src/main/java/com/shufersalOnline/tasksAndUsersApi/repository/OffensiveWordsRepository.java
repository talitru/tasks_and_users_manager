package com.shufersalOnline.tasksAndUsersApi.repository;

import com.shufersalOnline.tasksAndUsersApi.entity.OffensiveWords;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OffensiveWordsRepository extends JpaRepository<OffensiveWords,String> {

}
