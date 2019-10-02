package UnitTesting;

import static org.junit.Assert.*;

import org.junit.Test;

import DAO_SERVICE.size_complexity.SizeMeasurement;

public class SizeTesting {

	@Test
	public void testSize() {
		SizeMeasurement s1 = new SizeMeasurement("public static long fibonacci(long number) { ");
	
		int result = (int) s1.getSizeComplexity();
		
		assertEquals(3, result);
	}

}
