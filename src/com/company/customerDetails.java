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

public class customerDetails extends JFrame implements ActionListener {

    Choice meternumber,cmonth;
    JTable table;
    JButton search,print;

    customerDetails(){
        super("Customer Details");
        setSize(1200,650);
        setLocation(200,150);

        table = new JTable();

        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer");
            table.setModel(DbUtils.resultSetToTableModel(rs));

        }catch(Exception e){
            e.printStackTrace();
        }

        JScrollPane sp = new JScrollPane(table);
        add(sp);


        print = new JButton("Print");
        print.addActionListener(this);
        add(print,"South");

        setVisible(true);

    }

    public static void main(String[] args) {
        new customerDetails();
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
