package edu.montana.cerg.tempsignal.heat.edge.atmexchange;

import org.neosimulation.neo.framework.holon.Cell;
import org.neosimulation.neo.framework.holon.Face;
import org.neosimulation.neo.framework.stateval.StateDouble;
import org.neosimulation.neo.user.InitDynamDouble;
import org.neosimulation.neo.user.ManualDynamDouble;

import edu.montana.cerg.tempsignal.heat.TempSignalModel;
import edu.montana.cerg.tempsignal.heat.cell.atmosphere.mechanistic.Temp;



/**
 * Controls the evaporation or condensation flux rate between surface water and air
 * 
 * <p>This is a simple calculator called by <code>Heat</code>
 * 
 * <p><b>References:</b></p>
 * <ul style="list-style-type: none; line-height: 200%">
 * <li>Evans, E. C., G. R. McGregor, and G. E. Petts (1998) River energy budgets with special reference to 
 * 		river bed processes. Hydrological Processes 12, 575-595.</li>
 * <li>Penman, H. L. (1948) Natural evaporation from open water, bare soil, and grass.  
 * 		Proceedings of the Royal Society of London A 193, 120-145.</li>
 * <li>Webb, B. W. and Y. Zhang (1997) Spatial and seasonal variability in the components of the river heat budget.
 * 		Hydrological Processes 11, 79-101.</li>
 * </ul>
 * 
 * @see Heat
 * @author robert.payn
 */
public class Evaporation extends ManualDynamDouble {
	
	public static final String REQ_STATE_PENMAN_INT = "PenmanIntercept" ;
	public static final String REQ_STATE_PENMAN_SLOPE = "PenmanSlope";
	public static final String REQ_STATE_SPEED_WIND = "SpeedWind";
	public static final String REQ_STATE_VAPOR_PRESSURE = "VaporPressure";
	public static final String REQ_STATE_VAPOR_PRESSURE_SAT = "VaporPressureSat";

	//public static final String REQ_STATE_SAT_VAPOR_PRESSURE_SLOPE = "SatVaporPressureSlope";
	//public static final String REQ_STATE_LATENT_VAP_WATER = "LatentVapWater";
	/**
	 * Value of the intercept for the empirical Penman linear wind function
	 */
	private StateDouble penmanInt;
	/**
	 * Value of the slope for the empirical Penman linear wind function
	 */
	private StateDouble penmanSlope;	
	/**
	 * Wind speed (m sec<sup><small>-1</small></sup>)
	 */
	private StateDouble speedWind;
	/**
	 * Vapor pressure in the air (mbar)
	 */
	private StateDouble vaporPressureAir;
	/**
	 * Saturated vapor pressure at water temperature (mbar)
	 */
	private StateDouble vaporPressureSat;
	

	//private StateDouble satVaporPressureSlope;
	//private StateDouble latentVapWater;
	//private StateDouble psychrometricConstant;
	//private StateDouble shortwaveEvapEquivalent;
	
	/**
	 * Calculates the flux rate of evaporation (negative) or condensation (positive)
	 * 
	 * @return Evaporation rate (m sec<sup><small>-1</small></sup>)
	 */
	@Override
	public double calculate() 
	{
		 //return 0.165*(0.8+(speedWind.value*(8.64e4/1000)/100)*(vaporPressureAir.value - vaporPressureSat.value)) / (1000*8.64e4);
		
		//return ((penmanInt.value + (penmanSlope.value * speedWind.value)) * (vaporPressureAir.value - vaporPressureSat.value)) / 8.64e7;	
	    
		// (0.408 * satVapPressSlope.value * (shortwaveNet.value - longwaveNet.value)
		//		+ (psychometricConstant.value * (37/(tempAir.value + 273))) * speedWind.value
		//		* (vaporPressureAir.value - vaporPressureSat.value)) / 
		//		satVapPressSlope.value + psychometricConstant.value * (1 + 0.34*speedWind.value);
	
		return 0.5 * (0.8 + (speedWind.value * 86400/1000)/100) * (vaporPressureAir.value - vaporPressureSat.value) / 8.64e7;
		
		//d2
		//return (5.91411e-09 + (2.996258e-08 * speedWind.value)) * (vaporPressureAir.value - vaporPressureSat.value);
		
		//d3f
		//return (1.582283e-08 + (-7.579168e-09 * speedWind.value)) * (vaporPressureAir.value - vaporPressureSat.value);
		
		//d3p1
		//return (-2.854551e-09 + (1.755108e-08 * speedWind.value)) * (vaporPressureAir.value - vaporPressureSat.value);
		
		//d3p2
		//return (5.175535e-09 + (1.166464e-08 * speedWind.value)) * (vaporPressureAir.value - vaporPressureSat.value);

		//d5h
		//return (7.784706e-09 + (5.280655e-09 * speedWind.value)) * (vaporPressureAir.value - vaporPressureSat.value);

		//d5s
		//return (3.445422e-09 + (1.383111e-08 * speedWind.value)) * (vaporPressureAir.value - vaporPressureSat.value);
		
		//return ((satVaporPressureSlope.value * shortwaveEvapEquivalent.value) +
		//		psychrometricConstant.value * (6430 * (0.1 + 0.0536 * speedWind.value) * (vaporPressureAir.value - vaporPressureSat.value)) / latentVapWater.value) / (satVaporPressureSlope.value + psychrometricConstant.value);
		
	}

	/**
	 * Calculates the initial flux rate of evaporation.
	 * 
	 * @see wren.heat.utils.Constants
	 * @return Evaporation rate (m sec<sup><small>-1</small></sup>)
	 */
	@Override
	public double initialize() 
	{

		return calculate();
	}

	@Override
	public void setCalcDeps()
	{
 		
		Cell fromCell = ((Face)holon).getEdge().getFromCell();
 		Cell toCell = ((Face)holon).getEdge().getToCell();  
 		
		penmanInt = (StateDouble)createDependency(REQ_STATE_PENMAN_INT);
		penmanSlope = (StateDouble)createDependency(REQ_STATE_PENMAN_SLOPE);
		
		speedWind = (StateDouble)createDependency(fromCell,REQ_STATE_SPEED_WIND);
		vaporPressureAir = (StateDouble)createDependency(fromCell,REQ_STATE_VAPOR_PRESSURE);
		vaporPressureSat = (StateDouble)createDependency(toCell,REQ_STATE_VAPOR_PRESSURE_SAT);
		
		//satVaporPressureSlope = (StateDouble)createDependency(fromCell, REQ_STATE_SAT_VAPOR_PRESSURE_SLOPE);
		//latentVapWater = (StateDouble)createDependency(toCell, REQ_STATE_LATENT_VAP_WATER);
		//psychrometricConstant = (StateDouble)createDependency(PsychrometricConstant.class.getSimpleName());
		//shortwaveEvapEquivalent = (StateDouble)createDependency(ShortwaveEvapEquivalent.class.getSimpleName());
	}


}
