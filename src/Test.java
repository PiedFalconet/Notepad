import javax.swing.JFrame;


public class Test {
	static Notepad notepad=new Notepad();
	
	public static void main(String[] args){ 
	    notepad.setVisible(true); 
	    notepad.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
