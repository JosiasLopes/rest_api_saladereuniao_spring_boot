package com.crud.saladereuniao.saladereuniao.repository;

import com.crud.saladereuniao.saladereuniao.entity.Room;
import com.crud.saladereuniao.saladereuniao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
