package challenge.model;

import challenge.exception.EstacionamentoException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class Estacionamento {

    private final int  limiteEstacionamento = 10;
    private List<Carro> carrosEstacionados = new ArrayList<>();

    public void estacionar(Carro carro) throws EstacionamentoException {

      this.isFree();
      this.isYounger(carro);
      this.estacionarMaisUmCarro(carro);


    }

    private void isFree () throws EstacionamentoException {

        if (this.carrosEstacionados.size() > this.limiteEstacionamento) {
            throw new EstacionamentoException("Estacionamento Lotado");
        }
    }

    private void isYounger (Carro carro) throws  EstacionamentoException {
        try {
            int idade = carro.getMotorista().getIdade();
        } catch (IllegalArgumentException err){
            throw new EstacionamentoException("Motorista do carro é de menor de idade");
        }
    }

    private boolean canPark () {
        return true;
    }

    private void estacionarMaisUmCarro (Carro carro) {
        if (this.carrosEstacionados() < this.limiteEstacionamento) {
            this.carrosEstacionados.add(carro);
        }
    }

    public int carrosEstacionados() {
        return this.carrosEstacionados.size();
    }

    public boolean carroEstacionado(Carro carro) {
        AtomicBoolean exist = new AtomicBoolean(false);
        this.carrosEstacionados.forEach( (estacionado) -> {
            if ( estacionado.getPlaca().equals(carro.getPlaca()) ) {
                exist.set(true);
            }
        });
        return exist.get();
    }
}
