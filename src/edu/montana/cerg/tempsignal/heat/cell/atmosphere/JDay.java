package edu.montana.cerg.tempsignal.heat.cell.atmosphere;

import org.neosimulation.neo.framework.stateval.StateDouble;
import org.neosimulation.neo.user.AutoDynamDouble;


public class JDay extends AutoDynamDouble {
	
	private StateDouble fractionalJDay;

	@Override
	public void setCalcDeps() {
		fractionalJDay = (StateDouble)createDependency(FractionalJDay.class.getSimpleName());
		
	}

	@Override
	public double calculate() {
	
		if(Math.floor(fractionalJDay.value) == fractionalJDay.value){
			System.out.println("JDay: "+ (int)fractionalJDay.value);
		}
		
		return Math.floor(fractionalJDay.value);
	}

}
