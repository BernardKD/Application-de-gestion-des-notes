package SuperProf;

import java.awt.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class EcranAcc extends JFrame {
    
    JLabel lblLogos, lblTitre;
    JButton btnProf, btnEtud, btnMod, btnPrec;
    
    public EcranAcc(String datab,String userx,String passx){
        super.setTitle("SuperProf");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        int desiredWidth = (int) (screenWidth*0.8);
        int desiredHeight = (int) (screenHeight*0.8);
        super.setSize(desiredWidth, desiredHeight);
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
        lblTitre = new JLabel("Bienvenue sur notre application");
        lblTitre.setBounds(180,10, 435, 30);
        lblTitre.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitre.setForeground(new Color(255,255,255));
        //lblTitre.setLocation(400, 225);
        pn.add(lblTitre);
        
        lblTitre = new JLabel("Welcome on our application");
        lblTitre.setBounds(250,35, 400, 20);
        lblTitre.setFont(new Font("Arial", Font.ITALIC, 15));
        lblTitre.setForeground(new Color(255,255,255));
        //lblTitre.setLocation(400, 225);
        pn.add(lblTitre);
        
        //
        ImageIcon i  = new ImageIcon(new ImageIcon("image.jpg").getImage().getScaledInstance(700,350, Image.SCALE_DEFAULT));
        JLabel lblLogo = new JLabel(i);
        lblLogo.setBounds(0,85, 700,350);
        lblLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLogo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pn.add(lblLogo);
        //*/425,775
        
        btnPrec = new JButton("QUITTER");
        btnPrec.setBounds(275,490,150,30);
        btnPrec.setFont(new Font("Arial", Font.BOLD,14));
        btnPrec.setForeground(Color.BLACK);
        btnPrec.setBackground(new Color(0,255,255));
        btnPrec.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrecActionPerformed(evt);
            }
            
            private void btnPrecActionPerformed(ActionEvent evt) {
                
                EcranAcc ecr = new EcranAcc(datab,userx,passx);
                ecr.setVisible(false);
                dispose();
                
            } 
        });
        pn.add(btnPrec);
        
        
        btnMod = new JButton("MODERATEUR");
        btnMod.setBounds(275,450,150,30);
        btnMod.setFont(new Font("Arial", Font.BOLD, 15));
        btnMod.setForeground(Color.BLACK);
        btnMod.setBackground(new Color(0,255,255));
        btnMod.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModActionPerformed(evt);
            }
            
            private void btnModActionPerformed(ActionEvent evt) {
                Mdpmdrt e = new Mdpmdrt("CrtPw",0,null,null,datab,userx,passx);
                e.setVisible(true);
                EcranAcc en = new EcranAcc(datab,userx,passx);
                en.setVisible(false);
                dispose();
                
            } 
        });
        pn.add(btnMod);
        
        btnEtud = new JButton("ETUDIANT");
        btnEtud.setBounds(75,450,150, 30);
        btnEtud.setFont(new Font("Arial", Font.BOLD, 15));
        btnEtud.setForeground(Color.BLACK);
        btnEtud.setBackground(new Color(0,255,255));
        btnEtud.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEtudActionPerformed(evt);
            }
            
            private void btnEtudActionPerformed(ActionEvent evt) {
                Etudiant e = new Etudiant(datab,userx,passx);
                e.setVisible(true);
                dispose();
                
            } 
        });
        pn.add(btnEtud);
        
        btnProf = new JButton("PROFESSEUR");
        btnProf.setBounds(475,450, 150,30);
        btnProf.setFont(new Font("Arial", Font.BOLD, 15));
        btnProf.setForeground(Color.BLACK);
        btnProf.setBackground(new Color(0,255,255));
        btnProf.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEtudActionPerformed(evt);
            }
            
            private void btnEtudActionPerformed(ActionEvent evt) {
                Prof e = new Prof(datab,userx,passx);
                e.setVisible(true);
                dispose();
                
            } 
        });
        pn.add(btnProf);
        
    }
    
}
