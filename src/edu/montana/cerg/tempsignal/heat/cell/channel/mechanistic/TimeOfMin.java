package edu.montana.cerg.tempsignal.heat.cell.channel.mechanistic;

import org.neosimulation.neo.framework.stateval.StateDouble;
import org.neosimulation.neo.user.AutoDynamDouble;

import edu.montana.cerg.tempsignal.heat.cell.channel.Temp;

public class TimeOfMin extends AutoDynamDouble{
	
	private StateDouble thisTemp;
	private double minTemp;
	private StateDouble newDay;
	private double timeOfMin;
	private StateDouble timeOfDay;
	
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
			minTemp = 999;
		}
		if(thisTemp.value < minTemp){
			minTemp = thisTemp.value;
			timeOfMin = timeOfDay.value;
		}
		
		return timeOfMin;
	}

	@Override
	public void setCalcDeps() {
		thisTemp = (StateDouble)createDependency(Temp.class.getSimpleName());
		newDay = (StateDouble)createDependency(NewDay.class.getSimpleName());
		timeOfDay = (StateDouble)createDependency(TimeOfDay.class.getSimpleName());
	}

}
