package br.com.codenation.calculadora;

public class DescontoSalarioINSS {

    double faixaA = 8;
    double faixaB = 9;
    double faixaC = 11;


    public double buscarTaxaDesconto(double salario) {
        if (salario <= 1500){
            return faixaA;
        }
        if (salario > 1500 && salario <= 4000) {
            return faixaB;
        }
        if (salario > 4000) {
            return faixaC;
        }
        return 0;
    }
}
