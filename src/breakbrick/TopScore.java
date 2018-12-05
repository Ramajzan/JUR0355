package breakbrick;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.IOException;


import javax.swing.*;
import java.sql.*;


public class TopScore {
    private JFrame pl;
    public TopScore(JFrame menu){
        pl=menu;
        initUI();
    }
    private void initUI(){
        JFrame frame = new JFrame("Top Score");
        JButton button=new JButton("Zpět");

        JTextArea area=new JTextArea();
        area.setBounds(20,20,300,200);

        String text="";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://localhost/projekt?user=root&password=");
            PreparedStatement dotaz = con.prepareStatement("SELECT jmeno,score FROM score ORDER BY score");
            ResultSet vysledky = dotaz.executeQuery();
            while (vysledky.next()) {
                String jmeno = vysledky.getString("jmeno");
                int score = vysledky.getInt("score");
                text=text+"Jmeno: "+jmeno+" score: "+score+System.lineSeparator();
            }

            area.setText(text);

            area.setVisible(true);
            System.out.println(text);
        }catch(Exception x){ System.out.println(x);}
        button.setBounds(200,250,80,30);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                pl.setVisible(true);
            }
        });
        area.disable();
        frame.add(area);
        frame.add(button);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setSize(400,350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);


    }
}
