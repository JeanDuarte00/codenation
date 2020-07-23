package br.com.codenation;

import br.com.codenation.ententies.Jogador;
import br.com.codenation.ententies.Time;
import br.com.codenation.service.TimeService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class DesafioMeuTimeApplication implements MeuTimeInterface {

	TimeService timeService = new TimeService();

	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
		this.timeService.criar(new Time(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario));
		//throw new UnsupportedOperationException();
	}

	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
		this.timeService.incluirJogador(new Jogador(id, idTime, nome, dataNascimento, nivelHabilidade, salario));
		//throw new UnsupportedOperationException();
	}

	public void definirCapitao(Long idJogador) {
		this.timeService.definirCaptao(idJogador);
		//throw new UnsupportedOperationException();
	}

	public Long buscarCapitaoDoTime(Long idTime) {
		return this.timeService.buscarCaptao(idTime);
		//throw new UnsupportedOperationException();
	}

	public String buscarNomeJogador(Long idJogador) {
		return this.timeService.buscarJogadorById(idJogador).getNome();
		//throw new UnsupportedOperationException();
	}

	public String buscarNomeTime(Long idTime) {
		return this.timeService.getTimeName(idTime);
		//throw new UnsupportedOperationException();
	}

	public List<Long> buscarJogadoresDoTime(Long idTime) {
		return this.timeService.getListaIdJogadoresTime(idTime);
		//throw new UnsupportedOperationException();
	}

	public Long buscarMelhorJogadorDoTime(Long idTime) {
		return this.timeService.getMelhorJogador(idTime);
		//throw new UnsupportedOperationException();
	}

	public Long buscarJogadorMaisVelho(Long idTime) {
		return this.timeService.getjogadorMaisVelho(idTime);
		//throw new UnsupportedOperationException();
	}

	/***
	 *
	 * @return lista dos ids dos times
	 */
	public List<Long> buscarTimes() {
		return this.timeService.getListaIdTime();
		//throw new UnsupportedOperationException();
	}

	public Long buscarJogadorMaiorSalario(Long idTime) {
		return this.timeService.getJogadorMaisCaro(idTime);
		//throw new UnsupportedOperationException();
	}

	public BigDecimal buscarSalarioDoJogador(Long idJogador) {
		return this.timeService.getSalarioJogador(idJogador);
		//throw new UnsupportedOperationException();
	}

	public List<Long> buscarTopJogadores(Integer top) {
		return this.timeService.getTopNJogadores(top);
		//throw new UnsupportedOperationException();
	}

}
