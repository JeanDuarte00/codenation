package challenge;

import challenge.EstacionamentoException;
import challenge.MotoristaException;
import java.util.Objects;

public class Motorista {

    private final String nome;

    private final int idade;

    private final int pontos;

    private final String habilitacao;

    private Motorista(String nome, int idade, int pontos, String habilitacao) throws NullPointerException, IllegalArgumentException, EstacionamentoException {
        this.nome = nome;
        this.idade = idade;
        this.pontos = pontos;
        this.habilitacao = habilitacao;

        MotoristaHandler.handleMotorista(this);

    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public int getPontos() {
        return pontos;
    }

    public String getHabilitacao() {
        return habilitacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Motorista motorista = (Motorista) o;
        return Objects.equals(habilitacao, motorista.habilitacao);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(habilitacao);
    }

    @Override
    public String toString() {
        return "Motorista{" +
                "nome='" + nome + '\'' +
                ", idade=" + idade +
                ", pontos=" + pontos +
                ", habilitacao='" + habilitacao + '\'' +
                '}';
    }

    public static MotoristaBuilder builder() {
        return new MotoristaBuilder();
    }


    public static class MotoristaBuilder {

        private String nome;

        private int idade;

        private int pontos;

        private String habilitacao;

        private MotoristaBuilder() {
        }

        public MotoristaBuilder withNome(String nome) {
            MotoristaHandler.handleNome(nome);
            this.nome = nome;
            return this;
        }

        public MotoristaBuilder withIdade(int idade) {
            MotoristaHandler.handleIdade(idade);
            this.idade = idade;
            return this;
        }

        public MotoristaBuilder withPontos(int pontos) {
            MotoristaHandler.handlePontos(pontos);
            this.pontos = pontos;
            return this;
        }

        public MotoristaBuilder withHabilitacao(String habilitacao) {
            MotoristaHandler.handleHabilitacao(habilitacao);
            this.habilitacao = habilitacao;
            return this;
        }


        public Motorista build() {
            return new Motorista(nome, idade, pontos, habilitacao);
        }
    }

    public static class MotoristaHandler {
        private static void handleMotorista (Motorista motorista) throws NullPointerException , IllegalArgumentException, EstacionamentoException {

            handleNome(motorista.getNome());
            handleHabilitacao(motorista.getHabilitacao());
            handleIdade(motorista.getIdade());
            handlePontos(motorista.getPontos());


        }

        private static void handleNome (String nome) throws NullPointerException {
            if (nome.isEmpty()) {
                throw new NullPointerException("Deve passar o nome do motorista");
            }
        }

        private static void handleIdade (int idade) throws IllegalArgumentException, EstacionamentoException {
            if (idade < 0) {
                throw new IllegalArgumentException("Idade invalida");
            }
            else if (idade < 18) {
                throw new EstacionamentoException("O motorista não pode ser menor de idade");
            }
        }

        private static void handlePontos (int pontos) throws IllegalArgumentException, EstacionamentoException {
            if (pontos < 0) {
                throw new IllegalArgumentException("Pontos invalidos");
            }
            else if (pontos > 20) {
                throw new EstacionamentoException("O motorista não pode dirigir, pontos na carteira acima do limite");
            }
        }

        private static void handleHabilitacao (String nome) throws MotoristaException {
            if (nome.isEmpty()) {
                throw new NullPointerException("O motorista deve ter habilitação");
            }
        }
    }
}
