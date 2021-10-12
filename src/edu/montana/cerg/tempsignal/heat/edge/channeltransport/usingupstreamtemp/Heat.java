package edu.montana.cerg.tempsignal.heat.edge.channeltransport.usingupstreamtemp;

import org.neosimulation.neo.framework.holon.Cell;
import org.neosimulation.neo.framework.holon.Face;
import org.neosimulation.neo.framework.stateval.StateDouble;
import org.neosimulation.neo.user.FaceFluxDynamDouble;

import edu.montana.cerg.tempsignal.heat.Constants;
import edu.montana.cerg.tempsignal.heat.cell.channel.Temp;

public class Heat extends FaceFluxDynamDouble {    
	
    private StateDouble water;
    private StateDouble fromTemp;
    private StateDouble toTemp;
    private double SpHeat;

    @Override
    public double calculate()
    {
        if (water.value > 0)        
            return water.value * (fromTemp.value + Constants.C_MINUS_K) * SpHeat;        
        else        
            return water.value * (toTemp.value + Constants.C_MINUS_K) * SpHeat;        
    }

    @Override
    public void setCalcDeps()
    {
        water = (StateDouble)createDependency(Constants.REQ_CURRENCY_ADVECT_MEDIUM);

        Cell fromCell = ((Face)holon).getEdge().getFromCell();
        Cell toCell = ((Face)holon).getEdge().getToCell();
        fromTemp = (StateDouble)createDependency(fromCell, Temp.class.getSimpleName());
        toTemp = (StateDouble)createDependency(toCell, Temp.class.getSimpleName());
        SpHeat = Constants.SP_HEAT_WATER_10;
    }
}
