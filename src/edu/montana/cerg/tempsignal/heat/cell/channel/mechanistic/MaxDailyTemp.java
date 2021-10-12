package edu.montana.cerg.tempsignal.heat.cell.channel.mechanistic;

import org.neosimulation.neo.framework.stateval.StateDouble;
import org.neosimulation.neo.user.AutoDynamDouble;

import edu.montana.cerg.tempsignal.heat.cell.channel.Temp;

public class MaxDailyTemp extends AutoDynamDouble{
	
	private StateDouble thisTemp;
	private double maxTemp;
	private StateDouble newDay;
	
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

		if(newDay.value ==1){
			maxTemp = -999;
		}
		if(thisTemp.value > maxTemp){
			maxTemp = thisTemp.value;
		}
		return maxTemp;
	}

	@Override
	public void setCalcDeps() {
		thisTemp = (StateDouble)createDependency(Temp.class.getSimpleName());
		newDay = (StateDouble)createDependency(NewDay.class.getSimpleName());
	}
}
