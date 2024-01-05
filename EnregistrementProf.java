package Etudiant;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EnregistrementProf extends JFrame {
    
    JLabel lblTitre, lblIdentifiant, lblMdp, lblNom, lblSexe, lblFiliere, lblNiveau, lblUe, lblImage;
    JTextField txtIdentifiant, txtMdp, txtNom;
    JComboBox comboSexe, comboFiliere, comboUe, comboImage, comboNiveau;
    JCheckBox cbxL1, cbxL2, cbxL3, cbxM1, cbxM2, cbxD;
    JButton btnenregistrer, btnsupprimer, btnPrec, btnVoir;
    byte [] userimage = null;
    String path = null;
    Statement pst;
    ResultSet rs;
    
    public EnregistrementProf(String prof){
        
        super.setTitle("SuperProf");
        super.setSize(1000, 600);
        super.setLocationRelativeTo(null);
        super.setResizable(true);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel pn = new JPanel();
        getContentPane().add(pn);
        pn.setLayout(null);
        add(pn);
        pn.setBackground(new Color(50,50,100));
        
        
        lblTitre = new JLabel("ENREGISTREMENT COMPTE PROFESSEUR");
        lblTitre.setBounds(300,10, 600, 30);
        lblTitre.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitre.setForeground(new Color(255,255,255));
        //lblTitre.setLocation(400, 225);
        pn.add(lblTitre);
        
        btnPrec = new JButton("<-     RETOUR");
        btnPrec.setBounds(200,420,150,30);
        btnPrec.setFont(new Font("Arial", Font.BOLD,14));
        btnPrec.setForeground(new Color(255,255,255));
        btnPrec.setBackground(new Color(100,200,200));
        btnPrec.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrecActionPerformed(evt);
            }
            
            private void btnPrecActionPerformed(ActionEvent evt) {
                
                Prof ecr = new Prof();
                ecr.setVisible(true);
                dispose();
                
            } 
        });
        pn.add(btnPrec);
        
        lblIdentifiant = new JLabel("IDENTIFIANT");
        lblIdentifiant.setBounds(30,100,190,30);
        lblIdentifiant.setFont(new Font("Arial", Font.BOLD,16));
        lblIdentifiant.setForeground(new Color(255,255,255));
        pn.add(lblIdentifiant);
        
        txtIdentifiant = new JTextField();  
        txtIdentifiant.setText(prof);
        txtIdentifiant.setBounds(200, 100, 325, 30);
        txtIdentifiant.setFont(new Font("Arial", Font.PLAIN, 14));
        pn.add(txtIdentifiant);
        
        lblMdp = new JLabel("MOT DE PASSE");
        lblMdp.setBounds(30,140,190,30);
        lblMdp.setFont(new Font("Arial", Font.BOLD,16));
        lblMdp.setForeground(new Color(255,255,255));
        pn.add(lblMdp);
        
        txtMdp = new JTextField();  
        txtMdp.setBounds(200, 140, 325, 30);
        txtMdp.setFont(new Font("Arial", Font.PLAIN, 14));
        pn.add(txtMdp);
        
        lblNom = new JLabel("NOM ET PRENOM");
        lblNom.setBounds(30,180,190,30);
        lblNom.setFont(new Font("Arial", Font.BOLD,16));
        lblNom.setForeground(new Color(255,255,255));
        pn.add(lblNom);
        
        txtNom = new JTextField();  
        txtNom.setBounds(200, 180, 325, 30);
        txtNom.setFont(new Font("Arial", Font.PLAIN, 14));
        pn.add(txtNom);
        
        lblSexe = new JLabel("SEXE");
        lblSexe.setBounds(30, 220, 190, 30);
        lblSexe.setFont(new Font("Arial", Font.BOLD, 16));
        lblSexe.setForeground(new Color(255,255,255));
        pn.add(lblSexe);
        
        comboSexe = new JComboBox();
        comboSexe.setBounds(200,220,100,30);
        comboSexe.setFont(new Font("Arial", Font.PLAIN, 14));
        comboSexe.addItem("");
        comboSexe.addItem("Masculin");
        comboSexe.addItem("Feminin");
        pn.add(comboSexe);
        
        lblNiveau = new JLabel("NIVEAU");
        lblNiveau.setBounds(30,260,190,30);
        lblNiveau.setFont(new Font("Arial", Font.BOLD, 16));
        lblNiveau.setForeground(new Color(255,255,255));
        pn.add(lblNiveau);
        
        comboNiveau = new JComboBox();
        comboNiveau.setBounds(200,260,100,30);
        comboNiveau.setFont(new Font("Arial", Font.PLAIN, 14));
        comboNiveau.addItem("");
        comboNiveau.addItem("L1");
        comboNiveau.addItem("L2");
        comboNiveau.addItem("L3");
        pn.add(comboNiveau);
        
        lblFiliere = new JLabel("FILIERE");
        lblFiliere.setBounds(30, 300, 190, 30);
        lblFiliere.setFont(new Font("Arial", Font.BOLD, 16));
        lblFiliere.setForeground(new Color(255,255,255));
        pn.add(lblFiliere);
        
        comboFiliere = new JComboBox();
        comboFiliere.setBounds(200,300,100,30);
        comboFiliere.setFont(new Font("Arial", Font.PLAIN, 14));
        comboFiliere.addItem("");
        comboFiliere.addItem("INFORMATIQUE");
        comboFiliere.addItem("MATHEMATIQUES");
        comboFiliere.addItem("PHYSIQUE");
        comboFiliere.addItem("CHIMIE");
        comboFiliere.addItem("BIOSCIENCES");
        comboFiliere.addItem("GEOSCIENCES");
        comboFiliere.addItem("ICT4D");
        pn.add(comboFiliere);
        
        lblUe = new JLabel("UE ENSEIGNEE");
        lblUe.setBounds(30,340,190,30);
        lblUe.setFont(new Font("Arial", Font.BOLD, 16));
        lblUe.setForeground(new Color(255,255,255));
        pn.add(lblUe);
        
        comboUe = new JComboBox();
        comboUe.setBounds(200,340,100,30);
        comboUe.setFont(new Font("Arial", Font.PLAIN, 14));
        comboUe.addItem("");
        comboUe.addItem("INF111");
        comboUe.addItem("INF121");
        comboUe.addItem("INF131");
        comboUe.addItem("INF141");
        comboUe.addItem("INF151");
        comboUe.addItem("PHY161");
        comboUe.addItem("MAT131");
        comboUe.addItem("ANG111");
        comboUe.addItem("FRA111");
        comboUe.addItem("INF112");
        comboUe.addItem("INF122");
        comboUe.addItem("INF132");
        comboUe.addItem("INF142");
        comboUe.addItem("INF152");
        comboUe.addItem("PHY162");
        comboUe.addItem("MAT112");
        comboUe.addItem("PPE112");
        pn.add(comboUe);
        
        btnenregistrer = new JButton("ENREGISTRER");
        btnenregistrer.setBounds(200,380,150,30);
        btnenregistrer.setFont(new Font("Arial", Font.BOLD, 15));
        btnenregistrer.setForeground(Color.BLACK);
        btnenregistrer.setBackground(new Color(0,255,255));
        
        btnenregistrer.addActionListener(new ActionListener(){
            
            public void actionPerformed(ActionEvent ev){
                if(txtIdentifiant.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Veuillez entrez un identifiant", null, JOptionPane.ERROR_MESSAGE);
                } else {
                    String Id, Mdp, Nom, Sexe, Fil, Niv, Ue;
                    try {
                        Connect con = new Connect();
                        Id = txtIdentifiant.getText();
                        String rq = "delete from tb_prof where identifiant ='" + Id + "'";
                        pst = con.maConnection().createStatement();
                        pst.executeUpdate(rq);
                        con.maConnection().close();
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Erreur!" + ex.getMessage(), null, JOptionPane.ERROR_MESSAGE);
                    }
                    if((txtMdp.getText().equals(""))||(txtNom.getText().equals(""))||(comboSexe.getSelectedItem().equals(""))||(comboFiliere.getSelectedItem().equals(""))||(comboNiveau.getSelectedItem().equals(""))||(comboUe.getSelectedItem().equals(""))){
                        JOptionPane.showMessageDialog(null, "Veuillez toutes vos informations", null, JOptionPane.ERROR_MESSAGE);
                    } else {
                        Id = txtIdentifiant.getText();
                        Mdp = txtMdp.getText();
                        Nom = txtNom.getText();
                        Sexe = comboSexe.getSelectedItem().toString();
                        Fil = comboFiliere.getSelectedItem().toString();
                        Niv = comboNiveau.getSelectedItem().toString();
                        Ue = comboUe.getSelectedItem().toString();
                    
                        try {
                            Connect con = new Connect();
                            String rqq = "insert into tb_prof(identifiant,mdp,nom,sexe,filiere,niveau,ue,photo) values(?,?,?,?,?,?,?,?)";
                            PreparedStatement ps = con.maConnection().prepareStatement(rqq);
                            ps.setString(1, Id);
                            ps.setString(2, Mdp);
                            ps.setString(3, Nom);
                            ps.setString(4, Sexe);
                            ps.setString(5, Fil);
                            ps.setString(6, Niv);
                            ps.setString(7, Ue);
                            ps.setBytes(8, userimage);
                            ps.executeUpdate();
                            JOptionPane.showMessageDialog(null,"Compte Professeur enregistrer!",null,JOptionPane.INFORMATION_MESSAGE);
                            con.maConnection().close();
                        } catch (SQLException ex){
                            JOptionPane.showMessageDialog(null,"Ereur!"+ex.getMessage(),null,JOptionPane.ERROR_MESSAGE);
                        }   
                        dispose();
                        EnregistrementProf etd = new EnregistrementProf(prof);
                        etd.setVisible(true);
                    }
                }
                
            }

        });
        pn.add(btnenregistrer);    
        
        btnVoir = new JButton("OBSERVER");
        btnVoir.setBounds(370,420,150,30);
        btnVoir.setFont(new Font("Arial", Font.BOLD, 15));
        btnVoir.setForeground(Color.BLACK);
        btnVoir.setBackground(new Color(0,255,255));
        btnVoir.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoirActionPerformed(evt);
            }
            
            private void btnVoirActionPerformed(ActionEvent evt) {
                if(txtIdentifiant.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Veuillez entrez un identifiant", null, JOptionPane.ERROR_MESSAGE);
                } else {
                    try{
                        Connect con = new Connect();
                        String rq = "Select * from tb_prof where identifiant =?";
                        PreparedStatement ps = con.maConnection().prepareStatement(rq);
                        ps.setString(1,txtIdentifiant.getText());
                        rs = ps .executeQuery();
                        if (rs.next() == false) {
                        
                            JOptionPane.showMessageDialog(null,"Identifiant n'existe pas",null, JOptionPane.ERROR_MESSAGE);
                            txtIdentifiant.setText("");
                        } else {
                            txtMdp.setText(rs.getString(2).trim());
                            txtNom.setText(rs.getString(3).trim());
                            comboSexe.setSelectedItem(rs.getString(4).trim());
                            comboFiliere.setSelectedItem(rs.getString(5).trim());
                            comboNiveau.setSelectedItem(rs.getString(6).trim());
                            comboUe.setSelectedItem(rs.getString(7).trim());
                            try{
                                Blob blob1 = rs.getBlob("photo");
                                byte[] imagebyte = blob1.getBytes(1, (int) blob1.length());
                                ImageIcon imag = new ImageIcon(new ImageIcon(imagebyte).getImage().getScaledInstance(350, 350,Image.SCALE_DEFAULT));
                                lblImage.setIcon(imag);
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(null,"Erreur!"+e.getMessage(), null, JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    } catch (SQLException ex){
                        ex.printStackTrace();
                    }
                    
                }
            }
                
        });
        pn.add(btnVoir);
        

        //BoutonSupprimer
        btnsupprimer = new JButton("SUPPRIMER");
        btnsupprimer.setBounds(370,380,150,30);
        btnsupprimer.setFont(new Font("Arial", Font.BOLD, 15));
        btnsupprimer.setForeground(Color.BLACK);
        btnsupprimer.setBackground(new Color(0,255,255));
        btnsupprimer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                if(txtIdentifiant.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Veuillez entrez un identifiant", null, JOptionPane.ERROR_MESSAGE);
                } else {
                    String Id, Mdp, Nom, Sexe, Fil, Niv, Ue;
                    Connect con = new Connect();
                    Id = txtIdentifiant.getText();
                    String rq = "delete from tb_prof where identifiant ='" + Id + "'";
                    try {
                        pst = con.maConnection().createStatement();
                        pst.executeUpdate(rq);
                        JOptionPane.showMessageDialog(null, "Compte Professur Supprimer!", null, JOptionPane.INFORMATION_MESSAGE);
                        con.maConnection().close();
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Erreur!" + ex.getMessage(), null, JOptionPane.ERROR_MESSAGE);
                    }
                    
                    dispose();
                    EnregistrementProf etd = new EnregistrementProf(prof);
                    etd.setVisible(true);
                }
                
            }
            
        });
        pn.add(btnsupprimer);
        
        lblImage = new JLabel();
        lblImage.setBounds(530, 100, 350, 350);
        lblImage.setFont(new Font("Arial", Font.BOLD, 16));
        lblImage.setBackground(new java.awt.Color(255,0,0));
        //image1.setFont(new java.awt.Font("Yu Gothic Light", 0, 18));
        lblImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER); 
        lblImage.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblImage.addMouseListener(new java.awt.event.MouseAdapter() {
           public void mouseClicked(java.awt.event.MouseEvent evt) {
               lblImageMouseClicked(evt);
           } 
           
           private void lblImageMouseClicked(MouseEvent evt) {
               JFileChooser pic = new JFileChooser();
               pic.showOpenDialog(null);
               
               File picture = pic.getSelectedFile();
               
               path = picture.getAbsolutePath();
               BufferedImage img;
               try {
                   img = ImageIO.read(pic.getSelectedFile());
                   ImageIcon imageic  = new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(350,350, Image.SCALE_DEFAULT));
                   lblImage.setIcon(imageic);
                   File image = new File(path);
                   FileInputStream fis = new FileInputStream(image);
                   ByteArrayOutputStream boss = new ByteArrayOutputStream();
                   byte[] buff = new byte[1024];
                   
                   for (int i; (i = fis.read(buff)) != -1;) {
                       boss.write(buff, 0, i);
                   }
                   
                   userimage = boss.toByteArray();
               } catch (Exception e) {
                   e.printStackTrace();
               }
           }
        });
        
        pn.add(lblImage);
        
    }
    
    public static void main(String[] args){
        
        EnregistrementProf en = new EnregistrementProf("");
        en.setVisible(true);
        
    }
    
}
