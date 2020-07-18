package br.com.codenation;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StatisticUtilTest {

	@Test
	public void testAverage() {
		assertEquals(3, StatisticUtil.average(new int[] {1,2,3,4,5}));

		assertEquals(4, StatisticUtil.average(new int[] {1,2,5,10,2,3,4,5}));
	}

	@Test
	public void testMode() {
		assertEquals(3, StatisticUtil.mode(new int[] {1,2,3,3}));

		assertEquals(1, StatisticUtil.mode(new int[] {1,1,1,2,2,3,3}));
	}
	
	@Test
	public void testMedian() {
		assertEquals(3, StatisticUtil.median(new int[] {1,2,3,4,5})); //impar

		assertEquals(3, StatisticUtil.median(new int[] {1,2,3,9,4,5}));// par

		assertEquals(3, StatisticUtil.median(new int[] {1,2,3,9,0,4,5}));//impar
		//0,1,2,3,4,5,9
	}
}
