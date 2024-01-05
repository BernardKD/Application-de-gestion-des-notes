package Etudiant;

import ConnecProf.ConnecProf;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.sql.Statement;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ProfConnect extends JFrame {
    
    Connect con = new Connect();
    JLabel lblTitre, lblCc, lblTp, lblSn, lblMat, lblInf1, lblInf2, lblInf3, lblLogo;
    JButton btnEnregistrer, btnPrec, btnSup, btnAjouEtud, btnMod;
    JTextField txtMat, txtCc, txtTp, txtSn;
    JTable table, table1;
    JScrollPane scroll, scroll1;
    boolean note = false;
    float T=0;
    ResultSet rs, rs1;
    Statement pst;
    
    public ProfConnect(String prof, String ue){
        
        super.setTitle("SuperProf");
        super.setSize(1100, 600);
        super.setLocationRelativeTo(null);
        super.setResizable(true);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel pn = new JPanel();
        getContentPane().add(pn);
        pn.setLayout(null);
        add(pn);
        pn.setBackground(new Color(100,50,100));
        lblTitre = new JLabel("Bienvenue "+prof);
        lblTitre.setBounds(350,10, 450, 30);
        lblTitre.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitre.setForeground(new Color(255,255,255));
        //lblTitre.setLocation(400, 225);
        pn.add(lblTitre);
        
        ImageIcon i  = new ImageIcon(new ImageIcon("pubg1.jpg").getImage().getScaledInstance(250,250, Image.SCALE_DEFAULT));
        JLabel lblLogo = new JLabel(i);
        lblLogo.setBounds(800,60, 250,250);
        lblLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLogo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pn.add(lblLogo);
        
        try {
            Connect con = new Connect();
            String rq = "Select * from tb_prof where identifiant=?";
            PreparedStatement ps = con.maConnection().prepareStatement(rq);
            ps.setString(1,prof);
            rs = ps .executeQuery();
            if(rs.next()==false){
                JOptionPane.showMessageDialog(null,"Enregistrement de "+prof+" incomplet",null, JOptionPane.ERROR_MESSAGE);
                lblLogo.setIcon(null);
            } else{
                try{
                    Blob blob1 = rs.getBlob("photo");
                    byte[] imagebyte = blob1.getBytes(1, (int) blob1.length());
                    ImageIcon imag = new ImageIcon(new ImageIcon(imagebyte).getImage().getScaledInstance(250, 250,Image.SCALE_DEFAULT));
                    lblLogo.setIcon(imag);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null,"Erreur!"+e.getMessage(), null, JOptionPane.ERROR_MESSAGE);
                }
            }
            con.maConnection().close();
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null,"Erreuruuu!"+ex.getMessage(), null, JOptionPane.ERROR_MESSAGE);
        }
        
        lblInf1 = new JLabel("Veuillez entrer 0 si l'UE ne comporte pas de TP");
        lblInf1.setBounds(295,100,220,30);
        lblInf1.setFont(new Font("Arial", Font.BOLD,8));
        lblInf1.setForeground(new Color(0,0,0));
        pn.add(lblInf1);
        
        lblInf2 = new JLabel("La note de CC sera sur 30 et celle de SN sur 70");
        lblInf2.setBounds(295,140,220,30);
        lblInf2.setFont(new Font("Arial", Font.BOLD,8));
        lblInf2.setForeground(new Color(0,0,0));
        pn.add(lblInf2);
        
        lblInf3 = new JLabel("dans le cas contraire alors CC/20, TP/30, SN/50");
        lblInf3.setBounds(295,180,220,30);
        lblInf3.setFont(new Font("Arial", Font.BOLD,8));
        lblInf3.setForeground(new Color(0,0,0));
        pn.add(lblInf3);
        
        lblMat = new JLabel("MATRICULE");
        lblMat.setBounds(30,60,180,30);
        lblMat.setFont(new Font("Arial", Font.BOLD,16));
        lblMat.setForeground(new Color(255,255,255));
        pn.add(lblMat);
        
        txtMat = new JTextField();  
        txtMat.setBounds(180, 60, 335, 30);
        txtMat.setFont(new Font("Arial", Font.PLAIN, 14));
        pn.add(txtMat);
        
        lblCc = new JLabel("NOTE DE CC");
        lblCc.setBounds(30,100,150,30);
        lblCc.setFont(new Font("Arial", Font.BOLD, 16));
        lblCc.setForeground(new Color(255,255,255));
        pn.add(lblCc);
        
        txtCc = new JTextField();
        txtCc.setBounds(180,100,110,30);
        txtCc.setFont(new Font("Arial", Font.PLAIN, 14));
        pn.add(txtCc);
        
        lblTp = new JLabel("NOTE DE TP");
        lblTp.setBounds(30,140,150,30);
        lblTp.setFont(new Font("Arial", Font.BOLD, 16));
        lblTp.setForeground(new Color(255,255,255));
        pn.add(lblTp);
        
        txtTp = new JTextField();
        txtTp.setBounds(180,140,110,30);
        txtTp.setFont(new Font("Arial", Font.PLAIN, 14));
        pn.add(txtTp);
        
        lblSn = new JLabel("NOTE DE SN");
        lblSn.setBounds(30,180,150,30);
        lblSn.setFont(new Font("Arial", Font.BOLD, 16));
        lblSn.setForeground(new Color(255,255,255));
        pn.add(lblSn);
        
        txtSn = new JTextField();
        txtSn.setBounds(180,180,110,30);
        txtSn.setFont(new Font("Arial", Font.PLAIN, 14));
        pn.add(txtSn);
        
        btnEnregistrer = new JButton("MISE A JOUR");
        btnEnregistrer.setBounds(520,60,260,30);
        btnEnregistrer.setFont(new Font("Arial", Font.BOLD, 15));
        btnEnregistrer.setForeground(Color.BLACK);
        btnEnregistrer.setBackground(new Color(50,205,205));
        btnEnregistrer.addActionListener(new java.awt.event.ActionListener(){
            
            public void actionPerformed(java.awt.event.ActionEvent evt){
                btnEnregistrerActionPerformed(evt);
            }
            private void btnEnregistrerActionPerformed(ActionEvent evt) {
                
                String Mat;
                String Ue = ue;
                String Ue0;
                String Mat0;
                float Q=0;
                while((txtMat==null)||(txtCc==null)||(txtTp==null)||(txtSn==null)){
                    JOptionPane.showMessageDialog(null,"Veuillez entrer toutes les informations requises",null, JOptionPane.ERROR_MESSAGE);
                } 
                Mat = txtMat.getText();
                float Cc = (Float) Float.parseFloat(txtCc.getText());
                float Tp = (Float) Float.parseFloat(txtTp.getText());
                float Sn = (Float) Float.parseFloat(txtSn.getText());
                
                if(note==true){
                     T = (Cc*20+Tp*30+Sn*50)/100;
                } else {
                     T = Cc+Tp+Sn;
                }
                if((T>=0)&&(T<35)){
                    Q=0;
                } else if((T>=35)&&(T<40)) {
                    Q=1;
                } else if((T>=40)&&(T<45)) {
                    Q=(Float) Float.parseFloat("1.30");
                } else if((T>=45)&&(T<50)) {
                    Q=(Float) Float.parseFloat("1.70");
                } else if((T>=50)&&(T<55)) {
                    Q=2;
                } else if((T>=55)&&(T<60)) {
                    Q=(Float) Float.parseFloat("2.30");
                } else if((T>=60)&&(T<65)) {
                    Q=(Float) Float.parseFloat("2.70");
                } else if((T>=65)&&(T<70)) {
                    Q=3;
                } else if((T>=70)&&(T<75)) {
                    Q=(Float) Float.parseFloat("3.30");
                } else if((T>=75)&&(T<80)) {
                    Q=(Float) Float.parseFloat("3.70");
                } else if((T>=80)&&(T<=100)) {
                    Q=4;
                }
                Connect con = new Connect();
                
                try{
                    String rq0 = "Select * from notes_etudiant where ue =?";
                    PreparedStatement ps0 = con.maConnection().prepareStatement(rq0);
                    ps0.setString(1, Ue);
                    rs = ps0 .executeQuery();
                    if (rs.next() == false) {
                        
                        JOptionPane.showMessageDialog(null,"Aucun eleve enregistrer pour "+Ue,null, JOptionPane.ERROR_MESSAGE);
                        con.maConnection().close();
                    } else {
                        
                            Mat0 = rs.getString(2).trim();
                        
                            if(Mat.equals(Mat0)){
                                int Nc = rs.getInt(8);
                                float Pa = Q*Nc;
                                String rq = "update notes_etudiant set cc=?, tp=?, sn=?, total=?, qualite=?, points_acc=? where ((matricule = ?)and(ue=?))";
                                PreparedStatement ps = con.maConnection().prepareStatement(rq);
                                if((Cc>=0&&Cc<=20)&&(Tp>=0&&Tp<=30)&&(Sn>=0&&Sn<=50)){
                                    ps.setFloat(1,Cc);
                                    ps.setFloat(2, Tp);
                                    ps.setFloat(3, Sn);
                                    ps.setFloat(4, T);
                                    ps.setFloat(5, Q);
                                    ps.setFloat(6, Pa);
                                    ps.setString(7, Mat);
                                    ps.setString(8, Ue);
                                    ps.executeUpdate();
                                    JOptionPane.showMessageDialog(null,"Notes enregistrers!",null,JOptionPane.INFORMATION_MESSAGE);
                                    con.maConnection().close();
                                    ProfConnect en = new ProfConnect(prof,ue);
                                    en.setVisible(true);
                                    dispose();
                                } else if ((Cc>=0&&Cc<=30)&&(Tp==0)&&(Sn>=0&&Sn<=70)){
                                    ps.setFloat(1,Cc);
                                    ps.setFloat(2, Tp);
                                    ps.setFloat(3, Sn);
                                    ps.setFloat(4, T);
                                    ps.setFloat(5, Q);
                                    ps.setFloat(6, Pa);
                                    ps.setString(7, Mat);
                                    ps.setString(8, Ue);
                                    ps.executeUpdate();
                                    JOptionPane.showMessageDialog(null,"Notes enregistrer!",null,JOptionPane.INFORMATION_MESSAGE);
                                    con.maConnection().close();
                                    ProfConnect en = new ProfConnect(prof,ue);
                                    en.setVisible(true);
                                    dispose();
                                } else {
                                    JOptionPane.showMessageDialog(null,"Les notes ne sont pas correctes",null, JOptionPane.ERROR_MESSAGE);
                                    con.maConnection().close();
                                }
                              
                            } else{
                                do{
                                    Mat0=rs.getString(2).trim();
                                    if(Mat.equals(Mat0)){
                                        int Nc = rs.getInt(8);
                                        float Pa = Q*Nc;
                                        String rq = "update notes_etudiant set cc=?, tp=?, sn=?, total=?, qualite=?, points_acc=? where ((matricule = ?)and(ue=?))";
                                        PreparedStatement ps = con.maConnection().prepareStatement(rq);
                                        if((Cc>=0&&Cc<=20)&&(Tp>=0&&Tp<=30)&&(Sn>=0&&Sn<=50)){
                                            ps.setFloat(1,Cc);
                                            ps.setFloat(2, Tp);
                                            ps.setFloat(3, Sn);
                                            ps.setFloat(4, T);
                                            ps.setFloat(5, Q);
                                            ps.setFloat(6, Pa);
                                            ps.setString(7, Mat);
                                            ps.setString(8, Ue);
                                            ps.executeUpdate();
                                            JOptionPane.showMessageDialog(null,"Notes enregistrers!",null,JOptionPane.INFORMATION_MESSAGE);
                                            con.maConnection().close();
                                            ProfConnect en = new ProfConnect(prof,ue);
                                            en.setVisible(true);
                                            dispose();
                                        } else if ((Cc>=0&&Cc<=30)&&(Tp==0)&&(Sn>=0&&Sn<=70)){
                                            ps.setFloat(1,Cc);
                                            ps.setFloat(2, Tp);
                                            ps.setFloat(3, Sn);
                                            ps.setFloat(4, T);
                                            ps.setFloat(5, Q);
                                            ps.setFloat(6, Pa);
                                            ps.setString(7, Mat);
                                            ps.setString(8, Ue);
                                            ps.executeUpdate();
                                            JOptionPane.showMessageDialog(null,"Notes enregistrer!",null,JOptionPane.INFORMATION_MESSAGE);
                                            con.maConnection().close();
                                            ProfConnect en = new ProfConnect(prof,ue);
                                            en.setVisible(true);
                                            dispose();
                                        } else {
                                                JOptionPane.showMessageDialog(null,"Les notes ne sont pas correctes",null, JOptionPane.ERROR_MESSAGE);
                                                con.maConnection().close();
                                        }
                              
                                    }
                                }while(rs.next()==true);
                                    JOptionPane.showMessageDialog(null,"Le matriclue "+Mat+" n'est pas enregistrer pour "+Ue,null,JOptionPane.INFORMATION_MESSAGE);
                                    con.maConnection().close();
                            }
                            
                    }
                    
                } catch (SQLException e){
                    e.printStackTrace();
                }
                
            }
        });
        pn.add(btnEnregistrer);
        
        btnAjouEtud = new JButton("AJOUT COMPTE ETUDIANT");
        btnAjouEtud.setBounds(520,100,260,30);
        btnAjouEtud.setFont(new Font("Arial", Font.BOLD, 15));
        btnAjouEtud.setForeground(Color.BLACK);
        btnAjouEtud.setBackground(new Color(50,205,205));
        btnAjouEtud.addActionListener(new ActionListener(){
            
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAjouEtudActionPerformed(evt);
            }
            
            private void btnAjouEtudActionPerformed(ActionEvent evt) {
                
                EnregistrementEtudiant ecr = new EnregistrementEtudiant();
                ecr.setVisible(true);
                dispose();
                
            }

        });
        pn.add(btnAjouEtud);
        
        btnMod = new JButton("MODIFIER MON COMPTE");
        btnMod.setBounds(520,140,260,30);
        btnMod.setFont(new Font("Arial", Font.BOLD, 14));
        btnMod.setForeground(Color.BLACK);
        btnMod.setBackground(new Color(50,205,205));
        btnMod.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModActionPerformed(evt);
            }
            
            private void btnModActionPerformed(ActionEvent evt) {
                Mdpmdrt es = new Mdpmdrt("CrtPr",1,prof,ue);
                es.setVisible(true);
                dispose();
            } 
        });
        pn.add(btnMod);
        
        btnSup = new JButton("SUPPRIMER MON COMPTE");
        btnSup.setBounds(520,260,260,30);
        btnSup.setFont(new Font("Arial", Font.BOLD, 14));
        btnSup.setForeground(Color.BLACK);
        btnSup.setBackground(new Color(173,216,230));
        btnSup.addActionListener(new ActionListener(){
            
            public void actionPerformed(ActionEvent ev){
                
                String Id, Mdp, Nom, Sexe, Fil, Niv, Ue;
                Connect con = new Connect();
                
                String rq = "delete from tb_prof where identifiant ='" + prof + "'";
                try {
                    pst = con.maConnection().createStatement();
                    pst.executeUpdate(rq);
                    JOptionPane.showMessageDialog(null, "Compte Professur Supprimer!", null, JOptionPane.INFORMATION_MESSAGE);
                    con.maConnection().close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erreur!" + ex.getMessage(), null, JOptionPane.ERROR_MESSAGE);
                }
                    
                dispose();
                Prof etd = new Prof();
                etd.setVisible(true);
                
            }
            
        });
        //pn.add(btnSup);
        
        btnPrec = new JButton("SE DECONNECTER");
        btnPrec.setBounds(520,180,260,30);
        btnPrec.setFont(new Font("Arial", Font.BOLD,14));
        btnPrec.setForeground(Color.BLACK);
        btnPrec.setBackground(new Color(70,195,195));
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
        DefaultTableModel model = new DefaultTableModel();
        init();
        pn.add(scroll1);
        model.addColumn("Matricule");
        model.addColumn("Cc");
        model.addColumn("Tp");
        model.addColumn("Sn");
        model.addColumn("Total");
        
        
        table1.setModel(model);
        String sql = "select * from notes_etudiant order by ue desc";
        try{
            pst = con.maConnection().createStatement();
            rs = pst.executeQuery(sql);
            while(rs.next()){
                String Ue2 = rs.getString("ue");
                String Ue1 = ue;
                if(Ue1.equals(Ue2)){
                    model.addRow(new Object[]{
                        rs.getString("matricule"),
                        rs.getString("cc"),
                        rs.getString("tp"),
                        rs.getString("sn"),
                        rs.getString("total"),
                    });
                }
                
            }
            con.maConnection().close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erreur!", null, JOptionPane.ERROR_MESSAGE);
        }
        //Debut code completer
        table1.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                table1MouseReleased(evt);
            }
            
            private void table1MouseReleased(MouseEvent evt) {
                int selectionner = table1.getSelectedRow();
                DefaultTableModel model = (DefaultTableModel) table1.getModel();
                txtMat.setText(model.getValueAt(selectionner, 0).toString());
                txtCc.setText(model.getValueAt(selectionner, 1).toString());
                txtTp.setText(model.getValueAt(selectionner, 2).toString());
                txtSn.setText(model.getValueAt(selectionner, 3).toString());
            }
        });
        
    }
    
    public static void main(String[] args){
        
        ProfConnect en = new ProfConnect("Bernard","INF151");
        en.setVisible(true);
        
    }
    
    public void init() {
        table1 = new JTable();
        scroll1 = new JScrollPane();
        scroll1.setBounds(10, 320, 770, 230);
        scroll1.setViewportView(table1);
    }
                
                
}            
    

