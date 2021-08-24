package com.crud.saladereuniao.saladereuniao.controller;

import com.crud.saladereuniao.saladereuniao.entity.Room;
import com.crud.saladereuniao.saladereuniao.exception.RoomNotFoundException;
import com.crud.saladereuniao.saladereuniao.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

//foi usado o crossorigin porque é a porta padrão do angular
@RestController @CrossOrigin(origins="http://localhost:4200")
@RequestMapping(value ="api/v1/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping()
    public List<Room> getAllRoom(){
        return roomService.getAllRoom();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable(value="id") Long id){

        return roomService.getRoomById(id);

    }

    @PostMapping
    public Room createRoom(@Valid @RequestBody Room room){

        return roomService.saveRoom(room);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable(value="id") Long id, @Valid @RequestBody Room details){
            Room tmp = roomService.getRoomById(id).getBody();
            Room tmp2;
            if(tmp!=null){
                tmp.setName(details.getName());
                tmp.setData(details.getData());
                tmp.setStartHour(details.getStartHour());
                tmp.setEndHour(details.getEndHour());
                tmp2= roomService.saveRoom(tmp);
                return ResponseEntity.ok(tmp2);
            }else{
                throw new RoomNotFoundException(id);
            }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRoom(@PathVariable(value="id") Long id){
        Room tmp = roomService.getRoomById(id).getBody();
        if(tmp!=null){

             roomService.deleteRoom(id);
            return ResponseEntity.ok().body("Sala "+id+"apagada com sucesso");
        }else{
            throw new RoomNotFoundException(id);
        }
    }

}
