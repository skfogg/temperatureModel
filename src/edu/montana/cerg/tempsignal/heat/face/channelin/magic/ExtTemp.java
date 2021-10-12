package edu.montana.cerg.tempsignal.heat.face.channelin.magic;

import org.neosimulation.neo.framework.holon.Cell;
import org.neosimulation.neo.framework.holon.Face;
import org.neosimulation.neo.framework.stateval.StateDouble;


import org.neosimulation.neo.user.AutoDynamDouble;

import edu.montana.cerg.tempsignal.heat.cell.channel.Temp;

public class ExtTemp extends AutoDynamDouble{

	private StateDouble topTemp;
	@Override
	public double calculate() {
		return topTemp.value;
	}

	@Override
	public void setCalcDeps() {
		Cell topChannelCell = ((Face)holon).getEdge().getToCell();
		topTemp = (StateDouble)createDependency(topChannelCell,Temp.class.getSimpleName());
		
	}
	

}
