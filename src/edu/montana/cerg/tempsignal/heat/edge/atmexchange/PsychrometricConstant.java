package edu.montana.cerg.tempsignal.heat.edge.atmexchange;

import org.neosimulation.neo.framework.holon.Cell;
import org.neosimulation.neo.framework.holon.Face;
import org.neosimulation.neo.framework.stateval.StateDouble;
import org.neosimulation.neo.user.AutoDynamDouble;

import edu.montana.cerg.tempsignal.heat.cell.atmosphere.Pressure;

public class PsychrometricConstant extends AutoDynamDouble {

	public static final String REQ_STATE_PRESSURE = "Pressure";
	public static final String REQ_STATE_LATENT_VAP_WATER = "LatentVapWater";
	
	private StateDouble pressure;
	private StateDouble latentVapWater;
	
	@Override
	public double calculate() {
		return 0.00016286 * (pressure.value / latentVapWater.value);
	}

	@Override
	public void setCalcDeps() {
		Cell fromCell = ((Face)holon).getEdge().getFromCell();
 		Cell toCell = ((Face)holon).getEdge().getToCell();  
 		
 		pressure = (StateDouble)createDependency(fromCell, REQ_STATE_PRESSURE);
 		latentVapWater = (StateDouble)createDependency(toCell, REQ_STATE_LATENT_VAP_WATER);
	}

}

