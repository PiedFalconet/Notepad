import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GraphicsEnvironment;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.BoxLayout;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class MyFont extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField styletext;
	private JTextField sizetext;
	private JTextField fonttext;
	private JScrollPane fontscroll;
	private JScrollPane stylescroll;
	private JScrollPane sizescroll;
	private GraphicsEnvironment en=GraphicsEnvironment.getLocalGraphicsEnvironment();  
    private final String fontName[]=en.getAvailableFontFamilyNames();
	private final int style[]={Font.PLAIN,Font.BOLD,Font.ITALIC,Font.BOLD+Font.ITALIC};  
	private final String fontStyle[]={"Regular","Italic","Bold","Bold Italic"};
	private final String fontSize[]={"8","9","10","11","12","14","16","18","20","22","24","26","28","36","48","72"};  
	private JLabel lblSample;
	private JList<String> fontlist;
	private JList<String> stylelist;
	private JList<String> sizelist;
	private Font sampleFont1;
	private Font sampleFont2;
	private Font sampleFont3;
	private Font sampleFont4;
	private JPanel fontpanel;
	private JPanel stylepanel;
	private JPanel sizepanel;
	private JPanel samplepanel;
	private JButton okButton;
	private JButton cancelButton;
	private Font currentFont;
	
	/**
	 * Create the dialog.
	 */
	public MyFont(JTextArea textarea) {
		setTitle( "Font");
		setResizable(false);
		setModal(true);
		setBounds(700, 250, 420, 500);
		
		currentFont=textarea.getFont();
		
		getContentPane().setLayout(null);
		contentPanel.setBounds(8, 8, 400, 210);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));
		
		//sample
		samplepanel = new JPanel();
		samplepanel.setBounds(15, 240, 380, 125);
		getContentPane().add(samplepanel);
		samplepanel.setBorder(BorderFactory.createTitledBorder("Sample"));
		
		lblSample = new JLabel("AaBbYyZz");
		lblSample.setPreferredSize(new Dimension(350,100));
		lblSample.setFont(currentFont);
		samplepanel.add(lblSample);	 
			
		
		//font panel
		fontpanel = new JPanel();
		fontpanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		contentPanel.add(fontpanel);
		fontpanel.setLayout(new BoxLayout(fontpanel, BoxLayout.Y_AXIS));
		
		JLabel lblFont = new JLabel("Font :");
		lblFont.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblFont.setFont(new Font("Tahoma", Font.PLAIN, 16));
		fontpanel.add(lblFont);
	
		fonttext = new JTextField();
			
		//set font as the text using font
		fonttext.setText(currentFont.getFontName());  
	    fonttext.selectAll();  
		fonttext.setColumns(15);
		fonttext.setMaximumSize(new Dimension(400, 50));
		fonttext.setPreferredSize(new Dimension(250, 30));
		fontpanel.add(fonttext);
		
	    fontlist=new JList<>(fontName);
		fontlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
		fontscroll=new JScrollPane(fontlist);
		fontscroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		fontpanel.add(fontscroll);
		
			
		//style panel
		stylepanel = new JPanel();
		contentPanel.add(stylepanel);
		stylepanel.setLayout(new BoxLayout(stylepanel, BoxLayout.Y_AXIS));
		
		JLabel lblFontStyle = new JLabel("Font style :");
		lblFontStyle.setFont(new Font("Tahoma", Font.PLAIN, 16));
		stylepanel.add(lblFontStyle);
	
		styletext = new JTextField();
		styletext.setColumns(10);
		if(currentFont.getStyle()==Font.PLAIN)  
			styletext.setText("Regular");  
		else if(currentFont.getStyle()==Font.BOLD)  
			styletext.setText("Bold");  
		else if(currentFont.getStyle()==Font.ITALIC)  
			styletext.setText("Italic");  
		else if(currentFont.getStyle()==(Font.BOLD+Font.ITALIC))  
			styletext.setText("Bold Italic");
		styletext.setMaximumSize(new Dimension(400, 40));
		styletext.setPreferredSize(new Dimension(100, 30));
		stylepanel.add(styletext);
			
		stylelist=new JList<>(fontStyle);
		stylescroll=new JScrollPane(stylelist);
		stylelist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		stylescroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		stylescroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		stylepanel.add(stylescroll);
		
		
		
		//size panel
		sizepanel = new JPanel();
		contentPanel.add(sizepanel);
		sizepanel.setLayout(new BoxLayout(sizepanel, BoxLayout.Y_AXIS));
		
		JLabel lblSize = new JLabel("Size :");
		lblSize.setFont(new Font("Tahoma", Font.PLAIN, 16));
		sizepanel.add(lblSize);
	
		sizetext = new JTextField();
		sizetext.setColumns(5);
		String str=String.valueOf(currentFont.getSize());  
	    sizetext.setText(str); 
		sizetext.setMaximumSize(new Dimension(500, 50));
		sizetext.setPreferredSize(new Dimension(125, 30));
		sizepanel.add(sizetext);
	
		
		sizelist=new JList<>(fontSize);
		sizelist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		sizescroll=new JScrollPane(sizelist);
		sizescroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		sizescroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		sizepanel.add(sizescroll);
	
		JPanel buttonPane = new JPanel();
		buttonPane.setBounds(140, 390, 260, 50);
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane);
		
		okButton = new JButton("OK");
		okButton.setActionCommand("OK");
		okButton.setPreferredSize(new Dimension(100,35));
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
		
		cancelButton = new JButton("Cancel");	
		cancelButton.setActionCommand("Cancel");
		cancelButton.setPreferredSize(new Dimension(100,35));
		buttonPane.add(cancelButton);
		

		fontlist.addListSelectionListener(new ListSelectionListener()  {   
			public void valueChanged(ListSelectionEvent event){ 
				int i=0;
				fonttext.setText(fontlist.getSelectedValue());  
				fonttext.selectAll();  
				if(styletext.getText().equals("Regular"))  
					i=0;  
				else if(styletext.getText().equals("Bold"))  
					i=1; 
				else if(styletext.getText().equals("Italic"))  
					i=2; 
				else if(styletext.getText().equals("Bold Italic"))  
					i=3;
				sampleFont1=new Font(fonttext.getText(),style[i],Integer.parseInt(sizetext.getText())); 
				lblSample.setFont(sampleFont1);  
			}  
		}); 
		stylelist.addListSelectionListener(new ListSelectionListener() { 
			public void valueChanged(ListSelectionEvent event)  {
				int s=stylelist.getSelectedIndex();  
				styletext.setText(fontStyle[s]); 
				styletext.selectAll();  
				int i=0;
				if(styletext.getText().equals("Regular"))  
					i=0;  
				else if(styletext.getText().equals("Bold"))  
					i=1; 
				else if(styletext.getText().equals("Italic"))  
					i=2; 
				else if(styletext.getText().equals("Bold Italic"))  
					i=3;
				sampleFont2=new Font(fonttext.getText(),style[i],Integer.parseInt(sizetext.getText())); 
				lblSample.setFont(sampleFont2);  
			}  
		});  
		sizelist.addListSelectionListener(new ListSelectionListener()  
	    {   public void valueChanged(ListSelectionEvent event)  
	        {   
	    		sizetext.setText(fontSize[sizelist.getSelectedIndex()]);    
	        	sizetext.selectAll();  
	        	int i=0;
	        	if(styletext.getText().equals("Regular"))  
					i=0;  
				else if(styletext.getText().equals("Bold"))  
					i=1; 
				else if(styletext.getText().equals("Italic"))  
					i=2; 
				else if(styletext.getText().equals("Bold Italic"))  
					i=3;
		        sampleFont3=new Font(fonttext.getText(),style[i],Integer.parseInt(sizetext.getText())); 
	            lblSample.setFont(sampleFont3);  
	        }  
	    });
		sizetext.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try{
					int num=Integer.parseInt(sizetext.getText());
					int i=0;
		        	if(styletext.getText().equals("Regular"))  
						i=0;  
					else if(styletext.getText().equals("Bold"))  
						i=1; 
					else if(styletext.getText().equals("Italic"))  
						i=2; 
					else if(styletext.getText().equals("Bold Italic"))  
						i=3;
					sampleFont4=new Font(fonttext.getText(),style[i],num); 
					lblSample.setFont(sampleFont4);  
				}
				catch(NumberFormatException e1){
					JOptionPane.showMessageDialog(null, "Size must be a number !", 
							"Font",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		okButton.addActionListener(new ActionListener()  
	    {   public void actionPerformed(ActionEvent e)  
	        {   Font okFont=new Font(fonttext.getText(),style[stylelist.getSelectedIndex()+1],Integer.parseInt(sizetext.getText()));  
	            textarea.setFont(okFont);  
	            dispose();  
	        }  
	    });  
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==cancelButton)
					dispose();
			}
		});
		
		
	}

}
