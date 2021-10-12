package edu.montana.cerg.tempsignal.heat.edge.atmexchange;

import org.neosimulation.neo.framework.holon.Cell;
import org.neosimulation.neo.framework.holon.Face;
import org.neosimulation.neo.framework.stateval.StateDouble;
import org.neosimulation.neo.user.FaceFluxDynamDouble;
import org.neosimulation.neo.user.ManualDynam;
import org.neosimulation.neo.user.ManualDynamDouble;


/**
 * <p>Determines the movement of energy between surface water and air (kJ s-1).</p>
 * Elements of the energy balance include transfers that influence the
 * heat content of the water:
 * <ul style="list-style-type:none">
 * <li>net shortwave radiation</li>
 * <li>net longwave radiation</li>
 * <li>evaporation and condensation</li>
 * <li>sensible heat transfer</li>
 * </ul>
 * <p>
 * 'From' side of edge should be connected to an air patch and 'to' side of
 * edge should be connected to a surface water patch. In other words:<br>
 * Negative value indicates energy loss from water<br>
 * Positive value indicates energy gain to water
 * </p>
 * 
 * <b>Static states (parameters) or externally controlled states required by this resource behavior:</b>
 * </b><ul style="list-style-type:none; line-height:200%">
 * <li><i>AREA (double, 'to' patch)</i> - Wetted area of 'to' patch over which heat is conducted 
 * 		(m<sup><small>2</small></sup>)</li>
 * </ul>
 * 
 * <p><b>Optional input parameters that can be changed for energy calculations:</b></p>
 * <ul style="list-style-type: none">
 * <li><i>Shortwave radiation</i><ul style="list-style-type: none">
 * 		<li><i>ReflectShortwave (double, defaults to 0.1)</i> - fraction of incoming shortwave radiation that is reflected
 * 			by the water surface (fraction of energy)</li>
 * </ul></li>
 * <li><i>Longwave radiation</i><ul style="list-style-type: none">
 * 		<li><i>EmmissivityWater (double, in channel ('to') cell, default 0.97)</i> - emissivity of water, which is the fraction of 
 * 			the longwave radiation released by a perfect black-body (fraction of energy)</li>
 * 		<li><i>ReflectLongwave (double, default 0.03)</i> - fraction of incoming longwave radiation that is reflected
 * 			by the water surface (fraction of energy)</li>
 * </ul></li>
 * <li><i>Evaporation and condensation</i><ul style="list-style-type: none">
 * 		<li><i>PenmanIntercept, PenmanSlope (double, defaults 0.132 and 0.14256)</i> - parameters for an empirical linear wind function for the Penman-type
 * 			estimate of evaporation/condensation (must provide both or defaults will be used)
 * </ul></li>
 * </ul>
 * 
 * 
 * @see ShortwaveNet
 * @see LongwaveFluxWater
 * @see LongwaveNet
 * @see Evaporation
 * @see HeatLatentEvap
 * @see BowenRatio
 * @see HeatSensible
 * @author Rob Payn, Sam Carlson, Geoff Poole
 */
public class Heat extends FaceFluxDynamDouble {
	
	
	
	public static final String REQ_STATE_SHORT_WAVE_NET = "ShortwaveNet";
	public static final String REQ_STATE_LONG_WAVE_NET = "LongwaveNet";
	public static final String REQ_STATE_SENSIBLE_HEAT = "HeatSensible";
	public static final String REQ_STATE_EVAP_LATENT_HEAT = "HeatLatentEvap";
	
	/**
	 * Wetted surface area of water in from patch (m<sup><small>2</sup></small>)
	 */
	private StateDouble areaWetted;
	/**
	 * Latent heat removed by evaporating water or added by condensing water
	 * (kJ m<sup><small>-2</sup></small> sec<sup><small>-1</sup></small>)
	 */
	private StateDouble heatLatentEvap;
	/**
	 * Dynam for latent heat
	 */
	private ManualDynamDouble heatLatentEvapDynam;
	/**
	 * Sensible heat flux
	 * (kJ m<sup><small>-2</sup></small> sec<sup><small>-1</sup></small>)
	 */
	private StateDouble heatSensible;
	/**
	 * Dynam for sensible heat
	 */
	private ManualDynamDouble heatSensibleDynam;
	/**
	 * Net shortwave radiation flux (kJ m<sup><small>-2</sup></small> sec<sup><small>-1</sup></small>)
	 */
	private StateDouble shortwaveNet;
	/**
	 * Dynam for net shortwave radiation 
	 */
	private ManualDynamDouble shortwaveNetDynam;
	/**
	 * Net longwave radiation flux (kJ m<sup><small>-2</sup></small> sec<sup><small>-1</sup></small>)
	 */
	private StateDouble longwaveNet;
	/**
	 * Dynam for net longwave radiation
	 */
	private ManualDynamDouble longwaveNetDynam;
	
    @Override
    public double initialize()
    {
    	shortwaveNetDynam.doInitialize();
    	longwaveNetDynam.doInitialize();
    	heatLatentEvapDynam.doInitialize();
    	heatSensibleDynam.doInitialize();
    	return 0;
    }
    
    /**
     * Computes the net energy transfer between air and water.
     * 
     * @return Rate of net energy transfer (kJ sec<sup><small>-1</sup></small>)
     */
    @Override
	public double calculate() 
    {

    	shortwaveNetDynam.doUpdate();
    	longwaveNetDynam.doUpdate();
    	heatLatentEvapDynam.doUpdate();
    	heatSensibleDynam.doUpdate();
    	
    	return areaWetted.value * (shortwaveNet.value + longwaveNet.value + heatLatentEvap.value + heatSensible.value);   
    	
    	
    }

    
    @Override
    public void setCalcDeps() 
    {
    	
    	
    	Cell surfaceCell = ((Face)holon).getEdge().getToCell();
    	
    	areaWetted = (StateDouble)createDependency(surfaceCell, 
				edu.montana.cerg.tempsignal.heat.cell.channel.Heat.REQ_STATE_TOCELL_AREA); 		
    	shortwaveNet = (StateDouble)createDependency(REQ_STATE_SHORT_WAVE_NET);
    	longwaveNet = (StateDouble)createDependency(REQ_STATE_LONG_WAVE_NET);
		heatLatentEvap = (StateDouble)createDependency(REQ_STATE_EVAP_LATENT_HEAT);
		heatSensible = (StateDouble)createDependency(REQ_STATE_SENSIBLE_HEAT);    
    }
   
    @Override
    public void setRegistrations()
    {
    	
    	shortwaveNetDynam = (ManualDynamDouble)setRegistration(REQ_STATE_SHORT_WAVE_NET);
    	longwaveNetDynam = (ManualDynamDouble)setRegistration(REQ_STATE_LONG_WAVE_NET);
    	heatLatentEvapDynam = (ManualDynamDouble)setRegistration(REQ_STATE_EVAP_LATENT_HEAT);
    	heatSensibleDynam = (ManualDynamDouble)setRegistration(REQ_STATE_SENSIBLE_HEAT);
    
    }
    
    
    

}
