package com.company;
import javax.swing.*;
import java.awt.*;

public class splash extends JFrame implements Runnable{

    splash(){

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/elect.jpg"));
        Image i2 = i1.getImage().getScaledInstance(720,500,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);

        JLabel image = new JLabel(i3);
        add(image);

        setVisible(true);

        for(int i = 2; i<600; i+=2){
            setSize(i,i);
            setLocation(1100 - i,700-i);
 
            try{
                Thread.sleep(1);
            }catch(Exception e){
                e.printStackTrace();
            }
        }

       Thread t = new Thread(this);
        t.start();
    }

    public void run(){
        try{
            Thread.sleep(7000);
            setVisible(false);

            new Login();

            // login frame
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new splash();
    }
}
