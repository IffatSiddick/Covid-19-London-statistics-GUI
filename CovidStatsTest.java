

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

/**
 * The test class CovidStatsTest.
 * 
 * @author Gunel F., Abirami M., Ekaterina H., Iffat S.
 * @version 6/03/23
 */

public class CovidStatsTest
{
    /**
     * Default constructor for test class CovidStatsTest
     */
    public CovidStatsTest()
    {
    }
    
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
    }

    @Test
    public void boundaryAvgRetailRecreationTest()
    {
        CovidStats covidSta1 = new CovidStats();
        assertEquals(-19.26, covidSta1.averageRetailRecreation(java.time.LocalDate.of(2022,10,14), java.time.LocalDate.of(2022,10,15)), 0.1);
    }

    @Test
    public void boundaryAvgGroceryAndPharmacyTest()
    {
        CovidStats covidSta1 = new CovidStats();
        assertEquals(-2.85, covidSta1.averageGroceryAndPharmacy(java.time.LocalDate.of(2022, 10,14), java.time.LocalDate.of(2022, 10,15)), 0.1);
    }

    @Test
    public void boundarytotalDeathTest()
    {
        CovidStats covidSta1 = new CovidStats();
        assertEquals(22020, covidSta1.totalDeaths(java.time.LocalDate.of(2022,10,14), java.time.LocalDate.of(2022,10,15)));
    }

    @Test
    public void boundarytotalCasesTest()
    {
        CovidStats covidSta1 = new CovidStats();
        assertEquals(96255.0, covidSta1.totalCasesAverage(java.time.LocalDate.of(2022,10,14), java.time.LocalDate.of(2022,10,15)), 0.1);
    }

    @Test
    public void boundaryHighestTotalDeathDateTest()
    {
        CovidStats covidSta1 = new CovidStats();
        assertEquals("2022-10-15", covidSta1.highestTotalDeathDate(java.time.LocalDate.of(2022,10,14), java.time.LocalDate.of(2022,10,15)));
    }

    @Test
    public void avgRetailRecreationTest()
    {
        CovidStats covidSta1 = new CovidStats();
        assertEquals(-39.53, covidSta1.averageRetailRecreation(java.time.LocalDate.of(2020,07,13), java.time.LocalDate.of(2020,07,27)), 0.1);
    }
    
    @Test
    public void avgGroceryAndPharmacyTest()
    {
        CovidStats covidSta1 = new CovidStats();
        assertEquals(-17.43, covidSta1.averageGroceryAndPharmacy(java.time.LocalDate.of(2020,07,13), java.time.LocalDate.of(2020,07,27)), 0.1);
    }
    
    @Test
    public void totalDeathTest()
    {
        CovidStats covidSta1 = new CovidStats();
        assertEquals(6353, covidSta1.totalDeaths(java.time.LocalDate.of(2020,07,13), java.time.LocalDate.of(2020,07,27)));
    }
    
    @Test
    public void totalCasesTest()
    {
        CovidStats covidSta1 = new CovidStats();
        assertEquals(1096.0, covidSta1.totalCasesAverage(java.time.LocalDate.of(2020,07,13), java.time.LocalDate.of(2020,07,27)), 0.1);
    }
    
    @Test
    public void highestTotalDeathDateTest()
    {
        CovidStats covidSta1 = new CovidStats();
        assertEquals("2020-07-27", covidSta1.highestTotalDeathDate(java.time.LocalDate.of(2020,07,13), java.time.LocalDate.of(2020,07,27)));
    }
}













