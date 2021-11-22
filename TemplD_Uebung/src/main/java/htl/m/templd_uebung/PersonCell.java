/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package htl.m.templd_uebung;

import htl.m.templd_uebung.model.Person;
import javafx.scene.control.ListCell;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

/**
 *
 * @author User
 */
public class PersonCell extends ListCell<Person> {

    ImageView iv;

    @Override
    protected void updateItem(Person item, boolean empty) {
        super.updateItem(item, empty);

        if ((item != null) & !empty) {

            WritableImage image = new WritableImage(item.getWidth(), item.getHeight());
            image.getPixelWriter().setPixels(0, 0, item.getWidth(), item.getHeight(), PixelFormat.getIntArgbInstance(), item.getImage(), 0, item.getWidth() * 4);
            iv = new ImageView(image);
            setText("Person -> " + item.getVn() + " " + item.getNn());

            if (item.getBmi() >= 18.5 && item.getBmi() <= 25) {
                setTextFill(Color.BLUE);
                setTooltip(new Tooltip("BMI ist im normalen Bereich"));
            } else if (item.getFfmi() >= 21) {
                setTextFill(Color.GREEN);
                setTooltip(new Tooltip("Sportler!!"));
            } else {
                setTextFill(Color.RED);
                setTooltip(new Tooltip("Sie müssen etwas an Ihrer Lebensführung ändern"));
            }
        }
        setGraphic(iv);
    }
}
