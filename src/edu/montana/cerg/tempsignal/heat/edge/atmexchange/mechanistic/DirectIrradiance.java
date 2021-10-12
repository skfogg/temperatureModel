package edu.montana.cerg.tempsignal.heat.edge.atmexchange.mechanistic;

import org.neosimulation.neo.framework.holon.Cell;
import org.neosimulation.neo.framework.holon.Face;
import org.neosimulation.neo.framework.stateval.StateDouble;
import org.neosimulation.neo.user.AutoDynamDouble;
import org.neosimulation.neo.user.ManualDynamDouble;

import edu.montana.cerg.tempsignal.heat.cell.atmosphere.mechanistic.CosZenithAngle;
import edu.montana.cerg.tempsignal.heat.cell.atmosphere.mechanistic.GroundPerpRad;

/**
 * Calculates the direct shortwave radiation hitting a horizontal surface  
 * in same units as Solar Constant parameter.  (Model requires Solar Constant
 * in kJ m<sup><small>-2</small></sup> sec<sup><small>-1</small></sup>).  
 * Equation from  Campbell and Norman 1998. Environmental Biophysics, 2e.  
 * 
 * @author Sam Carlson
 *
 */
public class DirectIrradiance extends AutoDynamDouble {

	private StateDouble groundPerpRad;
	private StateDouble cosZenithAngle;
		
	@Override
	public double calculate() {
	  return groundPerpRad.value * cosZenithAngle.value ;
	}
	@Override
	public void setCalcDeps() {
		Cell atmCell = ((Face)holon).getEdge().getFromCell();
		groundPerpRad = (StateDouble)createDependency(atmCell, GroundPerpRad.class.getSimpleName());
		cosZenithAngle = (StateDouble)createDependency(atmCell, CosZenithAngle.class.getSimpleName());
	}
	
}
