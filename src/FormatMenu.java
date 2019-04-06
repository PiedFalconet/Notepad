import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;


public class FormatMenu extends JMenu {
	
	private static final long serialVersionUID = 1L;
	public JCheckBoxMenuItem WordWarp;
	public JMenuItem MyFont;
	
	public FormatMenu(){
		setText("Format");
		setMnemonic('o');
		
		WordWarp=new JCheckBoxMenuItem("Word Warp");
		WordWarp.setMnemonic('W');
		MyFont=new JMenuItem("Font",'F');
		
		add(WordWarp);
		add(MyFont);
		
		Handler handler=new Handler();
		WordWarp.addActionListener(handler);
		MyFont.addActionListener(handler);
	}
	private class Handler implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(WordWarp.isSelected()&&e.getSource()==WordWarp)
			{
				Test.notepad.editText.setLineWrap(true);
				Test.notepad.view.StatusBar.setEnabled(false); 
				Test.notepad.scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			}
			else{
				Test.notepad.editText.setLineWrap(false);
				Test.notepad.view.StatusBar.setEnabled(true); 
				Test.notepad.scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			}
				
			if(e.getSource()==MyFont)
			{
				try 
				{
					MyFont dialog = new MyFont(Test.notepad.editText);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				}
				catch (Exception e1) 
				{
					e1.printStackTrace();
				}
			}
		}		
	}
	
	
}
