package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class CreditDebit
{
    @FXML AnchorPane CreditDebitUI;
    @FXML Label account;
    @FXML Label saved;
    @FXML TextField choice;
    @FXML TextField credit;
    @FXML TextField debit;

    DBHelper dbHelper = new DBHelper();

    public void showAccountNo(int accountNo)
    {
        this.account.setText(String.valueOf(accountNo));
    }

    public void showSavings(Double save)
    {
        this.saved.setText(String.valueOf(save));
    }

    public void showUserProfile(MouseEvent mouseEvent) throws IOException
    {
        int ch = Integer.parseInt(choice.getText());

        FXMLLoader loader = new FXMLLoader(getClass().getResource("profile.fxml"));
        Parent root = loader.load();
        Profile profile = loader.getController();
        profile.displayAccountNo(Integer.parseInt(account.getText()));
        profile.displayFname(dbHelper.fetchFname(Integer.parseInt(account.getText())));
        profile.displayLname(dbHelper.fetchLname(Integer.parseInt(account.getText())));

        if(ch == 1)
        {
            profile.displayUpdatedSavings1(dbHelper.fetchCurrentSavings(Integer.parseInt(account.getText())),Double.parseDouble(credit.getText()));
        }
        else
        {
            profile.displayUpdatedSavings2(dbHelper.fetchCurrentSavings(Integer.parseInt(account.getText())),Double.parseDouble(debit.getText()));
        }

        Stage currentStage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        currentStage.setTitle("User Details");
        currentStage.setScene(scene);
        currentStage.show();

    }

    public void showUserProfile1(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("profile.fxml"));
        Parent root = loader.load();
        Profile profile = loader.getController();
        profile.displayAccountNo(Integer.parseInt(account.getText()));
        profile.displayFname(dbHelper.fetchFname(Integer.parseInt(account.getText())));
        profile.displayLname(dbHelper.fetchLname(Integer.parseInt(account.getText())));
        profile.displayUpdatedSavings(dbHelper.fetchCurrentSavings(Integer.parseInt(account.getText())));
        Stage currentStage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        currentStage.setTitle("User Details");
        currentStage.setScene(scene);
        currentStage.show();
    }
}
