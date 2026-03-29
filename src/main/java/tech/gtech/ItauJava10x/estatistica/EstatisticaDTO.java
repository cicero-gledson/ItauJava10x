package tech.gtech.ItauJava10x.estatistica;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Getter
@RequiredArgsConstructor
public class EstatisticaDTO {
    private final long count;
    private final BigDecimal avg;
    private final BigDecimal max;
    private final BigDecimal min;
    private final BigDecimal sum;
}
