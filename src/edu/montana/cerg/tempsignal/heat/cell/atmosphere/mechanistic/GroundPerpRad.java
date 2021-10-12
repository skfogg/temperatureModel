package edu.montana.cerg.tempsignal.heat.cell.atmosphere.mechanistic;

import org.neosimulation.neo.framework.holon.Cell;
import org.neosimulation.neo.framework.holon.Face;
import org.neosimulation.neo.framework.stateval.StateDouble;
import org.neosimulation.neo.user.AutoDynamDouble;
import org.neosimulation.neo.user.ManualDynamDouble;


public class GroundPerpRad extends AutoDynamDouble {

	private StateDouble transmissivity;
	private StateDouble spacePerpRad;
	private StateDouble opticalAir;
	
	@Override
	public double calculate() {
		return spacePerpRad.value*Math.pow(transmissivity.value, opticalAir.value);
	}

	@Override
	public void setCalcDeps() {
//		Cell atmCell = ((Face)holon).getCell();	T
		transmissivity = (StateDouble)createDependency(Transmissivity.class.getSimpleName());
//		transmissivity = (StateDouble)createDependency(Heat.REQ_STATE_TRANSMIT);
		spacePerpRad = (StateDouble)createDependency(SpacePerpRad.class.getSimpleName());
		opticalAir = (StateDouble)createDependency(OpticalAir.class.getSimpleName());
	}

}
