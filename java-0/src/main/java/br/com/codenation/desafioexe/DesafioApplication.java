package br.com.codenation.desafioexe;

import java.util.ArrayList;
import java.util.List;

public class DesafioApplication {

	private static final int fibonacciMax = 350;
	private static final List<Integer> lista = new ArrayList<>();

	private static int first = 0;
	private static int second = 1;

	public static List<Integer> fibonacci() {
		while(first < fibonacciMax){
			if(lista.isEmpty()){
				lista.add(0);
			}
			second += first;
			first = second - first;
			System.out.println(first);
			lista.add(first);

		}
		return lista;
	}

	public static Boolean isFibonacci(Integer a) {
		return lista.contains(a);
	}

}