import java.util.ArrayList;
import java.time.*;
import java.time.LocalDate;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * This class calculates the covid statistics over the selected period. 
 * 
 * @author Gunel F., Abirami M., Ekaterina H., Iffat S. 
 * @version 26/03/23
 */
public class CovidStats
{
    private ArrayList<CovidData> dataRecords;
    private CovidDataLoader dataLoader;
    private double totalRR = 0; 
    private double totalGP = 0;
    private int totalParks = 0;
    private int totalTS = 0;
    private int totalWorkplace = 0;
    private int totalResidential = 0;
    private int totalNewCases = 0;
    private int totalCases = 0;
    private int totalNewDeaths = 0;
    private int totalDeaths = 0;
    
    private static final DecimalFormat df = new DecimalFormat("0.00");

    /**
     * Constructor for objects of class CovidStats.
     */
    public CovidStats()
    {
        dataLoader = new CovidDataLoader();
        dataRecords = dataLoader.load();
    }
    
    /**
     * This method calculates the average Retail and Recreation values for all boroughs within the selected date range.
     * @param fromDate The from date seleted by the user.
     * @param toDate The to date selected by the user.
     */
    public double averageRetailRecreation(LocalDate fromDate, LocalDate toDate){
        int counter = 0;
        double avgRR = 0;
        for (LocalDate currentDate = fromDate; currentDate.isBefore(toDate) || currentDate.isEqual(toDate); currentDate = currentDate.plusDays(1)){
            for (CovidData dataRecord : dataRecords){
                if (currentDate.toString().equals(dataRecord.getDate())){
                    counter++;
                    totalRR += dataRecord.getRetailRecreationGMR();
                }
            }
        }
        avgRR = totalRR/counter;
        return Math.round(avgRR * 100) / 100.0;
    }
    
    /**
     * This method calculates the average Grocery and Pharmacy values for all boroughs within the selected date range.
     * @param fromDate The from date seleted by the user.
     * @param toDate The to date selected by the user.
     */
    public double averageGroceryAndPharmacy(LocalDate fromDate, LocalDate toDate){
        int counter = 0;
        double avgGP = 0;
        for (LocalDate currentDate = fromDate; currentDate.isBefore(toDate) || currentDate.isEqual(toDate); currentDate = currentDate.plusDays(1)){
            for (CovidData dataRecord : dataRecords){
                if (currentDate.toString().equals(dataRecord.getDate()))
                {
                    counter++;
                    totalGP += dataRecord.getGroceryPharmacyGMR();
                }
            }
        }
        avgGP = totalGP / counter;
        return Math.round(avgGP * 100) / 100.0;
    }
    
    /**
     * This method calculates the total deaths for all boroughs within the selected date range.
     * @param fromDate The from date seleted by the user.
     * @param toDate The to date selected by the user.
     */
    public int totalDeaths(LocalDate fromDate, LocalDate toDate)
    {
        for (LocalDate currentDate = fromDate; currentDate.isBefore(toDate) || currentDate.isEqual(toDate); currentDate = currentDate.plusDays(1)){
            for (CovidData dataRecord: dataRecords){
            if (currentDate.toString().equals(toDate.toString()) && toDate.toString().equals(dataRecord.getDate()))
            {
                totalDeaths += dataRecord.getTotalDeaths();
            }
            }
        }
        return totalDeaths;
    }
    
    /**
     * This method calculates the average of total cases for all boroughs within the selected date range.
     * @param fromDate The from date seleted by the user.
     * @param toDate The to date selected by the user.
     */
    public double totalCasesAverage(LocalDate fromDate, LocalDate toDate)
    {
        int counter = 0;
        double avg = 0;
        for (LocalDate currentDate = fromDate; currentDate.isBefore(toDate) || currentDate.isEqual(toDate); currentDate = currentDate.plusDays(1))
        {
            for (CovidData dataRecord: dataRecords)
            {
                if (currentDate.toString().equals(toDate.toString()) && toDate.toString().equals(dataRecord.getDate()))
                {
                    counter++;
                    totalCases += dataRecord.getTotalCases();
                }
            }
        }
        if (counter != 0){
            avg = totalCases / counter;
        }
        return Math.round(avg * 100) / 100.0;
    }
    
    /**
     * This method calculates the highest total death date and the highest total death for all boroughs within the selected date range.
     * Note: The maximum deaths are not currently being used in the gui, but was kept for further use.
     * @param fromDate The from date seleted by the user.
     * @param toDate The to date selected by the user.
     */

    public String highestTotalDeathDate(LocalDate fromDate, LocalDate toDate)
    {
        int MAX_Deaths = 0;
        String maxDeathDate = null;
        for (LocalDate currentDate = fromDate; currentDate.isBefore(toDate) || currentDate.isEqual(toDate); currentDate = currentDate.plusDays(1))
        {
            for (CovidData dataRecord: dataRecords)
            {
                if (currentDate.toString().equals(dataRecord.getDate()))
                {
                    if (MAX_Deaths <= dataRecord.getTotalDeaths())
                    {
                        MAX_Deaths = dataRecord.getTotalDeaths();
                        maxDeathDate = dataRecord.getDate();
                    }
                }
            }
        }
        System.out.println(MAX_Deaths);
        return maxDeathDate;
    }
}
