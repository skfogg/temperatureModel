package edu.montana.cerg.tempsignal.heat.cell.atmosphere;

import org.neosimulation.neo.framework.stateval.StateDouble;
import org.neosimulation.neo.user.InitDynamDouble;

import edu.montana.cerg.tempsignal.heat.cell.atmosphere.mechanistic.Heat;

public class StartDay extends InitDynamDouble{

	public StateDouble startDOY;
	@Override
	public double initialize() {
		return startDOY.value;
	}

	@Override
	public void setInitDeps() {
		startDOY = (StateDouble)createDependency(Heat.REQ_STATE_START_DAY);
	}
}
