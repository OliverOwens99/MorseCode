package ie.atu.sw;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class MorseWindow {
	private Colour[] colours = Colour.values(); //This might come in handy
	private ThreadLocalRandom rand = ThreadLocalRandom.current(); //This will definitely come in handy
	private JFrame win; //The GUI Window
	private JTextArea txtOutput = new JTextArea(); //The text box to output the results to
	private JTextField txtFilePath; //The file name to process

	public MorseWindow(){
		/*
		 * Create a window for the application. Building a GUI is an example of 
		 * "divide and conquer" in action. A GUI is really a tree. That is why
		 * we are able to create and configure GUIs in XML.
		 */
		win = new JFrame();
		win.setTitle("Data Structures & Algorithms 2023 - Morse Encoder/Decoder");
		win.setSize(650, 400);
		win.setResizable(false);
		win.setLayout(new FlowLayout());
		
        /*
         * The top panel will contain the file chooser and encode / decode buttons
         */
        var top = new JPanel(new FlowLayout(FlowLayout.LEADING));
        top.setBorder(new javax.swing.border.TitledBorder("Select File"));
        top.setPreferredSize(new Dimension(600, 80));

        txtFilePath =  new JTextField(20);
		txtFilePath.setPreferredSize(new Dimension(100, 30));

		
		var chooser = new JButton("Browse...");
		chooser.addActionListener((e) -> {
			var fc = new JFileChooser(System.getProperty("user.dir"));
			var val = fc.showOpenDialog(win);
			if (val == JFileChooser.APPROVE_OPTION) {
				var file = fc.getSelectedFile().getAbsoluteFile();
				txtFilePath.setText(file.getAbsolutePath());
			}
		});
		
		var btnEncodeFile = new JButton("Encode");
		btnEncodeFile.addActionListener(e -> {
			/*
			 * Start your encoding here, but put the logic in another class
			 */
			
		 String path = txtFilePath.getText();
//			StringBuilder sb = new StringBuilder();
//			try {
//				BufferedReader in = new BufferedReader(new FileReader(path));
//
//				while (in.readLine() != null) {
//					sb.append(in.readLine()).append("\n");
//				}
//
//				String contents = sb.toString();
//				appendText(morseCode.encode(contents));
//				in.close();
//			} catch (FileNotFoundException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
			// I use scanner to grab the file path from above and go through the whole file
			// above is where I tried buffered reader but it didnt work for me for some
			// reason
			File file = new File(path);
			String data = "";
			if (path != "") {
				try {
					Scanner scan = new Scanner(file);
					StringBuilder sb = new StringBuilder();
					while (scan.hasNextLine()) {
						data = scan.nextLine();
						sb.append(data);
						sb.append("\n");
					}
					
					String Contents = sb.toString();
					
					appendText(morseCode.encode(Contents));

					

				} catch (FileNotFoundException e1) {

					System.out.println("File not found: " + e1.getMessage());
				}
				 
			}
//			
			appendText(morseCode.encode(txtOutput.getText()));
//
			// Write out a message 10 times when the Encode button is clicked
			for (int i = 0; i < 10; i++) {
			appendText(morseCode.encode("THIS IS LONDON CALLING. COME IN TOBRUK.\n\n"));
//				
//
			}
			System.out.println(System.nanoTime());
			FileWriter fw;
			try {
				fw = new FileWriter(path +" -encoded " + "txt",false);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(txtOutput.getText());
				appendText("The file has been saved in the directory");
				bw.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
//			

		});
		
		var btnDecodeFile = new JButton("Decode");
		btnDecodeFile.addActionListener(e -> {
			/*
			 * Start your decoding here, but put the logic in another class
			 */
			
			
			String path = txtFilePath.getText(); //Call getText() to get the file name
			
			// StringBuilder sb = new StringBuilder();
//			try {
//				BufferedReader in = new BufferedReader(new FileReader(path));
//				String line = "";
//				while (in.readLine() != null) {
//					 sb.append(line).append("\n");
//				}
//
//				String contents = sb.toString();
//				appendText(morseCode.decode(contents));
//				in.close();
//			} catch (FileNotFoundException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//			 catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//	    
			// I use scanner to grab the file path from above and go through the whole file
			// above is where I tried buffered reader but it didnt work for me for some
			// reason
			File file = new File(path);
			if (path != "") {
				try {
					Scanner scan = new Scanner(file);
					StringBuilder sb = new StringBuilder();

					while (scan.hasNextLine()) {
						sb.append(scan.nextLine());
						sb.append("\n");

					}
					String Contents = sb.toString();
					// appendText(morseCode.morseToEnglish(code, Contents));
					appendText(morseCode.decode(Contents));
					scan.close();

				} catch (FileNotFoundException e1) {

					System.out.println("File not found: " + e1.getMessage());
				}

			} else {
				appendText(morseCode.decode(txtOutput.getText()));
			}
			FileWriter fw;
			try {
				fw = new FileWriter(path +"-decoded " + "txt",false);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(txtOutput.getText());
				appendText("The file has been saved in the directory of the file specified");
				bw.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println(System.nanoTime());
			// appendText(morseCode.decode(txtOutput.getText()));
			
			
		});
		
		//Add all the components to the panel and the panel to the window
        top.add(txtFilePath);
        top.add(chooser);
        top.add(btnEncodeFile);
        top.add(btnDecodeFile);
        win.getContentPane().add(top); //Add the panel to the window
        
        
        /*
         * The middle panel contains the coloured square and the text
         * area for displaying the outputted text. 
         */
        var middle = new JPanel(new FlowLayout(FlowLayout.LEADING));
        middle.setPreferredSize(new Dimension(600, 200));

        var dot = new JPanel();
        dot.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
        dot.setBackground(getRandomColour());
        dot.setPreferredSize(new Dimension(140, 150));
        dot.addMouseListener(new MouseAdapter() { 
        	//Can't use a lambda against MouseAdapter because it is not a SAM
        	public void mousePressed( MouseEvent e ) {  
        		dot.setBackground(getRandomColour());
        	}
        });
        
        //Add the text area
		txtOutput.setLineWrap(true);
		txtOutput.setWrapStyleWord(true);
		txtOutput.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
		
		var scroller = new JScrollPane(txtOutput);
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroller.setPreferredSize(new Dimension(450, 150));
		scroller.setMaximumSize(new Dimension(450, 150));
		
		//Add all the components to the panel and the panel to the window
		middle.add(dot);
		middle.add(scroller);
		win.getContentPane().add(middle);
		
		
		/*
		 * The bottom panel contains the clear and quit buttons.
		 */
		var bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottom.setPreferredSize(new java.awt.Dimension(500, 50));

        //Create and add Clear and Quit buttons
        var clear = new JButton("Clear");
        clear.addActionListener((e) -> txtOutput.setText(""));
        
        var quit = new JButton("Quit");
        quit.addActionListener((e) -> System.exit(0));
        
        //Add all the components to the panel and the panel to the window
        bottom.add(clear);
        bottom.add(quit);
        win.getContentPane().add(bottom);       
        
        
        /*
         * All done. Now show the configured Window.
         */
		win.setVisible(true);
	}
	
	private Color getRandomColour() {
		Colour c = colours[rand.nextInt(0, colours.length)];
		return Color.decode(c.hex() + "");
	}
	
	protected void replaceText(String text) {
		txtOutput.setText(text);
	}
	
	protected void appendText(String text) {
		txtOutput.setText(txtOutput.getText() + " " + text);
	}
}