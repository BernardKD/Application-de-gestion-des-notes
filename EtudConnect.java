package Etudiant;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import static java.awt.image.ImageObserver.HEIGHT;
import static java.awt.image.ImageObserver.WIDTH;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class EtudConnect extends JFrame {
    
    
    JLabel lblTitre;
    JTable table1, table;
    JButton btnSem1, btnSem2, btnPrec;
    JScrollPane scroll, scroll1;
    Statement pst;
    ResultSet rs;
    
    public EtudConnect(String etud, String ue, String ue2, String uet, String mat){
        
        super.setTitle("SuperProf");
        super.setSize(1000, 600);
        super.setLocationRelativeTo(null);
        super.setResizable(true);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel pn = new JPanel();
        getContentPane().add(pn);
        pn.setLayout(null);
        add(pn);
        pn.setBackground(new Color(50,50,200));
        lblTitre = new JLabel("Bienvenue "+etud);
        lblTitre.setBounds(350,10, 450, 30);
        lblTitre.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitre.setForeground(new Color(255,255,255));
        //lblTitre.setLocation(400, 225);
        pn.add(lblTitre);
        
        int Mgp=0, Cr=0, Pa=0;
        
        JLabel lblMgp = new JLabel("MGP = "+Mgp);
        lblMgp.setBounds(800,320, 160,30);
        lblMgp.setFont(new Font("Arial", Font.BOLD, 24));
        lblMgp.setForeground(new Color(255,255,255));
        pn.add(lblMgp);
        
        //ImageIcon i  = new ImageIcon(new ImageIcon("pubg.jpg").getImage().getScaledInstance(250,250, Image.SCALE_DEFAULT));
        JLabel lblLogo = new JLabel();
        lblLogo.setBounds(30,70, 250,250);
        lblLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLogo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pn.add(lblLogo);
        
        btnPrec = new JButton("SE DECONNECTER");
        btnPrec.setBounds(520,150,260,30);
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
        
        btnSem1 = new JButton("SEMESTRE 1");
        btnSem1.setBounds(520,70,260,30);
        btnSem1.setFont(new Font("Arial", Font.BOLD, 15));
        btnSem1.setForeground(Color.BLACK);
        btnSem1.setBackground(new Color(0,255,255));
        btnSem1.addActionListener(new ActionListener(){
            
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSem1ActionPerformed(evt);
            }
            
            private void btnSem1ActionPerformed(ActionEvent evt) {
                Connect con = new Connect();
                DefaultTableModel model = new DefaultTableModel();
                init();
                pn.add(scroll1);
                model.addColumn("Ue");
                model.addColumn("Note");
                model.addColumn("Qualite de points");
                model.addColumn("Nombre de credits");
                model.addColumn("Points accumules");
                int Cr=0;
                float Mgp, Pa=0;
        
                table1.setModel(model);
                String sql = "select * from notes_etudiant order by matricule desc";
                try{
                    pst = con.maConnection().createStatement();
                    rs = pst.executeQuery(sql);
                    while(rs.next()){
                        String Mat1 = rs.getString("matricule");
                        String Mat = mat;
                        if(Mat.equals(Mat1)){
                            model.addRow(new Object[]{
                                rs.getString("ue"),
                                rs.getString("total"),
                                rs.getString("qualite"),
                                rs.getString("nb_credit"),
                                rs.getString("points_acc"),
                            });
                            Cr += (int) Float.parseFloat(rs.getString("nb_credit"));
                            Pa += (Float) Float.parseFloat(rs.getString("points_acc"));
                            
                        }
                    }
                    Mgp = (Float) Pa/Cr;
                    lblMgp.setText("MGP ="+Mgp);
                    con.maConnection().close();
                    System.out.println(Mgp);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Calcul de la MGP impossible car les notes ne sont pas completes", null, JOptionPane.ERROR_MESSAGE);
                    lblMgp.setText("MGP = 0");
                }    
                
                
                
            }

        });
        pn.add(btnSem1);
        
        btnSem2 = new JButton("SEMESTRE 2");
        btnSem2.setBounds(520,110,260,30);
        btnSem2.setFont(new Font("Arial", Font.BOLD, 15));
        btnSem2.setForeground(Color.BLACK);
        btnSem2.setBackground(new Color(0,255,255));
        btnSem2.addActionListener(new ActionListener(){
            
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSem2ActionPerformed(evt);
            }
            
            private void btnSem2ActionPerformed(ActionEvent evt) {
                Connect con = new Connect();
                DefaultTableModel model = new DefaultTableModel();
                init();
                pn.add(scroll1);
                model.addColumn("Ue");
                model.addColumn("Note");
                model.addColumn("Qualite de points");
                model.addColumn("Nombre de credits");
                model.addColumn("Points accumules");
                
                int Cr=0;
                float Mgp=0, Pa=0;
        
                table1.setModel(model);
                String sql = "select * from notes_etudiant2 order by matricule desc";
                try{
                    pst = con.maConnection().createStatement();
                    rs = pst.executeQuery(sql);
                    while(rs.next()){
                        String Mat1 = rs.getString("matricule");
                        String Mat = mat;
                        if(Mat.equals(Mat1)){
                            model.addRow(new Object[]{
                                rs.getString("ue"),
                                rs.getString("total"),
                                rs.getString("qualite"),
                                rs.getString("nb_credit"),
                                rs.getString("points_acc"),
                            });
                            Cr += (int) Float.parseFloat(rs.getString("nb_credit"));
                            Pa += (Float) Float.parseFloat(rs.getString("points_acc"));
                            
                        }
                       
                    }
                    Mgp = (Float) Pa/Cr;
                    lblMgp.setText("MGP ="+Mgp);
                    System.out.println(Mgp);
                    con.maConnection().close();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Calcul de la MGP impossible car les notes ne sont pas completes", null, JOptionPane.ERROR_MESSAGE);
                    lblMgp.setText("MGP = 0");
                }    
                
                
                
            }

        });
        pn.add(btnSem2);
        
           
        try {
            Connect con = new Connect();
            String rq = "Select * from tb_etudiant where identifiant=?";
            PreparedStatement ps = con.maConnection().prepareStatement(rq);
            ps.setString(1, etud);
            rs = ps .executeQuery();
            if(rs.next()==false){
                JOptionPane.showMessageDialog(null,"Enregistrement de "+etud+" incomplet",null, JOptionPane.ERROR_MESSAGE);
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
        
        DefaultTableModel model = new DefaultTableModel();
        init();
        pn.add(scroll1);
        model.addColumn("Ue");
        model.addColumn("Note");
        model.addColumn("Qualite de points");
        model.addColumn("Nombre de credits");
        model.addColumn("Points accumules");
                
                    
                    
        
    }
    
    public static void main(String[] args){
        
        EtudConnect en = new EtudConnect("1","INF151","INF152","ANG111","1");
        en.setVisible(true);
        
    }

    public void init() {
        table1 = new JTable();
        scroll1 = new JScrollPane();
        scroll1.setBounds(10, 320, 770, 230);
        scroll1.setForeground(new Color(3,0,255));
        scroll1.setBackground(new Color(200,0,00));
        scroll1.setViewportView(table1);
    }
    
}
