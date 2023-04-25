package com.System;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Pieces extends JButton {
        URL url;
        Image image;

        Pieces(URL url, Point p){
        this.url=url;
        image=Toolkit.getDefaultToolkit().getImage(url);
        setPreferredSize(new Dimension(50,50));
        setBackground(Color.WHITE);
        setFocusable(false);
        setIcon(new ImageIcon(image.getScaledInstance(30,30,Image.SCALE_SMOOTH)));
        }

        public boolean hasQuestionMark(){
                boolean hasQuestion=false;
                URL url = getClass().getClassLoader().getSystemResource("\\com\\System\\Image\\question.png");
                if(image.equals(Toolkit.getDefaultToolkit().getImage(url))){
                        hasQuestion=true;
                }
                return hasQuestion;
        }
}
