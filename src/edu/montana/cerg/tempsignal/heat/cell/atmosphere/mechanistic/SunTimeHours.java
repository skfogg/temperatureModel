package edu.montana.cerg.tempsignal.heat.cell.atmosphere.mechanistic;

import org.neosimulation.neo.framework.holon.Cell;
import org.neosimulation.neo.framework.holon.Face;
import org.neosimulation.neo.framework.stateval.StateDouble;
import org.neosimulation.neo.user.AutoDynamDouble;

import edu.montana.cerg.tempsignal.heat.cell.atmosphere.FractionalJDay;

public class SunTimeHours extends AutoDynamDouble{

	private StateDouble fractionalJDay;
	@Override
	public double calculate() {
		return (fractionalJDay.value % 1) * 24;
	}

	@Override
	public void setCalcDeps() {
//		Cell atmCell = ((Face)holon).getCell();
		fractionalJDay = (StateDouble)createDependency(FractionalJDay.class.getSimpleName());
		
	}

}
