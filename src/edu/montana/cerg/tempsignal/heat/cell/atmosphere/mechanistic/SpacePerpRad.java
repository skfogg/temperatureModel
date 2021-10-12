package edu.montana.cerg.tempsignal.heat.cell.atmosphere.mechanistic;

import org.neosimulation.neo.framework.holon.Cell;
import org.neosimulation.neo.framework.holon.Face;
import org.neosimulation.neo.framework.stateval.StateDouble;
import org.neosimulation.neo.user.InitDynamDouble;


public class SpacePerpRad extends InitDynamDouble {
	
	private StateDouble solarConstant;
	
	//@return constant radiation flux outside the atmosphere in kw/m^2
	@Override
	public double initialize() {
		return solarConstant.value;
	}

	@Override
	public void setInitDeps() {
//		Cell atmCell = ((Face)holon).getEdge().getFromCell();		
		solarConstant = (StateDouble)createDependency(Heat.REQ_STATE_SOLAR_CONST);
	}

}
