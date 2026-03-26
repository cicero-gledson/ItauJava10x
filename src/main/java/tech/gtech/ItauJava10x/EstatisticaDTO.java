package tech.gtech.ItauJava10x;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class EstatisticaDTO {
    private final long count;
    private final double avg;
    private final double max;
    private final double min;
    private final double sum;
}
