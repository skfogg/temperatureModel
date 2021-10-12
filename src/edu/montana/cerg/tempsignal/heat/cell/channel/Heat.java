package edu.montana.cerg.tempsignal.heat.cell.channel;

import org.neosimulation.neo.framework.stateval.StateDouble;
import org.neosimulation.neo.user.CellPoolDynamDouble;

import edu.montana.cerg.tempsignal.heat.Constants;

public class Heat extends CellPoolDynamDouble {
    
    public static final String REQ_STATE_TOCELL_AREA = "Area";
    public static final String REQ_STATE_HYPO_RECHARGE_VELOCITY = "RechargeVelocity";
    public static final String REQ_STATE_MEAN_RESIDENCE_TIME = "MeanResidenceTime";
        
    private StateDouble water;
    private StateDouble temp;
    
    @Override
    public double initialize()
    {
       return water.value * (temp.value + Constants.C_MINUS_K) * Constants.SP_HEAT_WATER_10;        
    }
    
    @Override
    public void setInitDeps()
    {
        water = (StateDouble)createDependency(Constants.REQ_CURRENCY_ADVECT_MEDIUM);
        temp = (StateDouble)createDependency(Temp.class.getSimpleName());      
    }

}
