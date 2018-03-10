
package main;

import java.util.Random;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class PackageUI extends BorderPane {
    
    private final Label msgLabel = new Label();
    private final TextField idField = new TextField();
    private final TextField speedField = new TextField();
    private final TextField flowField = new TextField();
    private final TextField con_lenField = new TextField();
    private final TextField firstNameField = new TextField();
    private final TextField lastNameField = new TextField();
    private final TextField addressField = new TextField();
    
    private final Button create = new Button("New");
    private final Button update = new Button("Update");
    private final Button delete = new Button("Delete");
    private final Button first = new Button("First");
    private final Button previous = new Button("Previous");
    private final Button next = new Button("Next");
    private final Button last = new Button("Last");
    
    
    private PackageBean bean = new PackageBean();
    private Package p;
    
    public PackageUI () {
        setPadding(new Insets(10, 10, 10, 10));
        setTop(msgLabel);
        setCenter(initFields());
        setBottom(initButtons());
        setFieldData(bean.moveFirst());
    }
    
    public Pane initButtons() {
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(5);
        hBox.getChildren().add(create);
        create.setOnAction(new ButtonHandler());
        hBox.getChildren().add(update);
        update.setOnAction(new ButtonHandler());
        hBox.getChildren().add(delete);
        delete.setOnAction(new ButtonHandler());
        hBox.getChildren().add(first);
        first.setOnAction(new ButtonHandler());
        hBox.getChildren().add(previous);
        previous.setOnAction(new ButtonHandler());
        hBox.getChildren().add(next);
        next.setOnAction(new ButtonHandler());
        hBox.getChildren().add(last);
        last.setOnAction(new ButtonHandler());
        return hBox;
    }
    
    public Pane initFields() {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setHgap(20);
        gridPane.setVgap(2);
        gridPane.add(new Label("ID"), 1, 0);
        gridPane.add(idField, 2, 0);
        idField.setEditable(false);
        gridPane.add(new Label("Speed [ 2, 5, 10, 20, 50 or 100Mbit]"), 1, 1);
        gridPane.add(speedField, 2, 1);
        gridPane.add(new Label("Flow [ 1, 5, 10, 100GB or Flat]"), 1, 2);
        gridPane.add(flowField, 2, 2);
        gridPane.add(new Label("Contract Length [ 1 or 2 year]"), 1, 3);
        gridPane.add(con_lenField, 2, 3);
        gridPane.add(new Label("First Name"), 1, 4);
        gridPane.add(firstNameField, 2, 4);
        gridPane.add(new Label("Last Name"), 1, 5);
        gridPane.add(lastNameField, 2, 5);
        gridPane.add(new Label("Address"), 1, 6);
        gridPane.add(addressField, 2, 6);
        return gridPane;
    }
    
    public Package getFieldData() {
        Package p = new Package();
        p.setPackageId(Integer.parseInt(idField.getText()));
        p.setSpeed(String.valueOf(speedField.getText()));
        p.setFlow(String.valueOf(flowField.getText()));
        p.setContractLength(String.valueOf(con_lenField.getText()));
        p.setFirstName(String.valueOf(firstNameField.getText()));
        p.setLastName(String.valueOf(lastNameField.getText()));
        p.setAddress(String.valueOf(addressField.getText()));
        return p;
    }
    
    public void setFieldData(Package p) {
        idField.setText(String.valueOf(p.getPackageId()));
        speedField.setText(String.valueOf(p.getSpeed()));
        flowField.setText(String.valueOf(p.getFlow()));
        con_lenField.setText(String.valueOf(p.getContractLength()));
        firstNameField.setText(String.valueOf(p.getFirstName()));
        lastNameField.setText(String.valueOf(p.getLastName()));
        addressField.setText(String.valueOf(p.getAddress()));
    }
    
    public boolean isEmpltyFieldData() {
        boolean isEmpty = true;
        if (idField.getText() != null && idField.getText().equals("")){
            isEmpty = false;
        } 
        if (speedField.getText() != null && speedField.getText().equals("")) {
            isEmpty = false;
        }
        if (flowField.getText() != null && flowField.getText().equals("")) {
            isEmpty = false;
        }
        if (con_lenField.getText() != null && con_lenField.getText().equals("")) {
            isEmpty = false;
        }
        if (firstNameField.getText() != null && firstNameField.getText().equals("")) {
            isEmpty = false;
        }
        if (lastNameField.getText() != null && lastNameField.getText().equals("")) {
            isEmpty = false;
        }
        if (addressField.getText() != null && addressField.getText().equals("")) {
            isEmpty = false;
        }
        return isEmpty;
    }
    
    public class ButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent t) {
            Package p = getFieldData();
            if (t.getSource().equals(create) && create.getText().equals("Save")) {
                if (isEmpltyFieldData() == false) {
                    msgLabel.setText("Cannot create an empty record");
                    return;
                }
                if (bean.create(p) != null)
                    msgLabel.setText("New package created successfully");
                create.setText("New");
            } else if (t.getSource().equals(create) && create.getText().equals("New")) {
                p.setPackageId(new Random().nextInt(Integer.MAX_VALUE) + 1);
                p.setSpeed("");
                p.setFlow("");
                p.setContractLength("");
                p.setFirstName("");
                p.setLastName("");
                p.setAddress("");
                setFieldData(p);
                create.setText("Save");
            } else if (t.getSource().equals(update)) {
                if (isEmpltyFieldData() == false) {
                    msgLabel.setText("Cannot update an empty record");
                    return;
                }
                if (bean.update(p) != null)
                    msgLabel.setText("Package with ID: "
                    + String.valueOf(p.getPackageId()
                    + " is updated successfully"));
            } else if (t.getSource().equals(delete)) {
                
                if (isEmpltyFieldData() == false) {
                    msgLabel.setText("Cannot delete an empty record");
                    return;
                }
                p = bean.getCurrent();
                bean.delete();
                msgLabel.setText("Package with ID: "
                + String.valueOf(p.getPackageId()
                + " is deleted successfully"));
            } else if (t.getSource().equals(first)) {
                setFieldData(bean.moveFirst());
            } else if (t.getSource().equals(previous)) {
                setFieldData(bean.movePrevious());
            } else if (t.getSource().equals(next)) {
                setFieldData(bean.moveNext());
            } else if (t.getSource().equals(last)) {
                setFieldData(bean.moveLast());
            }
        }
        
    }
}
