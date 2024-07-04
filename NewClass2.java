package SuperProf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class NewClass2 extends JFrame {
    
    JTextField txtusr, txtusr1;
    JPasswordField pswd;
    JLabel lblusr, lbltitre, lblpswd, lblusr1;
    JButton btnconnect, btnPrec, btnPrec3, btnPrec2;
    JRadioButton showPasswordRadioButton;
    
    public NewClass2() {
        super.setTitle("SuperProf");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        int desiredWidth = (int) (screenWidth*0.8);
        int desiredHeight = (int) (screenHeight*0.8);
        super.setSize(desiredWidth, desiredHeight);
        //setLayout(new GridLayout(3, 2));
        super.setLocationRelativeTo(null);
        super.setResizable(true);
        JPanel pn = new JPanel();
        getContentPane().add(pn);
        pn.setLayout(null);
        pn.setRequestFocusEnabled(true);
        pn.setBackground(new Color(50,50,100));
        add(pn);
        
        lbltitre = new JLabel("Vérification des paramètres de connexion");
        lbltitre.setBounds(175,10, 600, 30);
        lbltitre.setFont(new Font("Arial", Font.BOLD, 20));
        lbltitre.setForeground(new Color(255,255,255));
        //lblTitre.setLocation(400, 225);
        pn.add(lbltitre);
        
        lbltitre = new JLabel("Verification of connection parameters");
        lbltitre.setBounds(375,35, 400, 20);
        lbltitre.setFont(new Font("Arial", Font.ITALIC, 13));
        lbltitre.setForeground(new Color(255,255,255));
        //lblTitre.setLocation(400, 225);
        pn.add(lbltitre);
        
        btnPrec = new JButton("QUITTER");
        btnPrec.setBounds(175,235,200,30);
        btnPrec.setFont(new Font("Arial", Font.BOLD,14));
        btnPrec.setForeground(Color.BLACK);
        btnPrec.setBackground(new Color(100,200,200));
        btnPrec.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrecActionPerformed(evt);
            }
            
            private void btnPrecActionPerformed(ActionEvent evt) {
                
                NewClass2 ecr = new NewClass2();
                ecr.setVisible(false);
                dispose();
                
            } 
        });
        pn.add(btnPrec);
        
        btnPrec2 = new JButton("RETOUR");
        btnPrec2.setBounds(175,285,200,30);
        btnPrec2.setFont(new Font("Arial", Font.BOLD,14));
        btnPrec2.setForeground(Color.BLACK);
        btnPrec2.setBackground(new Color(100,200,200));
        btnPrec2.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrecActionPerformed(evt);
            }
            
            private void btnPrecActionPerformed(ActionEvent evt) {
                
                Main ecr = new Main();
                ecr.setVisible(true);
                dispose();
                
            } 
        });
        pn.add(btnPrec2);
        
        btnPrec3 = new JButton("UTILISER EN MODE ESSAI");
        btnPrec3.setBounds(410,285,200,30);
        btnPrec3.setFont(new Font("Arial", Font.BOLD,11));
        btnPrec3.setForeground(Color.BLACK);
        btnPrec3.setBackground(new Color(100,200,200));
        btnPrec3.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrec3ActionPerformed(evt);
            }
            
            public void btnPrec3ActionPerformed(ActionEvent evt) {
                
                EcranAcc ecr = new EcranAcc("","","");
                ecr.setVisible(true);
                dispose();
                
            } 
        });
        pn.add(btnPrec3);
        
        lblusr = new JLabel("Nom d'utilisateur:");
        lblusr.setBounds(190,90,250,30);
        lblusr.setFont(new Font("Arial", Font.BOLD,14));
        lblusr.setForeground(new Color(100,200,200));
        pn.add(lblusr);
        
        lblusr1 = new JLabel("Nom de la base de donnee");
        lblusr1.setBounds(190,130,250,30);
        lblusr1.setFont(new Font("Arial", Font.BOLD,14));
        lblusr1.setForeground(new Color(100,200,200));
        pn.add(lblusr1);
        
        txtusr = new JTextField();
        txtusr.setBounds(410,90,200,30);
        txtusr.setFont(new Font("Arial", Font.PLAIN,14));
        pn.add(txtusr);
        
        lblpswd = new JLabel("Mot de passe:");
        lblpswd.setBounds(190,170,250,30);
        lblpswd.setFont(new Font("Arial", Font.BOLD,14));
        lblpswd.setForeground(new Color(100,200,200));
        pn.add(lblpswd);
        
        pswd = new JPasswordField();
        pswd.setBounds(410,170,200,30);
        pswd.setFont(new Font("Arial", Font.PLAIN, 14));
        pn.add(pswd);
        
        showPasswordRadioButton = new JRadioButton("Voir");
        showPasswordRadioButton.setBounds(410,205,80,20);
        showPasswordRadioButton.setFont(new Font("Arial", Font.BOLD, 15));
        showPasswordRadioButton.setForeground(new Color(0,0,0));
        showPasswordRadioButton.setBackground(new Color(50,50,100));
        showPasswordRadioButton.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showPasswordRadioButtonActionPerformed(evt);
            }
            public void showPasswordRadioButtonActionPerformed(ActionEvent evt) {
                JRadioButton source = (JRadioButton) evt.getSource();
                if (source.isSelected()) {
                    pswd.setEchoChar((char) 0); // Afficher le texte en clair
                } else {
                    pswd.setEchoChar('\u2022'); // Masquer le texte
                }
            }
        });
        pn.add(showPasswordRadioButton);
        
        txtusr1 = new JTextField();
        txtusr1.setBounds(410,130,200,30);
        txtusr1.setFont(new Font("Arial", Font.PLAIN,14));
        pn.add(txtusr1);
        
        btnconnect = new JButton("SE CONNECTER");
        btnconnect.setBounds(410,235,200,30);
        btnconnect.setFont(new Font("Arial", Font.BOLD,14));
        btnconnect.setForeground(Color.BLACK);
        btnconnect.setBackground(new Color(100,200,200));
        btnconnect.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnconnectActionPerformed(evt);
            }
            public void btnconnectActionPerformed(ActionEvent evt) {
                String url = "jdbc:mysql://localhost:3306";
                String username = txtusr.getText();
                String password = new String(pswd.getPassword());
                String datab= txtusr1.getText();;
                
                
                boolean credentialsValid = verifyCredentials(url, username, password);

                if (credentialsValid) {
                    createDatabase(url,username,password,datab);
                    EcranAcc en = new EcranAcc(datab,username,password);
                    en.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Entrer des parametres de connection correctes ou utilisez le mode essai si vous ne disposer pas de serveurs de bases de donnes", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });pn.add(btnconnect);

        
    }
    /*
    public static void main(String[] args) {
        NewClass2 en = new NewClass2();
        en.setVisible(true);
    }
    */
    
    public boolean verifyCredentials(String url, String username, String password) {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    
    
    public void createDatabase(String url, String username, String password, String databaseName) {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            Statement statement = connection.createStatement();
            
            String sql1 = "CREATE DATABASE " + databaseName;
            statement.executeUpdate(sql1);

            String sql2 = "USE " + databaseName;
            statement.executeUpdate(sql2);

            String sql3 = "CREATE TABLE id (app VARCHAR(50), user VARCHAR(50), pass VARCHAR(50))";
            statement.executeUpdate(sql3);
            
            String insertDataSQL = "INSERT INTO id (user, pass) VALUES ('" + username + "', '" + password + "')";
            statement.executeUpdate(insertDataSQL);
            
            String sql4 = "CREATE TABLE mot_de_passe (identifiant VARCHAR(50), pass VARCHAR(50))";
            statement.executeUpdate(sql4);
            
            String insertDataSQL1 = "INSERT INTO mot_de_passe (identifiant, pass) VALUES ('" + "SuperProf" + "', '" + password + "')";
            statement.executeUpdate(insertDataSQL1);
            
            String sql5 = "CREATE TABLE notes_etudiant (ue VARCHAR(50), matricule VARCHAR(50), cc FLOAT, tp FLOAT, sn FLOAT, total FLOAT, qualite FLOAT, nb_credit INT(10), points_acc FLOAT)";
            statement.executeUpdate(sql5);
            
            String sql6 = "CREATE TABLE notes_etudiant2 (ue VARCHAR(50), matricule VARCHAR(50), cc FLOAT, tp FLOAT, sn FLOAT, total FLOAT, qualite FLOAT, nb_credit INT(10), points_acc FLOAT)";
            statement.executeUpdate(sql6);
            
            String sql7 = "CREATE TABLE tb_etudiant (identifiant VARCHAR(50), mdp VARCHAR(50), matricule VARCHAR(50), nom VARCHAR(50), sexe VARCHAR(50), filiere VARCHAR(50), niveau VARCHAR(50), ue VARCHAR(50), ue2 VARCHAR(50), uet VARCHAR(50), photo LONGBLOB, mgp FLOAT, mp2 FLOAT, mgp3 FLOAT)";
            statement.executeUpdate(sql7);
            
            String sql8 = "CREATE TABLE tb_prof (identifiant VARCHAR(50), mdp VARCHAR(50), nom VARCHAR(50), sexe VARCHAR(50), filiere VARCHAR(50), niveau VARCHAR(50), ue VARCHAR(50), photo LONGBLOB)";
            statement.executeUpdate(sql8);
            
            
            JOptionPane.showMessageDialog(null, "La base de données " + databaseName + " a été créée avec succès !", "Succès", JOptionPane.INFORMATION_MESSAGE);
            EcranAcc enm = new EcranAcc(databaseName,username,password);
            enm.setVisible(true);
            dispose();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erreur lors de la création de la base de données : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
