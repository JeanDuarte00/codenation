package br.com.codenation.service;

import br.com.codenation.ententies.Jogador;
import br.com.codenation.ententies.Time;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class TimeService {


    List<Time> listaTime = new ArrayList();

    public TimeService () {

    }

    public void criar (Time time) throws IllegalArgumentException {
        if (this.existTime(time)) {
            throw new IllegalArgumentException("Time já existe");
        }
        this.listaTime.add(time);
    }

    private boolean existTime (Time time) {
        return this.listaTime.contains(time);
    }


    private boolean existTimeById (Long id) {
        AtomicBoolean resp = new AtomicBoolean(false);
        this.getListaTime().forEach(time -> {
            if ( time.getId() == id ) { resp.set(true); return;}
        });
        return resp.get();
    }

    private Time getTimeById (Long id) {
        AtomicReference<Time> found = new AtomicReference<>();
        this.getListaTime().forEach(time -> {
            if ( time.getId() == id ){ found.set(time); return;}
        });
        return found.get();
    }

    public List<Time> getListaTime() {
        return listaTime;
    }

    public List<Long> getListaIdTime() {
        List<Long> ids = new ArrayList<>();
        this.listaTime.forEach(time->{
            ids.add(time.getId());
        });
        return ids;
    }

    public void setListaTime(List<Time> listaTime) {
        this.listaTime = listaTime;
    }

    public void incluirJogador(Jogador jogador) {
        Time time = this.getTimeById(jogador.getId());
        if(time != null){
            if (!this.existJogador(jogador)) {
                time.adicionarJogador(jogador);
            }
        }
    }

    public boolean existJogador (Jogador jogador) {
        return this.getTimeById(jogador.getIdTime()).getListaJogador().contains(jogador);
    }

    public List<Long> getListaIdJogadoresTime(Long idTime) {
        List<Long> ids = new ArrayList<>();
        this.getTimeById(idTime).getListaJogador().forEach(jogador -> {
            ids.add(jogador.getId());
        });
        return ids;
    }

    public Jogador buscarJogadorById (Long idTime, Long idJogador) {
        AtomicReference<Jogador> found = new AtomicReference<>();
        this.getTimeById(idTime).getListaJogador().forEach(jogador -> {
            if (jogador.getId() == idJogador){
                found.set(jogador);
                return;
            }
        });
        return found.get();
    }

    public void definirCaptao(Long idTime, Long idJogador) {
        Time time = this.getTimeById(idTime);
        Jogador jogador = this.buscarJogadorById(idTime, idJogador);
        time.setCaptao(jogador);
    }

    public Long buscarCaptao(Long idTime) {
        Time time = this.getTimeById(idTime);
        return time.getCaptao().getId();
    }
}
