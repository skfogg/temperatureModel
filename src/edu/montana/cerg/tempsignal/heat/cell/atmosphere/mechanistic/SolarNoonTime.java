package edu.montana.cerg.tempsignal.heat.cell.atmosphere.mechanistic;

import org.neosimulation.neo.framework.holon.Cell;
import org.neosimulation.neo.framework.holon.Face;
import org.neosimulation.neo.framework.stateval.StateDouble;
import org.neosimulation.neo.user.AutoDynamDouble;


public class SolarNoonTime extends AutoDynamDouble {

	private StateDouble longitudeCorrection;
	private StateDouble equationTime;
	
	@Override
	public double calculate() {
		return 12 - longitudeCorrection.value - equationTime.value;
	}

	@Override
	public void setCalcDeps() {
//		Cell atmCell = ((Face)holon).getCell();	
		longitudeCorrection = (StateDouble)createDependency(Heat.REQ_STATE_LONG_CORRECT);
		equationTime = (StateDouble)createDependency(EquationTime.class.getSimpleName());
		
	}

}
