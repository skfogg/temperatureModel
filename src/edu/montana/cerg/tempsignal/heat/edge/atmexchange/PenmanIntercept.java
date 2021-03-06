package edu.montana.cerg.tempsignal.heat.edge.atmexchange;

import org.neosimulation.neo.framework.stateval.StateDouble;
import org.neosimulation.neo.user.InitDynamDouble;

import edu.montana.cerg.tempsignal.heat.Constants;


/**
 * <p>Controls the intercept of the Penman type linear wind function.</p>
 * 
 * <p>Uses the value in the initialization tables, if present.  Otherwise, uses
 * the default value in <code>wren.heat.utils.Constants</code>
 * 
 * @see wren.heat.utils.Constants
 * @author robert.payn
 *
 */
public class PenmanIntercept extends InitDynamDouble {

	/**
	 * Calculates the initial Penman intercept
	 * 
	 * @return Penman intercept
	 */
	@Override
	public double initialize() {
		
		// Use the default value if an initial value was not provided
		if (stateVal.isNull()) {
			return Constants.DEF_PENMAN_INT;
		} else {
			return ((StateDouble)stateVal).value;
		}
		
	}

	/**
	 * No dependencies
	 */
	@Override
	public void setInitDeps() 
	{
	}

}
