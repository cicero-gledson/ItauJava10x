package tech.gtech.ItauJava10x.estatistica;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.gtech.ItauJava10x.transacao.TransacaoService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/estatistica")
public class EstatisticaController implements tech.gtech.ItauJava10x.doc.EstatisticaController {

    private final TransacaoService transacaoService;

    @Value("${transacoes.estatisticas.segundos}")
    private int segundosDeTransacoes;


    public EstatisticaController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @GetMapping
    public ResponseEntity<EstatisticaDTO> estatisticas(){

        List<BigDecimal> valoresTransacoes = transacaoService.ultimasTranssacoes(segundosDeTransacoes);
        EstatisticaDTO estatisticaDTO = transacaoService.estaticasTransacoes(valoresTransacoes);
        return ResponseEntity.ok(estatisticaDTO);
    }


}
