package com.company;

import com.mysql.cj.exceptions.RSAException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class generateBill extends JFrame implements ActionListener {

    String meter;
    JButton bill;
    Choice cmonth;
    JTextArea area;

        generateBill(String meter){
            setSize(500,800);
            setLocation(550,30);

            setLayout(new BorderLayout());

            JPanel panel = new JPanel();

            JLabel heading = new JLabel("Generate Bill");
            JLabel meternumber = new JLabel(meter);

            cmonth = new Choice();

            cmonth.setBounds(520,20,150,20);
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

            area = new JTextArea(50,15);
            area.setText("\n\n\t------------Click on the-----------\n\t Generate Bill Button to get\n\tthe Bill of the selected month");
            area.setFont(new Font("Senserif", Font.ITALIC, 18));

            JScrollPane pane = new JScrollPane(area);

            bill = new JButton("Generate Bill");
            bill.addActionListener(this);

            panel.add(heading);
            panel.add(meternumber);
            panel.add(cmonth);
            add(panel, "North");

            add(pane, "Center");
            add(bill, "South");

            setVisible(true);


        }

        public void actionPerformed(ActionEvent ae){
            try{

                Conn c = new Conn();

                String month = cmonth.getSelectedItem();

                area.setText("\t  Reliance Power Limited\nELECTRICITY BILL GENERATED FOR THE MONTH\n\t OF "+month+", 2022\n\n\n");

                ResultSet rs = c.s.executeQuery("select * from customer where meterno = '339489'");

                if (rs.next()){
                    area.append("\n   Customer Name: " + rs.getString("name"));
                    area.append("\n   Meter Number: " + rs.getString("meterno"));
                    area.append("\n   Address: " + rs.getString("address"));
                    area.append("\n   City: " + rs.getString("city"));
                    area.append("\n   State: " + rs.getString("state"));
                    area.append("\n   Email: " + rs.getString("email"));
                    area.append("\n   Phone: " + rs.getString("phone"));
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }

    public static void main(String[] args) {
        new generateBill("");
    }
}
