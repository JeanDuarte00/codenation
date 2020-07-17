package br.com.codenation.calculadora;


public class CalculadoraSalario {

	private final double salarioMinimo = 1039.0;

	public long calcularSalarioLiquido(double salarioBase) {
		double salarioLiquido = 0.0;

		if (!this.isAteSalarioMinimo(salarioBase)) {
			salarioLiquido = this.calcularIrrf(this.calcularInss(salarioBase));
		}

		return Math.round(salarioLiquido);
	}

	private boolean isAteSalarioMinimo (double salarioBase) {
		return salarioBase <= this.salarioMinimo;
	}
	
	private double calcularInss(double salarioBase) {
		return this.aplicarDesconto(salarioBase, this.buscarDescontoInss(salarioBase));
	}

	private double calcularIrrf(double salarioBase) {
		return this.aplicarDesconto(salarioBase, this.buscarDescontoIrrf(salarioBase));
	}

	private double buscarDescontoInss (double salario) {
		return new DescontoSalarioINSS().buscarTaxaDesconto(salario);
	}

	private double buscarDescontoIrrf (double salario) {
		return new DesconstoSalarioIRRF().buscarTaxaDesconto(salario);
	}

	private double aplicarDesconto (double salario, double desconto) {
		return salario - ((salario * desconto) / 100);
	}

}
