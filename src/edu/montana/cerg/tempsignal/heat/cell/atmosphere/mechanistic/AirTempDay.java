package edu.montana.cerg.tempsignal.heat.cell.atmosphere.mechanistic;

import org.neosimulation.neo.framework.stateval.StateDouble;
import org.neosimulation.neo.user.AutoDynamDouble;

import edu.montana.cerg.tempsignal.heat.cell.atmosphere.AirSecondOfYear;

public class AirTempDay extends AutoDynamDouble {

	/**
	 * 
	 * 
	 */
	private StateDouble airTempDayAmp;
	private StateDouble airSecondOfYear;
	private StateDouble airTempDayMean;
	private StateDouble airTempDayPhase;
	
	@Override
	public double calculate() {
		
		return airTempDayMean.value + airTempDayAmp.value *
				Math.sin((2 * Math.PI / 86400) * airSecondOfYear.value + airTempDayPhase.value);
		
	}

	@Override
	public void setCalcDeps() {
		
		airSecondOfYear = (StateDouble)createDependency(AirSecondOfYear.class.getSimpleName());
		airTempDayAmp = (StateDouble)createDependency(AirTempDayAmp.class.getSimpleName());
		airTempDayMean = (StateDouble)createDependency(Heat.REQ_STATE_AIR_TEMP_DAY_MEAN);
		airTempDayPhase = (StateDouble)createDependency(Heat.REQ_STATE_AIR_TEMP_DAY_PHASE);
	}

}
