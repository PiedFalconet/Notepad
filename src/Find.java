import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JLabel;
import javax.swing.JCheckBox;



import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.Font;

import javax.swing.JRadioButton;


public class Find extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField findtext;
	private JCheckBox chckbxMatchCase;
	private JLabel lblFindWhat;
	private JPanel directionPanel;
	private ButtonGroup group;
	private JPanel buttonPane;
	private JButton btnFindNext;
	private JButton btnCancel;
	private String textString;
	private String findString; 
	private JRadioButton rdbtnUp;
	private JRadioButton rdbtnDown;

	public Find(Notepad n) {
		setResizable(false);
		setTitle("Find");
		setBounds(700, 300, 500, 200);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 340, 160);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		lblFindWhat = new JLabel("Find what :");
		lblFindWhat.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblFindWhat.setBounds(10, 30, 95, 35);
		contentPanel.add(lblFindWhat);
	
		chckbxMatchCase = new JCheckBox("Match case");
		chckbxMatchCase.setBounds(30, 105, 100, 25);
		chckbxMatchCase.setFont(new Font("Tahoma", Font.PLAIN, 13));
		contentPanel.add(chckbxMatchCase);	
		
		findtext = new JTextField();
		findtext.setBounds(105, 30, 230, 35);
		findtext.setColumns(20);
		findtext.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPanel.add(findtext);
		
		//set direction panel
		directionPanel = new JPanel();
		directionPanel.setBounds(160, 80, 175, 70);
		directionPanel.setBorder(BorderFactory.createTitledBorder("Direction"));  
		
		rdbtnUp = new JRadioButton("Up");
		directionPanel.add(rdbtnUp);
		
		rdbtnDown = new JRadioButton("Down");
		rdbtnDown.setSelected(true);
		directionPanel.add(rdbtnDown);
		
		group = new ButtonGroup();  
		group.add(rdbtnUp);
		group.add(rdbtnDown);
		
		contentPanel.add(directionPanel);
		
		//set button panel
		buttonPane = new JPanel();
		buttonPane.setBounds(350, 25, 115, 90);
		getContentPane().add(buttonPane);
		
		buttonPane.setLayout(new GridLayout(2, 0, 0, 5));
		
		btnFindNext = new JButton("Find Next");
		btnFindNext.setEnabled(false);
		buttonPane.add(btnFindNext);
		
		btnCancel = new JButton("Cancel");
		buttonPane.add(btnCancel);
		
	
		findtext.getDocument().addDocumentListener(new DocumentListener(){
       	 public void removeUpdate(DocumentEvent e)  
       	 {   
       	 }  
       	 public void insertUpdate(DocumentEvent e)  
       	 {   
       		btnFindNext.setEnabled(true);
       	 }  
       	 public void changedUpdate(DocumentEvent e)  
       	 {  
       		btnFindNext.setEnabled(true);
       	 }//DocumentListener结束  
       	
       });  
		findtext.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				if(findtext.getText().isEmpty())
					btnFindNext.setEnabled(false);
				else
				{
					btnFindNext.setEnabled(true);
					btnFindNext.doClick();
				}
			}
		});

		
		btnFindNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 int k=0;  
	             
	             textString=n.editText.getText();  
	             findString=findtext.getText();  
	             if(chckbxMatchCase.isSelected())//区分大小写  
	            	 ;
	             else//不区分大小写,此时把所选内容全部化成大写(或小写)，以便于查找   
	             {
	            	 textString=textString.toLowerCase();   
	            	 findString=findString.toLowerCase();
	             }  
	             if(rdbtnUp.isSelected())  
	             {   
	            	 if(n.editText.getSelectedText()==null)  
	            		 k=textString.lastIndexOf(findString,n.editText.getCaretPosition()-1);  
	            	 else  
	            		 k=textString.lastIndexOf(findString, n.editText.getCaretPosition()-findtext.getText().length()-1);      
	            	 if(k>-1)  
	            	 {   
	            		 n.editText.setCaretPosition(k);  
	            		 n.editText.select(k,k+findString.length());  
	            	 }  
	            	 else  
	            	 {   
	            		 JOptionPane.showMessageDialog(null,"Can't find \" "+findString+" \" !","Find",JOptionPane.INFORMATION_MESSAGE);  
	                 }  
	             }  
	             else if(rdbtnDown.isSelected())  
	             {   
	            	 if(n.editText.getSelectedText()==null)  
	            		 k=textString.indexOf(findString,n.editText.getCaretPosition()+1);  
	            	 else  
	            		 k=textString.indexOf(findString, n.editText.getCaretPosition()-findtext.getText().length()+1);      
	            	 if(k>-1)  
	            	 {
	            		 n.editText.setCaretPosition(k);  
	            		 n.editText.select(k,k+findString.length());  
	            	 }  
	            	 else  
	            	 {   
	            		 JOptionPane.showMessageDialog(null,"Can't find \" "+findString+" \" !","Find",JOptionPane.INFORMATION_MESSAGE);  
	            	 }
	            	 n.str=findString;
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

	
