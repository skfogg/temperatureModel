package edu.montana.cerg.tempsignal.heat.cell.atmosphere.mechanistic;

import org.neosimulation.neo.framework.stateval.StateDouble;
import org.neosimulation.neo.user.AutoDynamDouble;

import edu.montana.cerg.tempsignal.heat.cell.atmosphere.AirSecondOfYear;


public class RHumidAnnual extends AutoDynamDouble {

	private StateDouble rHumidAnnualMean;
	private StateDouble rHumidAnnualAmp;
	private StateDouble rHumidAnnualPhase;
	private StateDouble airSecondOfYear;
	
	@Override
	public double calculate() {
		
		return rHumidAnnualMean.value + rHumidAnnualAmp.value *
				Math.sin((2 * Math.PI / 31557600) * airSecondOfYear.value + rHumidAnnualPhase.value);
	}

	@Override
	public void setCalcDeps() {
	
		airSecondOfYear = (StateDouble)createDependency(AirSecondOfYear.class.getSimpleName());
		rHumidAnnualMean = (StateDouble)createDependency(Heat.REQ_STATE_R_HUMID_ANNUAL_MEAN);
		rHumidAnnualAmp = (StateDouble)createDependency(Heat.REQ_STATE_R_HUMID_ANNUAL_AMP);
		rHumidAnnualPhase = (StateDouble)createDependency(Heat.REQ_STATE_R_HUMID_ANNUAL_PHASE);
			
	}

}
