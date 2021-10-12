package edu.montana.cerg.tempsignal.heat.edge.atmexchange;

import org.neosimulation.neo.framework.holon.Cell;
import org.neosimulation.neo.framework.holon.Face;
import org.neosimulation.neo.framework.stateval.StateDouble;
import org.neosimulation.neo.user.ManualDynamDouble;

import edu.montana.cerg.tempsignal.heat.Constants;
import edu.montana.cerg.tempsignal.heat.cell.channel.mechanistic.EmissivityWater;


/**
 * <p>Calculates the longwave radiation emitted by the water in
 * the "from" patch, based on temperature and the Stefan-Boltzmann constant.</p>
 * <p>A value for emissivity of water other than 0.97 can be provided by adding
 * a parameter called "EmissivityWater" to channel cells, either in the default_celltype
 * table or in the initial values table for channel cells.
 *  
 * <p>This is a manual dynam called by <code>LongwaveFluxNet</code>
 * 
 * <p><b>References:</b></p>
 * <ul style="list-style-type: none; line-height: 200%">
 * <li>Evans, E. C., G. R. McGregor, and G. E. Petts (1998) River energy budgets with special reference to 
 * 		river bed processes. Hydrological Processes 12, 575-595.</li>
 * </ul>

 * @author robert.payn
 */
public class LongwaveFluxWater extends ManualDynamDouble {
	private static final String REQ_STATE_TEMP_C = "Temp";  
	
	/**
	 * Emissivity of water (fraction of energy)
	 */
	private StateDouble emissivityWater;
	/**
	 * Water temperature in "to" patch (C)
	 */
	private StateDouble tempC;
	/**
	 * Calculates the longwave radiation emitted by water
	 * 
	 * @return longwave radiation (kJ m<sup><small>-2</small></sup> sec<sup><small>-1</small></sup>)
	 */
	@Override
	public double calculate() 
	{
		return -emissivityWater.value * Constants.STEFBOLTZ * Math.pow((tempC.value + Constants.C_MINUS_K), 4.0);	
	}
	
	/**
	 * Calculates the initial longwave radiation emitted by water
	 * 
	 * @return longwave radiation (kJ m<sup><small>-2</small></sup> sec<sup><small>-1</small></sup>)
	 */
	@Override
	public double initialize() 
	{
		return calculate();
	}

	/**
	 * Define dependencies for calculation of longwave radiation emitted by water
	 * 
	 * @see wren.heat.patch.liquid.Emissivity
	 * @see wren.heat.patch.liquid.TempKelvin
	 */

	@Override
	public void setCalcDeps()
	{

		Cell toCell = ((Face)holon).getEdge().getToCell();
		
		emissivityWater = (StateDouble)createDependency(toCell,EmissivityWater.class.getSimpleName());
		tempC = (StateDouble)createDependency(toCell,REQ_STATE_TEMP_C);
	}


}
