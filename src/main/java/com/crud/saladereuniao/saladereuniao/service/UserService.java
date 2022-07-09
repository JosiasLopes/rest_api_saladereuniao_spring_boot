package com.crud.saladereuniao.saladereuniao.service;

import com.crud.saladereuniao.saladereuniao.entity.Room;
import com.crud.saladereuniao.saladereuniao.entity.User;
import com.crud.saladereuniao.saladereuniao.exception.RoomNotFoundException;
import com.crud.saladereuniao.saladereuniao.exception.UserNotFoundException;
import com.crud.saladereuniao.saladereuniao.repository.RoomRepository;
import com.crud.saladereuniao.saladereuniao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo; //faz a injecão

    public List<User> getAllUser(){

        return userRepo.findAll();

    }


//traz o response Entitu de room ou retorna exception
    public ResponseEntity<User> getUserById(Long id){
        Optional<User> opt = userRepo.findById(id);
        if(opt.isPresent()){

            return ResponseEntity.ok().body(opt.get());
        }else{
            throw new UserNotFoundException(id);
        }
    }

    public User saveUser(User user) {
       return userRepo.save(user);
    }

    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }

    public ResponseEntity<?> deleteUserById(Long id){
        Optional<User> opt = userRepo.findById(id);
        if(opt.isPresent()){
            User tmp = opt.get();
            userRepo.deleteById(tmp.getId());
            return ResponseEntity.ok().body("Usuario "+tmp.getId()+" excluido com sucesso");
        }else{
            throw new UserNotFoundException(id);
        }
    }
}
