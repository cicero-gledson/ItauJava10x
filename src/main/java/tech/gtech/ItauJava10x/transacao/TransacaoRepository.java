package tech.gtech.ItauJava10x.transacao;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TransacaoRepository {
    private List<TransacaoDTO> listaDeTransacoes = new ArrayList<>();

    public void salvarDados(TransacaoDTO transacaoDTO){
        listaDeTransacoes.add(transacaoDTO);
    }

    public void deletarDados(){
        listaDeTransacoes.clear();
    }

    public List<TransacaoDTO> getListaDeTransacoes() {
        return listaDeTransacoes;
    }
}
