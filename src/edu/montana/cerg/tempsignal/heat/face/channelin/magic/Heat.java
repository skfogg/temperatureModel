package edu.montana.cerg.tempsignal.heat.face.channelin.magic;

import org.neosimulation.neo.framework.holon.Cell;
import org.neosimulation.neo.framework.holon.Face;
import org.neosimulation.neo.framework.stateval.StateDouble;
import org.neosimulation.neo.user.FaceFluxDynamDouble;

import edu.montana.cerg.tempsignal.heat.Constants;
import edu.montana.cerg.tempsignal.heat.cell.channel.Temp;
import edu.montana.cerg.tempsignal.heat.face.channelin.ExtTemp;

public class Heat extends FaceFluxDynamDouble{
	
public static final String OPT_STATE_EXT_DENSITY = "ExtDensity";
    
    /**
     * State name for optional density of water in attached cell
     */
    public static final String OPT_STATE_DENSITY = "CellDensity";
    
    /**
     * State name for optional external specific heat
     */
    public static final String OPT_STATE_EXT_SPHEAT = "ExtSpHeat";
    
    /**
     * Rate of water movement into (+) or out of (-) the boundary
     */
    private StateDouble water;
    
    /**
     * Water temperature external to the model
     */
    private StateDouble extTemp;
    
    /**
     * Water temperature in the connected cell
     */
    private StateDouble toTemp;
    
    /**
     * Specific heat of water external to the model
     */
    private double extSpHeat;
    
    /**
     * Specific heat of water in the connected cell
     */
    private double toSpHeat;
    
    /**
     * Calculate the amount of heat moving into the model (water > 0) or out of the model (water < 0)
     */
    @Override
    public double calculate()
    {               
        return water.value > 0 ? 
        		water.value * (extTemp.value + Constants.C_MINUS_K) * extSpHeat:
        		water.value * (toTemp.value +  Constants.C_MINUS_K) * toSpHeat;
    }

    /**
     * Define the calculators and states necessary to calculate heat movement through the boundary.
     */
    @Override
    public void setCalcDeps()
    {
        water = (StateDouble)createDependency(Constants.REQ_CURRENCY_ADVECT_MEDIUM);
        extTemp = (StateDouble)createDependency(ExtTemp.class.getSimpleName());
        extSpHeat = Constants.SP_HEAT_WATER_10;

        Cell toCell = ((Face)holon).getCell();
        toTemp = (StateDouble)createDependency(toCell, Temp.class.getSimpleName());
        toSpHeat = Constants.SP_HEAT_WATER_10;
 
    }


}
