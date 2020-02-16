package sample.component;

import javafx.beans.value.ObservableValueBase;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Circle;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;



public class CircleColorPickerTableCell<T> extends TableCell<T, Circle> {

    protected Circle circle;




    public CircleColorPickerTableCell() {

        circle = new Circle(10.0);
        setAlignment(Pos.CENTER);



    };

    public Circle getCircle() {
        return circle;
    }

    @Override
    protected void updateItem(Circle item, boolean empty) {
        super.updateItem(item, empty);

        setText(null);
        if(empty) {
            setGraphic(null);
        } else {

            this.setGraphic(item);
        }
    }

    @Override
    public void startEdit() {
        super.startEdit();
        ColorPicker colorPicker = new ColorPicker((Color) circle.getFill());

        setGraphic(colorPicker);
        EventHandler<KeyEvent> handler = key -> {
            if (key.getCode() == KeyCode.ENTER) {
                circle.setFill(colorPicker.getValue());
                commitEdit(circle);
            } else if (key.getCode() == KeyCode.O) {
                cancelEdit();
            }
        };
        onKeyPressedProperty().setValue(handler);
        colorPicker.onKeyPressedProperty().setValue(handler);
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();
        setGraphic(circle);
        cleanupEditor();
    }

    @Override
    public void commitEdit(Circle newValue) {
        super.commitEdit(newValue);
        cleanupEditor();
    }


    private void cleanupEditor() {
        onKeyPressedProperty().setValue(null);

    }
}