package sample;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Profile
{
    @FXML AnchorPane ProfileUI;
    @FXML Label accountNo;
    @FXML Label fname;
    @FXML Label lname;
    @FXML Label savings;
    @FXML Label message;

    DBHelper dbHelper = new DBHelper();

    public void displayAccountNo(int fetchAccount)
    {
        this.accountNo.setText(String.valueOf(fetchAccount));
    }

    public void displayFname(String fetchfname)
    {
        this.fname.setText(fetchfname);
    }

    public void displayLname(String fetchLname)
    {
        this.lname.setText(fetchLname);
    }

    public void displayUpdatedSavings(double new_savings)
    {
        this.savings.setText(String.valueOf(new_savings));
    }

    public void goToLogin(MouseEvent mouseEvent) throws IOException
    {

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Stage currentStage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        currentStage.setTitle("Login Screen");
        currentStage.setScene(scene);
        currentStage.show();
    }

    public void displayUpdatedSavings1(double fetchCurrentSavings, double credit)
    {
       double new_savings = credit + fetchCurrentSavings;
       dbHelper.CreditDebit(new_savings, Integer.valueOf(accountNo.getText()));
       this.savings.setText(String.valueOf(dbHelper.fetchCurrentSavings(Integer.valueOf(accountNo.getText()))));
       this.message.setText("Credited to Savings... :)");
    }

    public void displayUpdatedSavings2(double fetchCurrentSavings, double debit)
    {
        double minBal = 500;

        if (fetchCurrentSavings == 0)
        {
            this.savings.setText(String.valueOf(dbHelper.fetchCurrentSavings(Integer.valueOf(accountNo.getText()))));
            this.message.setText("Your current Bank Balance : ₹" + fetchCurrentSavings + " :(");
        }
        else if (fetchCurrentSavings == minBal)
        {
            this.savings.setText(String.valueOf(dbHelper.fetchCurrentSavings(Integer.valueOf(accountNo.getText()))));
            this.message.setText("Your current Bank Balance is ₹" + fetchCurrentSavings + " & your Bank Balance should contain atleast ₹" + minBal);
        }
        else if (debit > fetchCurrentSavings)
        {
            this.savings.setText(String.valueOf(dbHelper.fetchCurrentSavings(Integer.valueOf(accountNo.getText()))));
            this.message.setText("The amount you want to withdraw is not available :(");
        }
        else
        {
            double new_savings = fetchCurrentSavings - debit;
            dbHelper.CreditDebit(new_savings, Integer.valueOf(accountNo.getText()));
            this.savings.setText(String.valueOf(dbHelper.fetchCurrentSavings(Integer.valueOf(accountNo.getText()))));
            this.message.setText("Debited from Savings... :)");
        }
    }
}
