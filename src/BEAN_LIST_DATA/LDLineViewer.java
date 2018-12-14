/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BEAN_LIST_DATA;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author HP Pavilion
 */
public class LDLineViewer {
    public JLabel ligne0, ligne01, ligne02, ligne03, ligne04, ligne05;
    
    public LDLineViewer(JLabel ligne0, JLabel ligne01, JLabel ligne02, JLabel ligne03, JLabel ligne04, JLabel ligne05) {
        this.ligne0 = ligne0;
        this.ligne01 = ligne01;
        this.ligne02 = ligne02;
        this.ligne03 = ligne03;
        this.ligne04 = ligne04;
        this.ligne05 = ligne05;
    }

    public JLabel getLigne0() {
        return ligne0;
    }

    public void setLigne0(JLabel ligne0) {
        this.ligne0 = ligne0;
    }

    public JLabel getLigne01() {
        return ligne01;
    }

    public void setLigne01(JLabel ligne01) {
        this.ligne01 = ligne01;
    }

    public JLabel getLigne02() {
        return ligne02;
    }

    public void setLigne02(JLabel ligne02) {
        this.ligne02 = ligne02;
    }

    public JLabel getLigne03() {
        return ligne03;
    }

    public void setLigne03(JLabel ligne03) {
        this.ligne03 = ligne03;
    }

    public JLabel getLigne04() {
        return ligne04;
    }

    public void setLigne04(JLabel ligne04) {
        this.ligne04 = ligne04;
    }

    public JLabel getLigne05() {
        return ligne05;
    }

    public void setLigne05(JLabel ligne05) {
        this.ligne05 = ligne05;
    }

    public void setValues(ImageIcon imageIcon, String ligne0, String ligne01, String ligne02, String ligne03, String ligne04, String ligne05) {
        this.ligne0.setIcon(imageIcon);
        //Ligne0
        if(ligne0.trim().length() != 0){
            this.ligne0.setText(ligne0);
            this.ligne0.setVisible(true);
        }else{
            this.ligne0.setVisible(false);
        }
        //Ligne01
        if(ligne01.trim().length() != 0){
            this.ligne01.setText(ligne01);
            this.ligne01.setVisible(true);
        }else{
            this.ligne01.setVisible(false);
        }
        //Ligne02
        if(ligne02.trim().length() != 0){
            this.ligne02.setText(ligne02);
            this.ligne02.setVisible(true);
        }else{
            this.ligne02.setVisible(false);
        }
        //Ligne03
        if(ligne03.trim().length() != 0){
            this.ligne03.setText(ligne03);
            this.ligne03.setVisible(true);
        }else{
            this.ligne03.setVisible(false);
        }
        //Ligne04
        if(ligne04.trim().length() != 0){
            this.ligne04.setText(ligne04);
            this.ligne04.setVisible(true);
        }else{
            this.ligne04.setVisible(false);
        }
        //Ligne05
        if(ligne05.trim().length() != 0){
            this.ligne05.setText(ligne05);
            this.ligne05.setVisible(true);
        }else{
            this.ligne05.setVisible(false);
        }
    }    
}
