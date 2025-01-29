import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.geometry.Insets;
import javafx.scene.image.*;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.Pos;
import java.util.ArrayList;
import java.io.*;
import java.net.URL;
import java.time.*;
import java.util.concurrent.TimeUnit;
import javafx.scene.control.Alert.AlertType;
import javafx.fxml.*;
import javafx.fxml.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * CovidGUI is the main class of the London COVID-19 Statistics application. It builds and
 * displays the application GUI and initialises all other components.
 *
 * @author Abirami M., Gunel F., Ekaterina H., Iffat S.
 * @date 18/03/23
 */
public class CovidGUI extends Application
{
    private Button previousButton;
    private Button nextButton;
    
    private Button previousButton1;
    private Button nextButton1;

    private DatePicker fromDateComponent;
    private DatePicker toDateComponent;
    private LocalDate fromDate;
    private LocalDate toDate;
    
    private Label imageLabel;
    private Label dateRange;

    private ArrayList <Node> panels;  
    private ArrayList <Node> statsPanelsArray; 
    private Node currentPanel;
    private Node currentStatsPanel;
    private BorderPane root;
    private BorderPane statsPane;
    
    private CovidStats statistics;
    
    private CovidDataCalc dataCalc;
    
    private CovidMapController mapController;


    /**
     * This method checks if the dates entered by the user are valid (to enable the buttons) and displays a common dialog accordingly.
     * @param ActionEvent event The event in which the method will execute its contents.
     */
    private void dateIsValid(ActionEvent event)
    {
        fromDate = fromDateComponent.getValue();
        toDate = toDateComponent.getValue();
        
        if (fromDate != null && toDate != null && fromDate.isAfter(toDate)) {
            //showing an alert, invalid date range 
            showInvalidDateRangeError();
                
            previousButton.setDisable(true);
            nextButton.setDisable(true);
        }
        else if (fromDate != null && toDate != null){            
            displayDateRange();
            boolean availableData = dataCalc.whetherThereIsAvailableData(fromDate, toDate);
            if (availableData == false) {
                //if there is no available data in this date range
                showNoAvailableDataError();

                previousButton.setDisable(true);
                nextButton.setDisable(true);
            }
            else {
                previousButton.setDisable(false);
                nextButton.setDisable(false);
                
                panels();
                
                mapController.setFromDate(fromDate);
                mapController.setToDate(toDate);
                mapController.setMapColours();
            }
        }
    }
    
    /**
     * Show an error message to the user informing them the from date is after the to date.
     */
    private void showInvalidDateRangeError()
    {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Date range invalid");
        alert.setHeaderText(null);
        alert.setContentText("The \'From\' date must be before the \'To\' date");
        
        alert.showAndWait();
    }

    /**
     * Show an error message to the user informing them there is no available 
     * data for any of the chosen dates.
     */
    private void showNoAvailableDataError()
    {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("No available data");
        alert.setHeaderText(null);
        alert.setContentText("There is no available data for the dates chosen");
        
        alert.showAndWait();
    }
    
    /**
     * The start method is the main entry point for every JavaFX application. 
     * It is called after the init() method has returned and after 
     * the system is ready for the application to begin running.
     *
     * @param  stage the primary stage for this application.
     */
    @Override
    public void start(Stage stage)
    {
        fromDateComponent = new DatePicker();
        toDateComponent = new DatePicker();

        dataCalc = new CovidDataCalc();

        statistics = new CovidStats();
        
        root = new BorderPane();
        
        panels = new ArrayList<>(4);

        
        panels.add(null);
        panels.add(null);
        panels.add(null);
        panels.add(null);
        
        panels.set(0, welcomePanel());
        
        currentPanel = panels.get(0);

        root.setCenter(currentPanel);
        root.setPrefSize(800,515);

        root.setTop(makeTopMenuBar());
        root.setBottom(makeBottomMenuBar());

        // JavaFX must have a Scene (window content) inside a Stage (window)
        Scene scene = new Scene(root);
        root.setId("root");
        scene.getStylesheets().add("CovidGUI.css");
        stage.setTitle("Covid distribution in London Boroughs");

        stage.setScene(scene);
        
        // Show the Stage (window)
        stage.show();
    }
    
    /**
     * Splitting center to border pane into VBox and returning the welcome label.
     * 
     * @param welcomeLabel Instructions for user on how to use program.
     * @param dateRange Description of date range once selected by user.
     * @return Node The welcomeLayout panel.
     */
    public Node welcomePanel()
    {
        VBox welcomeLayout = new VBox();
        welcomeLayout.setAlignment(Pos.CENTER);
        Label welcomeLabelMsg1 = new Label("Welcome user.");
        Label welcomeLabel = new Label(
            "\nOnce you have entered a date range, a map will display the number of covid related deaths in London." + 
            "\nThe map is colour coded with red indicating the highest death toll and green the lowest." +
            "\nSelecting a borough will allow you to see the COVID statistics of that particular borough." +
            "\nUse the arrows located in the bottom corners of the screen to move between panels and view further statistics " +
            "\nrelating to the whole of London.");;
        
        dateRange = new Label("");
        welcomeLabelMsg1.setId("text");
        welcomeLabel.setId("text");
        dateRange.setId("text");
        welcomeLayout.getChildren().addAll(welcomeLabelMsg1, welcomeLabel, dateRange);
        
        return welcomeLayout;
    }
     
    /**
     * Explain what this method does here...
     * @return Node The map panel.
     */
    public Node mapPanel()
    {   
        URL url = getClass().getResource("map.fxml");
        try
        {
            var loader = new FXMLLoader(url);
            Node panel = loader.load();
            mapController = loader.getController();

            return panel;
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
        return null;
    }
    
    /**
     * Once user has entered the dates using date picker, they will be displayed on the screen under the welcome message.
     * 
     * @param fromDateString The value entered via date picker.
     * @param toDateString The value entered via date picker.
     */
    public void displayDateRange()
    {
        String fromDateString = fromDateComponent.getEditor().getText();
        String toDateString = toDateComponent.getEditor().getText();
        dateRange.setText("The date range you have selected is: " + fromDateString + " - " + toDateString);
    }
    
    /**
     * This method creates the top menu bar.
     * @return HBox The HBox to be used in the start method.
     */
    public HBox makeTopMenuBar()
    {
        HBox menuTop = new HBox();
        menuTop.setAlignment(Pos.BASELINE_RIGHT);
        
        Label from = new Label("From: ");
        Label to = new Label("To: ");
        
        fromDateComponent.setOnAction(this::dateIsValid);
        
        toDateComponent.setOnAction(this::dateIsValid);
        
        menuTop.getChildren().addAll(from, fromDateComponent, to, toDateComponent);
        
        return menuTop;
    }
    
    /**
     * This method creates the bottom menu bar.
     * @return HBox The HBox to be used in the start method.
     */
    private HBox makeBottomMenuBar(){
        HBox menuBottom = new HBox();
        
        menuBottom.setAlignment(Pos.BOTTOM_CENTER);
        
        previousButton = new Button("<");
        previousButton.getStyleClass().add("previousNextButtons");
        previousButton.setOnAction(this::previousPanel);
        
        nextButton = new Button(">");
        nextButton.getStyleClass().add("previousNextButtons");
        nextButton.setOnAction(this::nextPanel);

        //initially the buttons are disabled, until a date is picked
        previousButton.setDisable(true);
        nextButton.setDisable(true);
        
        Pane space = new Pane();
        HBox.setHgrow(space, Priority.ALWAYS);
        space.setMinSize(10,1);
        
        menuBottom.getChildren().addAll(previousButton, space, nextButton);
        
        return menuBottom;
    }
    
    /**
     * Explain what this method does here...
     * @return Node The statistics panel.
     */
    public Node statisticsPanel()
    {   
        statsPanels();
        currentStatsPanel = statsPanelsArray.get(0);
        
        previousButton1 = new Button("<");
        previousButton1.setOnAction(this::previousStat);
        previousButton1.setId("previousNextStatsButton");

        nextButton1 = new Button(">");
        nextButton1.setOnAction(this::nextStat);
        nextButton1.setId("previousNextStatsButton");

        statsPane = new BorderPane(currentStatsPanel,null, nextButton1, null, previousButton1);
        
        return statsPane;
    }
    
    /**
     * This method gets the retail and recreation statistics and adds a label to them.
     * @return Node The retail and recreation panel.
     */
    public Node retailRecreationStats()
    {
        VBox centralPanel = new VBox();
        centralPanel.setAlignment(Pos.CENTER);
        
        String descString = "Average Retail and Recreation";
        double temp = statistics.averageRetailRecreation(fromDate, toDate);
        String statsString = Double.toString(temp);
        //nextButton1.setOnAction(this::nextStat);
        
        Label description = new Label(descString);
        Label stat = new Label(statsString);
        
        description.setId("statsTitle");
        stat.setId("stats");
        
        centralPanel.getChildren().addAll(description, stat);
        
        return centralPanel;
    }
    
    /**
     * This method gets the grocery and pharmacy statistics and adds a label to them.
     * @return Node The grocery and pharmacy panel.
     */
    public Node groceryPharmacyStats()
    {
        VBox centralPanel = new VBox();
        centralPanel.setAlignment(Pos.CENTER);
        String descString = "Average Grocery and Pharmacy";
        double temp = statistics.averageGroceryAndPharmacy(fromDate, toDate);
        String statsString = Double.toString(temp);
        
        Label description = new Label(descString);
        Label stat = new Label(statsString);
        
        description.setId("statsTitle");
        stat.setId("stats");
        
        centralPanel.getChildren().addAll(description, stat);
    
        return centralPanel;
    }
    
    /**
     * This method gets the total death statistics and adds a label to them.
     * @return Node The total deaths panel.
     */
    public Node totalDeathsStats()
    {
        VBox centralPanel = new VBox();
        centralPanel.setAlignment(Pos.CENTER);
        String descString = "Total Deaths";
        int temp = statistics.totalDeaths(fromDate, toDate);
        String statsString = Integer.toString(temp);
        
        Label description = new Label(descString);
        Label stat = new Label(statsString);
        
        description.setId("statsTitle");
        stat.setId("stats");
        
        centralPanel.getChildren().addAll(description, stat);
        
        return centralPanel;
    }
    
    /**
     * This method gets the total cases statistics and adds a label to them.
     * @return Node The total cases panel.
     */
    public Node totalCasesStats()
    {
        VBox centralPanel = new VBox();
        centralPanel.setAlignment(Pos.CENTER);
        String descString = "Average Total Cases";
        double temp = statistics.totalCasesAverage(fromDate, toDate);
        String statsString = Double.toString(temp);
        
        Label description = new Label(descString);
        Label stat = new Label(statsString);
        
        description.setId("statsTitle");
        stat.setId("stats");
        
        centralPanel.getChildren().addAll(description, stat);
        
        return centralPanel;
    }
    
    /**
     * This method gets the highest total death date statistics and adds a label to them.
     * @return Node The highest total death date panel.
     */
    public Node highestTotalDeathDateStats()
    {
        VBox centralPanel = new VBox();
        centralPanel.setAlignment(Pos.CENTER);
        String descString = "Highest Total Death Date";
        String statsString = statistics.highestTotalDeathDate(fromDate, toDate);
        
        Label description = new Label(descString);
        Label stat = new Label(statsString);
        
        description.setId("statsTitle");
        stat.setId("stats");
        
        centralPanel.getChildren().addAll(description, stat);
        
        return centralPanel;
    }
    
    /**
     * Explain what this method does here...
     */
    public void panels()
    {
        panels.set(1, mapPanel());
        panels.set(2, statisticsPanel());
        panels.set(3, makeLineGraphPanel());
    }
    
    /**
     * This method holds an array list with stats panels using the statistics calculated in the CovidStats class.
     */
    public void statsPanels()
    {
        statsPanelsArray = new ArrayList<>();
        
        statsPanelsArray.add(retailRecreationStats());
        statsPanelsArray.add(groceryPharmacyStats());
        statsPanelsArray.add(totalDeathsStats());
        statsPanelsArray.add(totalCasesStats());
        statsPanelsArray.add(highestTotalDeathDateStats()); 
    }
    
    /**
     * Explain what this method does here...
     */
    public LineChart makeLineGraphPanel()
    {
        LineGraph graph = new LineGraph();
        return graph.makeLineGraph(fromDate, toDate);
    }
    
    /**
     * Explain what this method does here...
     * @param event The event in which the method will execute its contents.
     */
    public void previousPanel(ActionEvent event)
    {
        if (panels.indexOf(currentPanel) == 0) {
            currentPanel = panels.get(panels.size() - 1);
        }
        else {
            Node newPanel = panels.get((panels.indexOf(currentPanel) - 1) % panels.size());
            currentPanel = newPanel;
        }
        root.setCenter(currentPanel);
    }
    
    /**
     * Explain what this method does here...
     * @param event The event in which the method will execute its contents.
     */
    public void nextPanel(ActionEvent event)
    {
        Node newPanel = panels.get((panels.indexOf(currentPanel) + 1) % panels.size());
        currentPanel = newPanel;
        root.setCenter(currentPanel);
    }
    
    /**
     * Explain what this method does here...
     * @param event The event in which the method will execute its contents.
     */
    public void previousStat(ActionEvent event)
    {
        if (statsPanelsArray.indexOf(currentStatsPanel) == 0) {
            currentStatsPanel = statsPanelsArray.get(statsPanelsArray.size() - 1);
        }
        else {
            Node newPanel = statsPanelsArray.get((statsPanelsArray.indexOf(currentStatsPanel) - 1) % statsPanelsArray.size());
            currentStatsPanel = newPanel;
        }
        statsPane.setCenter(currentStatsPanel);
    }
    
    /**
     * Explain what this method does here...
     * @param event The event in which the method will execute its contents.
     */
    public void nextStat(ActionEvent event)
    {
        Node newPanel = statsPanelsArray.get((statsPanelsArray.indexOf(currentStatsPanel) + 1) % statsPanelsArray.size());
        currentStatsPanel = newPanel;
        statsPane.setCenter(currentStatsPanel);
    }
    
    /**
     * This method returns the from date.
     * @return LocalDate The from date.
     */
    public LocalDate getFromDate()
    {
        return fromDate;
    }
    
    /**
     * This method returns the to date.
     * @return LocalDate The to date.
     */
    public LocalDate getToDate()
    {
        return toDate;
    }
}