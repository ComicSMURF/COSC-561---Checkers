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
		assertEquals(ai.returnCoord(6, 3), 26);
		assertEquals(ai.returnCoord(7, 6), 32);
		assertEquals(ai.returnCoord(0, 0), -1);
	}
}
