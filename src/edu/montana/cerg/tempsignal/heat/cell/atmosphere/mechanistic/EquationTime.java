package edu.montana.cerg.tempsignal.heat.cell.atmosphere.mechanistic;

import org.neosimulation.neo.framework.holon.Cell;
import org.neosimulation.neo.framework.holon.Face;
import org.neosimulation.neo.framework.stateval.StateDouble;
import org.neosimulation.neo.user.AutoDynamDouble;

import edu.montana.cerg.tempsignal.heat.cell.atmosphere.FractionalJDay;

public class EquationTime extends AutoDynamDouble {

	private StateDouble fractionalJDay;
	private double degToRad = (2*Math.PI/360);
	
	@Override
	public double calculate() {
		double f = degToRad*(279.575 + 0.9856*fractionalJDay.value);
//		return (-104.7*Math.sin(f)+596.2*Math.sin(2*f)+4.3*Math.sin(3*f)-12.7*Math.sin(4*f)
//				-429.3*Math.cos(f)-2.0*Math.cos(2*f)+19.3*Math.cos(3*f))/
//				3600;
		return 0;
	}

	@Override
	public void setCalcDeps() 
	{
//		Cell atmCell = ((Face)holon).getCell();
		fractionalJDay = (StateDouble)createDependency( 
				FractionalJDay.class.getSimpleName());
	}

}
