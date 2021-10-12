package edu.montana.cerg.tempsignal.heat.cell.channel.mechanistic;

import org.neosimulation.neo.framework.time.TimeKeeper;
import org.neosimulation.neo.user.AutoDynamDouble;

public class DayCount extends AutoDynamDouble{

	private TimeKeeper timeKeeper;
	
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
		return (Math.floor((timeKeeper.getCurrentTime()-3600)/86400));

	}

	@Override
	public void setCalcDeps() {
		timeKeeper = holon.getSimulationModel().getTimeKeeper();
		
	}

}
