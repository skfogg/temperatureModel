package edu.montana.cerg.tempsignal.heat.cell.atmosphere.mechanistic;

import org.neosimulation.neo.framework.stateval.StateDouble;
import org.neosimulation.neo.user.AutoDynamDouble;

import edu.montana.cerg.tempsignal.heat.cell.atmosphere.AirSecondOfYear;


public class RHumidDay extends AutoDynamDouble {

	private StateDouble rHumidDayAmp;
	private StateDouble airSecondOfYear;
	private StateDouble rHumidDayMean;
	private StateDouble rHumidDayPhase;
	
	@Override
	public double calculate() {
		
		return rHumidDayMean.value + rHumidDayAmp.value *
				Math.sin((2 * Math.PI / 86400) * airSecondOfYear.value + rHumidDayPhase.value);
		
	}

	@Override
	public void setCalcDeps() {
		
		airSecondOfYear = (StateDouble)createDependency(AirSecondOfYear.class.getSimpleName());
		rHumidDayAmp = (StateDouble)createDependency(RHumidDayAmp.class.getSimpleName());
		rHumidDayMean = (StateDouble)createDependency(Heat.REQ_STATE_R_HUMID_DAY_MEAN);
		rHumidDayPhase = (StateDouble)createDependency(Heat.REQ_STATE_R_HUMID_DAY_PHASE);

		
	}

}
