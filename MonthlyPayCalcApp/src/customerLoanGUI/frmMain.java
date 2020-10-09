

package customerLoanGUI;
import com.sun.deploy.uitoolkit.ui.UIFactory;
import java.awt.print.PrinterException;
import java.io.BufferedReader;
import javax.swing.JFileChooser;
import java.io.File;   
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class frmMain extends javax.swing.JFrame {
    
    ArrayList<customerLoan> customerList=new ArrayList<customerLoan>();
    /** Creates new form Antenna */
    public frmMain() {
        initComponents();
        
    }
        
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnImport = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        rptTable = new javax.swing.JTable();
        btnPrint = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CrossKey Monthly Loan Payment Calc.");
        setLocation(new java.awt.Point(100, 100));
        setName("frmMain"); // NOI18N
        setResizable(false);

        btnImport.setText("Import File");
        btnImport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnImportMouseClicked(evt);
            }
        });

        rptTable.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        rptTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Customer", "Total loan", "Interest", "Years", "Monthly Pay"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        rptTable.setName("rptTable"); // NOI18N
        jScrollPane1.setViewportView(rptTable);
        if (rptTable.getColumnModel().getColumnCount() > 0) {
            rptTable.getColumnModel().getColumn(0).setMinWidth(200);
            rptTable.getColumnModel().getColumn(0).setMaxWidth(200);
            rptTable.getColumnModel().getColumn(1).setMinWidth(80);
            rptTable.getColumnModel().getColumn(1).setMaxWidth(80);
            rptTable.getColumnModel().getColumn(2).setMinWidth(80);
            rptTable.getColumnModel().getColumn(2).setMaxWidth(80);
            rptTable.getColumnModel().getColumn(3).setMinWidth(60);
            rptTable.getColumnModel().getColumn(3).setMaxWidth(60);
            rptTable.getColumnModel().getColumn(4).setMinWidth(80);
            rptTable.getColumnModel().getColumn(4).setMaxWidth(80);
        }

        btnPrint.setText("Print");
        btnPrint.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPrintMouseClicked(evt);
            }
        });

        btnExit.setText("Exit");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 523, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnImport)
                        .addGap(18, 18, 18)
                        .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnImport, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnImportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnImportMouseClicked

        
        try {
            String record="";
            customerList.clear();
            
            //Select a file
            JFileChooser importFileDialog = new JFileChooser();               
            importFileDialog.setFileFilter(new FileNameExtensionFilter("Text File (*.txt)", "txt"));
            if(importFileDialog.showOpenDialog(this)==JFileChooser.APPROVE_OPTION)
            {              
                //open the file and send every line to creatCustomer method and fill an arraylist                
                BufferedReader reader = Files.newBufferedReader(importFileDialog.getSelectedFile().toPath(), StandardCharsets.UTF_8);
                                        
                reader.readLine();
                while ((record = reader.readLine()) != null)
			if(record.length()>0 && !record.equals("."))
                            customerList.add(creatCustomer(record));			
		reader.close();
            }
            
            
            //Clear datagrid and Fill data from arraylist to datagrid
            DefaultTableModel dgvModel=(DefaultTableModel) rptTable.getModel();
            Object[] rowCustomer=new Object[5];
                        
            while (dgvModel.getRowCount()>0)
                dgvModel.removeRow(0);
          
            for(customerLoan cl:customerList )
            {                
                rowCustomer[0]=cl.getName();
                rowCustomer[1]=String.format( "%,.2f",cl.getTotalLoan());
                rowCustomer[2]=String.format( "%,.2f",cl.getInterest());
                rowCustomer[3]=String.format( "%d",cl.getYears());
                rowCustomer[4]=String.format( "%,.2f",cl.getMonthlyPay());
                dgvModel.addRow(rowCustomer);
            }
            
        } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,"An Error Occurred!");
        }
    }//GEN-LAST:event_btnImportMouseClicked

    
    
    private void btnPrintMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPrintMouseClicked
        try {
            //Print Report
            if(rptTable.getRowCount()>0)
                rptTable.print();      
            else
                JOptionPane.showMessageDialog(this,"First Import Data!");
        } catch (PrinterException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_btnPrintMouseClicked

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        System.exit(0);        
    }//GEN-LAST:event_btnExitActionPerformed
    
    
    
    private  customerLoan creatCustomer(String record)
	{
		try
		{		
		
		int startIndex = 0;
		int fieldIndex = 0;
        String[] recordFields = new String[4];
        Boolean isquotation = false;
        int pointer = 0;
        
        //search till end of record
        while (pointer < record.length())
        {
            /*check every character in record until finding ','
            if before finding ',' it was a '"' in record, ignore the ',' until you find another '"'*/
            while (pointer < record.length() && (isquotation || record.charAt(pointer) != ','))
            {
                //if current character is '"', change value of isquotation
                if (record.charAt(pointer) == '"') isquotation = !isquotation;
                pointer++;

            }
            //extract the found field by substring from record
            recordFields[fieldIndex] = record.substring(startIndex, pointer);
            pointer++;
            fieldIndex++;
            //Set start Index to position of first character of next field
            startIndex = pointer;
        }
				        
		return new customerLoan(recordFields[0].replace('"',' ').trim(),Double.parseDouble(recordFields[1]) , Double.parseDouble(recordFields[2]), Integer.parseInt(recordFields[3]));
		
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}

    
    
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
            javax.swing.UIManager.LookAndFeelInfo[] installedLookAndFeels=javax.swing.UIManager.getInstalledLookAndFeels();
            for (int idx=0; idx<installedLookAndFeels.length; idx++)
                if ("Nimbus".equals(installedLookAndFeels[idx].getName())) {
                    javax.swing.UIManager.setLookAndFeel(installedLookAndFeels[idx].getClassName());
                    break;
                }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmMain().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnImport;
    private javax.swing.JButton btnPrint;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable rptTable;
    // End of variables declaration//GEN-END:variables
    
}
