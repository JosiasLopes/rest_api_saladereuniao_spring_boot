package com.crud.saladereuniao.saladereuniao.controller;

import com.crud.saladereuniao.saladereuniao.entity.User;
import com.crud.saladereuniao.saladereuniao.entity.User;
import com.crud.saladereuniao.saladereuniao.exception.UserNotFoundException;
import com.crud.saladereuniao.saladereuniao.exception.UserNotFoundException;
import com.crud.saladereuniao.saladereuniao.service.UserService;
import com.crud.saladereuniao.saladereuniao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

//foi usado o crossorigin porque é a porta padrão do angular
@RestController @CrossOrigin(origins="http://localhost:4200")
@RequestMapping(value ="api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
        public List<User> getAllUser(){
            return userService.getAllUser();
        
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value="id") Long id){

        return userService.getUserById(id);

    }

    @PostMapping
    public User createUser(@Valid @RequestBody User User){

        return userService.saveUser(User);

    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value="id") Long id, @Valid @RequestBody User details){
            User tmp = userService.getUserById(id).getBody();
            User tmp2;
	   //caso o tmp seja diferente de null
	   //pega as informações do requestBody e subsititui
	  //depois depois sava essas informações e retorna um response	
            if(tmp!=null){
                tmp.setNome(details.getNome());
                tmp.setSobrenome(details.getSobrenome());
                tmp.setIdade(details.getIdade());
                tmp.setProfissao(details.getProfissao());

                tmp2= userService.saveUser(tmp);
                return ResponseEntity.ok(tmp2);
            }else{
                throw new UserNotFoundException(id);
            }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable(value="id") Long id){
        User tmp = userService.getUserById(id).getBody();
        if(tmp!=null){

            userService.deleteUser(id);
            return ResponseEntity.ok().body("Usuario "+id+"apagado com sucesso");
        }else{
            throw new UserNotFoundException(id);
        }
    }

}
