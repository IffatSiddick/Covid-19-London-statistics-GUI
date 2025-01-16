import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.chart.*;
import javafx.scene.layout.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * This class represents a line graph in the application.
 *
 * @author Abirami M., Gunel F., Ekaterina H., Iffat S.
 * @date 18/03/23
 */
public class LineGraph
{
    private ArrayList<CovidData> dataRecords;
    private CovidDataLoader dataLoader;
    
    /**
     * Constructor for objects of class LineGraph.
     * Uses the CovidDataLoader classs to create an ArrayList of Covid Data
     * This allows the LineGraph class to access data from the csv file
     */
    public LineGraph()
    {   
        dataLoader = new CovidDataLoader();
        dataRecords = dataLoader.load();
    }
    
    /**
     * This creates a line graph over the dat range selcted by the user
     * It shows how the daily death rate changed over the time period selected
     * 
     * @param fromDate The from date, as a LocalDate object, seleted by the user.
     * @param toDate The to date, as a LocalDate object, selected by the user.
     * @return LineChart The graph is returned as a LineChart object
     */
    public LineChart makeLineGraph(LocalDate fromDate, LocalDate toDate)
    {
        ObservableList<String>dates = FXCollections.observableArrayList();
        
        XYChart.Series data = new XYChart.Series();
        data.setName("Change in deaths over selected date range: ");
        
        int highestNum = 0;
        for (LocalDate currentDate = fromDate; currentDate.isBefore(toDate) || currentDate.isEqual(toDate); currentDate = currentDate.plusDays(1))
        {
            int currentTotalDeaths = 0;
            for (CovidData dataRecord : dataRecords)
            {
                if (currentDate.toString().equals(dataRecord.getDate())) {
                    currentTotalDeaths += dataRecord.getNewDeaths();
                    if (currentTotalDeaths > highestNum) {
                        highestNum = currentTotalDeaths;
                    }
                }
            }
            data.getData().add(new XYChart.Data(currentDate.toString(), currentTotalDeaths));
            dates.add(currentDate.toString());
        }
        
        int startOfRange = 0;
        int endOfRange = highestNum;
        int step = (endOfRange - startOfRange) / 10;
        
        Axis xAxis = new CategoryAxis(dates);        
        Axis yAxis = new NumberAxis("Change in deaths over selected date range: ", startOfRange, endOfRange + 10, step);
        
        LineChart lineGraph = new LineChart(xAxis, yAxis);
        lineGraph.getData().add(data);
        
        return lineGraph;
    }
}
