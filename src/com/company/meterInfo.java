package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Random;

public class meterInfo extends JFrame {

    JTextField tfname,tfaddress,city,state,email,phone;
    JLabel meterno,days,note;
    JButton next,cancel;
    Choice meterLocation,metertype,phaseCode,billType;
    String meternumber;

    meterInfo(String meternumber){
        this.meternumber = meternumber;
        setSize(700,500);
        setLocation(400,200);

        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(173,216,230));
        add(p);

        JLabel heading = new JLabel("Meter Information");
        heading.setBounds(180,10,200,25);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 24));
        p.add(heading);

        JLabel lblname = new JLabel("Meter Number");
        lblname.setBounds(100,80,100,20);
        p.add(lblname);

        JLabel lblmeternumber = new JLabel(meternumber);
        lblmeternumber.setBounds(240,80,100,20);
        p.add(lblmeternumber);

        JLabel lblmeter = new JLabel("Meter Location");
        lblmeter.setBounds(100,120,100,20);
        p.add(lblmeter);

       meterLocation = new Choice();
       meterLocation.add("Outside");
       meterLocation.add("Inside");
       meterLocation.setBounds(240,120,100,20);
       p.add(meterLocation);

        meterno = new JLabel();
        meterno.setBounds(240,120,100,20);
        p.add(meterno);

        Random ran = new Random();
        long number = ran.nextLong() % 1000000;
        meterno.setText("" + Math.abs(number));


        JLabel lblmeterType = new JLabel("Meter Type");
        lblmeterType.setBounds(100,160,100,20);
        p.add(lblmeterType);

        metertype = new Choice();
        metertype.add("Electric Meter");
        metertype.add("Solar Meter");
        metertype.add("Smart Meter");
        metertype.setBounds(240,160,100,20);
        p.add(metertype);

        JLabel lblphasecode = new JLabel("Phase Code");
        lblphasecode.setBounds(100,200,100,20);
        p.add(lblphasecode);

        phaseCode = new Choice();
        phaseCode.add("001");
        phaseCode.add("002");
        phaseCode.add("003");
        phaseCode.add("004");
        phaseCode.add("005");
        phaseCode.add("006");
        phaseCode.add("007");
        phaseCode.add("008");
        phaseCode.setBounds(240,200,100,20);
        p.add(phaseCode);

        JLabel lblbilltype = new JLabel("Bill Type");
        lblbilltype.setBounds(100,240,100,20);
        p.add(lblbilltype);

        billType = new Choice();
        billType.add("Normal");
        billType.setBounds(240,240,200,20);
        p.add(billType);

        JLabel Days = new JLabel("Days");
        Days.setBounds(100,280,100,20);
        p.add(Days);

        days = new JLabel("30 Days");
        days.setBounds(240,280,500,20);
        p.add(days);

        JLabel lblnote = new JLabel("Phone");
        lblnote.setBounds(100,320,100,20);
        p.add(lblnote);

        note = new JLabel("By default bill is calculated for 30 days only");
        note.setBounds(240,320,500,20);
        p.add(note);

        next = new JButton("Submit");
        next.setBackground(Color.BLACK);
        next.setForeground(Color.white);
        next.setBounds(160,370,80,20);
        next.addActionListener(this::actionPerformed);
        p.add(next);

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
            String meter = meternumber;
            String Location = meterLocation.getSelectedItem();
            String type = metertype.getSelectedItem();
            String code = phaseCode.getSelectedItem();
            String typebill = billType.getSelectedItem();
            String days = "30";

            String query1 = "insert into meter_info values('"+meter+"', '"+Location+"', '"+type+"', '"+code+"', '"+typebill+"', '"+days+"')";

            try {
                Conn c = new Conn();
                c.s.executeUpdate(query1);

                JOptionPane.showMessageDialog(null, "Meter information added successfully");
                setVisible(false);

            }catch (Exception e){
                e.printStackTrace();
            }
        }else{

        }
    }

    public static void main(String[] args) {
        new meterInfo("");
    }
}
