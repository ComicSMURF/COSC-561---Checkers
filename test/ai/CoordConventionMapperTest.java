package ai;

import static org.junit.Assert.*;

import org.junit.Test;

public class CoordConventionMapperTest {

	@Test
	public void randomExample1(){
		CoordConventionMapper ai = new CoordConventionMapper();
		assertEquals(ai.getCoordOf(13),new Integer[] {3,0});
		assertEquals(ai.getCoordOf(32),new Integer[] {7,6});
		assertEquals(ai.getCoordOf(14),new Integer[] {3,2});
/*		System.out.println(ai.returnCoord(0, 1));
		System.out.println(ai.returnCoord(0, 3));
		System.out.println(ai.returnCoord(0, 5));
		System.out.println(ai.returnCoord(0, 7));
		System.out.println(ai.returnCoord(1, 0));
		System.out.println(ai.returnCoord(1, 2));
		System.out.println(ai.returnCoord(1, 4));
		System.out.println(ai.returnCoord(1, 6));
		System.out.println(ai.returnCoord(2, 1));
		System.out.println(ai.returnCoord(2, 3));
		System.out.println(ai.returnCoord(2, 5));
		System.out.println(ai.returnCoord(2, 7));
		System.out.println(ai.returnCoord(3, 0));
		System.out.println(ai.returnCoord(3, 2));
		System.out.println(ai.returnCoord(3, 4));
		System.out.println(ai.returnCoord(3, 6));
		System.out.println(ai.returnCoord(4, 1));
		System.out.println(ai.returnCoord(4, 3));
		System.out.println(ai.returnCoord(4, 5));
		System.out.println(ai.returnCoord(4, 7));
		System.out.println(ai.returnCoord(5, 0));
		System.out.println(ai.returnCoord(5, 2));
		System.out.println(ai.returnCoord(5, 4));
		System.out.println(ai.returnCoord(5, 6));
		System.out.println(ai.returnCoord(6, 1));
		System.out.println(ai.returnCoord(6, 3));
		System.out.println(ai.returnCoord(6, 5));
		System.out.println(ai.returnCoord(6, 7));
		System.out.println(ai.returnCoord(7, 0));
		System.out.println(ai.returnCoord(7, 2));
		System.out.println(ai.returnCoord(7, 4));
		System.out.println(ai.returnCoord(7, 6));*/
		assertEquals(ai.returnCoord(6, 3), 26);
		assertEquals(ai.returnCoord(7, 6), 32);
		assertEquals(ai.returnCoord(0, 0), -1);
	}
}
