package edu.montana.cerg.tempsignal.heat.edge.atmexchange.mechanistic;

import org.neosimulation.neo.framework.holon.Cell;
import org.neosimulation.neo.framework.holon.Face;
import org.neosimulation.neo.framework.stateval.StateDouble;
import org.neosimulation.neo.user.AutoDynamDouble;
import org.neosimulation.neo.user.ManualDynamDouble;

import edu.montana.cerg.tempsignal.heat.edge.atmexchange.Heat;
/**
 * Controls the net shortwave radiation through the atmosphere. 
 * Returns value of radiation (kJ m<sup><small>-2</small></sup> sec<sup><small>-1</small></sup>) before being partially reflected by water surface.
 * 
 * <p>This is a simple calculator called by <code>Heat</code>
 * 
 * <p><b>References:</b></p>
 * <ul style="list-style-type: none; line-height: 200%">
 * <li> Campbell and Norman, 1998. Environmental Biophysics, 2e.</li>
 * </ul>
 * 
 * @see Heat
 * @author Katie Fogg
 */
public class ShortwaveGross extends ManualDynamDouble {

	public static final String REQ_STATE_DIRECT_IRRADIANCE = "DirectIrradiance"; 
	public static final String REQ_STATE_DIFFUSE_IRRADIANCE = "DiffuseIrradiance"; 
	
	private StateDouble directIrradiance;
	private StateDouble diffuseIrradiance;
	
	@Override
	public double initialize() 
	{
		
		return calculate();
	}
	
	@Override
	public double calculate() {
	
		return directIrradiance.value + diffuseIrradiance.value;
	}

	@Override
	public void setCalcDeps() {
	
//		Cell airCell = ((Face)holon).getEdge().getFromCell();
//		Face solarFace = airCell.getFacesArray("heat","heat.solarradflux.mechanistic")[0];
		directIrradiance = (StateDouble)createDependency(REQ_STATE_DIRECT_IRRADIANCE);
		diffuseIrradiance = (StateDouble)createDependency(REQ_STATE_DIFFUSE_IRRADIANCE);
	}
	
	 
}
