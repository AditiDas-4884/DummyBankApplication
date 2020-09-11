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
import javafx.stage.Stage;

import javax.imageio.IIOParam;
import java.io.IOException;

public class Controller
{
    @FXML TextField accNo;
    @FXML PasswordField pin;
    @FXML Label result;

    DBHelper dbHelper = new DBHelper();

    public void launchRegister(MouseEvent mouseEvent) throws IOException
    {
        // Launch register.fxml

        Parent root = FXMLLoader.load(getClass().getResource("register.fxml"));
        Stage currentStage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        currentStage.setTitle("Make New Account");
        currentStage.setScene(scene);
        currentStage.show();

    }

    public void doCreditDebit(MouseEvent mouseEvent) throws IOException
    {
        boolean response = dbHelper.login(Integer.parseInt(accNo.getText()),pin.getText());

        if(response)
        {
            //Parent root = FXMLLoader.load(getClass().getResource("creditdebit.fxml"));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("creditdebit.fxml"));
            Parent root = loader.load();
            CreditDebit creditDebit = loader.getController();
            Double save = dbHelper.fetchCurrentSavings(Integer.parseInt(accNo.getText()));
            creditDebit.showAccountNo(Integer.parseInt(accNo.getText()));
            creditDebit.showSavings(save);
            Stage currentStage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            currentStage.setTitle("Credit / Debit");
            currentStage.setScene(scene);
            currentStage.show();
        }
        else
        {
            result.setText("Login Failed due to incorrect Account Number or Pin Code... :(");
        }

    }
}
