import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.Calendar;

import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.undo.CannotUndoException;


public class EditMenu extends JMenu {
	
	private static final long serialVersionUID = 1L;
	public JMenuItem Undo;
	public JMenuItem Cut;
	public JMenuItem Copy;
	public JMenuItem Paste;
	public JMenuItem Delete;
	public JMenuItem Find;
	public JMenuItem FindNext;
	public JMenuItem Replaceitem;
	public JMenuItem GoTo;
	public JMenuItem SelectAll;
	public JMenuItem TimeDate;

	
	public EditMenu(){
		setText("Edit");
		setMnemonic('E');
		
		Undo=new JMenuItem("Undo",'U');
		Undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,InputEvent.CTRL_MASK));  
		Undo.setEnabled(false); 
		
		Cut=new JMenuItem("Cut",'t');
		Cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,InputEvent.CTRL_MASK));
		Cut.setEnabled(false);
		
		Copy=new JMenuItem("Copy",'C');
		Copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,InputEvent.CTRL_MASK));
		Copy.setEnabled(false);
		
		Paste=new JMenuItem("Paste",'P');
		Paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,InputEvent.CTRL_MASK));
		
		Delete=new JMenuItem("Delete",'l');
		Delete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE,0)); 
		Delete.setEnabled(false);
		
		Find=new JMenuItem("Find...",'F');
		Find.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,InputEvent.CTRL_MASK));
		Find.setEnabled(false);		
		
		FindNext=new JMenuItem("Find Next",'N');
		FindNext.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3,0));
		FindNext.setEnabled(false);
		
		Replaceitem=new JMenuItem("Replace...",'R');
		Replaceitem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_MASK));
		
		GoTo=new JMenuItem("Go To...",'G');
		GoTo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_MASK));
		
		
		SelectAll=new JMenuItem("Select All",'A');
		SelectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
		SelectAll.setEnabled(false);
		
		TimeDate=new JMenuItem("Time/Date",'D');
		TimeDate.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5,0));
		
		add(Undo);
		addSeparator();
		add(Cut);
		add(Copy);
		add(Paste);
		add(Delete);
		addSeparator();
		add(Find);
		add(FindNext);
		add(Replaceitem);
		add(GoTo);
		addSeparator();
		add(SelectAll);
		add(TimeDate);
		
		addMenuListener(new MenuListener(){
			public void menuCanceled(MenuEvent e)//when menu is canceled 
            {   checkMenuItemEnabled();
          //set cut, copy, paste, delete, select all, find, find next status
            }  
            public void menuDeselected(MenuEvent e)//when menu is deselected  
            {   checkMenuItemEnabled();
          //set cut, copy, paste, delete, select all, find, find next status
            }  
            public void menuSelected(MenuEvent e)//when menu is selected
            {   checkMenuItemEnabled();
          //set cut, copy, paste, delete, select all, find, find next status
            }  
		});
		
		Handler handler=new Handler();
		Undo.addActionListener(handler);
		Cut.addActionListener(handler);
		Copy.addActionListener(handler);
		Paste.addActionListener(handler);
		Delete.addActionListener(handler);
		Find.addActionListener(handler);
		FindNext.addActionListener(handler);
		Replaceitem.addActionListener(handler);
		GoTo.addActionListener(handler);
		SelectAll.addActionListener(handler);
		TimeDate.addActionListener(handler);
		
		
	}
	
	private class Handler implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(e.getSource()==Undo)
			{
				if(Undo.isEnabled())
					undo();
			}
			else if(e.getSource()==Cut)
			{
				if(Cut.isEnabled())
					cut();
			}
			else if(e.getSource()==Copy)
			{
				if(Copy.isEnabled())
					copy();
			}
			else if(e.getSource()==Paste)
			{
				paste();
			}
			else if(e.getSource()==Delete)
			{
				if(Delete.isEnabled())
					delete();
			}
			else if(e.getSource()==Find)
			{
				if(Find.isEnabled())
					find();
			}
			else if(e.getSource()==FindNext)
			{
				if(FindNext.isEnabled())
					findNext(Test.notepad.str);
			}
			else if(e.getSource()==Replaceitem)
				replace();
			
			else if(e.getSource()==GoTo)
					goTo();
			
			else if(e.getSource()==SelectAll)
			{	
				if(SelectAll.isEnabled())
					selectAll();
			}
			else if(e.getSource()==TimeDate)
				timedate();
		}
	}
	
	
	private void undo(){
		Test.notepad.editText.requestFocus();  
        if(Test.notepad.undo.canUndo())  
        {   try  
            {   Test.notepad.undo.undo();  
            }  
            catch (CannotUndoException ex)  
            {   
            	System.out.println("Unable to undo:" + ex);  
            }  
        }  
        if(!Test.notepad.undo.canUndo())  
        	Undo.setEnabled(false); 
	}
	
	private void cut(){
		Test.notepad.editText.requestFocus();  
        String text=Test.notepad.editText.getSelectedText();  
        StringSelection selection=new StringSelection(text);  
        Test.notepad.clipBoard.setContents(selection,null);  
        Test.notepad.editText.replaceRange("",Test.notepad.editText.getSelectionStart(),Test.notepad.editText.getSelectionEnd());  
        checkMenuItemEnabled();
	}
	
	private void copy(){
		 Test.notepad.editText.requestFocus();  
         String text=Test.notepad.editText.getSelectedText();  
         StringSelection selection=new StringSelection(text);  
         Test.notepad.clipBoard.setContents(selection,null);  
         checkMenuItemEnabled();
	}
	
	private void paste(){
		Test.notepad.editText.requestFocus();  
        Transferable contents=Test.notepad.clipBoard.getContents(this);  
        if(contents==null)return;  
        String text="";  
        try  
        {   text=(String)contents.getTransferData(DataFlavor.stringFlavor);  
        }  
        catch (Exception exception)  
        {  
        }  
        Test.notepad.editText.replaceRange(text,Test.notepad.editText.getSelectionStart(),Test.notepad.editText.getSelectionEnd());  
        checkMenuItemEnabled(); 
	}
	
	private void delete(){
		Test.notepad.editText.requestFocus();  
    	Test.notepad.editText.replaceRange("",Test.notepad.editText.getSelectionStart(),Test.notepad.editText.getSelectionEnd());  
    	checkMenuItemEnabled(); 
	}
	
	private void replace(){
		Test.notepad.editText.requestFocus();
		try {
			Replace dialog = new Replace(Test.notepad.editText);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		checkMenuItemEnabled();
	}
	
	private void findNext(String string){
		Test.notepad.editText.requestFocus();
		int k=0;  
        String textString=Test.notepad.editText.getText();  
       	textString=textString.toLowerCase();   
       	string=string.toLowerCase();
        
       	 if(Test.notepad.editText.getSelectedText()==null)  
       		 k=textString.indexOf(string,Test.notepad.editText.getCaretPosition()+1);  
       	 else  
       		 k=textString.indexOf(string, Test.notepad.editText.getCaretPosition()-string.length()+1);      
       	 if(k>-1)  
       	 {
       		 Test.notepad.editText.setCaretPosition(k);  
       		 Test.notepad.editText.select(k,k+string.length());  
       	 }  
       	 else  
       	 {   
       		 JOptionPane.showMessageDialog(null,"Can't find \" "+string+" \" !","Find",JOptionPane.INFORMATION_MESSAGE);  
       	 }  
		checkMenuItemEnabled();
	}
	
	private void find(){
		Test.notepad.editText.requestFocus();
		try {
			Find dialog = new Find(Test.notepad);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		checkMenuItemEnabled();
	}
	
	private void goTo(){
		Test.notepad.editText.requestFocus();
		try {
			GotoJDialog dialog = new GotoJDialog(Test.notepad.editText);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void selectAll(){
		Test.notepad.editText.selectAll(); 
		checkMenuItemEnabled(); 
		
	}
	
	private void timedate(){
		Test.notepad.editText.requestFocus(); 
        Calendar c = Calendar.getInstance();//可以对每个时间域单独修改   
        int year = c.get(Calendar.YEAR);  
        int month = c.get(Calendar.MONTH);   
        int date = c.get(Calendar.DATE);    
        int hour = c.get(Calendar.HOUR_OF_DAY);   
        int minute = c.get(Calendar.MINUTE);   
        int second = c.get(Calendar.SECOND);    
        Test.notepad.editText.insert(year + "/" + month + "/" + date + " " +hour + ":" +minute + ":" + second,Test.notepad.editText.getCaretPosition());  
	}
	
	public void checkMenuItemEnabled()  
	 {   
		//set cut, copy, delete, select all status
		String selectText=Test.notepad.editText.getSelectedText();  
	    if(selectText==null)  
       {   Cut.setEnabled(false);  
           Copy.setEnabled(false);  
           Delete.setEnabled(false);  
           
       }  
       else  
       {   
    	   Cut.setEnabled(true);   
           Copy.setEnabled(true);  
           Delete.setEnabled(true);  
           if(selectText.equals(Test.notepad.editText.getText())) 
        	   SelectAll.setEnabled(false);
       } 
	   
	    
       //set paste status
       Transferable contents=Test.notepad.clipBoard.getContents(this);  
       
       if(contents==null)  
    	   Paste.setEnabled(false);  
       else  
    	   Paste.setEnabled(true);  
       
       //set find, find next, select all status
       
       String text=Test.notepad.editText.getText();
       if(text.isEmpty())
       {   
    	   Find.setEnabled(false);
    	   SelectAll.setEnabled(false);
       }
       else{
    	   
    	   Find.setEnabled(true);
    	   SelectAll.setEnabled(true);
       }
       if(Test.notepad.str==null||Test.notepad.str.equals(""))
    	   FindNext.setEnabled(false);
       else
    	   FindNext.setEnabled(true);
              
   }
	
}
