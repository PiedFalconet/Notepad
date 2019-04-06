import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.GridLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Replace extends JDialog {
	private static final long serialVersionUID = 1L;
	private JPanel textPanel;
	private JPanel checkBoxPanel;
	private JPanel buttonPanel;
	private JLabel lblFindWhat;
	private JLabel lblReplaceWith;
	private JTextField findtext;
	private JTextField replacetext;
	private JCheckBox chckbxMatchCase;
	private JButton btnFindNext;
	private JButton btnReplace;
	private JButton btnReplaceAll;
	private JButton btnCancel;
	private String textString;
	private String findString;
	private int k=0,replaceCount=0;
	
	public Replace(JTextArea textarea) {
		setResizable(false);
		setTitle("Replace");
		setBounds(700, 300, 500, 250);
		getContentPane().setLayout(null);
		
		textPanel = new JPanel();
		textPanel.setBounds(0, 30, 350, 115);
		getContentPane().add(textPanel);
		textPanel.setLayout(null);
		lblFindWhat = new JLabel("Find what :");
		lblFindWhat.setBounds(15, 10, 100, 40);
		lblFindWhat.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textPanel.add(lblFindWhat);
		
		findtext = new JTextField();
		findtext.setFont(new Font("Tahoma", Font.PLAIN, 16));
		findtext.setBounds(130, 10, 215, 40);
		findtext.setEnabled(true);
		findtext.setEditable(true);
		findtext.setColumns(20);
		findtext.requestFocus(true);
		textPanel.add(findtext);
			
		lblReplaceWith = new JLabel("Replace with :");
		lblReplaceWith.setBounds(15, 60, 115, 40);
		lblReplaceWith.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textPanel.add(lblReplaceWith);
			
		replacetext = new JTextField();
		replacetext.setFont(new Font("Tahoma", Font.PLAIN, 16));
		replacetext.setBounds(130, 60, 215, 40);
		replacetext.setEnabled(true);
		replacetext.setEditable(true);
		replacetext.setColumns(20);
		textPanel.add(replacetext);
	
		
		checkBoxPanel = new JPanel();
		checkBoxPanel.setBounds(10, 160, 175, 35);
		getContentPane().add(checkBoxPanel);
		
		chckbxMatchCase = new JCheckBox("Match case");
			checkBoxPanel.add(chckbxMatchCase);
	
			
		buttonPanel = new JPanel();
		buttonPanel.setBounds(360, 30, 110, 160);
		getContentPane().add(buttonPanel);
		buttonPanel.setLayout(new GridLayout(4, 0, 0, 4));
		
		btnFindNext = new JButton("Find Next");
		btnFindNext.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnFindNext.setEnabled(false);
		buttonPanel.add(btnFindNext);
		
		btnReplace = new JButton("Replace");
		btnReplace.setEnabled(false);
		buttonPanel.add(btnReplace);
		
		btnReplaceAll = new JButton("Replace All");
		btnReplaceAll.setEnabled(false);
		buttonPanel.add(btnReplaceAll);
			
		btnCancel = new JButton("Cancel");
		buttonPanel.add(btnCancel);		
		
		findtext.getDocument().addDocumentListener(new DocumentListener(){
	       	 public void removeUpdate(DocumentEvent e)  
	       	 {   
	       	 }  
	       	 public void insertUpdate(DocumentEvent e)  
	       	 {   
	       		btnFindNext.setEnabled(true);
				btnReplace.setEnabled(true);
	       		btnReplaceAll.setEnabled(true);
	       	 }  
	       	 public void changedUpdate(DocumentEvent e)  
	       	 {  
	       		btnFindNext.setEnabled(true);
				btnReplace.setEnabled(true);
	       		btnReplaceAll.setEnabled(true);
	       	 }//DocumentListener结束  
	       	
	       });  
		findtext.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
					
				if(findtext.getText().isEmpty())
				{
					btnFindNext.setEnabled(false);
					btnReplace.setEnabled(false);
		       		btnReplaceAll.setEnabled(false);
				}
				else
				{
					btnFindNext.setEnabled(true);
					btnReplace.setEnabled(true);
		       		btnReplaceAll.setEnabled(true);
					btnFindNext.doClick();
				}
			}
		});
			
		
		btnFindNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String findstr,textstr;
				findstr=findtext.getText();
				textstr=textarea.getText();
				int k=0;
				//arg0.getSource()==btnFindNext&&
				if(btnFindNext.isEnabled())
				{
					if(chckbxMatchCase.isSelected());
					else{
						textstr=textstr.toLowerCase();
						findstr=findstr.toLowerCase();
					}
					if(textarea.getSelectedText()==null)  
                        k=textstr.indexOf(findstr,textarea.getCaretPosition()+1);  
                    else  
                        k=textstr.indexOf(findstr, textarea.getCaretPosition()-findtext.getText().length()+1); //textField.getText().length()     
                    if(k>-1)  
                    {   
                    	textarea.setCaretPosition(k);  
                        textarea.select(k,k+findstr.length());  
                    }  
                    else  
                    {   
                    	JOptionPane.showMessageDialog(null,"Can't find \" "+findstr+" \" ！","Find",JOptionPane.INFORMATION_MESSAGE);  
                    }  
					
				}
					
			}
		});
		btnReplace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(replacetext.getText().length()==0 && textarea.getSelectedText()!=null)   
                    textarea.replaceSelection("");   
                if(replacetext.getText().length()>0 && textarea.getSelectedText()!=null)   
                    textarea.replaceSelection(replacetext.getText());  
			}
		});
		btnReplaceAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textarea.setCaretPosition(0);   //将光标放到编辑区开头      
                
                textString=textarea.getText();  
	            findString=findtext.getText();  
	            if(chckbxMatchCase.isSelected());//difference case
	            else//make no difference to case 
	            {
	            	 textString=textString.toLowerCase();   
	            	 findString=findString.toLowerCase();
	            } 
                while(k>-1){
                	
    	            if(textarea.getSelectedText()==null)  
               		 	k=textString.indexOf(findString,textarea.getCaretPosition()+1);  
               	 	else  
               	 		k=textString.indexOf(findString, textarea.getCaretPosition()-findtext.getText().length()+1);      
               	 	if(k>-1)  
               	 	{
               	 		textarea.setCaretPosition(k);  
               	 		textarea.select(k,k+findString.length());  
               	 	}  
               	 	else  
               	 	{   
               	 		if(replaceCount==0) 
               	 			JOptionPane.showMessageDialog(null,"Can't find \" "+findString+" \" !","Find",JOptionPane.INFORMATION_MESSAGE);  
               	 		else  
               	 		{   
               	 			JOptionPane.showMessageDialog(null,"Succeed replace "+replaceCount+" !","Notepad",JOptionPane.INFORMATION_MESSAGE);  
               	 		}
               	 	}
               	 	if(replacetext.getText().length()==0 && textarea.getSelectedText()!= null)  
               	 	{   
               	 		textarea.replaceSelection("");  
               	 		replaceCount++;  
               	 	}   
                   
               	 	if(replacetext.getText().length()>0 && textarea.getSelectedText()!= null)   
               	 	{   
               	 		textarea.replaceSelection(replacetext.getText());   
               	 		replaceCount++;  
               	 	}  
                
                }  
            
			}
		});
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource()==btnCancel)
					dispose();
			}
		});
	}
}
