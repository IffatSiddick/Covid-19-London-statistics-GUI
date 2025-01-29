import javafx.fxml.*;
import javafx.scene.control.*;
import java.time.LocalDate;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import java.util.List;
import javafx.scene.*;
import java.util.HashMap;

/**
 * This is the controller class of the map created in the java sceneBuilder.
 *
 * @author Abirami M., Gunel F., Ekaterina H., Iffat S.
 * @date 18/03/23
 */
public class CovidMapController
{
    @FXML
    private Button enfieldBoroughButton;
    @FXML
    private Button barnetBoroughButton;
    @FXML
    private Button haringeyBoroughButton;
    @FXML
    private Button walthamForestBoroughButton;
    @FXML
    private Button harrowBoroughButton;
    @FXML
    private Button brentBoroughButton;
    @FXML
    private Button camdenBoroughButton;
    @FXML
    private Button islingtonBoroughButton;
    @FXML
    private Button hackneyBoroughButton;
    @FXML
    private Button redbridgeBoroughButton;
    @FXML
    private Button haveringBoroughButton;
    @FXML
    private Button hillingdonBoroughButton;
    @FXML
    private Button ealingBoroughButton;
    @FXML
    private Button kensingtonAndChelseaBoroughButton;
    @FXML
    private Button westministerBoroughButton;
    @FXML
    private Button towerHamletsBoroughButton;
    @FXML
    private Button newhamBoroughButton;
    @FXML
    private Button barkingAndDagenhamBoroughButton;
    @FXML
    private Button hounslowBoroughButton;
    @FXML
    private Button hammersmithAndFulhamBoroughButton;
    @FXML
    private Button wandsworthBoroughButton;
    @FXML
    private Button cityOfLondonBoroughButton;
    @FXML
    private Button greenwichBoroughButton;
    @FXML
    private Button bexleyBoroughButton;
    @FXML
    private Button richmondUponThamesBoroughButton;
    @FXML
    private Button mertonBoroughButton;
    @FXML
    private Button lambethBoroughButton;
    @FXML
    private Button southwarkBoroughButton;
    @FXML
    private Button lewishamBoroughButton;
    @FXML
    private Button kingstonUponThamesBoroughButton;
    @FXML
    private Button suttonBoroughButton;
    @FXML
    private Button croydonBoroughButton;
    @FXML
    private Button bromleyBoroughButton;
    
    
    @FXML
    private AnchorPane anchorPaneMap;
    
    private List<Node> childrenOfMap;
    
    private CovidDataCalc dataCalc;
    
    private LocalDate fromDate;
    private LocalDate toDate;
    
    private HashMap<Node, String> relateBoroughToNames;

    /**
     * Constructor for objects of class CcvidMap
     */
    public CovidMapController()
    {
        dataCalc = new CovidDataCalc();
        relateBoroughToNames = new HashMap<>();
        
        //boroughGUI = new StatsPerBoroughGUI();
    }
    
    /**
     * This method calls the method initialiseBoroughButtons().
     */
    public void initialize() {
        initialiseBoroughButtons();
    }
    
    /**
     * This method puts the buttons into a hashMap and associates them with their full names.
     */
    private void initialiseBoroughButtons()
    {
        relateBoroughToNames.put(enfieldBoroughButton, "Enfield");
        relateBoroughToNames.put(barnetBoroughButton, "Barnet");
        relateBoroughToNames.put(haringeyBoroughButton, "Haringey");
        relateBoroughToNames.put(walthamForestBoroughButton, "Waltham Forest");
        relateBoroughToNames.put(harrowBoroughButton, "Harrow");
        relateBoroughToNames.put(brentBoroughButton, "Brent");
        relateBoroughToNames.put(camdenBoroughButton, "Camden");
        relateBoroughToNames.put(islingtonBoroughButton, "Islington");
        relateBoroughToNames.put(hackneyBoroughButton, "Hackney");
        relateBoroughToNames.put(redbridgeBoroughButton, "Redbridge");
        relateBoroughToNames.put(haveringBoroughButton, "Havering");
        relateBoroughToNames.put(hillingdonBoroughButton, "Hillingdon");
        relateBoroughToNames.put(ealingBoroughButton, "Ealing");
        relateBoroughToNames.put(kensingtonAndChelseaBoroughButton, "Kensington And Chelsea");
        relateBoroughToNames.put(westministerBoroughButton, "Westminster");
        relateBoroughToNames.put(towerHamletsBoroughButton, "Tower Hamlets");
        relateBoroughToNames.put(newhamBoroughButton, "Newham");
        relateBoroughToNames.put(barkingAndDagenhamBoroughButton, "Barking And Dagenham");
        relateBoroughToNames.put(hounslowBoroughButton, "Hounslow");
        relateBoroughToNames.put(hammersmithAndFulhamBoroughButton, "Hammersmith And Fulham");
        relateBoroughToNames.put(wandsworthBoroughButton, "Wandsworth");
        relateBoroughToNames.put(cityOfLondonBoroughButton, "City Of London");
        relateBoroughToNames.put(greenwichBoroughButton, "Greenwich");
        relateBoroughToNames.put(bexleyBoroughButton, "Bexley");
        relateBoroughToNames.put(richmondUponThamesBoroughButton, "Richmond Upon Thames");
        relateBoroughToNames.put(mertonBoroughButton, "Merton");
        relateBoroughToNames.put(lambethBoroughButton, "Lambeth");
        relateBoroughToNames.put(southwarkBoroughButton, "Southwark");
        relateBoroughToNames.put(lewishamBoroughButton, "Lewisham");
        relateBoroughToNames.put(kingstonUponThamesBoroughButton, "Kingston Upon Thames");
        relateBoroughToNames.put(suttonBoroughButton, "Sutton");
        relateBoroughToNames.put(croydonBoroughButton, "Croydon");
        relateBoroughToNames.put(bromleyBoroughButton, "Bromley");
    }
    
    /**
     * This method sets the class field fromDate to the specified date.
     * @param date The desired date to set the fromDate to.
     */
    public void setFromDate(LocalDate date)
    {
        fromDate = date;
    }
    
    /**
     * This method sets the class field toDate to the specified date.
     * @param date The desired date to set the toDate to.
     */
    public void setToDate(LocalDate date)
    {
        toDate = date;
    }
    
    /**
     * This method sets the buttons' colours based on the death toll in each of the boroughs.
     */
    public void setMapColours()
    {
        childrenOfMap = anchorPaneMap.getChildren();
        
        for (Node child : childrenOfMap) 
        {
            String boroughName = relateBoroughToNames.get(child);
            
            int totalDeaths = dataCalc.getTotalDeaths(fromDate, toDate, boroughName);
            
            if (totalDeaths <= 2000) {
                child.setStyle("-fx-background-color: #039C2C; ");
            }
            else if (totalDeaths <= 3000) {
                child.setStyle("-fx-background-color: #F5F502; ");
            }
            else if (totalDeaths <= 5000) {
                child.setStyle("-fx-background-color: #FC9B0A; ");
            }
            else{
                child.setStyle("-fx-background-color: #F71B1B; ");
            }
        }
    }
    
    /**
     * This method acts when the borough button is pressed.
     * @param event The event in which the method will execute its contents.
     */
    @FXML
    private void boroughPressed(ActionEvent event)
    {
        Object button = event.getSource();
        String nameOfBorough = relateBoroughToNames.get(button);
        
        //display all the stats in the new window:
        showBoroughData(nameOfBorough);
    }
    
    /**
     * This method displays the data in a new window.
     * @param nameOfBorough A string representing the borough name.
     */
    private void showBoroughData(String nameOfBorough)
    {
        StatsPerBoroughGUIFx.fromDate = fromDate;
        StatsPerBoroughGUIFx.toDate = fromDate;
        StatsPerBoroughGUIFx.borough = nameOfBorough;
        
        StatsPerBoroughGUIFx.listOfEntries = dataCalc.totalDataPerBorough(fromDate, toDate, nameOfBorough);
        
        StatsPerBoroughGUIFx boroughGUIFx = new StatsPerBoroughGUIFx();
        boroughGUIFx.start(StatsPerBoroughGUIFx.boroughWindow);
    }
}
