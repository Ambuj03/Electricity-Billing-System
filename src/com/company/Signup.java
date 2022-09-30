package com.company;
import com.mysql.cj.log.Log;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class Signup extends JFrame implements ActionListener{

    JButton back,create;
    Choice accountType;
    JTextField meterF, usernameF, passwordF, nameF;

    Signup(){
        setBounds(450,150,700,400);
        getContentPane().setBackground(Color.LIGHT_GRAY);
        setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(30,30,650,300);
        panel.setBorder(new TitledBorder(new LineBorder(new Color(173,216,230),2), "Create Acoount",TitledBorder.LEADING, TitledBorder.TOP,null,new Color(172,216,230)));
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);
        add(panel);

        JLabel heading = new JLabel("Create account as");
        heading.setBounds(100,50,140,20);
        heading.setForeground(Color.GRAY);
        heading.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(heading);

        accountType = new Choice();
        accountType.add("Admin");
        accountType.add("Customer");
        accountType.setBounds(260,50,140,20);
        panel.add(accountType);

        JLabel meter = new JLabel("Meter Number");
        meter.setBounds(100,80,140,20);
        meter.setForeground(Color.GRAY);
        meter.setFont(new Font("Tahoma", Font.BOLD, 14));
        meter.setVisible(false);
        panel.add(meter);

        meterF = new JTextField();
        meterF.setBounds(260,80,140,20);
        meterF.setVisible(false);
        panel.add(meterF);

        JLabel username = new JLabel("Username");
        username.setBounds(100,110,140,20);
        username.setForeground(Color.GRAY);
        username.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(username);

        usernameF = new JTextField();
        usernameF.setBounds(260,110,140,20);
        panel.add(usernameF);

        JLabel name = new JLabel("Name");
        name.setBounds(100,140,140,20);
        name.setForeground(Color.GRAY);
        name.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(name);

        nameF = new JTextField();
        nameF.setBounds(260,140,140,20);
        panel.add(nameF);

        meterF.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {

                try{
                    Conn c = new Conn();
                    ResultSet rs = c.s.executeQuery("select * from login where meter_no = '"+meterF.getText()+"'");
                    while (rs.next()){
                        nameF.setText(rs.getString("name"));
                    }
                }catch (Exception ae){
                    ae.printStackTrace();
                }

            }
        });


        JLabel password = new JLabel("Password");
        password.setBounds(100,170,140,20);
        password.setForeground(Color.GRAY);
        password.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(password);

        passwordF = new JTextField();
        passwordF.setBounds(260,170,140,20);
        panel.add(passwordF);

        accountType.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String user = accountType.getSelectedItem();
                if (user.equals("Customer")){
                    meter.setVisible(true);
                    meterF.setVisible(true);
                    nameF.setEditable(false);
                }else{
                    meter.setVisible(false);
                    meterF.setVisible(false);
                    nameF.setEditable(true);

                }
            }
        });

         create = new JButton("Create");
        create.setBounds(140,220,140,20);
        create.setBackground(Color.BLACK);
        create.setForeground(Color.white);
        create.addActionListener(this);
        panel.add(create);

         back = new JButton("back");
        back.setBounds(320,220,140,20);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.white);
        back.addActionListener(this);
        panel.add(back);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/signupImage.png"));
        Image i2 = i1.getImage().getScaledInstance(210,210,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel i4 = new JLabel(i3);
        i4.setBounds(430,30,250,250);
        panel.add(i4);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ie){

        if (ie.getSource() == create){
            String atype = accountType.getSelectedItem();
            String susername = usernameF.getText();
            String sname = nameF.getText();
            String smeter = meterF.getText();
            String spassword = passwordF.getText();

           try{

               Conn c = new Conn();

               String query = null;

               if (atype.equals("Admin")) {
                   query = "insert into login values('" + smeter + "','" + susername + "','" + sname + "', '" + spassword + "', '" + atype + "')";
               }else{
                   query = "update login set username = '"+susername+"', password = '"+spassword+"', user = '"+atype+"' where meter_no = '"+smeter+"'";
               }
               c.s.executeUpdate(query);

               JOptionPane.showMessageDialog(null, "Account created successfully");

               setVisible(false);
               new Login();

           }catch (Exception e){
               e.printStackTrace();
           }
        }
        else if (ie.getSource() == back){
            setVisible(false);
            new Login();
        }

  }

    public static void main(String[] args) {
        new Signup();
    }
}
