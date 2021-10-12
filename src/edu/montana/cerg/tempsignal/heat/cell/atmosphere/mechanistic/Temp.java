package edu.montana.cerg.tempsignal.heat.cell.atmosphere.mechanistic;


import org.neosimulation.neo.framework.holon.Cell;
import org.neosimulation.neo.framework.holon.Face;
import org.neosimulation.neo.framework.stateval.StateDouble;
import org.neosimulation.neo.framework.time.TimeKeeper;
import org.neosimulation.neo.user.AutoDynamDouble;

import edu.montana.cerg.tempsignal.heat.edge.atmexchange.mechanistic.DirectIrradiance;

public class Temp extends AutoDynamDouble {   
	
    private StateDouble airSecondOfYear;
    private StateDouble dailyOrNot;
    private StateDouble airTempDay;
    private StateDouble airTempAnnual;
    
    
    @Override
    public double calculate()
    {
    	if (dailyOrNot.value == 0)
    		return airTempAnnual.value;
    	else
    		return airTempDay.value + airTempAnnual.value;
    	
    	
    	
    	//if (dailyOrNot.value == 0)
    		//return subMeanTemp.value;
    	//else
    		//return subMeanTemp.value + subAmpTemp.value/2*Math.sin(airSecondOfYear.value*2*Math.PI*(1/subPeriod.value) +subPhase.value);
        
    }

    @Override
    public void setCalcDeps()
    {   
	
        
        dailyOrNot = (StateDouble)createDependency(Heat.REQ_STATE_DAILY_OR_NOT);
        airTempDay = (StateDouble)createDependency(AirTempDay.class.getSimpleName());
        airTempAnnual = (StateDouble)createDependency(AirTempAnnual.class.getSimpleName());
         
    }
}