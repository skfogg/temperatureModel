package edu.montana.cerg.tempsignal.heat.cell.channel.mechanistic;

import org.neosimulation.neo.framework.stateval.StateDouble;
import org.neosimulation.neo.user.AutoDynamDouble;

public class NewDay extends AutoDynamDouble{

	private StateDouble thisDay;
	private double previousDay;
	
	@Override
	public double initialize(){
		return calculate();
	}
	
	@Override
	public void setInitDeps(){
		setCalcDeps();
	}
	
	@Override
	public double calculate() {

		if(thisDay.value!=previousDay){
			previousDay = thisDay.value;
			return 1;
		}
		return 0;
	}

	@Override
	public void setCalcDeps() {

		thisDay = (StateDouble)createDependency(DayCount.class.getSimpleName());
	}

}
