package sample;
import java.sql.*;

public class DBHelper
{
    private static Connection conn;
    private static Statement statement;

    public DBHelper()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/bankdb","root","");
            statement = conn.createStatement();
            this.statement.executeUpdate("ALTER TABLE bankusers AUTO_INCREMENT = 10000");
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
    }

    public boolean register(String fname, String lname, String pin_code, double savings)
    {
        boolean response1 = this.verifyAccountAndPinLength(pin_code);
        boolean verify = false;

        try
        {
            if(response1)
            {
                this.statement.executeUpdate("INSERT INTO bankusers (account_no,fname,lname,pin_code,savings) VALUES (null," + "'" + fname + "'" + "," + "'" + lname + "'" + "," + "'" + pin_code + "'" + "," + "'" + savings + "'" + ")");
                verify = true;
            }
            return verify;
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
            return false;
        }
    }

    public boolean login(int account_no,String pin_code)
    {
        try
        {
            ResultSet set = this.statement.executeQuery("SELECT * FROM bankusers WHERE account_no ="+ "'" + account_no + "'"+ "AND pin_code LIKE" + "'" + pin_code + "'");

            if(!set.next())
            {
                return false;
            }
            else
            {
                return true;
            }
        }

        catch (SQLException throwables)
        {
            throwables.printStackTrace();
            return false;
        }

    }

    public int fetchAccount()
    {
        String query = "SELECT * FROM bankusers";
        try
        {
            int acc=10000;
            ResultSet rs = statement.executeQuery(query);

            while (rs.next())
            {
                acc = rs.getInt("account_no");
            }
            return acc;
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
            System.out.println("Error");
            return 0;
        }
    }

    public double fetchCurrentSavings(Integer account_no)
    {
        double saved = 0;
        String query = "SELECT * FROM bankusers WHERE account_no ="+ "'" + account_no + "'";
        try
        {
            ResultSet rs = statement.executeQuery(query);

            while (rs.next())
            {
                saved= rs.getDouble("savings");
            }
            return saved;

        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
            return -1;
        }
    }

    public void CreditDebit(double new_savings, Integer account_no)
    {
        String query = "UPDATE `bankusers` SET savings = " + "'" + new_savings + "'"+ " WHERE account_no = " +"'" + account_no + "'";
        try
        {
            statement.executeUpdate(query);
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
    }

    public boolean verifyAccountAndPinLength(String pin_code)
    {
        String accountNo = Integer.toString(fetchAccount());

        if(pin_code.length()==4 && accountNo.length()==5)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public String fetchPin()
    {
        double acc = this.fetchAccount();
        String query = "SELECT * FROM bankusers WHERE account_no = " + "'" + acc + "'";
        String pin = "";

        try
        {

            ResultSet rs = statement.executeQuery(query);

            while (rs.next())
            {
                pin = pin + rs.getString("pin_code");
            }
            return pin;
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
            System.out.println("Error");
            return pin;
        }
    }

    public String fetchFname(int acc)
    {

        String query = "SELECT * FROM bankusers WHERE account_no = " + "'" + acc + "'";
        String fname = "";

        try
        {

            ResultSet rs = statement.executeQuery(query);

            while (rs.next())
            {
                fname = fname + rs.getString("fname");
            }
            return fname;
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
            System.out.println("Error");
            return fname;
        }
    }

    public String fetchLname(int acc)
    {

        String query = "SELECT * FROM bankusers WHERE account_no = " + "'" + acc + "'";
        String lname = "";

        try
        {

            ResultSet rs = statement.executeQuery(query);

            while (rs.next())
            {
                lname = lname + rs.getString("lname");
            }
            return lname;
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
            System.out.println("Error");
            return lname;
        }
    }
}
