package com.chaos.TestJava;

import junit.framework.Assert;
import junit.framework.TestCase;

public class ImpDBAnalysisTC extends TestCase {

	// public void testInitDB() {
	// fail("Not yet implemented");
	// }

	public void testGetTimeStr() {
		Assert.assertEquals("2015-01-02", ImpDBAnalysis.getTimeStr("2015-01-01", 1));
		Assert.assertEquals("2015-03-01", ImpDBAnalysis.getTimeStr("2015-02-28", 1));
		Assert.assertEquals("2016-01-01", ImpDBAnalysis.getTimeStr("2015-12-31", 1));
		Assert.assertEquals("2016-01-01", ImpDBAnalysis.getTimeStr("2015-01-01", 365));
		// Assert.assertEquals("2016-1-01",
		// ImpDBAnalysis.getTimeStr("2015-12-31", 1));
	}
	//
	// public void testStatisticsQuery() {
	// fail("Not yet implemented");
	// }
	//
	// public void testGetStatistics() {
	// fail("Not yet implemented");
	// }
	//
	// public void testTestOracle() {
	// fail("Not yet implemented");
	// }
	//
	// public void testMain() {
	// fail("Not yet implemented");
	// }

}
