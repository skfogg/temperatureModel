package edu.montana.cerg.tempsignal.heat.cell.atmosphere.mechanistic;

import org.neosimulation.neo.framework.stateval.StateDouble;
import org.neosimulation.neo.user.AutoDynamDouble;

import edu.montana.cerg.tempsignal.heat.edge.atmexchange.Heat;

/**
 * Returns solar noon offset in degrees for a given longitude
 * 
 * @author sam.carlson
 */

public class DegFromSolarNoon extends AutoDynamDouble{

	private StateDouble sunTimeHours;
	private StateDouble solarNoonTime;
	
	@Override
	public double calculate() {
		return ( 15 * (sunTimeHours.value - solarNoonTime.value));
	}

	@Override
	public void setCalcDeps() {
		sunTimeHours = (StateDouble)createDependency(SunTimeHours.class.getSimpleName());
		solarNoonTime = (StateDouble)createDependency(SolarNoonTime.class.getSimpleName());
		
	}

}
