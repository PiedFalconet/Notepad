import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;


public class ViewMenu extends JMenu{
	private static final long serialVersionUID = 1L;
	public JCheckBoxMenuItem StatusBar;
	
	public ViewMenu(){
		setText("View");
		setMnemonic('V');
		
		StatusBar=new JCheckBoxMenuItem("Status Bar");
		StatusBar.setMnemonic('S');
		StatusBar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(StatusBar.isSelected()&&e.getSource()==StatusBar)
					Test.notepad.toolState.setVisible(true);//show status bar
				else   
					Test.notepad.toolState.setVisible(false);  
			}
		});
		add(StatusBar);
	}
	
}
