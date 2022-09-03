package bb.bloodbank;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.sql.*;

import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML private TextField tf_M_username;
    @FXML private PasswordField pf_M_password;
    Connection conn;
    PreparedStatement pat;
    ResultSet rs;//For login

    Connection conn1;
    PreparedStatement pat1;
    //For Signup

    Connection conn2;
    PreparedStatement pat2;
    //For Donor record

    Connection conn3;
    PreparedStatement pat3;
    //For Patient record


    @FXML private TextField tf_staffid;
    @FXML private TextField tf_password;

    @FXML private TextField tf_firstname;
    @FXML private TextField tf_lastname;//For Sign Up

    @FXML private TextField tf_D_firstname;
    @FXML private TextField tf_D_lastname;
    @FXML private TextField tf_D_phonenumber;
    @FXML private TextField tf_D_bloodgroup;
    @FXML private TextField tf_D_email;
    @FXML private TextField tf_D_age;//For Donor Submit

    @FXML private TextField tf_R_firstname;
    @FXML private TextField tf_R_lastname;
    @FXML private TextField tf_R_phonenumber;
    @FXML private TextField tf_R_bloodgroup;
    @FXML private TextField tf_R_email;
    @FXML private TextField tf_R_age;//For Patient Submit



    public void switchSceneADsignup(ActionEvent event) throws IOException {
        Parent root =  FXMLLoader.load(getClass().getResource("ADsignup.fxml"));
        stage =(Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Sign Up!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();


        //btn_signup_new_admin


    }
    public void switchScenewithsignupbutton(ActionEvent event) throws IOException { //btn_signup
        String sid=tf_staffid.getText();
        String pass=tf_password.getText();
        String fname=tf_firstname.getText();
        String lname=tf_lastname.getText();
        if (sid.equals("") || pass.equals("") || fname.equals("")||lname.equals("") )
        {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fill !");
            alert.setContentText("Make sure all the fields are filled !");
            alert.show();
        }else
        {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conn1=DriverManager.getConnection("jdbc:mysql://localhost:3306/blood_bank","root","");
                pat1=conn1.prepareStatement("INSERT INTO admin(staff_id,firstname,lastname,password) VALUES(?,?,?,?)");
                pat1.setString(1,sid);
                pat1.setString(2,fname);
                pat1.setString(3,lname);
                pat1.setString(4,pass);
                int rs1=pat1.executeUpdate();


                if (rs1 ==1)
                {
                    Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("");
                    alert.setContentText("Sucessful Registration!");
                    alert.show();
                    Parent root =  FXMLLoader.load(getClass().getResource("mnpage.fxml"));
                    stage =(Stage) ((Node)event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setTitle("Welcome Page!");
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.show();
                }
                else
                {
                    Alert alert=new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Fill!");
                    alert.setContentText("Make sure all the fields are filled!");
                    alert.show();
                    tf_staffid.setText("");
                    tf_password.setText("");
                    tf_firstname.setText("");
                    tf_firstname.setText("");
                    tf_staffid.requestFocus();

                }

            } catch (ClassNotFoundException e) {
              e.printStackTrace();
            } catch (SQLException e) {
            e.printStackTrace();
            }


        }
                //btn_signup


    }
    public void switchScenemnpage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("mnpage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Welcome Page!");
        stage.setScene(scene);
        stage.show();

        //btn_cancel
    }
    public void switchSceneLoggedinpage(ActionEvent event) throws IOException {
        String uname =tf_M_username.getText();
        String pass=pf_M_password.getText();

        if (uname.equals("") && pass.equals(""))
        {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Username or Password is Blank!");
            alert.setContentText("Enter Username or Password!");
            alert.show();
        }else
        {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/blood_bank","root","");
                pat=conn.prepareStatement("SELECT * FROM admin WHERE staff_id=? and password=?");
                pat.setString(1,uname);
                pat.setString(2,pass);
                rs =pat.executeQuery();

                if(rs.next())
                {
                    Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("");
                    alert.setContentText("Sucessful Login!");
                    alert.setHeaderText("Great!");
                    alert.show();
                    Parent root = FXMLLoader.load(getClass().getResource("Loggedinpage.fxml"));
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setTitle("Logged In");
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.show();

                }else
                {
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Wrong credentials");
                alert.setContentText("Login Failed!");
                alert.show();
                tf_M_username.setText("");
                pf_M_password.setText("");
                tf_M_username.requestFocus();
                }

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
           //ss

        }
        //btn_login
    }
    public void switchSceneLogoutpage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("mnpage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Welcome Page!");
        stage.setScene(scene);
        stage.show();

        //btn_logout
    }
    public void switchScenedonorpage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Donorpage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Donation!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        //btn_donor
    }
    public void switchScenepatientpage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Requestpage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Patient!");
        stage.setScene(scene);
        stage.show();

        //btn_patient
    }
    public void switchScenewithback(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Loggedinpage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Logged In");
        stage.setScene(scene);
        stage.show();

        //btn_back
    }
    public void switchScenewithsubmitdonor(ActionEvent event) throws IOException {
        String fname =tf_D_firstname.getText();
        String lname =tf_D_lastname.getText();
        String age =tf_D_age.getText();
        String bdgroup =tf_D_bloodgroup.getText();
        String email =tf_D_email.getText();
        String phone =tf_D_phonenumber.getText();

        if (age.equals("") || email.equals("") || fname.equals("")||lname.equals("") ||bdgroup.equals("") ||phone.equals("") )
        {
            //JOptionPane.showMessageDialog(null,"Username or Password is Blank!");
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Fill !");
            alert.setContentText("Make sure all the fields are filled !");
            alert.show();
        }else
        {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conn2=DriverManager.getConnection("jdbc:mysql://localhost:3306/blood_bank","root","");
                pat2=conn2.prepareStatement("INSERT INTO donor(age,bloodgroup,email,firstname,lastname) VALUES(?,?,?,?,?)");
                pat2.setString(1,age);
                pat2.setString(2,bdgroup);
                pat2.setString(3,email);
                pat2.setString(4,fname);
                pat2.setString(5,lname);
                int rs2=pat2.executeUpdate();

                if (rs2==1)
                {
                    Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("");
                    alert.setContentText("Sucessfully Recorded!");
                    alert.show();
                    Parent root =  FXMLLoader.load(getClass().getResource("Loggedinpage.fxml"));
                    stage =(Stage) ((Node)event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setTitle("Logged In !");
                    stage.setScene(scene);
                    stage.show();
                }else
                {
                    Alert alert=new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Fill!");
                    alert.setContentText("Make sure all the fields are filled!");
                    alert.show();

                }

            } catch (ClassNotFoundException e) {
              e.printStackTrace();
            } catch (SQLException e) {
            e.printStackTrace();
            }
            Parent root = FXMLLoader.load(getClass().getResource("Loggedinpage.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setTitle("Logged In");
            stage.setScene(scene);
            stage.show();

        }

        //btn_submit_donation
    }
    public void switchScenewithsubmitrequest(ActionEvent event) throws IOException {
        String fname =tf_R_firstname.getText();
        String lname =tf_R_lastname.getText();
        String age =tf_R_age.getText();
        String bdgroup =tf_R_bloodgroup.getText();
        String email =tf_R_email.getText();
        String phone =tf_R_phonenumber.getText();

        if (age.equals("") || email.equals("") || fname.equals("")||lname.equals("") ||bdgroup.equals("") ||phone.equals("") )
        {
            //JOptionPane.showMessageDialog(null,"Username or Password is Blank!");
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Fill !");
            alert.setContentText("Make sure all the fields are filled !");
            alert.show();
        }else
        {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conn3=DriverManager.getConnection("jdbc:mysql://localhost:3306/blood_bank","root","");
                pat3=conn3.prepareStatement("INSERT INTO patient(age,bloodgroup,email,firstname,lastname) VALUES(?,?,?,?,?)");
                pat3.setString(1,age);
                pat3.setString(2,bdgroup);
                pat3.setString(3,email);
                pat3.setString(4,fname);
                pat3.setString(5,lname);
                int rs2=pat3.executeUpdate();

                if (rs2==1)
                {
                    Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("");
                    alert.setContentText("Sucessfully Recorded!");
                    alert.show();
                    Parent root =  FXMLLoader.load(getClass().getResource("Loggedinpage.fxml"));
                    stage =(Stage) ((Node)event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setTitle("Logged In !");
                    stage.setScene(scene);
                    stage.show();
                }else
                {
                    Alert alert=new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Fill!");
                    alert.setContentText("Make sure all the fields are filled!");
                    alert.show();

                }

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            Parent root = FXMLLoader.load(getClass().getResource("Loggedinpage.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setTitle("Logged In");
            stage.setScene(scene);
            stage.show();

        }



        //btn_submit_request
    }
}