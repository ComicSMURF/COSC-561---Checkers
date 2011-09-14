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
	}
}
