package edu.montana.cerg.tempsignal.heat.edge.gwflow;

import org.neosimulation.neo.framework.holon.Cell;
import org.neosimulation.neo.framework.holon.Face;
import org.neosimulation.neo.framework.stateval.StateDouble;
import org.neosimulation.neo.user.FaceFluxDynamDouble;

import edu.montana.cerg.tempsignal.heat.Constants;
import edu.montana.cerg.tempsignal.heat.cell.channel.Temp;

public class Heat extends FaceFluxDynamDouble{
	
	//public static final String REQ_STATE_AREA = "Area";
	public static final String REQ_STATE_HYPO_RECHARGE_VELOCITY = "RechargeVelocity";
	public static final String REQ_STATE_RETURNING_FRACTION = "ReturningFraction";
	//public static final String REQ_STATE_INTERZONE_FLOW = "InterzoneFlow";
	public static final String REQ_STATE_INFLOW = "InFlow";
	public static final String REQ_STATE_OUTFLOW = "OutFlow";
	
	private StateDouble waterEntering;
	private StateDouble fromCellTemp;
	
	@Override
	public double calculate() {
		
		return waterEntering.value * (fromCellTemp.value + Constants.C_MINUS_K) * Constants.SP_HEAT_WATER_10;
	}

	@Override
	public void setCalcDeps() {
		 Cell fromCell = ((Face)holon).getEdge().getFromCell();
		 fromCellTemp = (StateDouble)createDependency(fromCell, Temp.class.getSimpleName());
		 waterEntering = (StateDouble)createDependency(H2O.class.getSimpleName());
		 		 
	}

}
