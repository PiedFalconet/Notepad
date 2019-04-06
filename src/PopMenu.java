import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.undo.CannotUndoException;


public class PopMenu extends JPopupMenu {
	private static final long serialVersionUID = 1L;
	public JMenuItem PopUndo;
	public JMenuItem PopCut;
	public JMenuItem PopCopy;
	public JMenuItem PopPaste;
	public JMenuItem PopDelete;
	public JMenuItem PopSelectAll;
	public JMenuItem RToL;
	public JMenuItem Show;
	public JMenuItem OpenIME;
	public JMenuItem Reconversion;
	public JMenu Insert;
	
	public PopMenu(){
		PopUndo=new JMenuItem("Undo");
		PopUndo.setEnabled(false);
		PopCut=new JMenuItem("Cut");
		PopCut.setEnabled(false);
		PopCopy=new JMenuItem("Copy");
		PopCopy.setEnabled(false);
		PopPaste=new JMenuItem("Paste");
		PopDelete=new JMenuItem("Delete");
		PopDelete.setEnabled(false);
		PopSelectAll=new JMenuItem("Select All");
		PopSelectAll.setEnabled(false);
		RToL=new JMenuItem("Right to left Reading order");
		Show=new JMenuItem("Show Unicode control characters");
		OpenIME=new JMenuItem("Open IME");
		Reconversion=new JMenuItem("Reconversion");
		Reconversion.setEnabled(false);
		
		Insert=new JMenu("Insert Unicode control character");
		JMenuItem LRM,RLM,ZWJ,ZWNJ,LRE,RLE,LRO,RLO,PDF,NADS,NODS,ASS,ISS,AAFS,IAFS,RS,US;
		
		LRM=new JMenuItem("LRM");
		RLM=new JMenuItem("RLM");
		ZWJ=new JMenuItem("ZWJ");
		ZWNJ=new JMenuItem("ZWNJ");
		LRE=new JMenuItem("LRE");
		RLE=new JMenuItem("RLE");
		LRO=new JMenuItem("LRO");
		RLO=new JMenuItem("RLO");
		PDF=new JMenuItem("PDF");
		NADS=new JMenuItem("NADS");
		NODS=new JMenuItem("NODS");
		ASS=new JMenuItem("ASS");
		ISS=new JMenuItem("ISS");
		AAFS=new JMenuItem("AAFS");
		IAFS=new JMenuItem("IAFS");
		RS=new JMenuItem("RS");
		US=new JMenuItem("US");
		
		add(PopUndo);
		addSeparator();
		add(PopCut);
		add(PopCopy);
		add(PopPaste);
		add(PopDelete);
		addSeparator();
		
		add(PopSelectAll);
		addSeparator();
		add(RToL);
		add(Show);
		add(Insert);
		addSeparator();
		add(OpenIME);
		add(Reconversion);
		
		Insert.add(LRM);
		Insert.add(RLM);
		Insert.add(ZWJ);
		Insert.add(ZWNJ);
		Insert.add(LRE);
		Insert.add(RLE);
		Insert.add(LRO);
		Insert.add(RLO);
		Insert.add(PDF);
		Insert.add(NADS);
		Insert.add(NODS);
		Insert.add(ASS);
		Insert.add(ISS);
		Insert.add(AAFS);
		Insert.add(IAFS);
		Insert.add(RS);
		Insert.add(US);
		
		
		Handler handler=new Handler();
		PopUndo.addActionListener(handler);
		PopCut.addActionListener(handler);
		PopCopy.addActionListener(handler);
		PopPaste.addActionListener(handler);
		PopDelete.addActionListener(handler);
		PopSelectAll.addActionListener(handler);
	}
	private class Handler implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(e.getSource()==PopUndo)
			{
				if(PopUndo.isEnabled())
					undo();
			}
			else if(e.getSource()==PopCut)
			{
				if(PopCut.isEnabled())
					cut();
			}
			else if(e.getSource()==PopCopy)
			{
				if(PopCopy.isEnabled())
					copy();
			}
			else if(e.getSource()==PopPaste)
				paste();
			else if(e.getSource()==PopDelete)
			{
				if(PopDelete.isEnabled())
					delete();
			}
			else if(e.getSource()==PopSelectAll)
				selectAll();
		}
	}
	private void undo(){
		Test.notepad.editText.requestFocus();  
        if(Test.notepad.undo.canUndo())  
        {   try  
            {   Test.notepad.undo.undo();  
            }  
            catch (CannotUndoException ex)  
            {   System.out.println("Unable to undo:" + ex);  
               
            }  
        }  
        if(!Test.notepad.undo.canUndo())  
        	PopUndo.setEnabled(false);  
	}
	private void cut(){
		Test.notepad.editText.requestFocus();  
        String text=Test.notepad.editText.getSelectedText();  
        StringSelection selection=new StringSelection(text);  
        Test.notepad.clipBoard.setContents(selection,null);  
        Test.notepad.editText.replaceRange("",Test.notepad.editText.getSelectionStart(),Test.notepad.editText.getSelectionEnd());  
        Test.notepad.checkpopEnabled();
	}
	private void copy(){
		 Test.notepad.editText.requestFocus();  
         String text=Test.notepad.editText.getSelectedText();  
         StringSelection selection=new StringSelection(text);  
         Test.notepad.clipBoard.setContents(selection,null);  
         Test.notepad.checkpopEnabled();
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
        Test.notepad.checkpopEnabled();
	}
	private void delete(){
		Test.notepad.editText.requestFocus();  
    	Test.notepad.editText.replaceRange("",Test.notepad.editText.getSelectionStart(),Test.notepad.editText.getSelectionEnd());  
    	 Test.notepad.checkpopEnabled(); 
	}
	private void selectAll(){
		Test.notepad.editText.selectAll(); 
	}
	
}
