package tech.gtech.ItauJava10x;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Service
public class TransacaoService {
    public void validarTransacao (TransacaoDTO transacaoDTO){

        if (transacaoDTO.getValor() == null) {
            throw new IllegalArgumentException("Valor não informado");
        }

        if (transacaoDTO.getDataHora() == null) {
            throw new IllegalArgumentException("Data e hora não informados");
        }

        if (transacaoDTO.getValor().compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("Transações devem ter valor maior ou igual a zero.");
        }

        if (transacaoDTO.getDataHora().isAfter(OffsetDateTime.now())){
            throw new IllegalArgumentException("Data da transação inválida");
        }

    }
}
