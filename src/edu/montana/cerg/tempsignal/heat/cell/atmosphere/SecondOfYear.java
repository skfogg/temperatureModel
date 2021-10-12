package edu.montana.cerg.tempsignal.heat.cell.atmosphere;

import org.neosimulation.neo.framework.stateval.StateDouble;
import org.neosimulation.neo.framework.time.TimeKeeper;
import org.neosimulation.neo.user.AutoDynamDouble;


public class SecondOfYear extends AutoDynamDouble{

	private TimeKeeper timeKeeper;
	private StateDouble startDay;
	
	@Override
	public double calculate() {
		
		return (timeKeeper.getCurrentTime() + (startDay.value-1)*86400) % ((86400*365*4)+86400);
	}

	@Override
	public void setCalcDeps() {
		timeKeeper = holon.getSimulationModel().getTimeKeeper();	
		startDay = (StateDouble)createDependency(StartDay.class.getSimpleName());
	}

}
