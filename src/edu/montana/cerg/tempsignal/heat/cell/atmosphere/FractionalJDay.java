package edu.montana.cerg.tempsignal.heat.cell.atmosphere;

import org.neosimulation.neo.framework.stateval.StateDouble;
import org.neosimulation.neo.user.AutoDynamDouble;


public class FractionalJDay extends AutoDynamDouble {

	private StateDouble secondOfYear;
	
	@Override
	public double calculate() {
		return (secondOfYear.value/86400)+1;
	}

	@Override
	public void setCalcDeps() {
		secondOfYear = (StateDouble)createDependency(SecondOfYear.class.getSimpleName());

	}

}
