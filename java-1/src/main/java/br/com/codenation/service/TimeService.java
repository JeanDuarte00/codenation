package br.com.codenation.service;

import br.com.codenation.ententies.Jogador;
import br.com.codenation.ententies.Time;
import br.com.codenation.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.exceptions.TimeNaoEncontradoException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class TimeService {


    List<Time> listaTime = new ArrayList();

    public TimeService () {

    }

    public void criar (Time time) throws IdentificadorUtilizadoException {
        if (this.existTimeById(time.getId())) {
            throw new IdentificadorUtilizadoException("Time já existe");
        }
        this.listaTime.add(time);
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

    public void incluirJogador(Jogador jogador) throws TimeNaoEncontradoException {
        Time time = this.getTimeById(jogador.getIdTime());

        if ( time == null ) throw new TimeNaoEncontradoException();
        if (this.existJogador(jogador)) throw new IdentificadorUtilizadoException();

        this.getTimeById(jogador.getIdTime()).adicionarJogador(jogador);

    }

    public boolean existJogador (Jogador jogador) {
        AtomicBoolean found = new AtomicBoolean(false);
        this.getTimeById(jogador.getIdTime()).getListaJogador().forEach(jogadorAtual -> {
            if (jogador.getId() == jogadorAtual.getId()) {
                found.set(true);
                return;
            }
        });
        return found.get();
    }

    public List<Long> getListaIdJogadoresTime(Long idTime) {
        List<Long> ids = new ArrayList<>();
        Time time = this.getTimeById(idTime);
        if (time == null) {
            throw new TimeNaoEncontradoException();
        }
        time.getListaJogador().forEach(jogador -> {
            ids.add(jogador.getId());
        });
        return ids;
    }

    public Jogador buscarJogadorById (Long idJogador) throws JogadorNaoEncontradoException {
        AtomicReference<Jogador> found = new AtomicReference<>();
        this.getListaTime().forEach(time -> {
          time.getListaJogador().forEach(jogador -> {
              if (jogador.getId() == idJogador){
                  found.set(jogador);
                  return;
              }
          });
        });
        if (found.get() == null)
            throw new JogadorNaoEncontradoException();
        return found.get();
    }

    public void definirCaptao(Long idJogador) throws JogadorNaoEncontradoException{
        AtomicBoolean found = new AtomicBoolean(false);
        this.listaTime.forEach(time -> {
            time.getListaJogador().forEach( jogador -> {
                if (jogador.getId() == idJogador) {
                    time.setCaptao(jogador);
                    found.set(true);
                }
            });
        });
        if (!found.get())
            throw new JogadorNaoEncontradoException();
    }

    public Long buscarCaptao(Long idTime) throws CapitaoNaoInformadoException{
        Time time = this.getTimeById(idTime);
        if(time == null) throw new TimeNaoEncontradoException();
        if (time.getCaptao() == null) throw new CapitaoNaoInformadoException();
        return time.getCaptao().getId();
    }

    public String getTimeName(Long idTime) throws TimeNaoEncontradoException{
        Time time = this.getTimeById(idTime);
        if ( time == null)
            throw  new TimeNaoEncontradoException();
        return time.getNome();
    }

    public Long getMelhorJogador(Long idTime) {
        AtomicReference<Long> melhorJogadorId = new AtomicReference<>();
        melhorJogadorId.set(Long.MIN_VALUE);

        AtomicReference<Integer> nivel = new AtomicReference<>();
        nivel.set(Integer.MIN_VALUE);

        Time time = this.getTimeById(idTime);
        if(time == null) throw new TimeNaoEncontradoException();
        List<Jogador> jogadores = time.getListaJogador();
        jogadores.forEach(jogador -> {
            if (jogador.getNivelHabilidade() > nivel.get()) {
                melhorJogadorId.set(jogador.getId());
                nivel.set(jogador.getNivelHabilidade());
            }
        });
        return melhorJogadorId.get();
    }

    public Long getjogadorMaisVelho(Long idTime) {
        AtomicReference<Jogador> maisVelho = new AtomicReference<>();
        AtomicReference<LocalDate> nascimento = new AtomicReference<>();
        nascimento.set(LocalDate.now());

        Time time = this.getTimeById(idTime);
        if (time == null) throw new TimeNaoEncontradoException();

        List<Jogador> jogadores = time.getListaJogador();
        jogadores.forEach(jogador -> {
            if (jogador.getDataNascimento().isBefore( nascimento.get() )) {
                maisVelho.set(jogador);
                nascimento.set(jogador.getDataNascimento());
            }
        });
        return maisVelho.get().getId();
    }

    public Long getJogadorMaisCaro(Long idTime) {
        AtomicReference<Jogador> maisCaro = new AtomicReference<>();
        AtomicReference<BigDecimal> valor = new AtomicReference<>();
        valor.set(BigDecimal.ZERO);

        Time time = this.getTimeById(idTime);
        if (time == null) throw new TimeNaoEncontradoException();

        List<Jogador> jogadores = time.getListaJogador();
        jogadores.forEach(jogador -> {
            if (jogador.getSalario().compareTo(valor.get()) == 1) {
                maisCaro.set(jogador);
                valor.set(jogador.getSalario());
            }
        });
        return maisCaro.get().getId();
    }

    public BigDecimal getSalarioJogador(Long idJogador) throws JogadorNaoEncontradoException{
        AtomicReference<BigDecimal> salario = new AtomicReference<>();
        this.listaTime.forEach( time -> {
            time.getListaJogador().forEach( jogador -> {
                if (idJogador == jogador.getId()) {
                    salario.set(jogador.getSalario());
                    return;
                }
            });
        });
        if (salario.get() == null)
            throw new JogadorNaoEncontradoException();
        return salario.get();
    }

    public List<Long> getTopNJogadores(Integer top) {
        List<Long> listTop = new ArrayList<>();

        this.listaTime.forEach( time -> {

            time.getListaJogador().sort(Comparator.comparing(Jogador::getNivelHabilidade).reversed());
            for (int i = 0; i < top; i++) {
                listTop.add(time.getListaJogador().get(i).getId());
            }

        });
        return listTop;
    }
}
