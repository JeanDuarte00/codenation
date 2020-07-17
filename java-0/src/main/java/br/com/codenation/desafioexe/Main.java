package br.com.codenation.desafioexe;

import java.util.List;

public class Main {

    public static void main(String[] args) {
	// write your code here
        List res = DesafioApplication.fibonacci();
        boolean exist = DesafioApplication.isFibonacci(377);

        System.out.println("SEQUENCE: " + res);
        System.out.println("EXIST: " + exist);

    }
}
