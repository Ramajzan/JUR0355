package breakbrick;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.sql.*;

public class Menu {
    Menu(){
        JFrame frame = new JFrame("Menu");
        JButton button,button1, button2, button3,button4,button5,button6;
        File f=new File("Resources/Score/score.txt");
        if(f.exists()){
            f.delete();
        }
        String help="0";
        FileWriter out=null;
        try{
            out = new FileWriter(f);
            out.write(help);
            out.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        button = new JButton("Level 1");
        button1 = new JButton("Level 2");
        button2 = new JButton("Level 3");
        button3 = new JButton("Level 4");
        button4 = new JButton("Level 5");

        button5 = new JButton("Uložit score");
        button6 = new JButton("Top Score");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BrickBreaker game = new BrickBreaker(0,frame);
                game.setVisible(true);
            }
        });
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Scanner scc=null;
                try{
                    scc = new Scanner(new File("Resources/Score/score.txt"));
                }catch(IOException a){
                    a.printStackTrace();
                }
                int a=scc.nextInt();
                if(a>=2100){
                    BrickBreaker game = new BrickBreaker(1,frame);
        game.setVisible(true);}
    }
});
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Scanner scc=null;
                try{
                    scc = new Scanner(new File("Resources/Score/score.txt"));
                }catch(IOException a){
                    a.printStackTrace();
                }
                int a=scc.nextInt();
                if(a>=4300){
                    BrickBreaker game = new BrickBreaker(2,frame);
                game.setVisible(true);}
            }
        });
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Scanner scc=null;
                try{
                    scc = new Scanner(new File("Resources/Score/score.txt"));
                }catch(IOException a){
                    a.printStackTrace();
                }
                int a=scc.nextInt();
                if(a>=6100){
                    BrickBreaker game = new BrickBreaker(3,frame);
                game.setVisible(true);}
            }
        });
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Scanner scc=null;
                try{
                    scc = new Scanner(new File("Resources/Score/score.txt"));
                }catch(IOException a){
                    a.printStackTrace();
                }
                int a=scc.nextInt();
                if(a>=7900){
                    BrickBreaker game = new BrickBreaker(4,frame);
                game.setVisible(true);}
            }
        });
        button.setBounds(20,20,80,30);
        button1.setBounds(20,60,80,30);
        button2.setBounds(20,100,80,30);
        button3.setBounds(20,140,80,30);
        button4.setBounds(20,180,80,30);
        button5.setBounds(140,250,140,30);
        button6.setBounds(140,180,140,30);
        JTextField test=new JTextField();
        test.setBounds(140,215,140,30);
        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Scanner scc=null;
                try{
                    scc = new Scanner(new File("Resources/Score/score.txt"));
                }catch(IOException a){
                    a.printStackTrace();
                }
                int a=scc.nextInt();
                String hel=test.getText();
                try{
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con=DriverManager.getConnection(
                            "jdbc:mysql://localhost/projekt?user=root&password=");
                    PreparedStatement dotaz = con.prepareStatement("INSERT INTO score (jmeno, score) VALUES (?, ?)");
                    dotaz.setString(1, hel);
                    dotaz.setInt(2, a);
                    dotaz.executeUpdate();
                }catch(Exception x){ System.out.println(x);}
            }

        });
        button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TopScore top=new TopScore(frame);
                frame.setVisible(false);
            }
        });
        frame.add(button);
        frame.add(button1);
        frame.add(button2);
        frame.add(button3);
        frame.add(button4);
        frame.add(button5);
        frame.add(button6);
        frame.add(test);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setSize(325,350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        Menu menu=new Menu();
    }
}
