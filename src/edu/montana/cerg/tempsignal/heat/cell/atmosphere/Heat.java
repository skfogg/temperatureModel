package edu.montana.cerg.tempsignal.heat.cell.atmosphere;

import org.neosimulation.neo.user.CellPoolDynamDouble;

public class Heat extends CellPoolDynamDouble {
    
    public static final String REQ_STATE_ANNUAL_OR_NOT = "AnnualAir(1)orNot(0)";
    public static final String REQ_STATE_DAILY_OR_NOT = "DailyAir(1)orNot(0)";
    
    public static final String REQ_STATE_START_DAY = "StartDayOfYear";
    
	public static final String REQ_STATE_LAT = "Latitude";
	public static final String REQ_STATE_LONG_CORRECT = "LongitudeCorrection";
	public static final String REQ_STATE_TRANSMIT = "AtmosphericTransmissivity";
	public static final String REQ_STATE_SOLAR_CONST = "SolarConstant";
	public static final String REQ_STATE_ANNUAL_SUN = "AnnualSunOrNot";
	
    public static final String REQ_STATE_EXTERNAL_TEMP_TABLE = "ExtTempTable";

	@Override
	public double calculate() {
		return 0;
	}

	
}
