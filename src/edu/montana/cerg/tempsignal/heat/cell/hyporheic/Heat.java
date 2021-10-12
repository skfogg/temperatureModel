package edu.montana.cerg.tempsignal.heat.cell.hyporheic;

import org.neosimulation.neo.framework.stateval.StateDouble;
import org.neosimulation.neo.user.CellPoolDynamDouble;

import edu.montana.cerg.tempsignal.heat.Constants;

public class Heat extends CellPoolDynamDouble{

	//public static final String REQ_STATE_DENSITY_WATER = "DensityWater";
	//public static final String REQ_STATE_DENSITY_SEDIMENT = "DensitySediment";
	//public static final String REQ_STATE_SPHEAT_WATER = "SpHeatWater";
	public static final String REQ_STATE_SPHEAT_SEDIMENT = "SpHeatSediment";
	public static final String REQ_STATE_VOLUME = "Volume";
	public static final String REQ_STATE_POROSITY = "Porosity";
	public static final String REQ_STATE_TEMP = "Temp";
	public static final String REQ_STATE_MEAN_RESIDENCE_TIME = "MeanResidenceTime";
	public static final String REQ_STATE_RETURNING_FRACTION = "ReturningFraction";
	//public static final String REQ_STATE_AREA = "Area";
	public static final String REQ_STATE_HYPO_RECHARGE_VELOCITY = "RechargeVelocity";
	public static final String REQ_STATE_HYPO_VOL = "HypoVolume";
	
	
	//private StateDouble densityWater;
	//private StateDouble densitySediment;
	//private double spHeatWater;
	//private StateDouble spHeatSediment;
	private StateDouble volume;
	//private StateDouble porosity;
	private StateDouble temp;
	private StateDouble spHeatAq;
	
	@Override
    public double initialize()
	{		

// Calculates KJ of heat required to have the aquifer at the initialized temperature
// using the average density and specific heat of water and sediment, weighted by
// porosity.
		
		return (temp.value + Constants.C_MINUS_K) *
    		spHeatAq.value * volume.value;
    }	
	
	
	@Override
    public void setInitDeps()
    {
		//densityWater = (StateDouble)createDependency(REQ_STATE_DENSITY_WATER);
		//densitySediment = (StateDouble)createDependency(REQ_STATE_DENSITY_SEDIMENT);
		//spHeatWater = Constants.SP_HEAT_WATER_10;
		//spHeatSediment = (StateDouble)createDependency(REQ_STATE_SPHEAT_SEDIMENT);
		volume = (StateDouble)createDependency(HypoVolume.class.getSimpleName());
		//porosity = (StateDouble)createDependency(REQ_STATE_POROSITY);
		temp = (StateDouble)createDependency(REQ_STATE_TEMP);
		spHeatAq = (StateDouble)createDependency(AquiferSpHeat.class.getSimpleName());
		
    }
	
}
