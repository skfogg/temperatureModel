package edu.montana.cerg.tempsignal.heat.cell.atmosphere;

import org.neosimulation.neo.framework.stateval.StateDouble;
import org.neosimulation.neo.user.AutoDynamDouble;

public class SatVaporPressureSlope extends AutoDynamDouble {

	private StateDouble tempCelsius;
	private StateDouble vaporPressureSatAir;
	
	public double calculate()
	{
		return ((4098 * vaporPressureSatAir.value)/(Math.pow((tempCelsius.value + 237.3), 2))) * 10;
	}

	@Override
	public void setCalcDeps() {
		tempCelsius = (StateDouble)createDependency(edu.montana.cerg.tempsignal.heat.cell.atmosphere.Temp.class.getSimpleName());
		vaporPressureSatAir = (StateDouble)createDependency(VaporPressureSatAir.class.getSimpleName());
	}
}
