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

	public void definirCapitao(Long idTime, Long idJogador) {
		this.timeService.definirCaptao(idTime, idJogador);
		//throw new UnsupportedOperationException();
	}

	public Long buscarCapitaoDoTime(Long idTime) {
		return this.timeService.buscarCaptao(idTime);
		//throw new UnsupportedOperationException();
	}

	public String buscarNomeJogador(Long idTime, Long idJogador) {
		return this.timeService.buscarJogadorById(idTime, idJogador).getNome();
		//throw new UnsupportedOperationException();
	}

	public String buscarNomeTime(Long idTime) {
		throw new UnsupportedOperationException();
	}

	public List<Long> buscarJogadoresDoTime(Long idTime) {
		return this.timeService.getListaIdJogadoresTime(idTime);
		//throw new UnsupportedOperationException();
	}

	public Long buscarMelhorJogadorDoTime(Long idTime) {
		throw new UnsupportedOperationException();
	}

	public Long buscarJogadorMaisVelho(Long idTime) {
		throw new UnsupportedOperationException();
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
		throw new UnsupportedOperationException();
	}

	public BigDecimal buscarSalarioDoJogador(Long idJogador) {
		throw new UnsupportedOperationException();
	}

	public List<Long> buscarTopJogadores(Integer top) {
		throw new UnsupportedOperationException();
	}

}
