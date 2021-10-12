package edu.montana.cerg.tempsignal.heat.edge.atmexchange;

import org.neosimulation.neo.framework.holon.Cell;
import org.neosimulation.neo.framework.holon.Face;
import org.neosimulation.neo.framework.stateval.StateDouble;
import org.neosimulation.neo.user.ManualDynamDouble;

import edu.montana.cerg.tempsignal.heat.Constants;
import edu.montana.cerg.tempsignal.heat.cell.atmosphere.EmissivityAir;
import edu.montana.cerg.tempsignal.heat.cell.atmosphere.Shade;


/**
 * Controls the flux of longwave radiation emitted by the atmosphere that will 
 * be transformed into heat through an energy balance. 
 * 
 * <p>This is a manual dynam called by <code>Heat</code>.
 * 
 * @see Heat
 * @author Rob Payn
 */
public class LongwaveFluxAir extends ManualDynamDouble {
	
	private static final String REQ_STATE_TEMP_K = "TempKelvin";  
	
	
	/**
	 * Effective daily emissivity (fraction of energy)
	 */
	private StateDouble emissivityAboveStream;
	/**
	 * Current air temperature (&deg;K)
	 */
	private StateDouble tempKelvin;
	

	/**
	 * Calculate the longwave radiation emitted by the atmosphere
	 * 
	 * @return Longwave radiation (kJ m<sup><small>-2</small></sup> sec<sup><small>-1</small></sup>)
	 */
	@Override
	public double calculate() {

		return  emissivityAboveStream.value * Constants.STEFBOLTZ * Math.pow((tempKelvin.value), 4.0);
	}

	/**
	 * Calculate the initial longwave radiation emitted by the atmosphere
	 * 
	 * @return Longwave radiation (kJ m<sup><small>-2</small></sup> sec<sup><small>-1</small></sup>)
	 */
	@Override
	public double initialize() 
	{
		return calculate();
	}

	/**
	 * Gets the references needed to calculate longwave radiation from the air.
	 */
	
	@Override
	public void setCalcDeps()
	{
		Cell atmCell = ((Face)holon).getEdge().getFromCell();
		
		emissivityAboveStream = (StateDouble)createDependency(EmissivityAboveStream.class.getSimpleName());
		tempKelvin = (StateDouble)createDependency(atmCell,REQ_STATE_TEMP_K);
		
	}
	

}
