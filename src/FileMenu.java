import java.awt.Graphics;
import java.awt.PrintJob;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.print.PageFormat;
import java.awt.print.PrinterJob;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;


public class FileMenu extends JMenu  {
	
	private static final long serialVersionUID = 1L;
	public JMenuItem New;
	public JMenuItem Open;
	public JMenuItem Save;
	public JMenuItem SaveAs;
	public JMenuItem PageSet;
	public JMenuItem Print;
	public JMenuItem Exit;
	
	public FileMenu(){
		setText("File");
		setMnemonic('F');
		
		New=new JMenuItem("New",'N');
		New.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,InputEvent.CTRL_MASK));
		Open=new JMenuItem("Open",'O');
		Open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,InputEvent.CTRL_MASK));
		Save=new JMenuItem("Save",'S');
		Save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,InputEvent.CTRL_MASK));
		SaveAs=new JMenuItem("Save As...",'A');
		PageSet=new JMenuItem("Page Setup...",'u');
		Print=new JMenuItem("Print...",'P');
		Print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
		Exit=new JMenuItem("Exit",'x');
		
		add(New);
		add(Open);
		add(Save);
		add(SaveAs);
		addSeparator();
		add(PageSet);
		add(Print);
		addSeparator();
		add(Exit);
		
		Handler handler=new Handler();
		New.addActionListener(handler);
		Open.addActionListener(handler);
		Save.addActionListener(handler);
		SaveAs.addActionListener(handler);
		PageSet.addActionListener(handler);
		Print.addActionListener(handler);
		Exit.addActionListener(handler);
		
	}
	private class Handler implements ActionListener{
		public void actionPerformed(ActionEvent event){
			if(event.getSource()==New)
				newFile();
			else if(event.getSource()==Open)
				openFile();
			else if(event.getSource()==Save)
				save();
			else if(event.getSource()==SaveAs)
				saveAs();
			else if(event.getSource()==PageSet)
				pageset();
			else if(event.getSource()==Print)
				print();
			else if(event.getSource()==Exit)
				exit();
			
		}
	}
	
	private void newFile(){
		Test.notepad.editText.requestFocus();
        String currentValue=Test.notepad.editText.getText();  
        boolean isTextChange=(currentValue.equals(Test.notepad.oldValue))?false:true;  
        if(isTextChange)  
        {   
        	try {
    			Check dialog = new Check(Test.notepad);
    			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    			dialog.setVisible(true);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
        }
         
        {   Test.notepad.editText.replaceRange("",0,Test.notepad.editText.getText().length());  
        	Test.notepad.setTitle("Untitled - Notepad");  
            Test.notepad.isNewFile=true;  
            Test.notepad.undo.discardAllEdits();//撤消所有的"撤消"操作  
            Test.notepad.edit.Undo.setEnabled(false);  
            Test.notepad.oldValue=Test.notepad.editText.getText();  
        }  
        	
	}
	
	private void openFile(){
		Test.notepad.editText.requestFocus();
        String currentValue=Test.notepad.editText.getText();  
        boolean isTextChange=(currentValue.equals(Test.notepad.oldValue))?false:true;  
        if(isTextChange)  
        {   
        	try {
    			Check dialog = new Check(Test.notepad);
    			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    			dialog.setVisible(true);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
        }
        {   
        	String str=null;  
        	JFileChooser fileChooser=new JFileChooser();  
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);    
            fileChooser.setDialogTitle("Open");  
            int result=fileChooser.showOpenDialog(this);  
            if(result==JFileChooser.APPROVE_OPTION)  
            {  // Test.notepad.statusLabel.setText(" 您没有选择任何文件 ");  
            	File fileName=fileChooser.getSelectedFile(); 
            	if(fileName==null || fileName.getName().equals(""))  
                {   
            		JOptionPane.showMessageDialog(this,"Can't Open this !","Notepad",JOptionPane.ERROR_MESSAGE);  
                }  
                else  
                {   try  
                    {   
                		InputStreamReader ir=new InputStreamReader(new FileInputStream(fileName),"GBK");
                		//FileReader fr=new FileReader(fileName);  
                        BufferedReader bfr=new BufferedReader(ir);
                        Test.notepad.editText.setText("");  
                        while((str=bfr.readLine())!=null)  
                        {   
                        	str+='\n';  
                        	Test.notepad.editText.append(str);  
                        }  
                        Test.notepad.setTitle(fileName.getName()+" - Notepad");  
                        ir.close();  
                        Test.notepad.isNewFile=false;  
                        Test.notepad.currentFile=fileName;  
                        Test.notepad.oldValue=Test.notepad.editText.getText();  
                    }  
                    catch (IOException ioException)  
                    {  
                    }  
                }  
            }
        }
	}
	
	private void save(){
		Test.notepad.editText.requestFocus();
        String currentValue=Test.notepad.editText.getText();  
        boolean isTextChange=(currentValue.equals(Test.notepad.oldValue))?false:true;  
        if(isTextChange)  
        {   
        	try {
    			Check dialog = new Check(Test.notepad);
    			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    			dialog.setVisible(true);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
        }
        Test.notepad.isNewFile=false; 
        Test.notepad.oldValue=Test.notepad.editText.getText();
        
	}
	
	private void saveAs(){
		Test.notepad.editText.requestFocus();
        String currentValue=Test.notepad.editText.getText();  
        boolean isTextChange=(currentValue.equals(Test.notepad.oldValue))?false:true;  
        if(isTextChange)  
        {   
        	try {
    			Check dialog = new Check(Test.notepad);
    			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    			dialog.setVisible(true);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
        }
	}
	
	private void pageset(){
		PageFormat format = new PageFormat();  
        PrinterJob.getPrinterJob().pageDialog(format);  
	}
	
	private void print(){
		try{  
        	PrintJob  p=null;
        	Graphics  g=null;//要打印的对象  
            p = getToolkit().getPrintJob(Test.notepad,"OK",null);  
            g = p.getGraphics();//p 获取一个用于打印的 Graphics 的对象  
            Test.notepad.editText.printAll(g);  
            p.end();//释放对象 g    
        }  
        catch(Exception a){  
        }   
	}

	private void exit(){
		Test.notepad.exitWindowChoose();
		
	}
}
