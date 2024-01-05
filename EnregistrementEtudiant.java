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
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
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

public class EnregistrementEtudiant extends JFrame {
    
    JLabel lblTitre, lblIdentifiant, lblMdp, lblMat, lblNom, lblSexe, lblFiliere, lblNiveau, lblUe, lblImage, lblUe2, lblUet;
    JTextField txtIdentifiant, txtMdp, txtMat, txtNom;
    JComboBox comboSexe, comboFiliere, comboUe, comboImage, comboNiveau, comboUe2, comboUet;
    JCheckBox cbxL1, cbxL2, cbxL3, cbxM1, cbxM2, cbxD;
    JButton btnenregistrer, btnsupprimer, btnPrec;
    byte [] userimage = null;
    String path = null;
    Statement pst;
    
    
    public EnregistrementEtudiant(){
        
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
        
        //String Ni = comboNiveau.getSelectedItem().toString();
        //String Fi = comboFiliere.getSelectedItem().toString();
        
        lblTitre = new JLabel("ENREGISTREMENT COMPTE ETUDIANT");
        lblTitre.setBounds(300,10, 600, 30);
        lblTitre.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitre.setForeground(new Color(255,255,255));
        //lblTitre.setLocation(400, 225);
        pn.add(lblTitre);
        
        btnPrec = new JButton("<-     RETOUR");
        btnPrec.setBounds(370,420,150,30);
        btnPrec.setFont(new Font("Arial", Font.BOLD,14));
        btnPrec.setBackground(new Color(100,200,200));
        btnPrec.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrecActionPerformed(evt);
            }
            
            private void btnPrecActionPerformed(ActionEvent evt) {
                
                Etudiant ecr = new Etudiant();
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
        
        lblMat = new JLabel("MATRICULE");
        lblMat.setBounds(30,180,190,30);
        lblMat.setFont(new Font("Arial", Font.BOLD,16));
        lblMat.setForeground(new Color(255,255,255));
        pn.add(lblMat);
        
        txtMat = new JTextField();  
        txtMat.setBounds(200, 180, 325, 30);
        txtMat.setFont(new Font("Arial", Font.PLAIN, 14));
        pn.add(txtMat);
        
        lblNom = new JLabel("NOM ET PRENOM");
        lblNom.setBounds(30,220,190,30);
        lblNom.setFont(new Font("Arial", Font.BOLD,16));
        lblNom.setForeground(new Color(255,255,255));
        pn.add(lblNom);
        
        txtNom = new JTextField();  
        txtNom.setBounds(200, 220, 325, 30);
        txtNom.setFont(new Font("Arial", Font.PLAIN, 14));
        pn.add(txtNom);
        
        lblSexe = new JLabel("SEXE");
        lblSexe.setBounds(30, 260, 190, 30);
        lblSexe.setFont(new Font("Arial", Font.BOLD, 16));
        lblSexe.setForeground(new Color(255,255,255));
        pn.add(lblSexe);
        
        comboSexe = new JComboBox();
        comboSexe.setBounds(200,260,150,30);
        comboSexe.setFont(new Font("Arial", Font.PLAIN, 14));
        comboSexe.addItem("");
        comboSexe.addItem("Masculin");
        comboSexe.addItem("Feminin");
        pn.add(comboSexe);
        
        lblNiveau = new JLabel("NIVEAU");
        lblNiveau.setBounds(30,300,190,30);
        lblNiveau.setFont(new Font("Arial", Font.BOLD, 16));
        lblNiveau.setForeground(new Color(255,255,255));
        pn.add(lblNiveau);
        
        comboNiveau = new JComboBox();
        comboNiveau.setBounds(200,300,150,30);
        comboNiveau.setFont(new Font("Arial", Font.PLAIN, 14));
        comboNiveau.addItem("");
        comboNiveau.addItem("L1");
        comboNiveau.addItem("L2");
        comboNiveau.addItem("L3");
        //*/
        pn.add(comboNiveau);
        
        lblFiliere = new JLabel("FILIERE");
        lblFiliere.setBounds(30, 340, 190, 30);
        lblFiliere.setFont(new Font("Arial", Font.BOLD, 16));
        lblFiliere.setForeground(new Color(255,255,255));
        pn.add(lblFiliere);
        
        comboFiliere = new JComboBox();
        comboFiliere.setBounds(200,340,150,30);
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
        
        lblUe = new JLabel("UE OPTIONELLE 1");
        lblUe.setBounds(30,380,190,30);
        lblUe.setFont(new Font("Arial", Font.BOLD, 16));
        lblUe.setForeground(new Color(255,255,255));
        pn.add(lblUe);
        
        comboUe = new JComboBox();
        comboUe.setBounds(200,380,150,30);
        comboUe.setFont(new Font("Arial", Font.PLAIN, 14));
        String n = comboNiveau.getSelectedItem().toString();
        String f = comboFiliere.getSelectedItem().toString();
        //
        if(f.equals("INFORMATIQUE")){
            if(n.equals("L1")){
                comboUe.removeAllItems();
                comboUe.setSelectedItem(null);
                comboUe.addItem("");
                comboUe.addItem("INF151");
                comboUe.addItem("INF141");
                comboUe.addItem("PHY161");
                
            } else if (n.equals("L2")){
                comboUe.removeAllItems();
                comboUe.setSelectedItem(null);
                comboUe.addItem("");
                comboUe.addItem("INF251");
                comboUe.addItem("INF241");
                comboUe.addItem("INF261");
                
            } else if (n.equals("L3")){
                comboUe.removeAllItems();
                comboUe.setSelectedItem(null);
                comboUe.addItem("");
                comboUe.addItem("INF351");
                comboUe.addItem("INF341");
                comboUe.addItem("INF361");
                
            }
            
        } else if (f.equals("MATHEMATIQUES")){
            comboUe.removeAllItems();
            comboUe.setSelectedItem(null);
            comboUe.addItem("");
            comboUe.addItem("INF151");
            comboUe.addItem("INF141");
            comboUe.addItem("PHY161");                
        } else if (f.equals("PHYSIQUE")){
            comboUe.removeAllItems();
            comboUe.setSelectedItem(null);
            comboUe.addItem("");
            comboUe.addItem("INF151");
            comboUe.addItem("INF141");
            comboUe.addItem("PHY161");                
        } else if (f.equals("CHIMIE")){
            comboUe.removeAllItems();
            comboUe.setSelectedItem(null);
            comboUe.addItem("");
            comboUe.addItem("INF151");
            comboUe.addItem("INF141");
            comboUe.addItem("PHY161");                
        } else if (f.equals("GEOSCIENCES")){
            if(n.equals("L1")){
                comboUe.removeAllItems();
                comboUe.setSelectedItem(null);
                comboUe.addItem("");
                comboUe.addItem("BIO121");
                comboUe.addItem("MAT151");
                comboUe.addItem("INF111");
            } else if (n.equals("L2")){
                comboUe.removeAllItems();
                comboUe.setSelectedItem(null);
                comboUe.addItem("");
                comboUe.addItem("BIO221");
                comboUe.addItem("MAT251");
                comboUe.addItem("INF211");
            } else if (n.equals("L3")){
                comboUe.removeAllItems();
                comboUe.setSelectedItem(null);
                comboUe.addItem("");
                comboUe.addItem("BIO151");
                comboUe.addItem("MAT141");
                comboUe.addItem("INF161");
            }
                            
        } else if (f.equals("BIOSCIENCES")){
                            
        } else if (f.equals("ICT4D")){
                            
        }
        //*/
        pn.add(comboUe);
        
        lblUe2 = new JLabel("UE OPTIONELLE 2");
        lblUe2.setBounds(30,420,190,30);
        lblUe2.setFont(new Font("Arial", Font.BOLD, 16));
        lblUe2.setForeground(new Color(255,255,255));
        pn.add(lblUe2);
        
        comboUe2 = new JComboBox();
        comboUe2.setBounds(200,420,150,30);
        comboUe2.setFont(new Font("Arial", Font.PLAIN, 14));
        comboUe2.addItem("");
        
        //
        if(f.equals("INFORMATIQUE")){
            
            if(n.equals("L1")){
                comboUe2.removeAllItems();
                comboUe2.addItem("");
                comboUe2.addItem("INF252");
                comboUe2.addItem("INF242");
                comboUe2.addItem("INF262");
                           
            } else if (n.equals("L2")){
                comboUe2.removeAllItems();
                comboUe2.addItem("");
                comboUe2.addItem("INF252");
                comboUe2.addItem("INF242");
                comboUe2.addItem("INF262");
                            
            } else if (n.equals("L3")){
                comboUe2.removeAllItems();
                comboUe2.addItem("");
                comboUe2.addItem("INF252");
                comboUe2.addItem("INF242");
                comboUe2.addItem("INF262");
                            
            }
            
        } else if (f.equals("MATHEMATIQUES")){
            if(n.equals("L1")){
                 comboUe2.removeAllItems();
                comboUe2.addItem("");
                comboUe2.addItem("INF252");
                comboUe2.addItem("INF242");
                comboUe2.addItem("INF262");
                           
            } else if (n.equals("L2")){
                comboUe2.removeAllItems();
                comboUe2.addItem("");
                comboUe2.addItem("INF252");
                comboUe2.addItem("INF242");
                comboUe2.addItem("INF262");
                            
            } else if (n.equals("L3")){
                comboUe2.removeAllItems();
                comboUe2.addItem("");
                comboUe2.addItem("INF252");
                comboUe2.addItem("INF242");
                comboUe2.addItem("INF262");
                            
            }                
        } else if (f.equals("PHYSIQUE")){
            if(n.equals("L1")){
                comboUe2.removeAllItems();
                comboUe2.addItem("");
                comboUe2.addItem("INF252");
                comboUe2.addItem("INF242");
                comboUe2.addItem("INF262");
                            
            } else if (n.equals("L2")){
                comboUe2.removeAllItems();
                comboUe2.addItem("");
                comboUe2.addItem("INF252");
                comboUe2.addItem("INF242");
                comboUe2.addItem("INF262");
                            
            } else if (n.equals("L3")){
                comboUe2.removeAllItems();
                comboUe2.addItem("");
                comboUe2.addItem("INF252");
                comboUe2.addItem("INF242");
                comboUe2.addItem("INF262");
                            
            }                
        } else if (f.equals("CHIMIE")){
            if(n.equals("L1")){
                comboUe2.removeAllItems();
                comboUe2.addItem("");
                comboUe2.addItem("INF252");
                comboUe2.addItem("INF242");
                comboUe2.addItem("INF262");
                            
            } else if (n.equals("L2")){
                comboUe2.removeAllItems();
                comboUe2.addItem("");
                comboUe2.addItem("INF252");
                comboUe2.addItem("INF242");
                comboUe2.addItem("INF262");
                            
            } else if (n.equals("L3")){
                comboUe2.removeAllItems();
                comboUe2.addItem("");
                comboUe2.addItem("INF252");
                comboUe2.addItem("INF242");
                comboUe2.addItem("INF262");
                            
            }                
        } else if (f.equals("GEOSCIENCES")){
            if(n.equals("L1")){
                comboUe2.removeAllItems();
                comboUe2.addItem("");
                comboUe2.addItem("INF252");
                comboUe2.addItem("INF242");
                comboUe2.addItem("INF262");
                            
            } else if (n.equals("L2")){
                comboUe2.removeAllItems();
                comboUe2.addItem("");
                comboUe2.addItem("INF252");
                comboUe2.addItem("INF242");
                comboUe2.addItem("INF262");
                            
            } else if (n.equals("L3")){
                comboUe2.removeAllItems();
                comboUe2.addItem("");
                comboUe2.addItem("INF252");
                comboUe2.addItem("INF242");
                comboUe2.addItem("INF262");
                            
            }                
        } else if (f.equals("BIOSCIENCES")){
            if(n.equals("L1")){
                comboUe2.removeAllItems();
                comboUe2.addItem("");
                comboUe2.addItem("INF252");
                comboUe2.addItem("INF242");
                comboUe2.addItem("INF262");
                            
            } else if(n.equals("L2")){
                comboUe2.removeAllItems();
                comboUe2.addItem("");
                comboUe2.addItem("INF252");
                comboUe2.addItem("INF242");
                comboUe2.addItem("INF262");
                            
            } else if (n.equals("L3")){
                comboUe2.removeAllItems();
                comboUe2.addItem("");
                comboUe2.addItem("INF252");
                comboUe2.addItem("INF242");
                comboUe2.addItem("INF262");
                            
            }                
        } else if (f.equals("ICT4D")){
            if(n.equals("L1")){
                comboUe2.removeAllItems();
                comboUe2.addItem("");
                comboUe2.addItem("INF252");
                comboUe2.addItem("INF242");
                comboUe2.addItem("INF262");
                            
            } else if (n.equals("L2")){
                comboUe2.removeAllItems();
                comboUe2.addItem("");
                comboUe2.addItem("INF252");
                comboUe2.addItem("INF242");
                comboUe2.addItem("INF262");
                            
            } else if (n.equals("L3")){
                comboUe2.removeAllItems();
                comboUe2.addItem("");
                comboUe2.addItem("INF352");
                comboUe2.addItem("INF342");
                comboUe2.addItem("INF362");
                            
            }                
        }
        //*/
        pn.add(comboUe2);
        
        lblUet = new JLabel("UE TRANSVERSALE");
        lblUet.setBounds(30,460,190,30);
        lblUet.setFont(new Font("Arial", Font.BOLD, 15));
        lblUet.setForeground(new Color(255,255,255));
        pn.add(lblUet);
        
        comboUet = new JComboBox();
        comboUet.setBounds(200,460,150,30);
        comboUet.setFont(new Font("Arial", Font.PLAIN, 14));
        comboUet.addItem("");
        //
        if(n.equals("L1")){
            comboUet.addItem("ANG111");
            comboUet.addItem("FRA111");
        } else if (n.equals("L2")){
            comboUet.addItem("ANG211");
            comboUet.addItem("FRA211");            
        } else if (n.equals("L3")){
            comboUet.addItem("ANG311");
            comboUet.addItem("FRA311");            
        }
        //*/
        pn.add(comboUet);
        
        btnenregistrer = new JButton("ENREGISTRER");
        btnenregistrer.setBounds(370,300,150,30);
        btnenregistrer.setFont(new Font("Arial", Font.BOLD, 15));
        btnenregistrer.setForeground(Color.BLACK);
        btnenregistrer.setBackground(new Color(0,255,255));
        
        btnenregistrer.addActionListener(new ActionListener(){
            
            public void actionPerformed(ActionEvent ev){
                String Id, Mdp, Mat, Nom, Sexe, Fil, Niv, Ue, Ue2, Uet;
                
                Id = txtIdentifiant.getText();
                Mdp = txtMdp.getText();
                Mat = txtMat.getText();
                Nom = txtNom.getText();
                Sexe = comboSexe.getSelectedItem().toString();
                Fil = comboFiliere.getSelectedItem().toString();
                Niv = comboNiveau.getSelectedItem().toString();
                Ue = comboUe.getSelectedItem().toString();
                Ue2 = comboUe2.getSelectedItem().toString();
                Uet = comboUet.getSelectedItem().toString();
                
                Connect con = new Connect();
                
                try {
                    if(Fil=="INFORMATIQUE"){
                        if(Niv=="L1"){
                            
                            String rq0 = "insert into notes_etudiant(ue,matricule,nb_credit) values(?,?,?)";
                            PreparedStatement ps0 = con.maConnection().prepareStatement(rq0);
                            ps0.setString(1,"INF111");
                            ps0.setString(2, Mat);
                            ps0.setInt(3,6);
                            ps0.executeUpdate();
                            String rq1 = "insert into notes_etudiant(ue,matricule,nb_credit) values(?,?,?)";
                            PreparedStatement ps1 = con.maConnection().prepareStatement(rq1);
                            ps1.setString(1,"INF121");
                            ps1.setString(2, Mat);
                            ps1.setInt(3,6);
                            ps1.executeUpdate();
                            String rq2 = "insert into notes_etudiant(ue,matricule,nb_credit) values(?,?,?)";
                            PreparedStatement ps2 = con.maConnection().prepareStatement(rq2);
                            ps2.setString(1,"INF131");
                            ps2.setString(2, Mat);
                            ps2.setInt(3,6);
                            ps2.executeUpdate();
                            String rq3 = "insert into notes_etudiant(ue,matricule,nb_credit) values(?,?,?)";
                            PreparedStatement ps3 = con.maConnection().prepareStatement(rq3);
                            ps3.setString(1,"MAT131");
                            ps3.setString(2, Mat);
                            ps3.setInt(3,6);
                            ps3.executeUpdate();
                            String rq4 = "insert into notes_etudiant(ue,matricule,nb_credit) values(?,?,?)";
                            PreparedStatement ps4 = con.maConnection().prepareStatement(rq4);
                            ps4.setString(1,Ue);
                            ps4.setString(2, Mat);
                            ps4.setInt(3,3);
                            ps4.executeUpdate();
                            String rqt = "insert into notes_etudiant(ue,matricule,nb_credit) values(?,?,?)";
                            PreparedStatement pst = con.maConnection().prepareStatement(rqt);
                            pst.setString(1,Uet);
                            pst.setString(2, Mat);
                            pst.setInt(3,3);
                            pst.executeUpdate();
                            
                            String rq5 = "insert into notes_etudiant2(ue,matricule,nb_credit) values(?,?,?)";
                            PreparedStatement ps5 = con.maConnection().prepareStatement(rq5);
                            ps5.setString(1,"INF112");
                            ps5.setString(2, Mat);
                            ps5.setInt(3,6);
                            ps5.executeUpdate();
                            String rq6 = "insert into notes_etudiant2(ue,matricule,nb_credit) values(?,?,?)";
                            PreparedStatement ps6 = con.maConnection().prepareStatement(rq6);
                            ps6.setString(1,"INF122");
                            ps6.setString(2, Mat);
                            ps6.setInt(3,6);
                            ps6.executeUpdate();
                            String rq7 = "insert into notes_etudiant2(ue,matricule,nb_credit) values(?,?,?)";
                            PreparedStatement ps7 = con.maConnection().prepareStatement(rq7);
                            ps7.setString(1,"INF132");
                            ps7.setString(2, Mat);
                            ps7.setInt(3,6);
                            ps7.executeUpdate();
                            String rq8 = "insert into notes_etudiant2(ue,matricule,nb_credit) values(?,?,?)";
                            PreparedStatement ps8 = con.maConnection().prepareStatement(rq8);
                            ps8.setString(1,"MAT112");
                            ps8.setString(2, Mat);
                            ps8.setInt(3,6);
                            ps8.executeUpdate();
                            String rq9 = "insert into notes_etudiant2(ue,matricule,nb_credit) values(?,?,?)";
                            PreparedStatement ps9 = con.maConnection().prepareStatement(rq9);
                            ps9.setString(1,Ue2);
                            ps9.setString(2, Mat);
                            ps9.setInt(3,3);
                            ps9.executeUpdate();
                            String rq10 = "insert into notes_etudiant2(ue,matricule,nb_credit) values(?,?,?)";
                            PreparedStatement ps10 = con.maConnection().prepareStatement(rq10);
                            ps10.setString(1,"PPE112");
                            ps10.setString(2, Mat);
                            ps10.setInt(3,3);
                            ps10.executeUpdate();
                        } else if (Niv=="L2"){
                            String rq0 = "insert into notes_etudiant(ue,matricule,nb_credit) values(?,?,?)";
                            PreparedStatement ps0 = con.maConnection().prepareStatement(rq0);
                            ps0.setString(1,"INF211");
                            ps0.setString(2, Mat);
                            ps0.setInt(3,6);
                            ps0.executeUpdate();
                            String rq1 = "insert into notes_etudiant(ue,matricule,nb_credit) values(?,?,?)";
                            PreparedStatement ps1 = con.maConnection().prepareStatement(rq1);
                            ps1.setString(1,"INF221");
                            ps1.setString(2, Mat);
                            ps1.setInt(3,6);
                            ps1.executeUpdate();
                            String rq2 = "insert into notes_etudiant(ue,matricule,nb_credit) values(?,?,?)";
                            PreparedStatement ps2 = con.maConnection().prepareStatement(rq2);
                            ps2.setString(1,"INF231");
                            ps2.setString(2, Mat);
                            ps2.setInt(3,6);
                            ps2.executeUpdate();
                            String rq3 = "insert into notes_etudiant(ue,matricule,nb_credit) values(?,?,?)";
                            PreparedStatement ps3 = con.maConnection().prepareStatement(rq3);
                            ps3.setString(1,"MAT211");
                            ps3.setString(2, Mat);
                            ps3.setInt(3,6);
                            ps3.executeUpdate();
                            String rq4 = "insert into notes_etudiant(ue,matricule,nb_credit) values(?,?,?)";
                            PreparedStatement ps4 = con.maConnection().prepareStatement(rq4);
                            ps4.setString(1,Ue);
                            ps4.setString(2, Mat);
                            ps4.setInt(3,3);
                            ps4.executeUpdate();
                            String rqt = "insert into notes_etudiant(ue,matricule,nb_credit) values(?,?,?)";
                            PreparedStatement pst = con.maConnection().prepareStatement(rqt);
                            pst.setString(1,Uet);
                            pst.setString(2, Mat);
                            pst.setInt(3,3);
                            pst.executeUpdate();
                            
                            String rq5 = "insert into notes_etudiant2(ue,matricule,nb_credit) values(?,?,?)";
                            PreparedStatement ps5 = con.maConnection().prepareStatement(rq5);
                            ps5.setString(1,"INF212");
                            ps5.setString(2, Mat);
                            ps5.setInt(3,6);
                            ps5.executeUpdate();
                            String rq6 = "insert into notes_etudiant2(ue,matricule,nb_credit) values(?,?,?)";
                            PreparedStatement ps6 = con.maConnection().prepareStatement(rq6);
                            ps6.setString(1,"INF222");
                            ps6.setString(2, Mat);
                            ps6.setInt(3,6);
                            ps6.executeUpdate();
                            String rq7 = "insert into notes_etudiant2(ue,matricule,nb_credit) values(?,?,?)";
                            PreparedStatement ps7 = con.maConnection().prepareStatement(rq7);
                            ps7.setString(1,"INF232");
                            ps7.setString(2, Mat);
                            ps7.setInt(3,6);
                            ps7.executeUpdate();
                            String rq8 = "insert into notes_etudiant2(ue,matricule,nb_credit) values(?,?,?)";
                            PreparedStatement ps8 = con.maConnection().prepareStatement(rq8);
                            ps8.setString(1,"MAT212");
                            ps8.setString(2, Mat);
                            ps8.setInt(3,6);
                            ps8.executeUpdate();
                            String rq9 = "insert into notes_etudiant2(ue,matricule,nb_credit) values(?,?,?)";
                            PreparedStatement ps9 = con.maConnection().prepareStatement(rq9);
                            ps9.setString(1,Ue2);
                            ps9.setString(2, Mat);
                            ps9.setInt(3,3);
                            ps9.executeUpdate();
                            String rq10 = "insert into notes_etudiant2(ue,matricule,nb_credit) values(?,?,?)";
                            PreparedStatement ps10 = con.maConnection().prepareStatement(rq10);
                            ps10.setString(1,"PPE212");
                            ps10.setString(2, Mat);
                            ps10.setInt(3,3);
                            ps10.executeUpdate();
                        } else if (Niv=="L3"){
                            String rq0 = "insert into notes_etudiant(ue,matricule,nb_credit) values(?,?,?)";
                            PreparedStatement ps0 = con.maConnection().prepareStatement(rq0);
                            ps0.setString(1,"INF311");
                            ps0.setString(2, Mat);
                            ps0.setInt(3,6);
                            ps0.executeUpdate();
                            String rq1 = "insert into notes_etudiant(ue,matricule,nb_credit) values(?,?,?)";
                            PreparedStatement ps1 = con.maConnection().prepareStatement(rq1);
                            ps1.setString(1,"INF321");
                            ps1.setString(2, Mat);
                            ps1.setInt(3,6);
                            ps1.executeUpdate();
                            String rq2 = "insert into notes_etudiant(ue,matricule,nb_credit) values(?,?,?)";
                            PreparedStatement ps2 = con.maConnection().prepareStatement(rq2);
                            ps2.setString(1,"INF331");
                            ps2.setString(2, Mat);
                            ps2.setInt(3,6);
                            ps2.executeUpdate();
                            String rq3 = "insert into notes_etudiant(ue,matricule,nb_credit) values(?,?,?)";
                            PreparedStatement ps3 = con.maConnection().prepareStatement(rq3);
                            ps3.setString(1,"INF341");
                            ps3.setString(2, Mat);
                            ps3.setInt(3,6);
                            ps3.executeUpdate();
                            String rq4 = "insert into notes_etudiant(ue,matricule,nb_credit) values(?,?,?)";
                            PreparedStatement ps4 = con.maConnection().prepareStatement(rq4);
                            ps4.setString(1,Ue);
                            ps4.setString(2, Mat);
                            ps4.setInt(3,3);
                            ps4.executeUpdate();
                            String rqt = "insert into notes_etudiant(ue,matricule,nb_credit) values(?,?,?)";
                            PreparedStatement pst = con.maConnection().prepareStatement(rqt);
                            pst.setString(1,Uet);
                            pst.setString(2, Mat);
                            pst.setInt(3,3);
                            pst.executeUpdate();
                            
                            String rq5 = "insert into notes_etudiant2(ue,matricule,nb_credit) values(?,?,?)";
                            PreparedStatement ps5 = con.maConnection().prepareStatement(rq5);
                            ps5.setString(1,"INF112");
                            ps5.setString(2, Mat);
                            ps5.setInt(3,6);
                            ps5.executeUpdate();
                            String rq6 = "insert into notes_etudiant2(ue,matricule,nb_credit) values(?,?,?)";
                            PreparedStatement ps6 = con.maConnection().prepareStatement(rq6);
                            ps6.setString(1,"INF122");
                            ps6.setString(2, Mat);
                            ps6.setInt(3,6);
                            ps6.executeUpdate();
                            String rq7 = "insert into notes_etudiant2(ue,matricule,nb_credit) values(?,?,?)";
                            PreparedStatement ps7 = con.maConnection().prepareStatement(rq7);
                            ps7.setString(1,"INF132");
                            ps7.setString(2, Mat);
                            ps7.setInt(3,6);
                            ps7.executeUpdate();
                            String rq8 = "insert into notes_etudiant2(ue,matricule,nb_credit) values(?,?,?)";
                            PreparedStatement ps8 = con.maConnection().prepareStatement(rq8);
                            ps8.setString(1,"MAT112");
                            ps8.setString(2, Mat);
                            ps8.setInt(3,6);
                            ps8.executeUpdate();
                            String rq9 = "insert into notes_etudiant2(ue,matricule,nb_credit) values(?,?,?)";
                            PreparedStatement ps9 = con.maConnection().prepareStatement(rq9);
                            ps9.setString(1,Ue2);
                            ps9.setString(2, Mat);
                            ps9.setInt(3,3);
                            ps9.executeUpdate();
                            String rq10 = "insert into notes_etudiant2(ue,matricule,nb_credit) values(?,?,?)";
                            PreparedStatement ps10 = con.maConnection().prepareStatement(rq10);
                            ps10.setString(1,"PPE112");
                            ps10.setString(2, Mat);
                            ps10.setInt(3,3);
                            ps10.executeUpdate();
                        }
                        
                    } else if (Fil=="MATHEMATIQUES"){
                        
                        if(Niv=="L1"){
                            
                            
                            
                        } else if (Niv=="L2"){
                            
                            
                            
                        } else if (Niv=="L3"){
                            
                        }
                        
                    } else if (Fil=="PHYSIQUE"){
                        
                        if(Niv=="L1"){
                            
                            
                            
                        } else if (Niv=="L2"){
                            
                        } else if (Niv=="L3"){
                            
                        }
                        
                    } else if (Fil=="GEOSCIENCES"){
                        
                        if(Niv=="L1"){
                            
                            
                            
                        } else if (Niv=="L2"){
                            
                        } else if (Niv=="L3"){
                            
                        }
                        
                    } else if (Fil=="BIOSCIENCES"){
                        
                        if(Niv=="L1"){
                            
                            
                            
                        } else if (Niv=="L2"){
                            
                        } else if (Niv=="L3"){
                            
                        }
                        
                    } else if (Fil=="CHIMIE"){
                        
                        if(Niv=="L1"){
                            
                            
                            
                        } else if (Niv=="L2"){
                            
                        } else if (Niv=="L3"){
                            
                        }
                        
                    } else if (Fil=="ICT4D"){
                        
                        if(Niv=="L1"){
                            
                            
                            
                        } else if (Niv=="L2"){
                            
                        } else if (Niv=="L3"){
                            
                        }
                        
                    } 
                    String rq = "insert into tb_etudiant(identifiant,mdp,matricule,nom,sexe,filiere,niveau,ue,ue2,uet,photo) values(?,?,?,?,?,?,?,?,?,?,?)";
                    PreparedStatement ps = con.maConnection().prepareStatement(rq);
                    ps.setString(1, Id);
                    ps.setString(2, Mdp);
                    ps.setString(3, Mat);
                    ps.setString(4, Nom);
                    ps.setString(5, Sexe);
                    ps.setString(6, Fil);
                    ps.setString(7, Niv);
                    ps.setString(8, Ue);
                    ps.setString(9, Ue2);
                    ps.setString(10, Uet);
                    ps.setBytes(11, userimage);
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Compte Etudiant enregistrer!",null,JOptionPane.INFORMATION_MESSAGE);
                    con.maConnection().close();
                } catch (SQLException ex){
                    JOptionPane.showMessageDialog(null,"Ereur!"+ex.getMessage(),null,JOptionPane.ERROR_MESSAGE);
                }
                dispose();
                EnregistrementEtudiant etd = new EnregistrementEtudiant();
                etd.setVisible(true);
                
            }

        });
        pn.add(btnenregistrer);         

        //BoutonSupprimer
        btnsupprimer = new JButton("SUPPRIMER");
        btnsupprimer.setBounds(370,260,150,30);
        btnsupprimer.setFont(new Font("Arial", Font.BOLD, 15));
        btnsupprimer.setForeground(Color.BLACK);
        btnsupprimer.setBackground(new Color(0,255,255));
        btnsupprimer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                String Id, Mdp, Mat, Nom, Sexe, Fil, Niv, Ue;
                Connect con = new Connect();
                Id = txtIdentifiant.getText();
                Mat = txtMat.getText();
                
                try {
                    
                    String rq0= "delete from notes_etudiant where matricule ='" + Mat + "'";
                    String rq = "delete from tb_etudiant where identifiant ='" + Id + "'";
                    pst = con.maConnection().createStatement();
                    pst.executeUpdate(rq);
                    pst.executeUpdate(rq0);
                    JOptionPane.showMessageDialog(null, "Compte Etudiant Supprimer!", null, JOptionPane.INFORMATION_MESSAGE);
                    con.maConnection().close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erreur!" + ex.getMessage(), null, JOptionPane.ERROR_MESSAGE);
                }
                    
                dispose();
                EnregistrementEtudiant etd = new EnregistrementEtudiant();
                etd.setVisible(true);
                
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
        
        EnregistrementEtudiant en = new EnregistrementEtudiant();
        en.setVisible(true);
        
    }
    
}
