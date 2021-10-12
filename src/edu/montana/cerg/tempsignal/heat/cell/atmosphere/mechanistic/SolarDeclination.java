package edu.montana.cerg.tempsignal.heat.cell.atmosphere.mechanistic;

import org.neosimulation.neo.framework.holon.Cell;
import org.neosimulation.neo.framework.holon.Face;
import org.neosimulation.neo.framework.stateval.StateDouble;
import org.neosimulation.neo.user.AutoDynamDouble;

import edu.montana.cerg.tempsignal.heat.cell.atmosphere.FractionalJDay;
import edu.montana.cerg.tempsignal.heat.edge.atmexchange.Heat;

/**
 * Calculates solar declination in degrees from fractional JDay
 * 
 * @author sam.carlson
 */
public class SolarDeclination extends AutoDynamDouble {

	private StateDouble fractionalJDay;
	private double degToRad = (2*Math.PI/360);
	
	@Override
	public double calculate() {
		return (1/degToRad)*Math.asin(0.39785*Math.sin(degToRad*(278.97 + 0.9856*fractionalJDay.value + 1.9165*Math.sin(degToRad*(356.6 + 0.9856*fractionalJDay.value)))));
	}

	@Override
	public void setCalcDeps() {
//		Cell atmCell = ((Face)holon).getCell();
		fractionalJDay = (StateDouble)createDependency(
				FractionalJDay.class.getSimpleName()
				);
	}

}
