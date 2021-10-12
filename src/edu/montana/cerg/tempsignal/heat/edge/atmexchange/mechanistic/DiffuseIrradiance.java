package edu.montana.cerg.tempsignal.heat.edge.atmexchange.mechanistic;

import org.neosimulation.neo.framework.holon.Cell;
import org.neosimulation.neo.framework.holon.Face;
import org.neosimulation.neo.framework.stateval.StateDouble;
import org.neosimulation.neo.user.AutoDynamDouble;

import edu.montana.cerg.tempsignal.heat.cell.atmosphere.Heat;
import edu.montana.cerg.tempsignal.heat.cell.atmosphere.mechanistic.CosZenithAngle;
import edu.montana.cerg.tempsignal.heat.cell.atmosphere.mechanistic.OpticalAir;
import edu.montana.cerg.tempsignal.heat.cell.atmosphere.mechanistic.SpacePerpRad;
import edu.montana.cerg.tempsignal.heat.cell.atmosphere.mechanistic.Transmissivity;

/**
 * Calculates the diffuse shortwave radiation hitting a horizontal surface  
 * in same units as Solar Constant parameter.  (Model requires Solar Constant
 * in kJ m<sup><small>-2</small></sup> sec<sup><small>-1</small></sup>).  
 * Equation from  Campbell and Norman 1998. Environmental Biophysics, 2e.  
 * Assumes clear sky conditions.
 * 
 * @author Geoffrey Poole
 *
 */

public class DiffuseIrradiance extends AutoDynamDouble {
	
		private StateDouble transmissivity;
		private StateDouble spacePerpRad;
		private StateDouble opticalAir;
		private StateDouble cosZenithAngle;
		
		@Override
		public double calculate() {
			return 0.3*(1-Math.pow(transmissivity.value,opticalAir.value))*spacePerpRad.value*cosZenithAngle.value;
		}

		@Override
		public void setCalcDeps() {
			Cell atmCell = ((Face)holon).getEdge().getFromCell();
			transmissivity = (StateDouble)createDependency(atmCell,Transmissivity.class.getSimpleName());
//			transmissivity = (StateDouble)createDependency(atmCell, Heat.REQ_STATE_TRANSMIT);
			spacePerpRad = (StateDouble)createDependency(atmCell,SpacePerpRad.class.getSimpleName());
			opticalAir = (StateDouble)createDependency(atmCell,OpticalAir.class.getSimpleName());
			cosZenithAngle = (StateDouble)createDependency(atmCell,CosZenithAngle.class.getSimpleName());
		}

	}


