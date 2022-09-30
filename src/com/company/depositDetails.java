package com.company;

import com.mysql.cj.xdevapi.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Map;
import java.util.jar.JarEntry;
import net.proteanit.sql.DbUtils;

public class depositDetails extends JFrame implements ActionListener {

        Choice meternumber,cmonth;
        JTable table;
        JButton search,print;

    depositDetails(){
        super("Deposit Details");
        setSize(700,700);
        setLocation(400,100);

        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JLabel lblmeternumber = new JLabel("Search by meter number");
        lblmeternumber.setBounds(20,20,150,20);
        add(lblmeternumber);

        meternumber = new Choice();
        meternumber.setBounds(180,20,150,20);
        add(meternumber);

        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("Select * from customer");
            while (rs.next()){
                meternumber.add(rs.getString("meterno"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        JLabel lblmonth = new JLabel("Search by month");
        lblmonth.setBounds(370,20,100,20);
        add(lblmonth);

        cmonth = new Choice();
        cmonth.setBounds(480,20,150,20);
        cmonth.add("Jan");
        cmonth.add("Feb");
        cmonth.add("Mar");
        cmonth.add("Apr");
        cmonth.add("May");
        cmonth.add("June");
        cmonth.add("July");
        cmonth.add("Aug");
        cmonth.add("Sept");
        cmonth.add("Oct");
        cmonth.add("Nov");
        cmonth.add("Dec");
        add(cmonth);

        table = new JTable();

        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from bill");
            table.setModel(DbUtils.resultSetToTableModel(rs));

        }catch(Exception e){
            e.printStackTrace();
        }

        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(0,100,700,600);
        add(sp);

        search = new JButton("Search");
        search.setBounds(20,70,80,20);
        search.addActionListener(this);
        add(search);

        print = new JButton("Print");
        print.setBounds(120,70,80,20);
        print.addActionListener(this);
        add(print);

        setVisible(true);

    }

    public static void main(String[] args) {
        new depositDetails();
    }

    public void actionPerformed(ActionEvent ae){

        if (ae.getSource() == search){

            String query = "select * from bill where meter_no = '"+meternumber.getSelectedItem()+"' and month = '"+cmonth.getSelectedItem()+"'";

            try{
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(rs));

            }catch(Exception e){
                e.printStackTrace();
            }

        }else{
            try {
                table.print();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
