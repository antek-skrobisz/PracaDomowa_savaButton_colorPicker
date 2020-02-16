
package sample.component;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


public class CheckBoxEditableTableCell<T> extends TableCell<T, Boolean> {

    protected CheckBox checkBox = new CheckBox();

    public CheckBoxEditableTableCell() {
        setAlignment(Pos.CENTER);
        checkBox.setDisable(true);
        checkBox.setAllowIndeterminate(true);
        checkBox.setAlignment(Pos.CENTER);
    }

    @Override
    protected void updateItem(Boolean value, boolean isEmpty) {
        super.updateItem(value, isEmpty);
        if (isEmpty) {
            setText(null);
            setGraphic(null);
        } else {
            setGraphic(checkBox);
            checkBox.setSelected(value);
        }
    }

    @Override
    public void startEdit() {
        super.startEdit();
        EventHandler<KeyEvent> handler = key -> {
            if (key.getCode() == KeyCode.ENTER) {
                commitEdit(checkBox.selectedProperty().getValue());
            } else if (key.getCode() == KeyCode.ESCAPE) {
                cancelEdit();
            }
        };
        onKeyPressedProperty().setValue(handler);
        checkBox.onKeyPressedProperty().setValue(handler);
        checkBox.setDisable(false);
    }

    @Override
    public void commitEdit(Boolean newValue) {
        super.commitEdit(newValue);
        cleanupEditor();
    }

    private void cleanupEditor() {
        checkBox.setDisable(true);
        onKeyPressedProperty().setValue(null);
        checkBox.onKeyPressedProperty().setValue(null);
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();
        cleanupEditor();
    }
}

