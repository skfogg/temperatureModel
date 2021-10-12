package edu.montana.cerg.tempsignal.heat.edge.gwflow.interzone;

import org.neosimulation.neo.framework.holon.Cell;
import org.neosimulation.neo.framework.holon.Face;
import org.neosimulation.neo.framework.model.StateValContainerException;
import org.neosimulation.neo.framework.stateval.StateDouble;
import org.neosimulation.neo.user.AutoDynamDouble;
import org.neosimulation.neo.user.InitDynamDouble;

import edu.montana.cerg.tempsignal.heat.edge.gwflow.Heat;


public class H2O extends InitDynamDouble{

	public static final String REQ_STATE_INFLOW = "InFlow";
	//private StateDouble waterEntering;
	//private StateDouble waterExiting;
	private StateDouble inFlow;
	
	@Override
	public double initialize() {
		
		return inFlow.value;
		//return waterEntering.value - waterExiting.value;

	}

	@Override
	public void setInitDeps() {
		
		//Cell fromCell = ((Face)holon).getEdge().getFromCell();
		
		//Face[] GwFlowFaces = fromCell.getFacesArray("Heat", "Heat.gwflow");
				
		//if (GwFlowFaces.length == 0){
			//GwFlowFaces = fromCell.getFacesArray("Heat", "Heat.gwflow.interzone");
		//}

		//Face GwFlowFace = GwFlowFaces[0];
		
		//Face DischargeFace = fromCell.getFacesArray("Heat", "Heat.gwflow.discharge")[0];
			
		//waterEntering = (StateDouble)createDependency(GwFlowFace,H2O.class.getSimpleName());
		
		//waterExiting = (StateDouble)createDependency(DischargeFace,H2O.class.getSimpleName());
	

		inFlow = (StateDouble)createDependency(Heat.REQ_STATE_INFLOW);
	}

	
	
}
