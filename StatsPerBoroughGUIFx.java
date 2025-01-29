import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.collections.ObservableList;
import java.time.LocalDate;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * This is the GUI of the StatsPerBoroughEntry class.
 *
 * @author Abirami M., Gunel F., Ekaterina H., Iffat S.
 * @date 18/03/23
 */
public class StatsPerBoroughGUIFx extends Application
{
    static Stage boroughWindow = new Stage();
    private TableView<StatsPerBoroughEntry> table;
    
    static ObservableList<StatsPerBoroughEntry> listOfEntries;
    
    static LocalDate fromDate;
    static LocalDate toDate;
    static String borough;

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
        table = new TableView<StatsPerBoroughEntry>();
        
        boroughWindow = stage; 
        boroughWindow.setTitle(borough);
        
        //setting up date column
        TableColumn<StatsPerBoroughEntry, String> dateColumn = new TableColumn<>("Date");
        dateColumn.setMinWidth(100);
        dateColumn.setCellValueFactory(new PropertyValueFactory<StatsPerBoroughEntry, String>("date"));
        
        //setting up rr column
        TableColumn<StatsPerBoroughEntry, String> rrColumn = new TableColumn<>("RR GMR");
        rrColumn.setMinWidth(100);
        rrColumn.setCellValueFactory(new PropertyValueFactory<StatsPerBoroughEntry, String>("retailRecreationGMR"));
        
        //setting up gp column
        TableColumn<StatsPerBoroughEntry, String> gpColumn = new TableColumn<>("GP GMR");
        gpColumn.setMinWidth(100);
        gpColumn.setCellValueFactory(new PropertyValueFactory<StatsPerBoroughEntry, String>("groceryPharmacyGMR"));
        
        //setting up parks column
        TableColumn<StatsPerBoroughEntry, String> parksColumn = new TableColumn<>("Parks GMR");
        parksColumn.setMinWidth(100);
        parksColumn.setCellValueFactory(new PropertyValueFactory<StatsPerBoroughEntry, String>("parksGMR"));
        
        //setting up transit column
        TableColumn<StatsPerBoroughEntry, String> transitColumn = new TableColumn<>("Transit GMR");
        transitColumn.setMinWidth(100);
        transitColumn.setCellValueFactory(new PropertyValueFactory<StatsPerBoroughEntry, String>("transitGMR"));
        
        //setting up workplace column
        TableColumn<StatsPerBoroughEntry, String> workplaceColumn = new TableColumn<>("Workplace GMR");
        workplaceColumn.setMinWidth(100);
        workplaceColumn.setCellValueFactory(new PropertyValueFactory<StatsPerBoroughEntry, String>("workplacesGMR"));
        
        //setting up residential column
        TableColumn<StatsPerBoroughEntry, String> residentialColumn = new TableColumn<>("Residential GMR");
        residentialColumn.setMinWidth(100);
        residentialColumn.setCellValueFactory(new PropertyValueFactory<StatsPerBoroughEntry, String>("residentialGMR"));
        
        //setting up newCases column
        TableColumn<StatsPerBoroughEntry, String> newCasesColumn = new TableColumn<>("New Cases");
        newCasesColumn.setMinWidth(100);
        newCasesColumn.setCellValueFactory(new PropertyValueFactory<StatsPerBoroughEntry, String>("newCases"));
        
        //setting up totalCases column
        TableColumn<StatsPerBoroughEntry, String> totalCasesColumn = new TableColumn<>("Total Cases");
        totalCasesColumn.setMinWidth(100);
        totalCasesColumn.setCellValueFactory(new PropertyValueFactory<StatsPerBoroughEntry, String>("totalCases"));
        
        //setting up newDeaths column
        TableColumn<StatsPerBoroughEntry, String> newDeathsColumn = new TableColumn<>("New Deaths");
        newDeathsColumn.setMinWidth(100);
        newDeathsColumn.setCellValueFactory(new PropertyValueFactory<StatsPerBoroughEntry, String>("newDeaths"));
        
        table.setItems(listOfEntries);
        table.getColumns().addAll(dateColumn, rrColumn, gpColumn, parksColumn, transitColumn, workplaceColumn, 
                                    residentialColumn, newCasesColumn, totalCasesColumn, newDeathsColumn);
        
        VBox component = new VBox();
        component.getChildren().addAll(table);
        //component.setPrefSize(300, 300);
        
        Scene scene = new Scene(component);
        
        boroughWindow.setScene(scene);

        // Show the Stage (window)
        boroughWindow.show();
    }
}
