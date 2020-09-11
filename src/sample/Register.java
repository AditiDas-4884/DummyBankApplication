package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Register
{
    @FXML AnchorPane registerUI;
    @FXML TextField fname;
    @FXML TextField lname;
    @FXML PasswordField pin;
    @FXML Label result;
    @FXML Label AccountNo;

    DBHelper dbHelper = new DBHelper();

    public void performSignup(MouseEvent mouseEvent)
    {
        boolean response = dbHelper.register(fname.getText(),lname.getText(),pin.getText(),500);

        if(response)
        {
            result.setText("Registration Successful. Login to proceed.... :)");
            AccountNo.setText("Account Number Generated : " + dbHelper.fetchAccount());
        }
        else
        {
            result.setText("Registration Failed. User should enter a 4-digit pin & also the bank should generate a 5-digit account no.... :(");
        }
    }

    public void loadLogin(MouseEvent mouseEvent) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Stage currentStage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        currentStage.setTitle("Login Screen");
        currentStage.setScene(scene);
        currentStage.show();
    }
}
