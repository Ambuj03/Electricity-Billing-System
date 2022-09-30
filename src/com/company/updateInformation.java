package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class updateInformation extends JFrame implements ActionListener {

    JTextField tfaddress,tfcity,tfstate,tfemail,tfphone;
    JLabel tfname,tfmeternumber;
    JButton cancel,update;
    String meter;

    updateInformation(String meter){
        this.meter = meter;
        setBounds(300,150,1050,450);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("UPDATE CUSTOMER INFORMATION");
        heading.setBounds(110,0,400,30);
        heading.setFont(new Font("Tahoma", Font.PLAIN,20));
        add(heading);

        JLabel lblname = new JLabel("Name");
        lblname.setBounds(30,70,100,20);
        add(lblname);

        tfname = new JLabel("");
        tfname.setBounds(170,70,200,20);
        add(tfname);

        JLabel lblmeternumber = new JLabel("Meter Number");
        lblmeternumber.setBounds(30,110,100,20);
        add(lblmeternumber);

        tfmeternumber = new JLabel("");
        tfmeternumber.setBounds(170,110,200,20);
        add(tfmeternumber);

        JLabel lbladdress = new JLabel("Address");
        lbladdress.setBounds(30,150,100,20);
        add(lbladdress);

        tfaddress = new JTextField();
        tfaddress.setBounds(170,150,200,20);
        add(tfaddress);

        JLabel lblcity = new JLabel("City");
        lblcity.setBounds(30,190,100,20);
        add(lblcity);

        tfcity = new JTextField("");
        tfcity.setBounds(170,190,200,20);
        add(tfcity);

        JLabel lblstate = new JLabel("State");
        lblstate.setBounds(500,80,100,20);
        add(lblstate);

        tfstate = new JTextField("");
        tfstate.setBounds(600,80,200,20);
        add(tfstate);

        JLabel lblemail = new JLabel("Email");
        lblemail.setBounds(500,140,100,20);
        add(lblemail);

        tfemail = new JTextField("");
        tfemail.setBounds(600,140,200,20);
        add(tfemail);

        JLabel lblphone = new JLabel("Phone");
        lblphone.setBounds(500,200,100,20);
        add(lblphone);

        tfphone = new JTextField("");
        tfphone.setBounds(600,200,200,20);
        add(tfphone);

        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer where meterno = '"+meter+"'");
            while (rs.next()){
                tfname.setText(rs.getString("name"));
                tfaddress.setText(rs.getString("address"));
                tfcity.setText(rs.getString("city"));
                tfstate.setText(rs.getString("state"));
                tfemail.setText(rs.getString("email"));
                tfphone.setText(rs.getString("phone"));
                tfmeternumber.setText(rs.getString("meterno"));

            }
        }catch(Exception e){
            e.printStackTrace();
        }

        cancel = new JButton("Cancel");
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.setBounds(230,340,100,25);
        cancel.addActionListener(this);
        add(cancel);

        update = new JButton("Update");
        update.setBackground(Color.BLACK);
        update.setForeground(Color.WHITE);
        update.setBounds(400,340,100,25);
        update.addActionListener(this);
        add(update);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/viewcustomer.jpg"));
        Image i2 = i1.getImage().getScaledInstance(600,300,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(20,360,600,300);
        add(image);

       setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        if (e.getSource() == update){
            String address = tfaddress.getText();
            String city = tfcity.getText();
            String state = tfstate.getText();
            String email = tfemail.getText();
            String phone = tfphone.getText();

            try {
                Conn c = new Conn();
                c.s.executeUpdate("update customer set address = '"+address+"', city = '"+city+"', state = '"+state+"', email = '"+email+"', phone = '"+phone+"' where meterno = '"+meter+"'");

                JOptionPane.showMessageDialog(null, "User Information updated successfully");
                setVisible(false);

            }catch (Exception ae){
                ae.printStackTrace();
            }
        }else{
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new updateInformation("");
    }
}
