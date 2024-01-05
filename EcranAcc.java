
package Etudiant;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class EcranAcc extends JFrame {
    JLabel lblLogos, lblTitre;
    JButton btnProf, btnEtud, btnMod;
    JComboBox t;
    
    public EcranAcc(){
        super.setTitle("SuperProf");
        super.setSize(1000, 900);
        super.setLocationRelativeTo(null);
        super.setRootPaneCheckingEnabled(true);
        super.setFocusableWindowState(true);
        super.setResizable(true);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel pn = new JPanel();
        getContentPane().add(pn);
        pn.setLayout(null);
        pn.setRequestFocusEnabled(true);
        pn.setBackground(new Color(50,50,50));
        add(pn);
        lblTitre = new JLabel("Bienvenue sur notre Application");
        lblTitre.setBounds(280,10, 435, 30);
        lblTitre.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitre.setForeground(new Color(255,255,255));
        //lblTitre.setLocation(400, 225);
        pn.add(lblTitre);
        //
        ImageIcon i  = new ImageIcon(new ImageIcon("image.jpg").getImage().getScaledInstance(1000,660, Image.SCALE_DEFAULT));
        JLabel lblLogo = new JLabel(i);
        lblLogo.setBounds(0,50, 1000,660);
        lblLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLogo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pn.add(lblLogo);
        //*/
        
        t = new JComboBox();
        
        
        btnMod = new JButton("MODERATEUR");
        btnMod.setBounds(425,735,150,30);
        btnMod.setFont(new Font("Arial", Font.BOLD, 15));
        btnMod.setForeground(Color.BLACK);
        btnMod.setBackground(new Color(0,255,255));
        btnMod.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModActionPerformed(evt);
            }
            
            private void btnModActionPerformed(ActionEvent evt) {
                Mdpmdrt e = new Mdpmdrt("CrtPw",0,null,null);
                e.setVisible(true);
                EcranAcc en = new EcranAcc();
                en.setVisible(false);
                dispose();
                
            } 
        });
        pn.add(btnMod);
        
        btnEtud = new JButton("ETUDIANT");
        btnEtud.setBounds(200,735,150, 30);
        btnEtud.setFont(new Font("Arial", Font.BOLD, 15));
        btnEtud.setForeground(Color.BLACK);
        btnEtud.setBackground(new Color(0,255,255));
        btnEtud.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEtudActionPerformed(evt);
            }
            
            private void btnEtudActionPerformed(ActionEvent evt) {
                Etudiant e = new Etudiant();
                e.setVisible(true);
                EcranAcc en = new EcranAcc();
                en.setVisible(false);
                dispose();
                
            } 
        });
        pn.add(btnEtud);
        
        btnProf = new JButton("PROFESSEUR");
        btnProf.setBounds(650,735, 150,30);
        btnProf.setFont(new Font("Arial", Font.BOLD, 15));
        btnProf.setForeground(Color.BLACK);
        btnProf.setBackground(new Color(0,255,255));
        btnProf.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEtudActionPerformed(evt);
            }
            
            private void btnEtudActionPerformed(ActionEvent evt) {
                Prof e = new Prof();
                e.setVisible(true);
                EcranAcc en = new EcranAcc();
                en.setVisible(false);
                dispose();
                
            } 
        });
        pn.add(btnProf);
    }
    public static void main(String[] args){
        EcranAcc en = new EcranAcc();
        en.setVisible(true);
    }
}
