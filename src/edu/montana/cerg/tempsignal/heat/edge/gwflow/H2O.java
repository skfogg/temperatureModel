package edu.montana.cerg.tempsignal.heat.edge.gwflow;

import org.neosimulation.neo.framework.holon.Cell;
import org.neosimulation.neo.framework.holon.Face;
import org.neosimulation.neo.framework.stateval.StateDouble;
import org.neosimulation.neo.user.AutoDynamDouble;
import org.neosimulation.neo.user.InitDynamDouble;

import edu.montana.cerg.tempsignal.heat.Constants;

public class H2O extends InitDynamDouble{
	
	public static final String REQ_STATE_INFLOW = "InFlow";
	//public StateDouble streamBedArea;
	//public StateDouble hypoRecharge;
	private StateDouble inFlow;
	
	@Override
	public double initialize() {
		return inFlow.value;
		//return hypoRecharge.value * streamBedArea.value;
	}

	@Override
	public void setInitDeps() {
		inFlow = (StateDouble)createDependency(Heat.REQ_STATE_INFLOW);
		//Cell fromCell = ((Face)holon).getEdge().getFromCell();
		//streamBedArea = (StateDouble)createDependency(fromCell, Heat.REQ_STATE_AREA);
		//hypoRecharge = (StateDouble)createDependency(fromCell, Heat.REQ_STATE_HYPO_RECHARGE_VELOCITY);
	}

}
