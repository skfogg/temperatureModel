package edu.montana.cerg.tempsignal.heat.cell.atmosphere.mechanistic;

import org.neosimulation.neo.user.CellPoolDynamDouble;

public class Heat extends CellPoolDynamDouble {
    
    public static final String REQ_STATE_ANNUAL_OR_NOT = "AnnualAir(1)orNot(0)";
    public static final String REQ_STATE_DAILY_OR_NOT = "DailyAir(1)orNot(0)";
    
    public static final String REQ_STATE_START_DAY = "StartDayOfYear";
    
	public static final String REQ_STATE_TRANSMIT = "AtmosphericTransmissivity";
	public static final String REQ_STATE_LAT = "Latitude";
	public static final String REQ_STATE_LONG_CORRECT = "LongitudeCorrection";
	public static final String REQ_STATE_SOLAR_CONST = "SolarConstant";
	public static final String REQ_STATE_ANNUAL_SUN = "AnnualSunOrNot";
	
	public static final String REQ_STATE_R_HUMID_ANNUAL_MEAN = "RHumidAnnualMean";
	public static final String REQ_STATE_R_HUMID_ANNUAL_AMP = "RHumidAnnualAmp";
	public static final String REQ_STATE_R_HUMID_ANNUAL_PHASE = "RHumidAnnualPhase";
	public static final String REQ_STATE_R_HUMID_DAY_AMP_MEAN = "RHumidDayAmpMean";
	public static final String REQ_STATE_R_HUMID_DAY_AMP_AMP = "RHumidDayAmpAmp";
	public static final String REQ_STATE_R_HUMID_DAY_AMP_PHASE = "RHumidDayAmpPhase";
	public static final String REQ_STATE_R_HUMID_DAY_MEAN = "RHumidDayMean";
	public static final String REQ_STATE_R_HUMID_DAY_PHASE = "RHumidDayPhase";
	
	public static final String REQ_STATE_AIR_TEMP_ANNUAL_MEAN = "AirTempAnnualMean";
	public static final String REQ_STATE_AIR_TEMP_ANNUAL_AMP = "AirTempAnnualAmp";
	public static final String REQ_STATE_AIR_TEMP_ANNUAL_PHASE = "AirTempAnnualPhase";
	public static final String REQ_STATE_AIR_TEMP_DAY_AMP_MEAN = "AirTempDayAmpMean";
	public static final String REQ_STATE_AIR_TEMP_DAY_AMP_AMP = "AirTempDayAmpAmp";
	public static final String REQ_STATE_AIR_TEMP_DAY_AMP_PHASE = "AirTempDayAmpPhase";
	public static final String REQ_STATE_AIR_TEMP_DAY_MEAN = "AirTempDayMean";
	public static final String REQ_STATE_AIR_TEMP_DAY_PHASE = "AirTempDayPhase";
	
	public static final String REQ_STATE_TRANSMISSIVITY_MEAN = "TransmissivityMean";
	public static final String REQ_STATE_TRANSMISSIVITY_AMP = "TransmissivityAmp";
	public static final String REQ_STATE_TRANSMISSIVITY_PHASE = "TransmissivityPhase";
	public static final String REQ_STATE_OUTFLOW = "OutFlow";
	
	@Override
	public double calculate() {
		return 0;
	}
	
}
