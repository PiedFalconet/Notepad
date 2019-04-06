import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;


public class HelpMenu extends JMenu {
	private static final long serialVersionUID = 1L;
	public JMenuItem ViewHelp;
	public JMenuItem AboutNotepad; 
	
	public HelpMenu(){
		setText("Help");
		setMnemonic('H');
		
		ViewHelp=new JMenuItem("View Help");
		AboutNotepad=new JMenuItem("About Notepad");
		
		add(ViewHelp);
		addSeparator();
		add(AboutNotepad);
		
		Handler handler=new Handler();
		ViewHelp.addActionListener(handler);
		AboutNotepad.addActionListener(handler);
	}
	private class Handler implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(e.getSource()==ViewHelp)
				viewHelp();
			else if(e.getSource()==AboutNotepad)
				aboutNotepad();
		}
	}
	public void viewHelp(){
		JDialog help=new JDialog();
		help.setTitle("Help");
		help.setSize(600,200);
		help.setResizable(false);
		JPanel panel=new JPanel();
		help.setLocationRelativeTo(null);
		help.setBackground(Color.WHITE);
		JLabel title=new JLabel("More Information at the Following Website:");
		title.setFont(new Font("Airal",Font.CENTER_BASELINE,20));
		title.setBounds(85,10, 460, 35);
		JLabel web=new JLabel("https://answers.microsoft.com/en-us/windows/forum/apps_windows_10");
		web.setFont(new Font("Airal",Font.CENTER_BASELINE,15));
		web.setBounds(45,60, 550, 30);
		help.add(panel);
		panel.setLayout(null);
		panel.add(title);
		panel.add(web);
		help.setModal(true);
		help.setVisible(true);
		
	}
	private void aboutNotepad(){
		JDialog about=new JDialog();
		about.setTitle("About");
		about.setSize(600,350);
		about.setResizable(false);
		about.setLocationRelativeTo(null);
		about.setBackground(Color.WHITE);
		JPanel panel=new JPanel();
		panel.setLayout(null);
		JLabel note=new JLabel("Notepad");
		note.setFont(new Font("Times New Roman",Font.CENTER_BASELINE,40));
		note.setBounds(230, 20, 400, 45);
		JLabel line=new JLabel("！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
		line.setBounds(40, 50, 600, 25);
		JLabel author=new JLabel("            Author:  Yaxin Luo");
		author.setFont(new Font("Times New Roman",Font.CENTER_BASELINE,25));
		author.setBounds(150, 120, 400, 30);
		JLabel no=new JLabel("Student Number:  24320162202883");
		no.setFont(new Font("Times New Roman",Font.CENTER_BASELINE,25));
		no.setBounds(125, 200, 400, 30);
		JButton okbutton=new JButton("OK");
		okbutton.setBounds(425, 250, 100, 40);
		okbutton.setFont(new Font("Airal",Font.PLAIN,12));
		okbutton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(e.getSource()==okbutton)
					about.dispose();
			}
		});
		panel.add(note);
		panel.add(line);
		panel.add(author);
		panel.add(no);
		panel.add(okbutton);
		about.add(panel);
		about.setModal(true);
		about.setVisible(true);
	}
}
