package edu.montana.cerg.tempsignal.heat.cell.atmosphere;

import org.neosimulation.neo.framework.stateval.StateDouble;
import org.neosimulation.neo.user.AutoDynamDouble;

import edu.montana.cerg.tempsignal.heat.cell.atmosphere.mechanistic.RelativeHumidity;

/**
 * Controls the vapor pressure in the air
 * 
 * <p><b>References:</b></p>
 * <ul style="list-style-type: none; line-height: 200%">
 * <li>Evans, E. C., G. R. McGregor, and G. E. Petts (1998) River energy budgets with special reference to 
 * 		river bed processes. Hydrological Processes 12, 575-595.</li>
 * <li>Murray, F. W. (1967) On the computation of saturation vapour pressure. 
 * 		Journal of Applied Meteorology 6(1), 203-204.</li>
 * </ul>
 * 
 * @author robert.payn
 *
 */
public class VaporPressure extends AutoDynamDouble {
	
	private StateDouble vaporPressureSatAir;
	/**
	 * Relative humidity (%)
	 */
	//private StateDouble humidityRelative;
	/**
	 * Air temperature (&deg;C)
	 */
	//private StateDouble tempCelsius;
	private StateDouble relativeHumidity;

	/**
	 * Calculate the vapor pressure in the air
	 * 
	 * @return Vapor pressure (mbar)
	 */
	@Override
	public double calculate() {
		
		return (relativeHumidity.value / 100) * vaporPressureSatAir.value;
				
	}

	//return relativeHumidity.value * vaporPressureSat.value;
	
	//return (humidityRelative.value / 100) * 
	//			6.1275 * Math.exp((17.2693882 * tempCelsius.value) / (tempCelsius.value + Constants.C_MINUS_K - 35.86));
			
	//	return (humidityRelative.value / 100) * vaporPressureSat.value;
	
	/**
	 * Calculate the initial vapor pressure in the air
	 * 
	 * @return Vapor pressure (mbar)
	 */
	@Override
	public double initialize() {
		return calculate();	
	}

	/**
	 * Define the state dependencies for calculating vapor pressure
	 * 
	 * @see RelativeHumidity
	 * @see TempCelsius
	 * @see TempKelvin
	 */
	@Override
	public void setCalcDeps() 
	{
 
		vaporPressureSatAir = (StateDouble)createDependency(VaporPressureSatAir.class.getSimpleName());
		relativeHumidity = (StateDouble)createDependency(RelativeHumidity.class.getSimpleName());
	}
		/*
		humidityRelative = (StateDouble)createDependency(REQ_STATE_HUMID_RELATIVE);
		tempCelsius = (StateDouble)createDependency(REQ_STATE_TEMP_C);
		*/

	
	/*
	@Override
	public void setInitDeps()
	{

		humidityRelative = (StateDouble)createDependency(REQ_STATE_HUMID_RELATIVE);
		tempCelsius = (StateDouble)createDependency(REQ_STATE_TEMP_C);

	}
*/
}
