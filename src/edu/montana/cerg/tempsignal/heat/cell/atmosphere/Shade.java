package edu.montana.cerg.tempsignal.heat.cell.atmosphere;

import org.neosimulation.neo.framework.stateval.StateGeneric;
import org.neosimulation.neo.user.AutoDynamDouble;
import org.neosimulation.neo.user.interpolator.InterpolatorFactory;
import org.neosimulation.neo.user.interpolator.InterpolatorFactoryException;
import org.neosimulation.neo.user.interpolator.MathInterpolator;

public class Shade extends AutoDynamDouble {

    private static final String REQ_STATE_SHADE_TABLE = "ShadeFactor";
	
	
	/**
	 * Interpolator containing table input data for pressure
	 */
	private MathInterpolator interp;

	
	@Override
	public double calculate() 
	{
		return interp.getValue(holon.getSimulationModel().getTimeKeeper().getCurrentTime());
	}

	
	@Override
	public double initialize() 
	{
		return calculate();	
	}

	
	@Override
	public void setCalcDeps() 
	{
		
		String tableName = (String)((StateGeneric<?>)createDependency(
                REQ_STATE_SHADE_TABLE)).value;		

 
		try
		{
			interp = InterpolatorFactory.getInstance().createMathInterpolator(
					tableName, holon.getSimulationModel());
		}
		catch(InterpolatorFactoryException e)
		{
            holon.getSimulationModel().getLogger().logSevere(
                    stateVal.getName() + " in " +
                    holon.getName() + " cannot create an interpolator");
		}
		
		
	}


}
