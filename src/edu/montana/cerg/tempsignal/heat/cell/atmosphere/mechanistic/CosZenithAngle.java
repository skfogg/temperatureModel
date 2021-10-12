package edu.montana.cerg.tempsignal.heat.cell.atmosphere.mechanistic;

import org.neosimulation.neo.framework.holon.Cell;
import org.neosimulation.neo.framework.holon.Face;
import org.neosimulation.neo.framework.stateval.StateDouble;
import org.neosimulation.neo.user.AutoDynamDouble;


/**
 * Returns the cosine of the Zenith angle of the sun based on solar declination,
 * latitude, and offset from central meridian of local time zone.  Returns 0 when
 * sun is below the horizon.
 * 
 * <p><b>References:</b></p>
 * <ul style="list-style-type: none; line-height: 200%">
 * <li>Campbell and Norman. 1998. Environmental Biophysics, 2e.</li>
 * </ul>
 * 
 * @author Sam Carlson, Geoff Poole
 */

public class CosZenithAngle extends AutoDynamDouble {

	private StateDouble solarDeclination;
	private StateDouble degFromSolarNoon;
	private StateDouble latitude;
	private double degToRad = (2*Math.PI/360);
	
	@Override
	public double calculate() {
		return Math.max(0, Math.sin(latitude.value*degToRad)*Math.sin(solarDeclination.value*degToRad)+
				Math.cos(latitude.value*degToRad)*Math.cos(degToRad*solarDeclination.value)*Math.cos(degToRad*degFromSolarNoon.value));
	}

	@Override
	public void setCalcDeps() {
//		Cell atmCell = ((Face)holon).getCell();	
		latitude = (StateDouble)createDependency(Heat.REQ_STATE_LAT);
		solarDeclination = (StateDouble)createDependency(SolarDeclination.class.getSimpleName());
		degFromSolarNoon = (StateDouble)createDependency(DegFromSolarNoon.class.getSimpleName());
	}

}
