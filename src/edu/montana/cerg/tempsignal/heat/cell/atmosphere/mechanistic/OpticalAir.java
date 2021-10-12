package edu.montana.cerg.tempsignal.heat.cell.atmosphere.mechanistic;

import org.neosimulation.neo.framework.holon.Cell;
import org.neosimulation.neo.framework.holon.Face;
import org.neosimulation.neo.framework.stateval.StateDouble;
import org.neosimulation.neo.user.AutoDynamDouble;

import edu.montana.cerg.tempsignal.heat.cell.atmosphere.Pressure;

public class OpticalAir extends AutoDynamDouble {

	private StateDouble pressure;
	private StateDouble cosZenithAngle;
	private double degToRad = (2*Math.PI/360);
	private double r = 6371/9;
	
	@Override
	public double calculate() {
		//return pressure.value / (1013 *cosZenithAngle.value);
		//The above breaks at zenith = 90 (returning infinity).  Better formula below from wikipedia
		return (pressure.value/1013) *( Math.sqrt(Math.pow(r*cosZenithAngle.value, 2) + 2*r + 1) - r*cosZenithAngle.value);
	}

	@Override
	public void setCalcDeps() {
//		Cell atmCell = ((Face)holon).getCell();	
		pressure = (StateDouble)createDependency(Pressure.class.getSimpleName());
		cosZenithAngle = (StateDouble)createDependency(CosZenithAngle.class.getSimpleName());
	}

}
