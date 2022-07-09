package com.crud.saladereuniao.saladereuniao.service;


import com.crud.saladereuniao.saladereuniao.entity.ConsultaFrete;
import com.crud.saladereuniao.saladereuniao.entity.Endereco;
import com.crud.saladereuniao.saladereuniao.entity.Room;
import com.crud.saladereuniao.saladereuniao.exception.EnderecoNotFoundException;
import com.crud.saladereuniao.saladereuniao.exception.RoomNotFoundException;
import com.crud.saladereuniao.saladereuniao.repository.ConsultaFreteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ConsultaFreteService {

    @Autowired
    ConsultaFreteRepository ctRepo;

    public List<ConsultaFrete> getAllConsultaFrete(){
        return this.ctRepo.findAll();
    }

    public ResponseEntity<ConsultaFrete> getConsultaFreteById(Long id){
        Optional<ConsultaFrete> opt = ctRepo.findById(id);
        if(opt.isPresent()){

            return ResponseEntity.ok().body(opt.get());
        }else{
            throw new RoomNotFoundException(id);
        }
    }

    public ConsultaFrete saveConsultaFrete(ConsultaFrete consulta) {
        return ctRepo.save(consulta);
    }

    public void deleteConsultaFrete(Long id) {
        ctRepo.deleteById(id);
    }

    public ResponseEntity<?> deleteConsultaFreteById(Long id){
        Optional<ConsultaFrete> opt = ctRepo.findById(id);
        if(opt.isPresent()){
            ConsultaFrete tmp = opt.get();
            ctRepo.deleteById(tmp.getId());
            return ResponseEntity.ok().body("Consulta "+tmp.getId()+" excluida com sucesso");
        }else{
            throw new RoomNotFoundException(id);
        }
    }
}
