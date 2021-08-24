package com.crud.saladereuniao.saladereuniao.service;

import com.crud.saladereuniao.saladereuniao.entity.Room;
import com.crud.saladereuniao.saladereuniao.exception.RoomNotFoundException;
import com.crud.saladereuniao.saladereuniao.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepo; //faz a injecão

    public List<Room> getAllRoom(){

        return roomRepo.findAll();

    }

    public ResponseEntity<Room> getRoomById(Long id){
        Optional<Room> opt = roomRepo.findById(id);
        if(opt.isPresent()){

            return ResponseEntity.ok().body(opt.get());
        }else{
            throw new RoomNotFoundException(id);
        }
    }

    public Room saveRoom(Room room) {
       return roomRepo.save(room);
    }

    public void deleteRoom(Long id) {
        roomRepo.deleteById(id);
    }

    public ResponseEntity<?> deleteRoomById(Long id){
        Optional<Room> opt = roomRepo.findById(id);
        if(opt.isPresent()){
            Room tmp = opt.get();
            roomRepo.deleteById(tmp.getId());
            return ResponseEntity.ok().body("Sala "+tmp.getId()+" excluida com sucesso");
        }else{
            throw new RoomNotFoundException(id);
        }
    }
}
