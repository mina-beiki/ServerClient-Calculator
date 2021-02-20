package gui;

import client.Client;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.awt.*;

public class Controller {

    @FXML
    private Label resultLabel ;

    @FXML
    private TextField textField;
    @FXML
    private javafx.scene.control.Button button0 , button1 , button2 , button3 , button4 , button5 , button6 ,
    button7 , button8 , button9 , buttonFract , buttonMultiply , buttonSum , buttonSub , buttonClear , buttonEqual , buttonDelete;

    private String numStr ;
    private int ctr = 0 ;

    private Client client ;

    private String result = "";

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

        //send to server :
        if(ctr==0){
            client = new Client(7660);
            client.runClient();
            client.sendMessage(0+"",numStr);

        }else{
            result = client.sendMessage(0+"",numStr);
            System.out.println(result);
        }

        resultLabel.setText(result);
        client.sendMessage(1+"",operator);

        ctr++ ;
    }

    public void clearClicked(MouseEvent mouseEvent) {
        textField.setText("");
    }

}
