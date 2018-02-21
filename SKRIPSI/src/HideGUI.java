
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Gavrila Sianturi
 */
public class HideGUI extends javax.swing.JFrame {

    public String coverImagePath;
    public String stegoImagePath;
    public MPITSteganography pit;
    public LSBSteganography lsb;

    /**
     * Creates new form GUI
     */
    public HideGUI() {
        initComponents();
        generateButton.setVisible(false);
        randomNumberTextField.setText("0");
        this.lsb = new LSBSteganography();
        this.pit = new MPITSteganography();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        jLabel1 = new javax.swing.JLabel();
        metodeComboBox = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        coverImageTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        secretDataTextField = new javax.swing.JTextField();
        browseButton = new javax.swing.JButton();
        nomorPikselLabel = new javax.swing.JLabel();
        randomNumberTextField = new javax.swing.JTextField();
        generateButton = new javax.swing.JButton();
        prosesButton = new javax.swing.JButton();
        resetButton = new javax.swing.JButton();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Penyembunyian Pesan Rahasia");

        jLabel1.setText("Metode Steganografi");

        metodeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Least Significant Bit", "Pixel Indicator Technique" }));
        metodeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                metodeComboBoxActionPerformed(evt);
            }
        });

        jLabel2.setText("Secret Data");

        coverImageTextField.setEditable(false);
        coverImageTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                coverImageTextFieldActionPerformed(evt);
            }
        });

        jLabel3.setText("Cover Image");

        secretDataTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                secretDataTextFieldActionPerformed(evt);
            }
        });

        browseButton.setText("Browse");
        browseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseButtonActionPerformed(evt);
            }
        });

        nomorPikselLabel.setText("Nomor Piksel Awal");

        randomNumberTextField.setEditable(false);
        randomNumberTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                randomNumberTextFieldActionPerformed(evt);
            }
        });

        generateButton.setText("Generate Random Number");
        generateButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                generateButtonMouseClicked(evt);
            }
        });
        generateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateButtonActionPerformed(evt);
            }
        });

        prosesButton.setText("Proses");
        prosesButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                prosesButtonMouseClicked(evt);
            }
        });
        prosesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prosesButtonActionPerformed(evt);
            }
        });

        resetButton.setText("Reset");
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(prosesButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(resetButton))
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(metodeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(coverImageTextField)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(browseButton))
                    .addComponent(secretDataTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(randomNumberTextField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nomorPikselLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(generateButton)))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(metodeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(secretDataTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(coverImageTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(browseButton))
                .addGap(18, 18, 18)
                .addComponent(nomorPikselLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(randomNumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(generateButton))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(prosesButton)
                    .addComponent(resetButton))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void coverImageTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_coverImageTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_coverImageTextFieldActionPerformed

    private void secretDataTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_secretDataTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_secretDataTextFieldActionPerformed

    private void randomNumberTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_randomNumberTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_randomNumberTextFieldActionPerformed

    private void browseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseButtonActionPerformed
        // TODO add your handling code here:
        randomNumberTextField.setText(null);
        JFileChooser browse = new JFileChooser();
        int returnName = browse.showOpenDialog(null);
        if (returnName == JFileChooser.APPROVE_OPTION) {
            File f = browse.getSelectedFile();
            if (f != null) {
                this.coverImagePath = f.getAbsolutePath();
                this.coverImageTextField.setText(coverImagePath);
            }
        }
    }//GEN-LAST:event_browseButtonActionPerformed

    private void metodeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_metodeComboBoxActionPerformed
        // TODO add your handling code here:
        if (metodeComboBox.getSelectedIndex() == 0) {
            randomNumberTextField.setText("0");
            generateButton.setVisible(false);
        } else {
            randomNumberTextField.setText(null);
            generateButton.setVisible(true);
        }
    }//GEN-LAST:event_metodeComboBoxActionPerformed

    private void generateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_generateButtonActionPerformed

    private void generateButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_generateButtonMouseClicked
        // TODO add your handling code here:
        if (metodeComboBox.getSelectedIndex() == 1) {
            if (secretDataTextField.getText().isEmpty() && coverImageTextField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Masukkan secret data dan pilih cover image dari direktori!", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (secretDataTextField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Masukkan secret data!", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (coverImageTextField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Pilih cover image dari direktori!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    this.pit.setSecretData(secretDataTextField.getText());
                    ImageProcessor img = new ImageProcessor(coverImagePath);
                    this.pit.setCoverImage(img);
                    this.pit.generateRandomNumber();
                    randomNumberTextField.setText("" + this.pit.randNumber);
                } catch (IOException ex) {
                    Logger.getLogger(HideGUI.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
    }//GEN-LAST:event_generateButtonMouseClicked

    private void prosesButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_prosesButtonMouseClicked
        // TODO add your handling code here:
        if (metodeComboBox.getSelectedIndex() == 0) {
            if (secretDataTextField.getText().isEmpty() && coverImageTextField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Masukkan secret data dan pilih cover image dari direktori!", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (secretDataTextField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Masukkan secret data!", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (coverImageTextField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Pilih cover image dari direktori!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    this.lsb.setSecretData(secretDataTextField.getText());
                    ImageProcessor img = new ImageProcessor(coverImagePath);
                    this.lsb.setCoverImage(img);
                    this.lsb.hideSecretData();
                    this.lsb.image.createImage();
                    this.stegoImagePath = this.coverImagePath.substring(0, this.coverImagePath.length() - 4) + "Output.bmp";
                    JOptionPane.showMessageDialog(null, "Hasil penyisipan : " + this.stegoImagePath, "Success", JOptionPane.PLAIN_MESSAGE);
                } catch (IOException ex) {
                    Logger.getLogger(HideGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            try {
                this.pit.setSecretData(secretDataTextField.getText());
                ImageProcessor img = new ImageProcessor(coverImagePath);
                this.pit.setCoverImage(img);
                pit.hideSecretData(Integer.parseInt(randomNumberTextField.getText()));
                pit.image.createImage();
                this.stegoImagePath = this.coverImagePath.substring(0, this.coverImagePath.length() - 4) + "Output.bmp";
                JOptionPane.showMessageDialog(null, "Hasil penyisipan : " + this.stegoImagePath, "Success", JOptionPane.PLAIN_MESSAGE);
            } catch (IOException ex) {
                Logger.getLogger(HideGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_prosesButtonMouseClicked

    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
        // TODO add your handling code here:
        secretDataTextField.setText(null);
        coverImageTextField.setText(null);
        if (metodeComboBox.getSelectedIndex() == 1) {
            randomNumberTextField.setText(null);
        }
    }//GEN-LAST:event_resetButtonActionPerformed

    private void prosesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prosesButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_prosesButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HideGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HideGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HideGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HideGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new HideGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton browseButton;
    private javax.swing.JTextField coverImageTextField;
    private javax.swing.JButton generateButton;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JComboBox<String> metodeComboBox;
    private javax.swing.JLabel nomorPikselLabel;
    private javax.swing.JButton prosesButton;
    private javax.swing.JTextField randomNumberTextField;
    private javax.swing.JButton resetButton;
    private javax.swing.JTextField secretDataTextField;
    // End of variables declaration//GEN-END:variables
}
