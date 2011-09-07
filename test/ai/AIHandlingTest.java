package ai;

import static org.junit.Assert.*;

import org.junit.Test;

public class AIHandlingTest {

	@Test
	public void randomExample1(){
		CoordConventionMapper ai = new CoordConventionMapper();
		assertEquals(ai.convertCoord(13),new Integer[] {3,0});
		assertEquals(ai.convertCoord(32),new Integer[] {7,6});
		assertEquals(ai.convertCoord(14),new Integer[] {3,2});
	}
}
