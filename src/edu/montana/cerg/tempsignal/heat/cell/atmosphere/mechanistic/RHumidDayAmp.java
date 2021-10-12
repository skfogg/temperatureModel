package edu.montana.cerg.tempsignal.heat.cell.atmosphere.mechanistic;

import org.neosimulation.neo.framework.stateval.StateDouble;
import org.neosimulation.neo.user.AutoDynamDouble;

import edu.montana.cerg.tempsignal.heat.cell.atmosphere.AirSecondOfYear;


public class RHumidDayAmp extends AutoDynamDouble{

	private StateDouble rHumidDayAmpMean;
	private StateDouble rHumidDayAmpAmp;
	private StateDouble rHumidDayAmpPhase;
	private StateDouble airSecondOfYear;
	
	@Override
	public double calculate() {
	
		return rHumidDayAmpMean.value + rHumidDayAmpAmp.value *
				Math.sin((2 * Math.PI / 31557600) * airSecondOfYear.value + rHumidDayAmpPhase.value);
	}

	@Override
	public void setCalcDeps() {
		
		airSecondOfYear = (StateDouble)createDependency(AirSecondOfYear.class.getSimpleName());
		rHumidDayAmpMean = (StateDouble)createDependency(Heat.REQ_STATE_R_HUMID_DAY_AMP_MEAN);
		rHumidDayAmpAmp = (StateDouble)createDependency(Heat.REQ_STATE_R_HUMID_DAY_AMP_AMP);
		rHumidDayAmpPhase = (StateDouble)createDependency(Heat.REQ_STATE_R_HUMID_DAY_AMP_PHASE);
		
	}

}
