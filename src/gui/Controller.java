package gui;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.awt.*;

public class Controller {

    @FXML
    private TextField textField;
    @FXML
    private javafx.scene.control.Button button0 , button1 , button2 , button3 , button4 , button5 , button6 ,
    button7 , button8 , button9 , buttonFract , buttonMultiply , buttonSum , buttonSub , buttonClear , buttonEqual , buttonDelete;

    private String numStr ;

    public void insertNumber(String num){
        textField.setText(textField.getText() + num);
    }

    public void numButtonClicked(MouseEvent mouseEvent) {
        javafx.scene.control.Button button = (javafx.scene.control.Button) mouseEvent.getSource();
        String number = button.getText();
        insertNumber(number);
    }


    public void deleteClicked(MouseEvent mouseEvent) {
        if(!(textField.getText().equals(""))) {
            String newStr = textField.getText().substring(0, textField.getText().length() - 1);
            textField.setText(newStr);
        }
    }

    public void operatorClicked(MouseEvent mouseEvent) {
        javafx.scene.control.Button button = (javafx.scene.control.Button) mouseEvent.getSource();
        String operator = button.getText();
        numStr = textField.getText();
        textField.setText("");
        System.out.println("number = "+numStr);
        System.out.println("operator = "+operator);
    }

    public void clearClicked(MouseEvent mouseEvent) {
        textField.setText("");
    }
}
