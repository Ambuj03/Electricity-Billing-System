package com.company;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import net.proteanit.sql.DbUtils;

public class billDetails extends JFrame {

    billDetails(String meter){

        setSize(700,650);
        setLocation(400,150);

        getContentPane().setBackground(Color.WHITE);

        JTable table = new JTable();

        try{
            Conn c = new Conn();
            String query = "select * from bill where meter_no = '"+meter+"'";

            ResultSet rs = c.s.executeQuery(query);

            table.setModel(DbUtils.resultSetToTableModel(rs));

        }catch (Exception e){
            e.printStackTrace();
        }

        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(0,0,700,650);
        add(sp);

        setVisible(true);
    }

    public static void main(String[] args) {
        new billDetails("");
    }
}
