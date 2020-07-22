package challenge;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class Estacionamento {

    private final int idadeIdoso = 60;
    private final int  limiteEstacionamento = 10;
    private List<Carro> carrosEstacionados = new ArrayList<>();

    public void estacionar(Carro carro) throws EstacionamentoException {

        this.liberarVaga();
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
        if (this.carrosEstacionados.size() < this.limiteEstacionamento) {
            return true;
        }
        return false;
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

    public void liberarVaga () throws EstacionamentoException {
        Carro carroQueSaiu = null;
        if ( !this.canPark() ) {
            int index = 0;
            for (Carro carro: carrosEstacionados) {
                if ( !isSenior(carro.getMotorista()) ){
                    carroQueSaiu = this.carrosEstacionados.get(index);
                    this.carrosEstacionados.remove(index);
                    return;
                }
                index++;
            }
            throw new EstacionamentoException("Todos os motoristas são Seniores, portanto, sem vaga");
        }
    }

    private boolean isSenior (Motorista motorista) {
        if (motorista.getIdade() >= this.idadeIdoso ){
            return true;
        }
        return false;
    }
}
