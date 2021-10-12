package edu.montana.cerg.tempsignal.heat.edge.atmexchange;


import org.neosimulation.neo.framework.holon.Cell;
import org.neosimulation.neo.framework.holon.Face;
import org.neosimulation.neo.framework.stateval.StateDouble;
import org.neosimulation.neo.user.InitDynam;
import org.neosimulation.neo.user.ManualDynam;
import org.neosimulation.neo.user.ManualDynamDouble;
import org.neosimulation.neo.user.ManualDynamException;
import org.neosimulation.neo.user.UnableToRegisterException;

import edu.montana.cerg.tempsignal.heat.edge.atmexchange.mechanistic.DirectIrradiance;
import edu.montana.cerg.tempsignal.heat.edge.atmexchange.mechanistic.ShortwaveGross;


/**
 * Controls the net shortwave radiation entering water (kJ m-2 s-1).
 * 
 * <p>This is a simple calculator called by <code>Heat</code>
 * 
 * <p><b>References:</b></p>
 * <ul style="list-style-type: none; line-height: 200%">
 * <li>Webb, B. W. and Y. Zhang (1997) Spatial and seasonal variability in the components of the river heat budget.
 * 		Hydrological Processes 11, 79-101.</li>
 * </ul>
 * 
 * @see Heat
 * @author Rob Payn
 */
public class ShortwaveNet extends ManualDynamDouble {

	public static final String REQ_STATE_REFLECT_SHORTWAVE = "ReflectShortwave";
	private static final String REQ_STATE_SHORTWAVE_GROSS = "ShortwaveGross";
	public static final String REQ_STATE_SHADE_TABLE = "Shade";
	
	/**
	 * Flux of shortwave radiation
	 * (kJ m<sup><small>-2</small></sup> sec<sup><small>-1</small></sup>)
	 */
	private ManualDynamDouble shortwaveGrossDynam;
	private StateDouble shortwaveGross;

	/**
	 * Incoming shortwave radiation that is transmitted into water (fraction of energy)
	 */
	private double transmitShortwave;  
	
	/**
	 * State containing albedo of water surface
	 */
	private StateDouble reflectShortwave;
	
	private StateDouble shade;

	/**
	 * Calculates the initial rate of heat transfer due to shortwave radiation
	 * 
	 * @return Rate of energy transfer (kJ m<sup><small>-2</small></sup> sec<sup><small>-1</small></sup>)
	 */
	@Override
	public double initialize() 
	{
		shortwaveGrossDynam.doInitialize();
		transmitShortwave = 1 - reflectShortwave.value;
		return 0.0;
	}

	
	/**
	 * Calculates the rate of heat transfer due to shortwave radiation
	 * 
	 * @return Rate of energy transfer (kJ m<sup><small>-2</small></sup> sec<sup><small>-1</small></sup>)
	 */
	@Override
	public double calculate() 
	{	
		shortwaveGrossDynam.doUpdate();
		return shortwaveGross.value * (1-shade.value) * transmitShortwave;

	}

	@Override
	public void setCalcDeps() 
	{
		Cell atmCell = ((Face)holon).getEdge().getFromCell();
		shade = (StateDouble)createDependency(atmCell,REQ_STATE_SHADE_TABLE);
		shortwaveGross = (StateDouble)createDependency(ShortwaveGross.class.getSimpleName());
	}

    
    @Override
    public void setInitDeps()
    {
    	reflectShortwave = (StateDouble)createDependency(REQ_STATE_REFLECT_SHORTWAVE);
    	
   }
@Override
	
	public void setRegistrations()
	{
		shortwaveGrossDynam = (ManualDynamDouble)setRegistration(REQ_STATE_SHORTWAVE_GROSS);
		
			}




//    @Override
//    public void setRegistrations(){
//    	Cell airCell = ((Face)holon).getEdge().getFromCell();
//		Face solarFace = airCell.getFacesArray("heat","heat.solarradflux.mechanistic")[0];
//
//	
////
////		try 
////		{
////			swfDynam = (ManualDynamDouble)setRegistration(solarFace, ShortwaveFlux.class.getSimpleName());
////		} catch (UnableToRegisterException e) {
////			e.printStackTrace();
////		}
////
//
//    }


}
