package br.com.codenation;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class StatisticUtil {

	public static int average(int[] elements) {

		int average = 0;
		int total = 0;

		for ( int element: elements ){
			total += element;
		}

		average = total / elements.length;

		return (int)average;
	}

	public static int mode(int[] elements) {

		AtomicInteger higher = new AtomicInteger(0);
		AtomicInteger mode = new AtomicInteger(0);
		Map<Integer, Integer> mapeamentoQuantidadeValor = new HashMap<>();

		for (int element : elements) {
			Integer itemValue = mapeamentoQuantidadeValor.get(element);
			if (itemValue == null) {
				mapeamentoQuantidadeValor.put(element, 1);
			}else {
				mapeamentoQuantidadeValor.replace(element, ++itemValue);
			}

		}

		mapeamentoQuantidadeValor.forEach( (key, value) -> {
			if (value > higher.get()) {
				higher.set(value);
				mode.set(key);
			}
		});

		return mode.get();
	}

	public static int median(int[] elements) {
		int median = 0;

		Arrays.sort(elements);

		int len = elements.length;

		if (len % 2 == 0) {
			int index = (int)(Math.floor(elements.length / 2));
			median = (int)(elements[index] + elements[index-1])/2;

		} else {
			int index = (int)(Math.floor(elements.length / 2));
			median = elements[index];
		}

		return median;
	}
}