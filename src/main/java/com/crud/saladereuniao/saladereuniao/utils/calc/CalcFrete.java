package com.crud.saladereuniao.saladereuniao.utils.calc;

import com.crud.saladereuniao.saladereuniao.entity.ConsultaFrete;
import com.crud.saladereuniao.saladereuniao.entity.Endereco;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CalcFrete {

    public static ConsultaFrete calcularFrete(double peso, Endereco origem, Endereco destino){


        ConsultaFrete ct = new ConsultaFrete();

        double vl = peso*1.0;


        //caso o ddd seja igual são somados 1 dia na data
        if(origem.getDDD().equals(destino.getDDD())){
            vl = vl-(vl*0.5);

            ct.setDataConsulta(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            ct.setDataPrevista(LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            ct.setCepDestino(destino.getCep());
            ct.setCepOrigem(origem.getCep());
        }else if(origem.getUf().equals(destino.getUf())){
            vl = vl-(vl*0.75);

            ct.setDataConsulta(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            ct.setDataPrevista(LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            ct.setCepDestino(destino.getCep());
            ct.setCepOrigem(origem.getCep());
        }else if(!origem.getUf().equals(destino.getUf())){
            vl = peso*1.0;

            ct.setDataConsulta(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            ct.setDataPrevista(LocalDate.now().plusDays(10).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            ct.setCepDestino(destino.getCep());
            ct.setCepOrigem(origem.getCep());
        }
        ct.setPeso(peso);
        ct.setTotalFrete(vl);
        return ct;

    }
}
