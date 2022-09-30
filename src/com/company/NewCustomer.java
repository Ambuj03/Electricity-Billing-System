package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Random;

public class NewCustomer  extends JFrame {

    JTextField tfname,tfaddress,city,state,email,phone;
    JLabel meterno;
    JButton next,cancel;

    NewCustomer(){
        setSize(700,500);
        setLocation(400,200);

        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(173,216,230));
        add(p);

        JLabel heading = new JLabel("New Customer");
        heading.setBounds(180,10,200,25);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 24));
        p.add(heading);

        JLabel lblname = new JLabel("Customer Name");
        lblname.setBounds(100,80,100,20);
        p.add(lblname);

        tfname = new JTextField();
        tfname.setBounds(240,80,200,20);
        p.add(tfname);

        JLabel lblmeter = new JLabel("Meter Number");
        lblmeter.setBounds(100,120,100,20);
        p.add(lblmeter);

        JLabel meter = new JLabel("");
        meter.setBounds(240,120,100,20);
        p.add(meter);

        meterno = new JLabel();
        meterno.setBounds(240,120,100,20);
        p.add(meterno);

        Random ran = new Random();
        long number = ran.nextLong() % 1000000;
        meterno.setText("" + Math.abs(number));


        JLabel lbladdress = new JLabel("Address");
        lbladdress.setBounds(100,160,100,20);
        p.add(lbladdress);

        tfaddress = new JTextField();
        tfaddress.setBounds(240,160,200,20);
        p.add(tfaddress);

        JLabel lblcity = new JLabel("City");
        lblcity.setBounds(100,200,100,20);
        p.add(lblcity);

        city = new JTextField();
        city.setBounds(240,200,200,20);
        p.add(city);

        JLabel lblstate = new JLabel("State");
        lblstate.setBounds(100,240,100,20);
        p.add(lblstate);

        state = new JTextField();
        state.setBounds(240,240,200,20);
        p.add(state);

        JLabel lblemail = new JLabel("E-Mail");
        lblemail.setBounds(100,280,100,20);
        p.add(lblemail);

        email = new JTextField();
        email.setBounds(240,280,200,20);
        p.add(email);

        JLabel lblphone = new JLabel("Phone");
        lblphone.setBounds(100,320,100,20);
        p.add(lblphone);

        phone = new JTextField();
        phone.setBounds(240,320,200,20);
        p.add(phone);

        next = new JButton("Next");
        next.setBackground(Color.BLACK);
        next.setForeground(Color.white);
        next.setBounds(140,370,80,20);
        next.addActionListener(this::actionPerformed);
        p.add(next);

        cancel = new JButton("Cancel");
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.white);
        cancel.setBounds(260,370,80,20);
        cancel.addActionListener(this::actionPerformed);
        p.add(cancel);

        setLayout(new BorderLayout());
        add(p,"Center");

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/hicon1.jpg"));
        Image i2 = i1.getImage().getScaledInstance(150,300,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        add(image, "West");



        setVisible(true);

    }

    public void actionPerformed(ActionEvent ae){

        if (ae.getSource() == next){
            String lname = tfname.getText();
            String lmeter = meterno.getText();
            String laddress = tfaddress.getText();
            String lCity = city.getText();
            String lstate = state.getText();
            String lemail = email.getText();
            String lphone = phone.getText();

            String query1 = "insert into customer values('"+lname+"', '"+lmeter+"', '"+laddress+"', '"+lCity+"', '"+lstate+"', '"+lemail+"','"+lemail+"')";
            String query2 = "insert into login values('"+lmeter+"','', '"+lname+"', '','')";

            try {
                Conn c = new Conn();
                c.s.executeUpdate(query1);
                c.s.executeUpdate(query2);

                JOptionPane.showMessageDialog(null, "Customer details added successfully");
                setVisible(false);

                new meterInfo(lmeter);

            }catch (Exception e){
                e.printStackTrace();
            }
        }else{
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new NewCustomer();
    }
}
