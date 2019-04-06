import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class Check extends JDialog {

	private static final long serialVersionUID = 1L;

	private final JPanel contentPanel = new JPanel();

	private JLabel message;
	private JButton btnSave;
	private JButton btnDontSave;
	private JButton btnCancel;
	private JPanel buttonPane;


	/**
	 * Create the dialog.
	 */
	public Check(Notepad n) {
		setResizable(false);
		setTitle("Notepad");
		setModal(true);
		setBounds(700, 300, 445, 220);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		contentPanel.setBackground(Color.WHITE);
		message = new JLabel();
		if(n.isNewFile)
			message.setText("Do you want to save changes to Untitled ?");
		else
			message.setText("Do you want to save changes to\n"+n.currentFile+" ?");
		message.setBounds(24, 13, 380, 100);
		message.setFont(new Font("Tahoma",Font.PLAIN,16));
		contentPanel.add(message);
		
		buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
		btnSave = new JButton("Save");
		btnSave.setPreferredSize(new Dimension(100,30));
		buttonPane.add(btnSave);

			
		btnDontSave = new JButton("Don't Save");
		btnDontSave.setPreferredSize(new Dimension(100,30));
		buttonPane.add(btnDontSave);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setPreferredSize(new Dimension(100,30));
		buttonPane.add(btnCancel);
		
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(n.isNewFile)
				{
					JFileChooser fileChooser=new JFileChooser();  
	                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);   
	                fileChooser.setDialogTitle("Save As"); 
	                fileChooser.setSelectedFile(new File("*.txt"));
	                int result = fileChooser.showSaveDialog(null);
	       		 	if(result==JFileChooser.APPROVE_OPTION)
	       		 	{
	       		 		File saveFileName=fileChooser.getSelectedFile();
	       		 		if(saveFileName==null|| saveFileName.getName().equals(""))
	       		 			JOptionPane.showMessageDialog(null,"File's name can't be empty !","Notepad",JOptionPane.ERROR_MESSAGE);
	       		 		else   
	       		 		{  
	       		 			try  
	       		 			{   
	       		 				FileWriter fw=new FileWriter(saveFileName);  
	       		 				BufferedWriter bfw=new BufferedWriter(fw);  
	       		 				bfw.write(Test.notepad.editText.getText(),0,Test.notepad.editText.getText().length());  
	       		 				bfw.flush();  
	       		 				fw.close();  
	       		 				Test.notepad.oldValue=Test.notepad.editText.getText();  
	       		 				Test.notepad.setTitle(saveFileName.getName()+"  - Notepad");  
	       		 				
	       		 			}                         
	       		 			catch(IOException ioException)  
	       		 			{                     
	       		 			}       
	       		 		}  
	       		 	}
	       		 	
				}
				else
				{
					try  
		            {   FileWriter fw=new FileWriter(Test.notepad.currentFile);  
		                BufferedWriter bfw=new BufferedWriter(fw);  
		                bfw.write(Test.notepad.editText.getText(),0,Test.notepad.editText.getText().length());  
		                bfw.flush();  
		                fw.close();  
		            }                             
		            catch(IOException ioException)  
		            {                     
		            }  
					
				}
				dispose();
			}
		});
		
		btnDontSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btnDontSave)
				{
					Test.notepad.undo.discardAllEdits(); //撤消所有的"撤消"操作  
	                Test.notepad.edit.Undo.setEnabled(false);  
	                Test.notepad.oldValue=Test.notepad.editText.getText(); 
					dispose();
				}
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btnCancel)
					dispose();
			}
		});
	}

}
