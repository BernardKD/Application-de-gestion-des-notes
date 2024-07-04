package SuperProf;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;
import javax.swing.JRadioButton;

public class Mdpmdrt extends JFrame {
    
    JPasswordField pwf, pwf2;
    JLabel lblMdp, lblMdp2, lblTitre;
    JButton btnModif, btnAnnul;
    ResultSet rs;
    JRadioButton showPasswordRadioButton, showPasswordRadioButton1;
    
    public Mdpmdrt(String action,int num,String prof,String ue, String datab, String userx, String passx){
        
        if(action.equals("CrtPw")) {
            super.setTitle("SuperProf");
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int screenWidth = screenSize.width;
            int screenHeight = screenSize.height;
            int desiredWidth = (int) (screenWidth*0.8);
            int desiredHeight = (int) (screenHeight*0.8);
            super.setSize(desiredWidth, desiredHeight);
            super.setLocationRelativeTo(null);
            super.setResizable(true);
            super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JPanel pn = new JPanel();
            getContentPane().add(pn);
            pn.setLayout(null);
            pn.setBackground(new Color(50,50,100));
            add(pn);
            
            lblMdp = new JLabel("ANCIEN MOT DE PASSE");
            lblMdp.setBounds(20,60, 250, 30);
            lblMdp.setFont(new Font("Arial", Font.BOLD, 14));
            lblMdp.setForeground(new Color(255,255,255));
            pn.add(lblMdp);
            
            pwf = new JPasswordField();
            pwf.setBounds(250,60,300,30);
            pwf.setFont(new Font("Arial", Font.PLAIN, 14));
            pn.add(pwf);
            
            lblMdp2 = new JLabel("NOUVEAU MOT DE PASSE");
            lblMdp2.setBounds(20,100, 250, 30);
            lblMdp2.setFont(new Font("Arial", Font.BOLD, 14));
            lblMdp2.setForeground(new Color(255,255,255));
            pn.add(lblMdp2);
            
            pwf2 = new JPasswordField();
            pwf2.setBounds(250,100,300,30);
            pwf2.setFont(new Font("Arial", Font.PLAIN, 14));
            pn.add(pwf2);
            
            showPasswordRadioButton = new JRadioButton("Voir1");
            showPasswordRadioButton.setBounds(250,135,100,20);
            showPasswordRadioButton.setFont(new Font("Arial", Font.BOLD, 15));
            showPasswordRadioButton.setForeground(Color.BLACK);
            showPasswordRadioButton.setBackground(new Color(50,50,100));
            showPasswordRadioButton.addActionListener(new java.awt.event.ActionListener(){
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    showPasswordRadioButtonActionPerformed(evt);
                }
                public void showPasswordRadioButtonActionPerformed(ActionEvent evt) {
                    JRadioButton source = (JRadioButton) evt.getSource();
                    if (source.isSelected()) {
                        pwf.setEchoChar((char) 0); // Afficher le texte en clair
                    } else {
                        pwf.setEchoChar('\u2022'); // Masquer le texte
                    }
                }
            });
            pn.add(showPasswordRadioButton);
            
            showPasswordRadioButton1 = new JRadioButton("Voir2");
            showPasswordRadioButton1.setBounds(360,135,100,20);
            showPasswordRadioButton1.setFont(new Font("Arial", Font.BOLD, 15));
            showPasswordRadioButton1.setForeground(Color.BLACK);
            showPasswordRadioButton1.setBackground(new Color(50,50,100));
            showPasswordRadioButton1.addActionListener(new java.awt.event.ActionListener(){
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    showPasswordRadioButton1ActionPerformed(evt);
                }
                public void showPasswordRadioButton1ActionPerformed(ActionEvent evt) {
                    JRadioButton source = (JRadioButton) evt.getSource();
                    if (source.isSelected()) {
                        pwf2.setEchoChar((char) 0); // Afficher le texte en clair
                    } else {
                        pwf2.setEchoChar('\u2022'); // Masquer le texte
                    }
                }
            });
            pn.add(showPasswordRadioButton1);
            
            lblTitre = new JLabel("ACTIONS DE MODERATEUR");
            lblTitre.setBounds(275,10, 300, 30);
            lblTitre.setFont(new Font("Arial", Font.BOLD, 18));
            lblTitre.setForeground(new Color(255,255,255));
            //lblTitre.setLocation(400, 225);
            pn.add(lblTitre);
            
            lblTitre = new JLabel("MODERATOR'S ACTIVITIES");
            lblTitre.setBounds(323,28, 300, 30);
            lblTitre.setFont(new Font("Arial", Font.ITALIC, 12));
            lblTitre.setForeground(new Color(255,255,255));
            //lblTitre.setLocation(400, 225);
            pn.add(lblTitre);
            
            btnModif = new JButton("CONFIRMER");
            btnModif.setBounds(560,60,200,30);
            btnModif.setFont(new Font("Arial", Font.BOLD, 15));
            btnModif.setForeground(Color.BLACK);
            btnModif.setBackground(new Color(0,255,255));
            btnModif.addActionListener(new java.awt.event.ActionListener(){
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnModifActionPerformed(evt);
                }
            
                private void btnModifActionPerformed(ActionEvent evt) {
                    char[]Mdp01 = pwf.getPassword();
                    char[]Mdp02 = pwf2.getPassword();
                    String Mdp1;
                    //Identifiant = txtId.getText();
                    String Mdpa = new String(Mdp01);
                    String Mdpb = new String(Mdp02);
                    Connect conn = new Connect(datab,userx,passx);
                    
                    try{
                        String rq0 = "Select * from mot_de_passe where pass =?";
                        PreparedStatement ps0 = conn.maConnection().prepareStatement(rq0);
                        ps0.setString(1,Mdpa);
                        rs = ps0 .executeQuery();
                        if (rs.next() == false) {
                            JOptionPane.showMessageDialog(null,"Mot de passe errone",null, JOptionPane.ERROR_MESSAGE);
                            conn.maConnection().close();
                        } else {
                            Mdp1 = rs.getString(2).trim();
                            if(Mdpb.equals(Mdp1)){
                                JOptionPane.showMessageDialog(null,"Mot de passe modifier",null, JOptionPane.INFORMATION_MESSAGE);
                                conn.maConnection().close();
                                Mdpmdrt es = new Mdpmdrt(action,num,prof,ue,datab,userx,passx);
                                es.setVisible(false);
                                EcranAcc eq = new EcranAcc(datab,userx,passx);
                                eq.setVisible(true);
                                dispose();
                            } else {
                                String rq = "update mot_de_passe set pass=?";
                                PreparedStatement ps = conn.maConnection().prepareStatement(rq);
                                ps.setString(1,Mdpb);
                                JOptionPane.showMessageDialog(null,"Mot de passe modifier",null, JOptionPane.INFORMATION_MESSAGE);
                                conn.maConnection().close();
                                Mdpmdrt es = new Mdpmdrt(action,num,prof,ue,datab,userx,passx);
                                es.setVisible(false);
                                EcranAcc eq = new EcranAcc(userx,datab,passx);
                                eq.setVisible(true);
                                dispose();
                            }
                        }
                    } catch (SQLException e){
                        e.printStackTrace();
                    }
                    
                } 
            });
            pn.add(btnModif);
            
            btnAnnul = new JButton("ANNULER");
            btnAnnul.setBounds(560,100,200,30);
            btnAnnul.setFont(new Font("Arial", Font.BOLD, 15));
            btnAnnul.setForeground(Color.BLACK);
            btnAnnul.setBackground(new Color(0,255,255));
            btnAnnul.addActionListener(new java.awt.event.ActionListener(){
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnAnnulActionPerformed(evt);
                }
            
                private void btnAnnulActionPerformed(ActionEvent evt) {
                    JOptionPane.showMessageDialog(null,"Operation annuler",null, JOptionPane.INFORMATION_MESSAGE);
                    Mdpmdrt es = new Mdpmdrt(action,num,prof,ue,datab,userx,passx);
                    es.setVisible(false);
                    EcranAcc eq = new EcranAcc(datab,userx,passx);
                    eq.setVisible(true);
                    dispose();
                }
            });    
            pn.add(btnAnnul);
        } else if(action.equals("CrtPr")) {
            super.setTitle("SuperProf");
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int screenWidth = screenSize.width;
            int screenHeight = screenSize.height;
            int desiredWidth = (int) (screenWidth*0.8);
            int desiredHeight = (int) (screenHeight*0.8);
            super.setSize(desiredWidth, desiredHeight);
            super.setLocationRelativeTo(null);
            super.setResizable(true);
            super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JPanel pn = new JPanel();
            getContentPane().add(pn);
            pn.setLayout(null);
            pn.setBackground(new Color(50,50,100));
            add(pn);
            
            lblMdp = new JLabel("MOT DE PASSE");
            lblMdp.setBounds(20,60, 120, 30);
            lblMdp.setFont(new Font("Arial", Font.BOLD, 14));
            lblMdp.setForeground(new Color(255,255,255));
            pn.add(lblMdp);
            
            pwf = new JPasswordField();
            pwf.setBounds(150,60,300,30);
            pwf.setFont(new Font("Arial", Font.PLAIN, 14));
            pn.add(pwf);
            
            showPasswordRadioButton = new JRadioButton("Voir");
            showPasswordRadioButton.setBounds(150,95,100,20);
            showPasswordRadioButton.setFont(new Font("Arial", Font.BOLD, 15));
            showPasswordRadioButton.setForeground(Color.BLACK);
            showPasswordRadioButton.setBackground(new Color(50,50,100));
            showPasswordRadioButton.addActionListener(new java.awt.event.ActionListener(){
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    showPasswordRadioButtonActionPerformed(evt);
                }
                public void showPasswordRadioButtonActionPerformed(ActionEvent evt) {
                    JRadioButton source = (JRadioButton) evt.getSource();
                    if (source.isSelected()) {
                        pwf.setEchoChar((char) 0); // Afficher le texte en clair
                    } else {
                        pwf.setEchoChar('\u2022'); // Masquer le texte
                    }
                }
            });
            pn.add(showPasswordRadioButton);
            
            lblTitre = new JLabel("JUSTIFIER QUE VOUS ETES MODERATEUR");
            lblTitre.setBounds(205,10, 450, 23);
            lblTitre.setFont(new Font("Arial", Font.BOLD, 18));
            lblTitre.setForeground(new Color(255,255,255));
            //lblTitre.setLocation(400, 225);
            pn.add(lblTitre);
            
            lblTitre = new JLabel("JUSTIFY THAT YOU ARE A MODERATOR");
            lblTitre.setBounds(250,30, 450, 20);
            lblTitre.setFont(new Font("Arial", Font.ITALIC, 11));
            lblTitre.setForeground(new Color(255,255,255));
            //lblTitre.setLocation(400, 225);
            pn.add(lblTitre);
            
            btnModif = new JButton("CONFIRMER");
            btnModif.setBounds(460,60,150,30);
            btnModif.setFont(new Font("Arial", Font.BOLD, 15));
            btnModif.setForeground(Color.BLACK);
            btnModif.setBackground(new Color(0,255,255));
            btnModif.addActionListener(new java.awt.event.ActionListener(){
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnModifActionPerformed(evt);
                }
            
                private void btnModifActionPerformed(ActionEvent evt) {
                    char[]Mdp01 = pwf.getPassword();
                    String Mdp1;
                    //Identifiant = txtId.getText();
                    String Mdpa = new String(Mdp01);
                    Connect conn = new Connect(datab,userx,passx);
                    
                    try{
                        String rq0 = "Select * from mot_de_passe where identifiant =?";
                        PreparedStatement ps0 = conn.maConnection().prepareStatement(rq0);
                        ps0.setString(1,"SuperProf");
                        rs = ps0 .executeQuery();
                        if (rs.next() == false) {
                            JOptionPane.showMessageDialog(null,"Moderateur n'existe pas",null, JOptionPane.ERROR_MESSAGE);
                            conn.maConnection().close();
                        } else {
                            Mdp1 = rs.getString(2).trim();
                            if(Mdpa.equals(Mdp1)){
                                conn.maConnection().close();
                                if(num==0){
                                    Mdpmdrt es = new Mdpmdrt(action,num,prof,ue,datab,userx,passx);
                                    es.setVisible(false);
                                    EnregistrementProf eq = new EnregistrementProf(prof,datab,userx,passx);//////////////////////////////////////////////////////////////////////////////////////////
                                    eq.setVisible(true);
                                    dispose();
                                } else if (num==1) {
                                    Mdpmdrt es = new Mdpmdrt(action,num,prof,ue,datab,userx,passx);
                                    es.setVisible(false);
                                    EnregistrementProf eq = new EnregistrementProf(prof,datab,userx,passx);
                                    eq.setVisible(true);
                                    dispose();
                                }
                            } else {
                                JOptionPane.showMessageDialog(null,"Mot de passe incorect "+Mdp1,null, JOptionPane.ERROR_MESSAGE);
                                conn.maConnection().close();
                            }
                        }
                    } catch (SQLException e){
                        e.printStackTrace();
                    }
                    
                } 
            });
            pn.add(btnModif);
            
            btnAnnul = new JButton("ANNULER");
            btnAnnul.setBounds(620,60,150,30);
            btnAnnul.setFont(new Font("Arial", Font.BOLD, 15));
            btnAnnul.setForeground(Color.BLACK);
            btnAnnul.setBackground(new Color(0,255,255));
            btnAnnul.addActionListener(new java.awt.event.ActionListener(){
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnAnnulActionPerformed(evt);
                }
            
                private void btnAnnulActionPerformed(ActionEvent evt) {
                    JOptionPane.showMessageDialog(null,"Operation annuler",null, JOptionPane.INFORMATION_MESSAGE);
                    if(num==0){
                        Mdpmdrt es = new Mdpmdrt(action,num,prof,ue,datab,userx,passx);
                        es.setVisible(false);
                        Prof eq = new Prof(datab,userx,passx);
                        eq.setVisible(true);
                        dispose();
                    } else if(num==1){
                        Mdpmdrt es = new Mdpmdrt(action,num,prof,ue,datab,userx,passx);
                        es.setVisible(false);
                        ProfConnect ew = new ProfConnect(prof,ue,datab,userx,passx);
                        ew.setVisible(true);
                        dispose();
                    }
                }
            });    
            pn.add(btnAnnul);
        }
        
    }
    
}
