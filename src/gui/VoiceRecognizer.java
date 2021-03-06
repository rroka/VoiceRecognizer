package gui;

import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class VoiceRecognizer extends javax.swing.JFrame {

   
    public VoiceRecognizer() {
        initComponents();
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("VoiceRecognizer");
        setName("VoiceRecognizer"); // NOI18N

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/mic.jpg"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 48)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(162, 162, 162)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(194, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        voiceRecognizer();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void voiceRecognizer(){
    ConfigurationManager cm;      
                
        jLabel1.setText("Mondj valamit!");
        jLabel1.paintImmediately(jLabel1.getVisibleRect());

        cm = new ConfigurationManager(VoiceRecognizer.class.getResource("newXMLDocument.xml"));
       

        Recognizer recognizer = (Recognizer) cm.lookup("recognizer");
        recognizer.allocate();

        Microphone microphone = (Microphone) cm.lookup("microphone");
        microphone.startRecording();      
     
        while (true) {           

            Result result = recognizer.recognize();                     

            if (result != null) {
                String resultText = result.getBestFinalResultNoFiller();
                jLabel1.setText(resultText + '\n');
                jLabel1.paintImmediately(jLabel1.getVisibleRect());
                if(resultText.equals("excel")){
                    jLabel2.setIcon(new ImageIcon(".\\src\\gui\\excel.png") );
                    jLabel2.paintImmediately(jLabel2.getVisibleRect());                    
                    try {            
                        Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + "C:\\Program Files\\Microsoft Office\\root\\Office16\\EXCEL.exe");
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "A megnyitás nem sikerült", "Hiba!", JOptionPane.ERROR_MESSAGE);
                    }                
                }
                if(resultText.equals("word")){
                    jLabel2.setIcon(new ImageIcon(".\\src\\gui\\word.png") );
                    jLabel2.paintImmediately(jLabel2.getVisibleRect());
                    try {            
                        Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + "C:\\Program Files\\Microsoft Office\\root\\Office16\\WINWORD.exe");
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "A megnyitás nem sikerült", "Hiba!", JOptionPane.ERROR_MESSAGE);
                    }                
                }
                if(resultText.equals("internet")){
                    jLabel2.setIcon(new ImageIcon(".\\src\\gui\\firefox.png") );
                    jLabel2.paintImmediately(jLabel2.getVisibleRect());
                    try {            
                        Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + "C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "A megnyitás nem sikerült", "Hiba!", JOptionPane.ERROR_MESSAGE);
                    }                
                }        
                
                if(resultText.equals("close"))
                    System.exit(0);                
            } else {
                 jLabel1.setText("Nem indult el a felvétel!");
                 jLabel1.paintImmediately(jLabel1.getVisibleRect());
            }
        }
    }
    
   
    public static void main(String args[]) {
       
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VoiceRecognizer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VoiceRecognizer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VoiceRecognizer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VoiceRecognizer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VoiceRecognizer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
