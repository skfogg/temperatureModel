package edu.montana.cerg.tempsignal.heat.edge.atmexchange;

import org.neosimulation.neo.framework.holon.Cell;
import org.neosimulation.neo.framework.holon.Face;
import org.neosimulation.neo.framework.stateval.StateGeneric;
import org.neosimulation.neo.user.ManualDynamDouble;
import org.neosimulation.neo.user.interpolator.InterpolatorFactory;
import org.neosimulation.neo.user.interpolator.InterpolatorFactoryException;
import org.neosimulation.neo.user.interpolator.MathInterpolator;

public class ShortwaveGross extends ManualDynamDouble {

	private static final String REQ_STATE_SW_RAD_TABLE = "ExtInsolationTable";

	private MathInterpolator interp;
	
	@Override
	public double calculate() {
		
		return interp.getValue(holon.getSimulationModel().getTimeKeeper().getCurrentTime());
	}
	
	@Override
	public double initialize() 
	{
    	return calculate();	
	}

	@Override
	public void setCalcDeps() {
		
		Cell atmCell = ((Face)holon).getEdge().getFromCell();
		String tableName = (String)((StateGeneric<?>)createDependency(atmCell, REQ_STATE_SW_RAD_TABLE)).value;		

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
