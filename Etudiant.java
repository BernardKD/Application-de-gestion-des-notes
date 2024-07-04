package SuperProf;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.beans.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import java.sql.*;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;

public class Etudiant extends JFrame {
    
     ResultSet rs;
    JLabel lblTitre, lblId, lblMdp; /*lblEnreg*/
    //JSeparator spr;
    JButton btnConnect, btnEnreg, btnPrec;
    JTextField txtId;
    JPasswordField pwf;
    JRadioButton rbtn;
    int i=0;
    
    public Etudiant(String datab,String userx, String passx){
        
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
        add(pn);
        pn.setBackground(new Color(50,50,100));
        
        rbtn = new JRadioButton("afficher");
        rbtn.setBounds(250,240,150,20);
        rbtn.setFont(new Font("Arial", Font.BOLD,12));
        rbtn.setBackground(new Color(0,200,200));
        rbtn.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnActionPerformed(evt);
            }
            
            private void rbtnActionPerformed(ActionEvent evt) {
                
                if(i%2==0){
                    char[]mo = pwf.getPassword();
                    String mp = new String(mo);
                    pwf.setText(mp);
                    i+=i;
                } else {
                    String mp = pwf.getText();
                    int l = mp.length();
                    char[] mo = new char[l];
                    for(int o=0; o<l; o++){
                        mo[o]=mp.charAt(o);
                    }
                    i+=i;
                }
            } 
        });
        //pn.add(rbtn);
        
        btnPrec = new JButton("Precedent");
        btnPrec.setBounds(700,240,150,20);
        btnPrec.setFont(new Font("Arial", Font.BOLD,12));
        btnPrec.setBackground(new Color(100,205,205));
        btnPrec.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrecActionPerformed(evt);
            }
            
            private void btnPrecActionPerformed(ActionEvent evt) {
                
                EcranAcc ecr = new EcranAcc(datab,userx,passx);
                ecr.setVisible(true);
                dispose();
                
            } 
        });
        pn.add(btnPrec);
        
        lblTitre = new JLabel("CONNECTEZ-VOUS A VOTRE COMPTE");
        lblTitre.setBounds(250,10, 500, 30);
        lblTitre.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitre.setForeground(new Color(255,255,255));
        //lblTitre.setLocation(400, 225);
        pn.add(lblTitre);
        
        lblTitre = new JLabel("CONNECT TO YOUR ACCOUNT");
        lblTitre.setBounds(340,35, 300, 20);
        lblTitre.setFont(new Font("Arial", Font.ITALIC, 15));
        lblTitre.setForeground(new Color(255,255,255));
        //lblTitre.setLocation(400, 225);
        pn.add(lblTitre);
        
        lblId = new JLabel("IDENTIFIANT");
        lblId.setBounds(20,100, 200, 30);
        lblId.setFont(new Font("Arial", Font.BOLD, 16));
        lblId.setForeground(new Color(255,255,255));
        pn.add(lblId);
        
        txtId = new JTextField();
        txtId.setBounds(250,100,300,30);
        txtId.setFont(new Font("Arial", Font.PLAIN, 14));
        pn.add(txtId);
        
        btnConnect = new JButton("CONNEXION");
        btnConnect.setBounds(700,100,200,30);
        btnConnect.setFont(new Font("Arial", Font.BOLD, 15));
        btnConnect.setBackground(new Color(0,255,255));
        btnConnect.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConnectActionPerformed(evt);
            }
            
            private void btnConnectActionPerformed(ActionEvent evt) {
                
                char[]Mdp0 = pwf.getPassword();
                String Identifiant,Mdp1,Ue,Ue2,Uet,Mat;
                Identifiant = txtId.getText();
                String Mdp = new String(Mdp0);
                Connect conn = new Connect(datab,userx,passx);
                
                try{
                    String rq = "Select * from tb_etudiant where identifiant =?";
                    PreparedStatement ps = conn.maConnection().prepareStatement(rq);
                    ps.setString(1, Identifiant);
                    rs = ps .executeQuery();
                    if (rs.next() == false) {
                        
                        JOptionPane.showMessageDialog(null,"Identifiant n'existe pas \nSi vous n'avez pas de compte, veuillez vous enregistrer",null, JOptionPane.ERROR_MESSAGE);
                        txtId.setText("");
                    } else {
                        Mdp1 = rs.getString(2).trim();
                        Mat = rs.getString(3).trim();
                        Ue = rs.getString(8).trim();
                        Ue2 = rs.getString(9).trim();
                        Uet = rs.getString(10).trim();
                        if(Mdp.equals(Mdp1)){
                            EtudConnect efn = new EtudConnect(Identifiant,Ue,Ue2,Uet,Mat,datab,userx,passx);
                            efn.setVisible(true);
                            dispose();
                        } else {
                            
                            JOptionPane.showMessageDialog(null,"Mot de passe errone",null, JOptionPane.ERROR_MESSAGE);
                        
                        }
                    }
                } catch (SQLException e){
                    e.printStackTrace();
                }
                
            } 
        });
        pn.add(btnConnect);
        
        lblMdp = new JLabel("MOT DE PASSE");
        lblMdp.setBounds(20,200, 250, 30);
        lblMdp.setFont(new Font("Arial", Font.BOLD, 16));
        lblMdp.setForeground(new Color(255,255,255));
        pn.add(lblMdp);
        
        pwf = new JPasswordField();
        pwf.setBounds(250,200,300,30);
        pwf.setFont(new Font("Arial", Font.PLAIN, 14));
        pn.add(pwf);
        
        
        
        btnEnreg = new JButton("S'ENREGISTRER");
        btnEnreg.setBounds(700,200,200,30);
        btnEnreg.setFont(new Font("Arial", Font.BOLD, 15));
        btnEnreg.setBackground(new Color(0,255,255));
        btnEnreg.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnregActionPerformed(evt);
            }
            
            private void btnEnregActionPerformed(ActionEvent evt) {
                
                EnregistrementEtudiant enr = new EnregistrementEtudiant(datab,userx,passx);
                enr.setVisible(true);
                dispose();
                
            } 
        });
        pn.add(btnEnreg);
    }
    
}
