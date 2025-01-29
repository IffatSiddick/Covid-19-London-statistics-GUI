/**
 * This class represents one record of data per borough derived from the COVID dataset.
 * The difference between the CovidData class and this class is that, this is only for storing a record of data of a specified borough.
 *
 * @author Abirami M., Gunel F., Ekaterina H., Iffat S.
 * @date 18/03/23
 */
public class StatsPerBoroughEntry
{
    /*
    The date the COVID information (cases & deaths) was collected
    */
    private String date;
    
    /*
    Google mobility data
    */
    private int retailRecreationGMR;
    private int groceryPharmacyGMR;
    private int parksGMR;
    private int transitGMR;
    private int workplacesGMR;
    private int residentialGMR;
    
    /*
    The COVID information that's collected daily for each London borough
    */
    private int newCases;
    private int totalCases;
    private int newDeaths;
    
    
    /**
     * Constructor for objects of class StatsPerBoroughEntry.
     */
    public StatsPerBoroughEntry(String date, int retailRecreationGMR, int groceryPharmacyGMR,
                                int parksGMR, int transitGMR, int workplacesGMR, int residentialGMR,
                                int newCases, int totalCases, int newDeaths)
    {
        this.date = date;
        
        this.retailRecreationGMR = retailRecreationGMR;
        this.groceryPharmacyGMR = groceryPharmacyGMR;
        this.parksGMR = parksGMR;
        this.transitGMR = transitGMR;
        this.workplacesGMR = workplacesGMR;
        this.residentialGMR = residentialGMR;
        
        this.newCases = newCases;
        this.totalCases = totalCases;
        this.newDeaths = newDeaths;
    }
    
    /**
     * This method returns the date.
     * @return String The date from the statistics.
     */
    public String getDate(){
        return date;
    }
    
    /**
     * This method returns the retail and recreation data.
     * @return int The value representing retail and recreation from the statistics.
     */
    public int getRetailRecreationGMR(){
        return retailRecreationGMR;
    }
    
    /**
     * This method returns the grocery and pharmacy data.
     * @return int The value representing grocery and pharmacy from the statistics.
     */
    public int getGroceryPharmacyGMR(){
        return groceryPharmacyGMR;
    }
    
    /**
     * This method returns the parks data.
     * @return int The value representing parks from the statistics.
     */
    public int getParksGMR() {
        return parksGMR;
    }

    /**
     * This method returns the transit data.
     * @return int The value representing transit from the statistics.
     */
    public int getTransitGMR() {
        return transitGMR;
    }

    /**
     * This method returns the workplaces data.
     * @return int The value representing workplaces from the statistics.
     */
    public int getWorkplacesGMR() {
        return workplacesGMR;
    }

    /**
     * This method returns the residential data.
     * @return int The value representing residential from the statistics.
     */
    public int getResidentialGMR() {
        return residentialGMR;
    }
    
    /**
     * This method returns the new cases data.
     * @return int The value representing new cases from the statistics.
     */
    public int getNewCases(){
        return newCases;
    }
    
    /**
     * This method returns the total cases data.
     * @return int The value representing total cases from the statistics.
     */
    public int getTotalCases(){
        return totalCases;
    }
    
    /**
     * This method returns the new deaths data.
     * @return int The value representing new deaths from the statistics.
     */
    public int getNewDeaths() {
        return newDeaths;
    }
}
