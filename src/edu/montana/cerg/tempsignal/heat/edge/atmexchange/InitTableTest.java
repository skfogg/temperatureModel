package edu.montana.cerg.tempsignal.heat.edge.atmexchange;

import org.neosimulation.neo.framework.stateval.StateDouble;
import org.neosimulation.neo.user.AutoDynamDouble;

public class InitTableTest extends AutoDynamDouble {

	public static final String REQ_STATE_INIT_TEST = "TestValue";
	private StateDouble testValue;
	
	@Override
	public double calculate() {
		
		return 0;

	}

	@Override
	public void setCalcDeps() {
		
//		testValue = (StateDouble)createDependency(REQ_STATE_INIT_TEST);
		
	}

}
