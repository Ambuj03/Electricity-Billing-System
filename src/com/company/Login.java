package com.company;

import com.mysql.cj.protocol.Resultset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {

    JButton cancel,signup,login;
    JTextField username1,password1;
    Choice logginin;
    Login(){

        super("Login Page");
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel username = new JLabel("Username");
        username.setBounds(300,20,100,20);
        add(username);

         username1 = new JTextField();
        username1.setBounds(400,20,150,20);
        add(username1);

        JLabel password = new JLabel("Password");
        password.setBounds(300,60,100,20);
        add(password);

         password1 = new JTextField();
        password1.setBounds(400,60,150,20);
        add(password1);

        JLabel loginas = new JLabel("Logging in as");
        loginas.setBounds(300,100,100,20);
        add(loginas);

         logginin = new Choice();
        logginin.add("Admin");
        logginin.add("Customer");
        logginin.setBounds(400,100,100,20);
        add(logginin);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/login.png"));
        Image i2 = i1.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT);

        login = new JButton("login",new ImageIcon(i2));
        login.setBounds(330 ,140,100,20);
        login.addActionListener(this);
        add(login);


        ImageIcon i3 = new ImageIcon(ClassLoader.getSystemResource("icon/cancel.jpg"));
        Image i4 = i3.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT);

        cancel = new JButton("cancel",new ImageIcon(i4));
        cancel.setBounds(460,140,100,20);
        cancel.addActionListener(this);
        add(cancel);

        ImageIcon i5 = new ImageIcon(ClassLoader.getSystemResource("icon/signup.png"));
        Image i6 = i5.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT);

         signup = new JButton("signup",new ImageIcon(i6));
        signup.setBounds(400  ,180,100,20);
        signup.addActionListener(this);
        add(signup);


        ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("icon/second.jpg"));
        Image i8 = i7.getImage().getScaledInstance(250,250, Image.SCALE_DEFAULT);
        ImageIcon i9 = new ImageIcon(i8);
        JLabel image = new JLabel(i9);
        image.setBounds(0,0,250,250);
        add(image);

        setSize(600,300);
        setLocation(400,150);
        setVisible(true);
    }


    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == login){

            String username = username1.getText();
            String password = password1.getText();
            String user = logginin.getSelectedItem();

            try {
                Conn c = new Conn();
                String query = "Select * from login where username = '"+username+"' and password = '"+password+"' and user = '"+ user+"'";

                ResultSet rs = c.s.executeQuery(query);

                if (rs.next()){
                    String meter = rs.getString("meter_no");
                    setVisible(false);
                    new project(user,meter);
                }else{
                    JOptionPane.showMessageDialog(null, "Invalid Login");
                }

            }catch (Exception e){
                e.printStackTrace();
            }

        }else if (ae.getSource() == signup){
            setVisible(false);
            new Signup();

        }else if (ae.getSource() == cancel){
            setVisible(false);
        }

    }


    public static void main(String[] args) {
        new Login();
    }
}
