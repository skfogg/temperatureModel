package edu.montana.cerg.tempsignal.heat.cell.atmosphere;

import org.neosimulation.neo.framework.stateval.StateDouble;
import org.neosimulation.neo.user.AutoDynamDouble;

import edu.montana.cerg.tempsignal.heat.cell.atmosphere.mechanistic.Heat;

public class AirSecondOfYear extends AutoDynamDouble{

	private StateDouble annual;
	private StateDouble secondOfYear;
	private StateDouble startDay;
	
	@Override
	public double calculate() {
    	if (annual.value == 0){
    		return (secondOfYear.value % 86400) + (((int)startDay.value-1) * 86400);
        }else{
        	return secondOfYear.value;  
        }
 	}

	@Override
	public void setCalcDeps() {
		secondOfYear = (StateDouble)createDependency(SecondOfYear.class.getSimpleName());
        annual = (StateDouble)createDependency(Heat.REQ_STATE_ANNUAL_OR_NOT);
        startDay = (StateDouble)createDependency(StartDay.class.getSimpleName());	
	}

}
