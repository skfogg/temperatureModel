package edu.montana.cerg.tempsignal.heat.face.channelin.compoundsin;

import org.neosimulation.neo.framework.holon.Cell;
import org.neosimulation.neo.framework.holon.Face;
import org.neosimulation.neo.framework.stateval.StateDouble;
import org.neosimulation.neo.framework.time.TimeKeeper;
import org.neosimulation.neo.user.AutoDynamDouble;

import edu.montana.cerg.tempsignal.heat.cell.atmosphere.StartDay;


public class SubAmpTemp extends AutoDynamDouble {
    

	private StateDouble period;
    private StateDouble average;
    private StateDouble amplitude;
    private StateDouble phaseDay;
    private StateDouble phase;
    private TimeKeeper timeKeeper;
    private StateDouble annual;
    private double phaseTime;

    @Override
    public double calculate()
    {
        if (annual.value == 0){
        	phaseTime = timeKeeper.getCurrentTime() - Math.floor(timeKeeper.getCurrentTime()/86400)*86400 + phaseDay.value*86400;
        }else{
        	phaseTime = (timeKeeper.getCurrentTime() + phaseDay.value*86400);
        }
           	
    	return (average.value + amplitude.value/2 * Math.sin(phaseTime*2*Math.PI*(1/period.value) + phase.value));
    	
    }

    @Override
    public void setCalcDeps()
    {
    	    	
    	Face[] faces = ((Face)holon).getCell().getFacesArray();
    	Cell atmCell = null;
    			
    	for (int i=0; i<faces.length; i++){
    		if (faces[i].getName().startsWith("atm")){
    			atmCell = faces[i].getEdge().getFromCell();
    		}
    		
    	}
    			
    			
    	timeKeeper = holon.getSimulationModel().getTimeKeeper();
        period = (StateDouble)createDependency(Heat.REQ_STATE_SUB_AMP_PERIOD);
        average = (StateDouble)createDependency(Heat.REQ_STATE_SUB_AMP_AVG);
        amplitude = (StateDouble)createDependency(Heat.REQ_STATE_SUB_AMP_AMP);
        phaseDay = (StateDouble)createDependency(atmCell, StartDay.class.getSimpleName());
        phase = (StateDouble)createDependency(Heat.REQ_STATE_SUB_AMP_PHASE);
        annual = (StateDouble)createDependency(Heat.REQ_STATE_ANNUAL_OR_NOT);
    }
    
}