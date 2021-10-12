package edu.montana.cerg.tempsignal.heat.cell.hyporheic;

import org.neosimulation.neo.framework.stateval.StateDouble;
import org.neosimulation.neo.user.InitDynamDouble;

import edu.montana.cerg.tempsignal.heat.Constants;

public class AquiferSpHeat extends InitDynamDouble{

	private StateDouble porosity;
	private StateDouble spHeatSediment;
	
	@Override
	public double initialize() {
		
		return (porosity.value * Constants.SP_HEAT_WATER_10) + ((1 - porosity.value) * spHeatSediment.value);
	}

	@Override
	public void setInitDeps() {
		porosity = (StateDouble)createDependency(Heat.REQ_STATE_POROSITY);
		spHeatSediment = (StateDouble)createDependency(Heat.REQ_STATE_SPHEAT_SEDIMENT);
	
	}

}
