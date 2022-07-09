import com.crud.saladereuniao.saladereuniao.entity.Endereco;
import com.crud.saladereuniao.saladereuniao.service.ConsultaFreteService;
import com.crud.saladereuniao.saladereuniao.service.EnderecoService;
import com.crud.saladereuniao.saladereuniao.utils.calc.CalcFrete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class main {
    public static void main(String[] args){
        RestTemplate restTemplate = new RestTemplate();
        String uri = "http://viacep.com.br/ws/06867450/json/";
        String uri2 = "http://viacep.com.br/ws/11432505/json/";

        Endereco origem = restTemplate.getForObject(uri, Endereco.class);
        Endereco destino = restTemplate.getForObject(uri2, Endereco.class);

        System.out.println("DDD da origem");
        System.out.println(origem.getDDD());
        System.out.println("DDD do destino");
        System.out.println(destino.getDDD());





        System.out.println(CalcFrete.calcularFrete(10,origem,destino));

    }
}
