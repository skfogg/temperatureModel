package edu.montana.cerg.tempsignal.heat.edge.atmexchange;

import org.neosimulation.neo.framework.holon.Cell;
import org.neosimulation.neo.framework.holon.Face;
import org.neosimulation.neo.framework.stateval.StateDouble;
import org.neosimulation.neo.user.AutoDynamDouble;

import edu.montana.cerg.tempsignal.heat.cell.atmosphere.EmissivityAir;
import edu.montana.cerg.tempsignal.heat.cell.atmosphere.Shade;

public class EmissivityAboveStream extends AutoDynamDouble {

	private StateDouble emissivityAir;
	private StateDouble shade;
	
	@Override
	public double calculate() {
		//following Stoy's recommendation, emissivity of a canopy is 0.97
		return (1-shade.value)* emissivityAir.value + shade.value*0.97;
	}

	@Override
	public void setCalcDeps() {
		Cell atmCell = ((Face)holon).getEdge().getFromCell();
		emissivityAir = (StateDouble)createDependency(atmCell,EmissivityAir.class.getSimpleName());
		shade = (StateDouble)createDependency(atmCell,Shade.class.getSimpleName());
	}

}
