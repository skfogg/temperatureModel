package edu.montana.cerg.tempsignal.heat.edge.bedexchange;

import org.neosimulation.neo.framework.holon.Cell;
import org.neosimulation.neo.framework.holon.Face;
import org.neosimulation.neo.framework.stateval.StateDouble;
import org.neosimulation.neo.user.FaceFluxDynamDouble;

public class Heat extends FaceFluxDynamDouble {

	public static final String REQ_STATE_THERM_COND_EFF = "ThermCondEff";
	public static final String REQ_STATE_VOL = "Volume";
	public static final String REQ_STATE_AREA = "Area";
	
	private StateDouble thermCondEff;

	private StateDouble area;
	private StateDouble tempHypo;
	private StateDouble tempWater;
	
	@Override
	public double calculate() 
	{
		
// length is set equal to 1m for thermal gradient in conduction equation because exchange of water 
// between channel and hyporheic zone is dominant mechanism of heat exchange.

		return thermCondEff.value * area.value * 
				(tempWater.value - tempHypo.value);
	}

	@Override
	public void setCalcDeps() 
	{
		thermCondEff = (StateDouble)createDependency(REQ_STATE_THERM_COND_EFF);
		tempWater = (StateDouble)createDependency(((Face)holon).getEdge().getFromCell(), 
				edu.montana.cerg.tempsignal.heat.cell.channel.Temp.class.getSimpleName());
		
		Cell bedCell = ((Face)holon).getEdge().getToCell();
		
		tempHypo = (StateDouble)createDependency(bedCell, 
				edu.montana.cerg.tempsignal.heat.cell.hyporheic.Temp.class.getSimpleName());
		area = (StateDouble)createDependency(bedCell, REQ_STATE_AREA);

	}
}
