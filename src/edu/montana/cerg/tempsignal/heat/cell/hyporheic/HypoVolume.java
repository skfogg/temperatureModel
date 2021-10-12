package edu.montana.cerg.tempsignal.heat.cell.hyporheic;

import org.neosimulation.neo.framework.holon.Cell;
import org.neosimulation.neo.framework.holon.Edge;
import org.neosimulation.neo.framework.holon.Face;
import org.neosimulation.neo.framework.stateval.StateDouble;
import org.neosimulation.neo.user.AutoDynamDouble;
import org.neosimulation.neo.user.InitDynamDouble;

import edu.montana.cerg.tempsignal.heat.cell.channel.Temp;
import edu.montana.cerg.tempsignal.heat.edge.gwflow.H2O;

public class HypoVolume extends InitDynamDouble{

	//private StateDouble porosity;
	//private StateDouble meanResidenceTime;
	//private StateDouble meanResidenceTimeUpstream;
	//private StateDouble waterIn;
	
	private StateDouble hypoVol;

	@Override
	public double initialize() {
		return hypoVol.value;
	}


	@Override
	public void setInitDeps() {
		hypoVol = (StateDouble)createDependency(Heat.REQ_STATE_HYPO_VOL);
	}

	//@Override
	//public double calculate() {
		//double meanResidenceTimeUpstreamValue = 0;
		//if (meanResidenceTimeUpstream != null)
		//{
		//	meanResidenceTimeUpstreamValue = meanResidenceTimeUpstream.value;
		//}
		//return (waterIn.value * (meanResidenceTime.value - meanResidenceTimeUpstreamValue))/porosity.value;
		
	//}
	

	//@Override
	//public void setCalcDeps() {
		//porosity = (StateDouble)createDependency(Heat.REQ_STATE_POROSITY);
		//meanResidenceTime = (StateDouble)createDependency(Heat.REQ_STATE_MEAN_RESIDENCE_TIME);
		
		//Face[] interzoneFaces = ((Cell)holon).getFacesArray("Heat", "Heat.gwflow.interzone");
		//Face toFace = null;
		//for (Face face: interzoneFaces)
		//{
			//if (face.getEdge().getToFace().equals(face))
			//{
			//	toFace = face;
			//	break;
			//}
		//}
		//if (toFace == null)
		//{
		//	meanResidenceTimeUpstream = null;
		//	toFace = ((Cell)holon).getFacesArray("Heat", "Heat.gwflow")[0];
		//}
		//else
		//{
		//	Cell fromCell = toFace.getEdge().getFromCell();
		//	meanResidenceTimeUpstream = (StateDouble)createDependency(fromCell, Heat.REQ_STATE_MEAN_RESIDENCE_TIME);
		//}
		//waterIn = (StateDouble)createDependency(toFace, H2O.class.getSimpleName());		
	//}


}
