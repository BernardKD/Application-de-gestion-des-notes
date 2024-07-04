package SuperProf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main extends JFrame {
    
    JTextField txtusr, txtusr1;
    JPasswordField pswd;
    JLabel lblusr, lbltitre, lbltitre1, lblpswd, lblusr1,lblusr2;
    JButton btnconnect, btnPrec, btnPrec2, btnPrec3;
    JRadioButton showPasswordRadioButton;
    
    public Main() {
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
        
        lbltitre1 = new JLabel("Verification of connection parameters");
        lbltitre1.setBounds(375,35, 400, 20);
        lbltitre1.setFont(new Font("Arial", Font.ITALIC, 13));
        lbltitre1.setForeground(new Color(255,255,255));
        //lblTitre.setLocation(400, 225);
        pn.add(lbltitre1);
        
        btnPrec = new JButton("QUITTER");
        btnPrec.setBounds(175,270,200,30);
        btnPrec.setFont(new Font("Arial", Font.BOLD,14));
        btnPrec.setForeground(Color.BLACK);
        btnPrec.setBackground(new Color(100,200,200));
        btnPrec.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrecActionPerformed(evt);
            }
            
            private void btnPrecActionPerformed(ActionEvent evt) {
                
                Main ecr = new Main();
                ecr.setVisible(false);
                dispose();
                
            } 
        });
        pn.add(btnPrec);
        
        
        btnPrec2 = new JButton("CREER LES TABLES");
        btnPrec2.setBounds(175,345,200,30);
        btnPrec2.setFont(new Font("Arial", Font.BOLD,14));
        btnPrec2.setForeground(Color.BLACK);
        btnPrec2.setBackground(new Color(100,200,200));
        btnPrec2.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrec2ActionPerformed(evt);
            }
            
            private void btnPrec2ActionPerformed(ActionEvent evt) {
                
                NewClass2 ecr = new NewClass2();
                ecr.setVisible(true);
                dispose();
                
            } 
        });
        pn.add(btnPrec2);
        
        btnPrec3 = new JButton("UTILISER LE MODE ESSAI");
        btnPrec3.setBounds(400,345,250,30);
        btnPrec3.setFont(new Font("Arial", Font.BOLD,14));
        btnPrec3.setForeground(Color.BLACK);
        btnPrec3.setBackground(new Color(100,200,200));
        btnPrec3.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrec3ActionPerformed(evt);
            }
            
            private void btnPrec3ActionPerformed(ActionEvent evt) {
                
                EcranAcc ecr = new EcranAcc("","","");
                ecr.setVisible(true);
                dispose();
                
            } 
        });
        pn.add(btnPrec3);
        
        lblusr = new JLabel("Nom d'utilisateur:");
        lblusr.setBounds(175,100,250,30);
        lblusr.setFont(new Font("Arial", Font.BOLD,14));
        lblusr.setForeground(new Color(100,200,200));
        pn.add(lblusr);
        
        lblusr1 = new JLabel("Nom de la base de donnee:");
        lblusr1.setBounds(175,150,250,30);
        lblusr1.setFont(new Font("Arial", Font.BOLD,14));
        lblusr1.setForeground(new Color(100,200,200));
        pn.add(lblusr1);
        
        txtusr = new JTextField();
        txtusr.setBounds(400,100,250,30);
        txtusr.setFont(new Font("Arial", Font.PLAIN,14));
        pn.add(txtusr);
        
        txtusr1 = new JTextField();
        txtusr1.setBounds(400,150,250,30);
        txtusr1.setFont(new Font("Arial", Font.PLAIN,14));
        pn.add(txtusr1);
        
        lblpswd = new JLabel("Mot de passe:");
        lblpswd.setBounds(175,200,200,30);
        lblpswd.setFont(new Font("Arial", Font.BOLD,14));
        lblpswd.setForeground(new Color(100,200,200));
        pn.add(lblpswd);
        
        
        pswd = new JPasswordField();
        pswd.setBounds(400,200,250,30);
        pswd.setFont(new Font("Arial", Font.PLAIN, 14));
        pn.add(pswd);
        
        showPasswordRadioButton = new JRadioButton("Voir");
        showPasswordRadioButton.setBounds(400,230,80,16);
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
        
        btnconnect = new JButton("SE CONNECTER");
        btnconnect.setBounds(400,270,250,30);
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
                    EcranAcc en = new EcranAcc(datab,username,password);
                    en.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Paramètres de connexion incorrects.\nSi vous avez deja creer les tables, veuillez réessayer.\nSi ce n'est pas le cas ou que l'erreur persiste, cliquer sur CREER LES TABLES.\nUtilisez le mode essai si vous ne disposer pas de serveurs de bases de donnes", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        pn.add(btnconnect);
        
        
    }

    public static void main(String[] args) {
        Main en = new Main();
        en.setVisible(true);
    }

    public boolean verifyCredentials(String url, String username, String password) {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    
}
