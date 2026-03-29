package tech.gtech.ItauJava10x.transacao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tech.gtech.ItauJava10x.estatistica.EstatisticaDTO;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;
import java.util.List;

import static java.math.BigDecimal.ROUND_HALF_UP;
@Slf4j
@Service
public class TransacaoService {

    private final TransacaoRepository transacaoRepository;

    public TransacaoService(TransacaoRepository transacaoRepository) {
        this.transacaoRepository = transacaoRepository;
    }

    public void validarTransacao (TransacaoDTO transacaoDTO){
        log.debug("Iniciando validação da transação: {}", transacaoDTO);

        if (transacaoDTO.getValor() == null) {
            log.warn("Validação falhou: Valor da transação não informado.");
            throw new IllegalArgumentException("Valor não informado");
        }

        if (transacaoDTO.getDataHora() == null) {
            log.warn("Validação falhou: Data e hora da transação não informadas.");
            throw new IllegalArgumentException("Data e hora não informados");
        }

        if (transacaoDTO.getValor().compareTo(BigDecimal.ZERO) < 0){
            log.warn("Validação falhou: Valor negativo recebido ({}).", transacaoDTO.getValor());
            throw new IllegalArgumentException("Transações devem ter valor maior ou igual a zero.");
        }

        if (transacaoDTO.getDataHora().isAfter(OffsetDateTime.now())){
            log.warn("Validação falhou: Data da transação no futuro ({}).", transacaoDTO.getDataHora());
            throw new IllegalArgumentException("Data da transação inválida");
        }

        log.debug("Transação validada com sucesso.");
    }
    public List<BigDecimal> ultimasTranssacoes (int aPartirDeQuantosSegundos){
        log.debug("Buscando transações ocorridas nos últimos {} segundos.", aPartirDeQuantosSegundos);
        OffsetDateTime momentoInicial = OffsetDateTime.now().minusSeconds(aPartirDeQuantosSegundos);

        List<BigDecimal> transacoesFiltradas = transacaoRepository.getListaDeTransacoes()
                .stream()
                .filter(x -> !x.getDataHora().isBefore(momentoInicial))
                .map(TransacaoDTO::getValor)
                .toList();

        // Usamos INFO aqui porque é um dado relevante para a auditoria de negócio
        log.info("Filtro concluído: {} transações encontradas no período.", transacoesFiltradas.size());

        return transacoesFiltradas;
    }

    public EstatisticaDTO estaticasTransacoes(List<BigDecimal> valoresTransacoes){
        long count = valoresTransacoes.size();

        log.debug("Iniciando cálculo de estatísticas para {} transações.", count);

        if (count == 0) {
            log.info("Nenhuma transação recebida para cálculo. Retornando estatísticas zeradas.");
            return new EstatisticaDTO(0, BigDecimal.ZERO,
                    BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
        }

        BigDecimal sum = valoresTransacoes.stream().reduce(BigDecimal.ZERO,BigDecimal::add);
        BigDecimal min = valoresTransacoes.stream().min(BigDecimal::compareTo).orElse(BigDecimal.ZERO).setScale(2, RoundingMode.HALF_UP);
        BigDecimal max = valoresTransacoes.stream().max(BigDecimal::compareTo).orElse(BigDecimal.ZERO).setScale(2, RoundingMode.HALF_UP);
        BigDecimal avg = sum.divide(BigDecimal.valueOf(count), 2, RoundingMode.HALF_UP).setScale(2, RoundingMode.HALF_UP);

        sum = sum.setScale(2, RoundingMode.HALF_UP);

        EstatisticaDTO estatisticaDTO = new EstatisticaDTO(count, avg, max, min, sum);

        log.info("Estatísticas calculadas com sucesso: {}", estatisticaDTO);
        return estatisticaDTO;
    }

}
