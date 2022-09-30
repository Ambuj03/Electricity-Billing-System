package com.company;

import com.mysql.cj.protocol.Resultset;
import com.sun.source.tree.WhileLoopTree;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.util.Random;

public class calculateBill extends JFrame {

    JTextField tfname,tfaddress,city,state,unitsConsumed;
    JTextField meterno;
    JButton next,cancel;
    Choice meternumber,month;
    JLabel address,name;

    calculateBill(){
        setSize(700,500);
        setLocation(400,200);

        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(173,216,230));
        add(p);

        JLabel heading = new JLabel("Calculate Bill");
        heading.setBounds(180,10,200,25);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 24));
        p.add(heading);

        JLabel lblmeternumber = new JLabel("Meter Number");
        lblmeternumber.setBounds(100,80,100,20);
        p.add(lblmeternumber);

        meternumber = new Choice();
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer");
            while (rs.next()){
                meternumber.add(rs.getString("meterno"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        meternumber.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                try {
                    Conn c = new Conn();
                    ResultSet rs = c.s.executeQuery("Select * from customer where meterno = '" + meternumber.getSelectedItem() + "'");
                    while (rs.next()) {
                        name.setText(rs.getString("name"));
                        address.setText(rs.getString("address"));

                    }
                } catch (Exception ae) {
                    ae.printStackTrace();
                }
            }
        });

        meternumber.setBounds(240,80,100,20);
        p.add(meternumber);

        JLabel lblname = new JLabel("Name");
        lblname.setBounds(100,120,100,20);
        p.add(lblname);

        name = new JLabel();
        name.setBounds(240,120,200,20);
        p.add(name);

        JLabel lbladdress = new JLabel("Address");
        lbladdress.setBounds(100,160,100,20);
        p.add(lbladdress);

        address = new JLabel();
        address.setBounds(240,160,200,20);
        p.add(address);

        JLabel unitsconsumed = new JLabel("Units Consumed");
        unitsconsumed.setBounds(100,200,100,20);
        p.add(unitsconsumed);

        unitsConsumed = new JTextField();
        unitsConsumed.setBounds(240,200,200,20);
        p.add(unitsConsumed);

        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("Select * from customer where meterno = '"+meternumber.getSelectedItem()+"'");
            while (rs.next()){
                name.setText(rs.getString("name"));
                address.setText(rs.getString("address"));

            }
        }catch (Exception e){
            e.printStackTrace();
        }

//        JLabel meter = new JLabel("Units Consumed");
//        meter.setBounds(240,200,100,20);
//        p.add(meter);
//
//        meterno = new JTextField();
//        meterno.setBounds(240,200,100,20);
//        p.add(meterno);

        JLabel lblmonth = new JLabel("Month");
        lblmonth.setBounds(100,240,100,20);
        p.add(lblmonth);

        month = new Choice();
        month.setBounds(240,240,200,20);
        month.add("Jan");
        month.add("Feb");
        month.add("Mar");
        month.add("Apr");
        month.add("May");
        month.add("June");
        month.add("July");
        month.add("Aug");
        month.add("Sept");
        month.add("Oct");
        month.add("Nov");
        month.add("Dec");
        p.add(month);

        next = new JButton("Submit");
        next.setBackground(Color.BLACK);
        next.setForeground(Color.white);
        next.setBounds(140,330,80,20);
        next.addActionListener(this::actionPerformed);
        p.add(next);

        cancel = new JButton("Cancel");
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.white);
        cancel.setBounds(260,330,80,20);
        cancel.addActionListener(this::actionPerformed);
        p.add(cancel);

        setLayout(new BorderLayout());
        add(p,"Center");

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/hicon2.jpg"));
        Image i2 = i1.getImage().getScaledInstance(150,300,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        add(image, "West");



        setVisible(true);

    }

    public void actionPerformed(ActionEvent ae){

        if (ae.getSource() == next){
            String meter = meternumber.getSelectedItem();
            String units = unitsConsumed.getText();
            String Month = month.getSelectedItem();

            int totalBill = 0;
            int units_consumed = Integer.parseInt(units);

            String query = "select * from tax";

            try{

                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);

                while(rs.next()){
                   totalBill +=  units_consumed * Integer.parseInt(rs.getString("cost_per_unit"));
                   totalBill +=  Integer.parseInt(rs.getString("meter_rent"));
                   totalBill +=  Integer.parseInt(rs.getString("service_charge"));
                   totalBill +=  Integer.parseInt(rs.getString("service_tax"));
                   totalBill +=  Integer.parseInt(rs.getString("swacch_bharat_cess"));
                   totalBill +=  Integer.parseInt(rs.getString("fixed_tax"));

                }


            }catch (Exception e){
                e.printStackTrace();
            }

             String query2 = "insert into bill values('"+meter+"', '"+Month+"', '"+units+"','"+totalBill+"','Not Paid ')";

            try{
                Conn c = new Conn();
                c.s.executeUpdate(query2);

                JOptionPane.showMessageDialog(null,"Customer bill updated successfully");
                setVisible(false);

            }catch (Exception e){
                e.printStackTrace();
            }


        }else{
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new calculateBill();
    }
}
