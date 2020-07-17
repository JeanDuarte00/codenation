package br.com.codenation.calculadora;

public class DesconstoSalarioIRRF {
    double faixaA = 0;
    double faixaB = 7.5;
    double faixaC = 15;


    public double buscarTaxaDesconto(double salario) {

        if (salario > 3000 && salario <= 6000) {
            return faixaB;
        }
        if (salario > 6000) {
            return faixaC;
        }
        return faixaA;
    }
}

