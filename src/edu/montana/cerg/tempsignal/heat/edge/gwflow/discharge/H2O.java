package edu.montana.cerg.tempsignal.heat.edge.gwflow.discharge;

import org.neosimulation.neo.framework.holon.Cell;
import org.neosimulation.neo.framework.holon.Face;
import org.neosimulation.neo.framework.stateval.StateDouble;
import org.neosimulation.neo.user.AutoDynamDouble;
import org.neosimulation.neo.user.InitDynamDouble;

import edu.montana.cerg.tempsignal.heat.cell.atmosphere.mechanistic.Heat;

public class H2O extends InitDynamDouble{
	
	public static final String REQ_STATE_OUTFLOW = "OutFlow";

	//private StateDouble returnFraction;
	//private StateDouble waterEntering;
	
	private StateDouble outFlow;
	
	@Override
	public double initialize() {
		return outFlow.value;
		//return returnFraction.value * waterEntering.value;
	}

	@Override
	public void setInitDeps() {
		
		outFlow = (StateDouble)createDependency(Heat.REQ_STATE_OUTFLOW);

		//Cell fromCell = ((Face)holon).getEdge().getFromCell();
		//returnFraction = (StateDouble)createDependency(edu.montana.cerg.tempsignal.heat.edge.gwflow.Heat.REQ_STATE_RETURNING_FRACTION);
		
		//Face[] GwFlowFaces = fromCell.getFacesArray("Heat", "Heat.gwflow");
		//if (GwFlowFaces.length == 0){
		//	GwFlowFaces = fromCell.getFacesArray("Heat", "Heat.gwflow.interzone");
		//}
		//Face GwFlowFace = GwFlowFaces[0];
		
		//waterEntering = (StateDouble)createDependency(GwFlowFace, H2O.class.getSimpleName());
		
	}

	
}
