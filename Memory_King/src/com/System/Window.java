package com.System;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Random;

public class Window extends JFrame implements ActionListener {

    Pieces[][] pieces;
    JPanel pnlContainer;
    URL[][] url=new URL[4][4];
    boolean alreadyClick=false;
    int tempRow,tempCol;
    int count=0;

    Window(){


        pieces = new Pieces[4][4];
        pnlContainer= new JPanel();


        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                pieces[i][j]=new Pieces(getClass().getClassLoader().getSystemResource("\\com\\System\\Image\\question.png"), new Point(i,j));
            }
        }

        pnlContainer.setLayout(new GridLayout(4,4,10,10));
        pnlContainer.setBorder(new EmptyBorder(10,5,10,5));
        pnlContainer.setBackground(Color.GRAY);
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
               pnlContainer.add(pieces[i][j]);
            }
        }

        url[0][0]=getClass().getClassLoader().getSystemResource("\\com\\System\\Image\\image_1.png");
        url[0][1]=getClass().getClassLoader().getSystemResource("\\com\\System\\Image\\image_1.png");
        url[0][2]=getClass().getClassLoader().getSystemResource("\\com\\System\\Image\\image_2.png");
        url[0][3]=getClass().getClassLoader().getSystemResource("\\com\\System\\Image\\image_2.png");
        url[1][0]=getClass().getClassLoader().getSystemResource("\\com\\System\\Image\\image_3.png");
        url[1][1]=getClass().getClassLoader().getSystemResource("\\com\\System\\Image\\image_3.png");
        url[1][2]=getClass().getClassLoader().getSystemResource("\\com\\System\\Image\\image_4.png");
        url[1][3]=getClass().getClassLoader().getSystemResource("\\com\\System\\Image\\image_4.png");
        url[2][0]=getClass().getClassLoader().getSystemResource("\\com\\System\\Image\\image_5.png");
        url[2][1]=getClass().getClassLoader().getSystemResource("\\com\\System\\Image\\image_5.png");
        url[2][2]=getClass().getClassLoader().getSystemResource("\\com\\System\\Image\\image_6.png");
        url[2][3]=getClass().getClassLoader().getSystemResource("\\com\\System\\Image\\image_6.png");
        url[3][0]=getClass().getClassLoader().getSystemResource("\\com\\System\\Image\\image_7.png");
        url[3][1]=getClass().getClassLoader().getSystemResource("\\com\\System\\Image\\image_7.png");
        url[3][2]=getClass().getClassLoader().getSystemResource("\\com\\System\\Image\\image_8.png");
        url[3][3]=getClass().getClassLoader().getSystemResource("\\com\\System\\Image\\image_8.png");

        suffle(url);

        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                pieces[i][j].putClientProperty("row",i);
                pieces[i][j].putClientProperty("coloum",j);
            }
        }

        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                pieces[i][j].addActionListener(this);
            }
        }




        add(pnlContainer);
        setTitle("Memory King");
        setSize(600,600);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void suffle(URL[][] url){
        Random random=new Random();

        for (int i = pieces.length-1; i >0 ; i--) {
            for (int j = pieces[i].length-1; j >0 ; j--) {
                int m=random.nextInt(i+1);
                int n=random.nextInt(j+1);
                URL temp=url[i][j];
                url[i][j]=url[m][n];
                url[m][n]=temp;


            }

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() instanceof JButton){
            int row = (int) ((JButton) e.getSource()).getClientProperty("row");
            int cols = (int) ((JButton) e.getSource()).getClientProperty("coloum");
            if(pieces[row][cols].hasQuestionMark()) {
                if (alreadyClick) {
                    Image image = Toolkit.getDefaultToolkit().getImage(url[row][cols]);
                    pieces[row][cols].image=image;
                    pieces[row][cols].setIcon(new ImageIcon(image.getScaledInstance(120, 120, Image.SCALE_SMOOTH)));
                    Timer timer = new Timer(500, action -> checkImg(tempRow, tempCol, row, cols));
                    timer.setRepeats(false);
                    timer.start();
                    alreadyClick = false;

                } else {
                    Image image = Toolkit.getDefaultToolkit().getImage(url[row][cols]);
                    pieces[row][cols].image=image;
                    pieces[row][cols].setIcon(new ImageIcon(image.getScaledInstance(120, 120, Image.SCALE_SMOOTH)));
                    tempRow = row;
                    tempCol = cols;
                    alreadyClick = true;
                }
            }
        }
        count=0;
        for (int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                if(!pieces[i][j].hasQuestionMark()){
                    count++;
                }
            }
        }
        System.out.println(count);
        if(count==16){
            JOptionPane.showMessageDialog(pnlContainer,"Thulo vayes");
            URL uri = getClass().getClassLoader().getSystemResource("\\com\\System\\Image\\question.png");
            Image img = Toolkit.getDefaultToolkit().getImage(uri);
            for (int i=0;i<4;i++) {
                for (int j = 0; j < 4; j++) {
                    pieces[i][j].setIcon((new ImageIcon(img.getScaledInstance(30, 30, Image.SCALE_SMOOTH))));
                }
            }
        }
    }

    private void checkImg(int tempRow, int tempCol, int row, int cols) {
        if(!url[tempRow][tempCol].equals(url[row][cols])){
            URL uri = getClass().getClassLoader().getSystemResource("\\com\\System\\Image\\question.png");
            Image img = Toolkit.getDefaultToolkit().getImage(uri);
            pieces[tempRow][tempCol].image=img;
            pieces[tempRow][tempCol].setIcon(new ImageIcon(img.getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
            pieces[row][cols].image=img;
            pieces[row][cols].setIcon(new ImageIcon(img.getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
        }
    }
}
