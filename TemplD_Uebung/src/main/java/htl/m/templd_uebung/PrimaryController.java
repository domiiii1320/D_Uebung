package htl.m.templd_uebung;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import htl.m.templd_uebung.model.Person;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;

public class PrimaryController  implements Initializable{

    @FXML
    private Label lbl_id;
    @FXML
    private Label lbl_Vorname;
    @FXML
    private Label lbl_Nachname;
    @FXML
    private Label lbl_Groesse;
    @FXML
    private Label lbl_Gewicht;
    @FXML
    private Label lbl_BMI;
    @FXML
    private Label lbl_FFMI;
    @FXML
    private BarChart<?, ?> bc_BMI;
    @FXML
    private BarChart<?, ?> bc_Groesse;
    @FXML
    private BarChart<?, ?> bc_Gewicht;
    @FXML
    private ImageView iv_Person;
    @FXML
    private ListView<?> lv_Persons;

  /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        iv_Person.setImage(new Image("animation2.gif"));
        handleDragAndDrop(iv_Person);
        
        
    }
    
    private void handleDragAndDrop(ImageView view) {

        view.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent t) {
                if (t.getDragboard().hasFiles()) {
                    t.acceptTransferModes(TransferMode.ANY);
                }
                t.consume();
            }
        });
        
        view.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent t) {
                List<File> files = t.getDragboard().getFiles();
                File jsonFile = files.get(0);
                try {
                    view.setImage(new Image(new FileInputStream(jsonFile)));
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
                }
                t.consume();
                
                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();
                Person p = null;
                try {
                    p = gson.fromJson(new FileReader(jsonFile), Person.class);
                    System.out.println("");
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}
