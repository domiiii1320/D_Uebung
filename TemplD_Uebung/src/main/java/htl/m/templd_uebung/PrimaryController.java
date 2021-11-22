package htl.m.templd_uebung;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import htl.m.templd_uebung.model.Person;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.WritableImage;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;

public class PrimaryController implements Initializable {

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
    private BarChart<String, Number> bc_BMI;
    @FXML
    private BarChart<String, Number> bc_Groesse;
    @FXML
    private BarChart<String, Number> bc_Gewicht;
    @FXML
    private ImageView iv_Person;
    @FXML
    private ListView<Person> lv_Persons;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        iv_Person.setImage(new Image("animation2.gif"));
        handleDragAndDrop(iv_Person);
        
        lv_Persons.setCellFactory((p) -> {
            return new PersonCell();
        });

        lv_Persons.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                setData(lv_Persons.getSelectionModel().getSelectedItem());
            }
        });
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
                lv_Persons.getItems().add(p);
                setData(p);
            }
        });
    }

    private void setData(Person p) {
        bc_BMI.getData().clear();
        bc_Gewicht.getData().clear();
        bc_Groesse.getData().clear();
        
        WritableImage image = new WritableImage(p.getWidth(), p.getHeight());
        image.getPixelWriter().setPixels(0, 0, p.getWidth(), p.getHeight(), PixelFormat.getIntArgbInstance(), p.getImage(), 0, p.getWidth() * 4);
        iv_Person.setImage(image);

        XYChart.Series<String, Number> s = new XYChart.Series<String, Number>();
        ObservableList l = FXCollections.observableArrayList();
        l.add(new XYChart.Data<String, Number>("BMI", p.getBmi()));
        l.add(new XYChart.Data<String, Number>("FFMI", p.getFfmi()));
        s.setData(l);
        bc_BMI.getData().add(s);

        XYChart.Series<String, Number> s2 = new XYChart.Series<String, Number>();
        ObservableList l2 = FXCollections.observableArrayList();
        l2.add(new XYChart.Data<String, Number>("Gewicht", p.getGewicht()));
        s2.setData(l2);
        bc_Gewicht.getData().add(s2);

        XYChart.Series<String, Number> s3 = new XYChart.Series<String, Number>();
        ObservableList l3 = FXCollections.observableArrayList();
        l3.add(new XYChart.Data<String, Number>("Groesse", p.getGroesse()));
        s3.setData(l3);
        bc_Groesse.getData().add(s3);

        setLabels(p);
    }

    private void setLabels(Person p) {
        lbl_id.setText(p.getId() + "");
        lbl_Vorname.setText(p.getVn());
        lbl_Nachname.setText(p.getNn());
        lbl_Groesse.setText(p.getGroesse() + "");
        lbl_Gewicht.setText(p.getGewicht() + "");
        lbl_BMI.setText(p.getBmi() + "");
        lbl_FFMI.setText(p.getFfmi() + "");
    }
}
