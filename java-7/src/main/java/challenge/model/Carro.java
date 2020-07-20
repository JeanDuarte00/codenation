package challenge.model;

import challenge.enumType.Cor;
import challenge.exception.EstacionamentoException;
import challenge.exception.MotoristaException;

import java.util.Objects;

public class Carro {

    private final Motorista motorista;

    private final String placa;

    private final Cor cor;

    private Carro(Motorista motorista, String placa, Cor cor) throws NullPointerException {
        this.motorista = motorista;
        this.placa = placa;
        this.cor = cor;
        CarroHandler.handleCarro(this);
    }

    public Motorista getMotorista() {
        return motorista;
    }

    public String getPlaca() {
        return placa;
    }

    public Cor getCor() {
        return cor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Carro carro = (Carro) o;
        return Objects.equals(motorista, carro.motorista) &&
                Objects.equals(placa, carro.placa) &&
                cor == carro.cor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(motorista, placa, cor);
    }

    @Override
    public String toString() {
        return "Carro{" +
                "motorista=" + motorista +
                ", placa='" + placa + '\'' +
                ", cor=" + cor +
                '}';
    }

    public static CarroBuilder builder() {
        return new CarroBuilder();
    }


    public static class CarroBuilder {

        private Motorista motorista;

        private String placa;

        private Cor cor;

        private CarroBuilder() {
        }

        public CarroBuilder withMotorista(Motorista motorista) {
            CarroHandler.handleMotorista(motorista);
            this.motorista = motorista;
            return this;
        }

        public CarroBuilder withPlaca(String placa) throws NullPointerException {
            CarroHandler.handlePlaca(placa);
            this.placa = placa;
            return this;
        }

        public CarroBuilder withCor(Cor cor) throws NullPointerException {
            CarroHandler.handleCor(cor);
            this.cor = cor;
            return this;
        }

        public Carro build() {
            return new Carro(motorista, placa, cor);
        }
    }

    public static class CarroHandler {
        private static void handleCarro (Carro carro) throws NullPointerException {

            handleMotorista(carro.getMotorista());
            handlePlaca(carro.getPlaca());
            handleCor(carro.getCor());
        }

        private static void handleMotorista (Motorista motorista) {
            if (motorista == null) {
                throw new EstacionamentoException("Não pode entrar carro autonomo");
            }
        }

        private static void handlePlaca (String placa) throws NullPointerException {
            if (placa.isEmpty()) {
                throw new NullPointerException("Deve passar a placa do carro");
            }
        }

        private static void handleCor (Cor cor) throws NullPointerException {
            if (cor == null) {
                throw new NullPointerException("Precisa passar uma cor");
            }
        }

    }
}
