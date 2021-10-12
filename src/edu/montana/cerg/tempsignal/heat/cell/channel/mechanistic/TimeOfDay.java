package edu.montana.cerg.tempsignal.heat.cell.channel.mechanistic;

import org.neosimulation.neo.framework.time.TimeKeeper;
import org.neosimulation.neo.user.AutoDynamDouble;

public class TimeOfDay extends AutoDynamDouble{

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
		return (timeKeeper.getCurrentTime()/86400 - Math.floor(timeKeeper.getCurrentTime()/86400)) * 24;
	}

	@Override
	public void setCalcDeps() {
		timeKeeper = holon.getSimulationModel().getTimeKeeper();
	}

}
