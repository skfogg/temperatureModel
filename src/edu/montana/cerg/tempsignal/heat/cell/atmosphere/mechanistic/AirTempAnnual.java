package edu.montana.cerg.tempsignal.heat.cell.atmosphere.mechanistic;

import org.neosimulation.neo.framework.stateval.StateDouble;
import org.neosimulation.neo.user.AutoDynamDouble;

import edu.montana.cerg.tempsignal.heat.cell.atmosphere.AirSecondOfYear;

public class AirTempAnnual extends AutoDynamDouble{

	private StateDouble airTempAnnualMean;
	private StateDouble airTempAnnualAmp;
	private StateDouble airTempAnnualPhase;
	private StateDouble airSecondOfYear;
	
	@Override
	public double calculate() {
		
		return airTempAnnualMean.value + airTempAnnualAmp.value *
				Math.sin((2 * Math.PI / 31557600) * airSecondOfYear.value + airTempAnnualPhase.value);
	}

	@Override
	public void setCalcDeps() {
		
		airSecondOfYear = (StateDouble)createDependency(AirSecondOfYear.class.getSimpleName());
		airTempAnnualMean = (StateDouble)createDependency(Heat.REQ_STATE_AIR_TEMP_ANNUAL_MEAN);
		airTempAnnualAmp = (StateDouble)createDependency(Heat.REQ_STATE_AIR_TEMP_ANNUAL_AMP);
		airTempAnnualPhase = (StateDouble)createDependency(Heat.REQ_STATE_AIR_TEMP_ANNUAL_PHASE);
		
	}

}
