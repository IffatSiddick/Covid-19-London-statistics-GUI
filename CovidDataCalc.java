import java.util.ArrayList;
import java.time.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This class holds some implementation methods for the application.
 *
 * @author Abirami M., Gunel F., Ekaterina H., Iffat S.
 * @date 18/03/23
 */
public class CovidDataCalc
{
    private CovidDataLoader dataLoader;
    private ArrayList<CovidData> dataRecords;
    private String date = null;
    private int totalRR = 0; 
    private int totalGP = 0;
    private int totalParks = 0;
    private int totalTS = 0;
    private int totalWorkplace = 0;
    private int totalResidential = 0;
    private int totalNewCases = 0;
    private int totalTotalCases = 0;
    private int totalNewDeaths = 0;
    private int totalDeaths = 0;
    
    /**
     * Constructor for objects of class CovidDataCalc.
     */
    public CovidDataCalc()
    {
        dataLoader = new CovidDataLoader();
        dataRecords = dataLoader.load();
    }
    
    /**
     * This method checks if the entered date exits in the csv file or not.
     * 
     * @param fromDate The from date selected by the user.
     * @param toDate The to date selected by the user.
     * @return boolean A boolean value representing if the data is available or not.
     */
    public boolean whetherThereIsAvailableData(LocalDate fromDate, LocalDate toDate) 
    {
        boolean availableData = false;
        
        test :
        for (LocalDate currentDate = fromDate; currentDate.isBefore(toDate) || currentDate.isEqual(toDate); currentDate = currentDate.plusDays(1))
        {
            for (CovidData dataRecord : dataRecords)
            {
                if (currentDate.toString().equals(dataRecord.getDate())) {
                    availableData = true;
                    break test;
                }
            }
        }
        
        return availableData;
    }
    
    /**
     * This method goes through the csv file and gets the statistics for each borough
     * based on the given date range.
     * 
     * @param fromDate The from date selected by the user.
     * @param toDate The to date selected by the user.
     * @return ObservableList<StatsPerBoroughEntry> A list of variables of type StatsPerBorough.
     */
    public ObservableList<StatsPerBoroughEntry> totalDataPerBorough(LocalDate fromDate, LocalDate toDate, String borough){
        ObservableList<StatsPerBoroughEntry> boroughEntries = FXCollections.observableArrayList();
        for (LocalDate currentDate = fromDate; currentDate.isBefore(toDate) || currentDate.isEqual(toDate); currentDate = currentDate.plusDays(1))
        {
            for (CovidData dataRecord : dataRecords)
            {
                if (currentDate.toString().equals(dataRecord.getDate()) && borough.equals(dataRecord.getBorough())) {
                    date = dataRecord.getDate();
                    totalRR = dataRecord.getRetailRecreationGMR();
                    totalGP = dataRecord.getGroceryPharmacyGMR();
                    totalParks = dataRecord.getParksGMR();
                    totalTS = dataRecord.getTransitGMR();
                    totalWorkplace = dataRecord.getWorkplacesGMR();
                    totalResidential = dataRecord.getResidentialGMR();
                    totalNewCases = dataRecord.getNewCases();
                    totalTotalCases = dataRecord.getTotalCases();
                    totalNewDeaths = dataRecord.getNewDeaths();
                    totalDeaths = dataRecord.getTotalDeaths();
                }
            }
            
            StatsPerBoroughEntry currentEntry = new StatsPerBoroughEntry(date, totalRR, totalGP, totalParks,
                                                totalTS, totalWorkplace, totalResidential, 
                                                totalNewCases, totalTotalCases, totalNewDeaths);
                                                
            boroughEntries.add(currentEntry);
        }
        
        return boroughEntries; 
    }
    
    /**
     * This method gets the total death for a specified borough based on the given date range.
     * 
     * @param fromDate The from date selected by the user.
     * @param toDate The to date selected by the user.
     * @param borough The borough to get the statistics for.
     * @return int The total value of deaths.
     */
    public int getTotalDeaths(LocalDate fromDate, LocalDate toDate, String borough)
    {
        int totalDeaths = 0;
        for (LocalDate currentDate = fromDate; currentDate.isBefore(toDate) || currentDate.isEqual(toDate); currentDate = currentDate.plusDays(1))
        {
            for (CovidData dataRecord : dataRecords)
            {
                if (currentDate.toString().equals(dataRecord.getDate()) && borough.equals(dataRecord.getBorough())) {
                    totalDeaths += dataRecord.getTotalDeaths();
                }
            }
        }
        
        return totalDeaths;
    }
}
