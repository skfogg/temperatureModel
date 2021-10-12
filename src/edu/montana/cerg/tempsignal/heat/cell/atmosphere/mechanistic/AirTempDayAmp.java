package edu.montana.cerg.tempsignal.heat.cell.atmosphere.mechanistic;

import org.neosimulation.neo.framework.stateval.StateDouble;
import org.neosimulation.neo.user.AutoDynamDouble;

import edu.montana.cerg.tempsignal.heat.cell.atmosphere.AirSecondOfYear;

public class AirTempDayAmp extends AutoDynamDouble {

	private StateDouble airTempDayAmpMean;
	private StateDouble airTempDayAmpAmp;
	private StateDouble airTempDayAmpPhase;
	private StateDouble airSecondOfYear;
	
	@Override
	public double calculate() {
		
		return airTempDayAmpMean.value + airTempDayAmpAmp.value *
				Math.sin((2 * Math.PI / 31557600) * airSecondOfYear.value + airTempDayAmpPhase.value);
	}

	@Override
	public void setCalcDeps() {
		
		airSecondOfYear = (StateDouble)createDependency(AirSecondOfYear.class.getSimpleName());
		airTempDayAmpMean = (StateDouble)createDependency(Heat.REQ_STATE_AIR_TEMP_DAY_AMP_MEAN);
		airTempDayAmpAmp = (StateDouble)createDependency(Heat.REQ_STATE_AIR_TEMP_DAY_AMP_AMP);
		airTempDayAmpPhase = (StateDouble)createDependency(Heat.REQ_STATE_AIR_TEMP_DAY_AMP_PHASE);
	
	}

}
