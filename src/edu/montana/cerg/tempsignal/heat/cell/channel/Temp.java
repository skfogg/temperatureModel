package edu.montana.cerg.tempsignal.heat.cell.channel;

import org.neosimulation.neo.framework.stateval.StateDouble;
import org.neosimulation.neo.user.AutoDynamDouble;

import edu.montana.cerg.tempsignal.heat.Constants;

public class Temp extends AutoDynamDouble {       
       
    private StateDouble water;
    private StateDouble heat;
 
    
    @Override
    public double calculate()
    {

    	return (heat.value / (water.value * Constants.SP_HEAT_WATER_10)) - Constants.C_MINUS_K;
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
    	water = (StateDouble)createDependency(Constants.REQ_CURRENCY_ADVECT_MEDIUM);
        heat = (StateDouble)createDependency(Heat.class.getSimpleName());
    }    
    
    @Override
    public void setInitDeps()
    {
    }
    
}
