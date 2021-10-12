package edu.montana.cerg.tempsignal.heat.cell.hyporheic;

import org.neosimulation.neo.framework.stateval.StateDouble;
import org.neosimulation.neo.user.AutoDynamDouble;

import edu.montana.cerg.tempsignal.heat.Constants;

public class Temp extends AutoDynamDouble {
		
	//private StateDouble densityWater;
	//private StateDouble densitySediment;
	//private double spHeatWater;
	//private StateDouble spHeatSediment;
	private StateDouble volume;
	//private StateDouble porosity;
	private StateDouble heat;
	private StateDouble spHeatAq;
	
	@Override
	public double calculate() 
	{		
// Not sure what this 'if' block is. Variable 'a' is not used. 
		//if (Double.isNaN(heat.value)){
			//int a=2;
		//}
		
		return (heat.value / (spHeatAq.value * volume.value)) - Constants.C_MINUS_K;
		
	}

	@Override
	public double initialize()
	{
        if (getStateVal().isNull())
        {
            holon.getSimulationModel().getLogger().logSevere(
                    "Temperature not initialized in " +
                    holon.getName());
            return 0;
        }
        else
        {
            return ((StateDouble)getStateVal()).value;
        }
	}
	
	@Override
	public void setCalcDeps() 
	{
		//densityWater = (StateDouble)createDependency(Heat.REQ_STATE_DENSITY_WATER);
		//densitySediment = (StateDouble)createDependency(Heat.REQ_STATE_DENSITY_SEDIMENT);
		//spHeatWater = Constants.SP_HEAT_WATER_10;
		//spHeatSediment = (StateDouble)createDependency(Heat.REQ_STATE_SPHEAT_SEDIMENT);
		volume = (StateDouble)createDependency(HypoVolume.class.getSimpleName());
		//porosity = (StateDouble)createDependency(Heat.REQ_STATE_POROSITY);
		heat = (StateDouble)createDependency(Heat.class.getSimpleName());
		spHeatAq = (StateDouble)createDependency(AquiferSpHeat.class.getSimpleName());
	}

	@Override
	public void setInitDeps()
	{
	}
	
}
