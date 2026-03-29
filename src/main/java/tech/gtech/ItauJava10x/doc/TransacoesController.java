package tech.gtech.ItauJava10x.doc;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.gtech.ItauJava10x.transacao.Transacao;
import tech.gtech.ItauJava10x.transacao.TransacaoDTO;
import tech.gtech.ItauJava10x.transacao.TransacaoRepository;
import tech.gtech.ItauJava10x.transacao.TransacaoService;


@Tag(name = "Transações", description = "Operações relacionadas ao processamento de transações financeiras")
public interface TransacoesController {


    @Operation(summary = "Receber nova transação", description = "Valida e armazena uma transação no sistema.")
    @ApiResponse(responseCode = "201", description = "Transação criada e validada com sucesso")
    @ApiResponse(responseCode = "422", description = "Erro de validação (ex: valor negativo)")@PostMapping
    @ApiResponse(responseCode = "400", description = "Requisição inválida. Pode ocorrer erro de formatação no JSON ou campos ausentes.")
    ResponseEntity<Transacao> adicionar(@RequestBody TransacaoDTO transacaoDTO);

    @Operation(summary = "Apagar transações", description = "Apaga todas as transações registradas")
    @ApiResponse(responseCode = "200", description = "Transações apagadas.")
    ResponseEntity deletarDados();

}



