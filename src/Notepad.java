import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.Transferable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;


public class Notepad extends JFrame {
	private static final long serialVersionUID = 1L;
	public JMenuBar bar;//menu bar
	
	public FileMenu file;//menu
	public EditMenu edit;
	public FormatMenu format;
	public ViewMenu view;
	public HelpMenu help;
	
	public PopMenu pop;//pop up menu
	
	public JPanel contentPane;//Using a panel to contain all the component 
	
	public JTextArea editText;//text area
	public Toolkit toolkit=Toolkit.getDefaultToolkit();  
    public Clipboard clipBoard=toolkit.getSystemClipboard();
    
    public JScrollPane scroller;
	
    public JToolBar toolState;  //status bar 
    public static JLabel time;  
    public JLabel lines;  
    public JLabel words;  
   
    public String currentPath=null; 
    public String oldValue;
    
    boolean isNewFile=true;//是否新文件(未保存过的)  
    boolean isChange=true;//whether the file is changed
    File currentFile;//当前文件名  
    protected UndoManager undo=new UndoManager();  
    protected UndoableEditListener undoHandler=new UndoHandler(); 
    
    public int linenum = 1;  
    public int columnnum = 1; 
    public int length=0;  
    public int sum=0;   
    
    GregorianCalendar c=new GregorianCalendar();  
    int hour=c.get(Calendar.HOUR_OF_DAY);  
    int min=c.get(Calendar.MINUTE);  
    int second=c.get(Calendar.SECOND);   
    String str;
    
    
	public Notepad(){
		super("Notepad");
		setSize(1200,800);
		setLocationRelativeTo(null);
		
		if(currentFile==null)
        {
        	isNewFile=true;
        	setTitle("Untitled - Notepad");
        }  	
        else{
        	isNewFile=false;
        	setTitle(currentFile+" - Notepad");
        }
		
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/image/note.jpg"));
		
		//change the frame's look and feel
		try {  
	        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());  
	    } catch (ClassNotFoundException event1) {  
	        event1.printStackTrace();  
	    } catch (InstantiationException event2) {  
	        event2.printStackTrace();  
	    } catch (IllegalAccessException event3) {  
	        event3.printStackTrace();  
	    } catch (UnsupportedLookAndFeelException event4) {  
	        event4.printStackTrace();  
	    }  
		
		bar=new JMenuBar();//set MenuBar
		
		//initial menu bar
		setJMenuBar(bar);
		
		//initial menu
		file=new FileMenu();
		edit=new EditMenu();
		format=new FormatMenu();
		view=new ViewMenu();
		help=new HelpMenu();
		
		bar.add(file);
		bar.add(edit);
		bar.add(format);
		bar.add(view);
		bar.add(help);
			
		contentPane = new JPanel();  
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));  
        //设置边框布局  
        contentPane.setLayout(new BorderLayout(0, 0));  
        setContentPane(contentPane);  
          
        editText=new JTextArea(15,40); 
        editText.setFont(new Font("SimSun",Font.PLAIN,15));
        oldValue=editText.getText();
        //add scroll pane to text area, vertical&&horizontal 
        scroller=new JScrollPane(editText);  
        scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  
        scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        contentPane.add(scroller,BorderLayout.CENTER);
        
		
        //set status bar
		toolState = new JToolBar();  
        toolState.setSize(editText.getSize().width, 10);//toolState.setLayout(new FlowLayout(FlowLayout.LEFT));  
        JLabel space1 =new JLabel("                           ");
        JLabel space2 =new JLabel("                           ");
        JLabel space3 =new JLabel("                           ");
        JLabel space4 =new JLabel("                           ");
        JLabel space5 =new JLabel("                           ");
        
        toolState.add(space1);
        
        //set time label
        time = new JLabel("Time:" + hour + ":" + min + ":" + second);  
        toolState.add(time);  
        
        toolState.add(space2);
        toolState.addSeparator();  
        toolState.add(space3);
        
        //set lines label
        lines = new JLabel("Line: " + linenum + " ,  Column: " + columnnum+"                           ");  
        toolState.add(lines);  
        
        toolState.add(space4);
        toolState.addSeparator();  
        toolState.add(space5);
        
        //set words label
        words = new JLabel("Total: " +length+" words  ");  
        toolState.add(words);  
       
        
        editText.addCaretListener(new CaretListener() {          
            public void caretUpdate(CaretEvent e) {  
                JTextArea editText = (JTextArea)e.getSource();  
   
                try {  
                    int caretpos = editText.getCaretPosition();  
                    linenum = editText.getLineOfOffset(caretpos);  
                    columnnum = caretpos - editText.getLineStartOffset(linenum);  
                    linenum += 1;  
                    lines.setText("\tLine: " + linenum + " ,  Column: " + (columnnum+1));  
                    length=editText.getText().toString().length();  
                    words.setText("   Total: " +length+" words  ");  
                }  
                catch(Exception ex) { }  
            }});  
          
        contentPane.add(toolState, BorderLayout.SOUTH);  
        toolState.setVisible(false);  
        toolState.setFloatable(false);  
        Clock clock=new Clock();  
        clock.start();  
		
		//initial pop up menu
		pop=new PopMenu();
		
		editText.addMouseListener(new MouseAdapter()  
        {   public void mousePressed(MouseEvent e)  
            {   if(e.isPopupTrigger())//返回此鼠标事件是否为该平台的弹出菜单触发事件  
                {   pop.show(e.getComponent(),e.getX(),e.getY());//在组件调用者的坐标空间中的位置 X、Y 显示弹出菜单  
                }  
                checkpopEnabled();//设置剪切，复制，粘帖，删除等功能的可用性  
                editText.requestFocus();//编辑区获取焦点  
            }  
            public void mouseReleased(MouseEvent e)  
            {   if(e.isPopupTrigger())//返回此鼠标事件是否为该平台的弹出菜单触发事件  
                {   pop.show(e.getComponent(),e.getX(),e.getY());//在组件调用者的坐标空间中的位置 X、Y 显示弹出菜单  
                }  
                checkpopEnabled();//设置剪切，复制，粘帖，删除等功能的可用性  
                editText.requestFocus();//编辑区获取焦点  
            }  
        });//文本编辑区注册右键菜单事件结束  
		
		editText.getDocument().addUndoableEditListener(undoHandler);  
        editText.getDocument().addDocumentListener(new DocumentListener(){
        	 public void removeUpdate(DocumentEvent e)  
        	 {   
        		 edit.Undo.setEnabled(true);  
        		 pop.PopUndo.setEnabled(true);
        	    
        	 }  
        	 public void insertUpdate(DocumentEvent e)  
        	 {   
        		 edit.Undo.setEnabled(true); 
        		 pop.PopUndo.setEnabled(true);
        	 }  
        	 public void changedUpdate(DocumentEvent e)  
        	 {   
        		 edit.Undo.setEnabled(true);  
        		 pop.PopUndo.setEnabled(true);
        	 }//DocumentListener结束  
        	
        });  

        
		addWindowListener(new WindowAdapter()  {
           public void windowClosing(WindowEvent e)  
            {   
        		exitWindowChoose();  
        		
            }  
        });
		editText.requestFocus();
	}
	
	
	
	public void checkpopEnabled()  
	{   
		String selectText=editText.getSelectedText();  
	    if(selectText==null)  
      {     
          pop.PopCut.setEnabled(false);  
          pop.PopCopy.setEnabled(false);    
          pop.PopDelete.setEnabled(false);  
      }  
      else  
      {    
          pop.PopCut.setEnabled(true);   
          pop.PopCopy.setEnabled(true);  
          pop.PopDelete.setEnabled(true);  
      }  
      //粘帖功能可用性判断  
      Transferable contents=clipBoard.getContents(this);  
      
      if(contents==null)  
          pop.PopPaste.setEnabled(false);  
      else  
    	  pop.PopPaste.setEnabled(true);      
	}
	
	
	public void exitWindowChoose()  
	{   
		editText.requestFocus();  
		if(editText.getText().equals(oldValue))
			isChange=false;
		if(isChange)
		{
			try {
		
				ExitDialog dialog = new ExitDialog(this);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		else
			System.exit(0);
	}
	
	class UndoHandler implements UndoableEditListener  
    {   public void undoableEditHappened(UndoableEditEvent e)  
        {   
    		undo.addEdit(e.getEdit());  
        }  
    } 
	
	 
}

