package edu.montana.cerg.tempsignal.heat.cell.atmosphere.mechanistic;

import org.neosimulation.neo.framework.stateval.StateDouble;
import org.neosimulation.neo.user.AutoDynamDouble;

public class RelativeHumidity extends AutoDynamDouble {

	/**
	 * 
	 */
	private StateDouble rHumidDay;
	private StateDouble rHumidAnnual;

	@Override
	public double calculate() {
		
		return rHumidDay.value + rHumidAnnual.value;
		
		//return (65.31705 + 16.83007*Math.sin((2*Math.PI/31557600)*airSecondOfYear.value - 4.48410)) + 
	
		//		17.27243 * Math.sin((2*Math.PI/86400)*airSecondOfYear.value - 0.94104);
	}

	@Override
	public double initialize() {
		return calculate();	
	}
	
	@Override
	public void setCalcDeps() {
		
		rHumidDay = (StateDouble)createDependency(RHumidDay.class.getSimpleName());
		rHumidAnnual = (StateDouble)createDependency(RHumidAnnual.class.getSimpleName());
		
	}

}
