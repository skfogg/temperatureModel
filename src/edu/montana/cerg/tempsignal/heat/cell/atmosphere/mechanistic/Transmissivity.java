package edu.montana.cerg.tempsignal.heat.cell.atmosphere.mechanistic;

import org.neosimulation.neo.framework.holon.Cell;
import org.neosimulation.neo.framework.holon.Face;
import org.neosimulation.neo.framework.stateval.StateDouble;
import org.neosimulation.neo.user.AutoDynamDouble;

import edu.montana.cerg.tempsignal.heat.cell.atmosphere.AirSecondOfYear;

public class Transmissivity extends AutoDynamDouble{

	public StateDouble airSecondOfYear;
	public StateDouble transmissivityMean;
	public StateDouble transmissivityAmp;
	public StateDouble transmissivityPhase;
	
	@Override
	public double calculate() {
		
		return transmissivityMean.value + 
			(transmissivityAmp.value * Math.sin(((2 * Math.PI/31557600) * airSecondOfYear.value) + transmissivityPhase.value));
	//return 1;
	}
	
	

	@Override
	public void setCalcDeps() {
		
		airSecondOfYear = (StateDouble)createDependency(AirSecondOfYear.class.getSimpleName());
		transmissivityMean = (StateDouble)createDependency(Heat.REQ_STATE_TRANSMISSIVITY_MEAN);
		transmissivityAmp = (StateDouble)createDependency(Heat.REQ_STATE_TRANSMISSIVITY_AMP);
		transmissivityPhase = (StateDouble)createDependency(Heat.REQ_STATE_TRANSMISSIVITY_PHASE);
	
	}

}
