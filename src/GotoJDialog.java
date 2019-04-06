import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class GotoJDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	JLabel lblLineNumber;
	JTextField linetext;
	JPanel buttonPane;
	JButton btnGoTo;
	JButton btnCancel;
	int num=1;
	/**
	 * Create the dialog.
	 */
	public GotoJDialog(JTextArea textarea) {
		setResizable(false);
		setModal(true);
		setTitle("Go To Line");
		setBounds(700, 300, 375, 195);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 335, 91);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		{
			lblLineNumber = new JLabel("Line number");
			lblLineNumber.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblLineNumber.setBounds(34, 13, 144, 37);
			contentPanel.add(lblLineNumber);
		}
		{
			linetext = new JTextField();
			linetext.setBounds(34, 54, 301, 29);
			linetext.setColumns(5);
			linetext.setText("1");  
			linetext.selectAll();
			linetext.requestFocus(true);
			contentPanel.add(linetext);
		}
		{
			buttonPane = new JPanel();
			buttonPane.setBounds(116, 98, 241, 49);
			getContentPane().add(buttonPane);
			buttonPane.setLayout(null);
			{
				btnGoTo = new JButton("Go To");
				
				btnGoTo.setBounds(15, 15, 95, 30);
				buttonPane.add(btnGoTo);
			}
			{
				btnCancel = new JButton("Cancel");
				btnCancel.setBounds(135, 15, 95, 30);
				btnCancel.setActionCommand("Cancel");
				buttonPane.add(btnCancel);
			}
		
			linetext.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					if(linetext.getText().isEmpty())
						btnGoTo.setEnabled(false);
					else{
						btnGoTo.setEnabled(true);
						btnGoTo.doClick();
					}
						
				}
			});
			btnGoTo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(btnGoTo.isEnabled()){
						int totalLine = textarea.getLineCount();  
		                int[] lineNumber = new int[totalLine + 1];  
		                String s = textarea.getText();  
		                int pos = 0, t = 0;  
		                while (true) {  
		                    pos = s.indexOf('\12', pos);   
		                    if (pos == -1)  
		                        break;  
		                    lineNumber[t++] = pos++;  
		                }  
		                try {  
		                    num = Integer.parseInt(linetext.getText());  
		                } catch (NumberFormatException efe) {  
		                    JOptionPane.showMessageDialog(null, "Please enter the lines !", "Message", JOptionPane.WARNING_MESSAGE);  
		                    linetext.requestFocus(true);  
		                    return;  
		                } 
		                if (num < 2 || num >= totalLine) {  
		                    if (num < 2)  
		                        textarea.setCaretPosition(0);  
		                    else  
		                        textarea.setCaretPosition(s.length());  
		                } else  
		                    textarea.setCaretPosition(lineNumber[num - 2] + 1);  
		  
		                dispose();  
					}
					
				}
			});
			
			btnCancel.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					dispose();
				}
				
			});
		}
	}

}
