/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package htl.m.templd_uebung;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author david
 */
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
    }    
    
}
