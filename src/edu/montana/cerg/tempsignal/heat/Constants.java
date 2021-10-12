package edu.montana.cerg.tempsignal.heat;

/**
 * Class containing static constants for the heat resource
 * 
 * <p><b>References:</b></p>
 * <ul style="list-style-type: none; line-height: 200%">
 * <li>Anderson, E. R. (1952) Energy budget studies. Water loss investigations: 
 * 		Vol. 1 Lake Hefner Studies, Technical Report, United States Geological 
 * 		Survey Circular  229: 71-119.</li>
 * <li>Evans, E. C., G. R. McGregor, and G. E. Petts (1998) River energy budgets with special reference to 
 * 		river bed processes. Hydrological Processes 12, 575-595.</li>
 * <li>Penman, H. L. (1948) Natural evaporation from open water, bare soil, and grass.  
 * 		Proceedings of the Royal Society of London A 193, 120-145.</li>
 * <li>Webb, B. W. and Y. Zhang (1997) Spatial and seasonal variability in the components of the river heat budget.
 * 		Hydrological Processes 11, 79-101.</li>
 * </ul>
 * 
 * @author robert.payn
 */
public class Constants {
	
    /**
     * Specific heat of water at 10 &deg;C (kJ m<sup><small>-3</small></sup> K<sup><small>-1</small></sup>)
     */
    public static final double SP_HEAT_WATER_10 = 4193;

    /**
	 * Name of currency that advects heat
	 */
    public static final String REQ_CURRENCY_ADVECT_MEDIUM = "Water";
   
	/**
	 * Difference between Celsius and Kelvin temperature scales
	 * (&deg;C or K)
	 */
	public static final double C_MINUS_K = 273.16;
	
	/**
	 * Thermal conductivity of liquid water at 10 &deg;C (kJ m<sup><small>-1</small></sup> 
	 * sec<sup><small>-1</small></sup> &deg;C<sup><small>-1</small></sup>)
	 */
	public static final double COND_THERM_H2O_LIQ = 0.000586;

	/**
	 * Default emissivity of water (Evans et al. 1998); update 3/3/2017, from 0.97 to 0.95 (Dingman reference), 0.97 is ice
	 */
	public static final double DEF_EMISS_H2O = 0.95;
	
	/**
	 * Default longwave reflectivity at the air-water interface
	 * (Anderson 1952)
	 */
	public static final double DEF_LW_REFLECT = 0.03;
	
	/**
	 * Default shortwave reflectivity (albedo) at the air-water interface
	 * (Webb and Zhang 1997)
	 */
	public static final double DEF_SW_REFLECT = 0.1;
	//public static final double DEF_SW_REFLECT = 0;
	/**
	 * Default slope of the Penman type wind function
	 * (Webb and Zhang 1997, Evans et al. 1998), converted for
	 * m/s wind input and m/s evaporation output.
	 * 
	 * Empirical relationship from Webb and Zhang required wind speed (U) in km/day.
	 * Slopes here are adjusted to require m/s (i.e. convert m/s to km/d) as follows.
	 * 
	 *  From Zhang:
	 *  Ev (mm/d) = 0.165(0.8 + U/100)(Ew-Ea).
	 *  
	 *  Reverse direction: negative numbers are evaporation in our model:
	 *  Ev (mm/d) = 0.165(0.8 + U/100)(Ea-Ew).
	 *  
	 *  Rearrange terms:
	 *  Ev (mm/d) = 0.132 + 0.00165*U)(Ea-Ew).
	 *  
	 *  Convert wind speed in m/s to km/d:
	 *  Ev (mm/d) = 0.132 + (0.00165*U*86400/1000)(Ea-Ew).
	 *  
	 *  Simplify:
	 *  Ev (mm/d) = 0.132 + 0.14256*U*(Ea-Ew).
	 *  
	 *  Convert final output to m/s from mm/d
	 *  Ev (m/s) = 0.132 + 0.14256*U*(Ea-Ew)/8.64e7
	 */

	public static final double DEF_PENMAN_INT = 0.132;
	
	/**
	 * Default slope of the Penman type wind function
	 * (Webb and Zhang 1997, Evans et al. 1998), converted for
	 * m/s wind input and m/s evaporation output.
	 * 
	 * Empirical relationship from Webb and Zhang required wind speed (U) in km/day.
	 * Slopes here are adjusted to require m/s (i.e. convert m/s to km/d) as follows.
	 * 
	 *  From Zhang:
	 *  Ev (mm/d) = 0.165(0.8 + U/100)(Ew-Ea).
	 *  
	 *  Reverse direction: negative numbers are evaporation in our model:
	 *  Ev (mm/d) = 0.165(0.8 + U/100)(Ea-Ew).
	 *  
	 *  Rearrange terms:
	 *  Ev (mm/d) = 0.132 + 0.00165*U)(Ea-Ew).
	 *  
	 *  Convert wind speed in m/s to km/d:
	 *  Ev (mm/d) = 0.132 + (0.00165*U*86400/1000)(Ea-Ew).
	 *  
	 *  Simplify:
	 *  Ev (mm/d) = 0.132 + 0.14256*U*(Ea-Ew).
	 *  
	 *  Convert final output to m/s from mm/d
	 *  Ev (m/s) = 0.132 + 0.14256*U*(Ea-Ew)/8.64e7
	 */

	public static final double DEF_PENMAN_SLOPE = 0.14256;
	
	/**
	 * Specific heat of water (kJ kg<sup><small>-1</small></sup> &deg;C<sup><small>-1</small></sup>)
	 */
	//public static final double SPEC_HEAT_H2O_LIQ = 4.186;

	/**
	 * Stefan-Boltzmann constant (kW m<sup><small>-2</small></sup> K<sup><small>-4</small></sup>)
	 */
	public static final double STEFBOLTZ = 5.67e-11;

}
