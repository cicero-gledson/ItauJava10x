package tech.gtech.ItauJava10x.doc;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.gtech.ItauJava10x.estatistica.EstatisticaDTO;
import tech.gtech.ItauJava10x.transacao.TransacaoService;

import java.math.BigDecimal;
import java.util.List;

@Tag(name="Estatísticas", description = "Devolve estatísticas de transações realizadas no tempo configurado")
public interface EstatisticaController {

    @Operation(summary = "Devolve as estatísticas", description = "Devolve quantidade, soma e médias das transações, e maior e menor valor de transação. ")
    @ApiResponse(responseCode="200", description = "Quantidade, soma, média, maior e menor valores das transações")
    ResponseEntity<EstatisticaDTO> estatisticas();


}
