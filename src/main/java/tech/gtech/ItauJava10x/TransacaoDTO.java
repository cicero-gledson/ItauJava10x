package tech.gtech.ItauJava10x;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransacaoDTO {
    private BigDecimal valor;
    private OffsetDateTime dataHora;
}
