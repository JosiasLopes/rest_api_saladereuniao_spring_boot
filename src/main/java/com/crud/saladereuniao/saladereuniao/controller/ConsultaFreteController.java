package com.crud.saladereuniao.saladereuniao.controller;

import com.crud.saladereuniao.saladereuniao.entity.ConsultaFrete;
import com.crud.saladereuniao.saladereuniao.entity.Endereco;
import com.crud.saladereuniao.saladereuniao.entity.Room;
import com.crud.saladereuniao.saladereuniao.exception.RoomNotFoundException;
import com.crud.saladereuniao.saladereuniao.service.ConsultaFreteService;
import com.crud.saladereuniao.saladereuniao.service.EnderecoService;
import com.crud.saladereuniao.saladereuniao.service.RoomService;
import com.crud.saladereuniao.saladereuniao.utils.calc.CalcFrete;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

//foi usado o crossorigin porque é a porta padrão do angular
@RestController @CrossOrigin(origins="http://localhost:4200")
@RequestMapping(value ="api/v1/contacaofrete")
public class ConsultaFreteController {

    @Autowired
    private ConsultaFreteService ctService;

    @Autowired
    private EnderecoService edService;

    @GetMapping()
    public List<ConsultaFrete> getAllConsultaFrete(){
        return ctService.getAllConsultaFrete();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultaFrete> getConsultaFreteById(@PathVariable(value="id") Long id){

        return ctService.getConsultaFreteById(id);

    }

    @PostMapping
    public ConsultaFrete createConsultaFrete(@Valid @RequestBody ConsultaFrete consulta){

        return ctService.saveConsultaFrete(consulta);

    }
    @GetMapping("{peso}/{cepOrigem}/{cepDestino}/{nomeDestinatario}")
    public ResponseEntity<String> buscaConsultaFrete(@PathVariable(value="peso") double peso,
                                             @PathVariable(value="cepOrigem") String cepOrigem,
                                             @PathVariable(value="cepDestino") String cepDestino,
                                             @PathVariable(value="nomeDestinatario") String nomeDestinatario,
                                             @Valid @RequestBody ConsultaFrete consulta) throws JsonProcessingException {

        //procura os dois endereços pelo cep
        ResponseEntity<Endereco> tmpOr = edService.getEnderecoByCep(cepOrigem);
        ResponseEntity<Endereco> tmpDst = edService.getEnderecoByCep(cepDestino);

        consulta.setCepOrigem(tmpOr.getBody().getCep());
        consulta.setCepDestino(tmpDst.getBody().getCep());
        consulta.setPeso(peso);
        consulta.setNomeDestinatario(nomeDestinatario);

        //faz o calculo do frete
        ConsultaFrete tmp = CalcFrete.calcularFrete(peso,tmpOr.getBody(),tmpDst.getBody());


        consulta.setDataPrevista(tmp.getDataPrevista());
        consulta.setDataConsulta(tmp.getDataConsulta());
        consulta.setTotalFrete(tmp.getTotalFrete());

        ObjectMapper objectMapper = new ObjectMapper();

        var json = objectMapper.writeValueAsString(consulta);
        return ResponseEntity.ok().body(json);
    }


    @PostMapping("{peso}/{cepOrigem}/{cepDestino}/{nomeDestinatario}")
    public ConsultaFrete createConsultaFrete(@PathVariable(value="peso") double peso,
                                    @PathVariable(value="cepOrigem") String cepOrigem,
                                    @PathVariable(value="cepDestino") String cepDestino,
                                    @PathVariable(value="nomeDestinatario") String nomeDestinatario,
                                    @Valid @RequestBody ConsultaFrete consulta){

        //procura os dois endereços pelo cep
        ResponseEntity<Endereco> tmpOr = edService.getEnderecoByCep(cepOrigem);
        ResponseEntity<Endereco> tmpDst = edService.getEnderecoByCep(cepDestino);

        //coloca os dois ceps dentro da consulta
        consulta.setCepOrigem(tmpOr.getBody().getCep());
        consulta.setCepDestino(tmpDst.getBody().getCep());
        consulta.setPeso(peso);
        consulta.setNomeDestinatario(nomeDestinatario);

        //faz o calculo do frete
        ConsultaFrete tmp = CalcFrete.calcularFrete(peso,tmpOr.getBody(),tmpDst.getBody());


        consulta.setDataPrevista(tmp.getDataPrevista());
        consulta.setDataConsulta(tmp.getDataConsulta());
        consulta.setTotalFrete(tmp.getTotalFrete());

        return ctService.saveConsultaFrete(consulta);

    }



    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteConsultaFrete(@PathVariable(value="id") Long id){
        ConsultaFrete tmp = ctService.getConsultaFreteById(id).getBody();
        if(tmp!=null){

            ctService.deleteConsultaFrete(id);
            return ok().body("Sala "+id+"apagada com sucesso");
        }else{
            throw new RoomNotFoundException(id);
        }
    }

}
