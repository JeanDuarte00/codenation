package br.com.codenation.calculadora;

public class Main {

    public static void main(String[] args) {

        Long res = new CalculadoraSalario().calcularSalarioLiquido(5000);

        System.out.println("RES: " + res);

    }
}
