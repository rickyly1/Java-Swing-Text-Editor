package labs.lab9;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class TextEditor implements ActionListener {

	private JFrame mainWindow;
	private JPanel mainPanel;
	private JMenuBar menuBar;
	public JTextArea textArea;
	JComboBox<String> fonts;
	JCheckBox italicCheckBox;
	JCheckBox boldCheckBox;
	JRadioButton size8, size16, size24, size32, size40;
	
	FontCommands fontCommands = new FontCommands(this);
	Edit edit = new Edit(this);
	//UndoManager undoManager = new UndoManager();

	/**
	 * Constructor that puts together all frames, areas, buttons, etc
	 */
	public TextEditor() {
		createMainWindow();
		createTextArea();
		createMenuBar();
		createFontOptions();

		fontCommands.currentFont = "Arial";
		fontCommands.setFont("Arial", italicCheckBox, boldCheckBox, 24);
		mainWindow.setVisible(true);
	}

	/**
	 * Creates the main JFrame and JPanel where the text area and functions will
	 * reside
	 */
	public void createMainWindow() {
		mainWindow = new JFrame("Ricky Ly - 87167210");
		mainWindow.setSize(800, 600);
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());

		JButton clearButton = new JButton("Clear");
		clearButton.addActionListener(this);
		clearButton.setActionCommand("Clear");

		JPanel clearPanel = new JPanel();
		clearPanel.add(clearButton);
		mainPanel.add(clearPanel, BorderLayout.SOUTH);

		mainWindow.add(mainPanel);
	}

	/**
	 * Creates the text area
	 */
	public void createTextArea() {
		textArea = new JTextArea();
		textArea.setLineWrap(true);
//		textArea.getDocument().addDocumentListener(new DocumentListener() {
//			
//			});
//		textArea.getDocument().addUndoableEditListener(new UndoableEditListener() {
//			public void undoableEditHappened(UndoableEditEvent e) {
//				undoManager.addEdit(e.getEdit());
//			}
//		});

		JScrollPane scrollBar = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		mainPanel.add(scrollBar, BorderLayout.CENTER);
	}

	/**
	 * Creates the menu bar at the top of the frame
	 */
	public void createMenuBar() {
		menuBar = new JMenuBar();
		mainWindow.setJMenuBar(menuBar);

		JMenu fileMenu = new JMenu("File");
		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(this);
		exitItem.setActionCommand("Exit");
		fileMenu.add(exitItem);
		menuBar.add(fileMenu);

		JMenu editMenu = new JMenu("Edit");
		JMenuItem undoItem = new JMenuItem("Undo");
		undoItem.addActionListener(this);
		undoItem.setActionCommand("Undo");

		JMenuItem redoItem = new JMenuItem("Redo");
		redoItem.addActionListener(this);
		redoItem.setActionCommand("Redo");

		editMenu.add(undoItem);
		editMenu.add(redoItem);
		menuBar.add(editMenu);

	}

	/**
	 * Creates the options for font, font size, and styles
	 */
	public void createFontOptions() {
		JPanel fontOptions = new JPanel();
		fontOptions.setLayout(new GridLayout(3, 1));

		// Creates the Combo Box for changing font
		fonts = new JComboBox<String>();
		fonts.addItem("Arial");
		fonts.addItem("Comic Sans MS");
		fonts.addItem("Times New Roman");
		fonts.setEditable(false);

		fonts.addActionListener(this);
		fonts.setActionCommand("Font");

		JPanel panelOne = new JPanel();
		panelOne.add(fonts);
		panelOne.setBorder(new TitledBorder(new EtchedBorder(), "Font"));
		fontOptions.add(panelOne);

		italicCheckBox = new JCheckBox("Italic");
		boldCheckBox = new JCheckBox("Bold");

		italicCheckBox.addActionListener(this);
		boldCheckBox.addActionListener(this);

		JPanel panelTwo = new JPanel();
		panelTwo.add(italicCheckBox);
		panelTwo.add(boldCheckBox);
		panelTwo.setBorder(new TitledBorder(new EtchedBorder(), "Style"));
		fontOptions.add(panelTwo);

		// Creates the Radio Buttons for changing font size
		size8 = new JRadioButton("8 pt.");
		size8.addActionListener(this);
		size8.setActionCommand("8 pt.");

		size16 = new JRadioButton("16 pt.");
		size16.addActionListener(this);
		size16.setActionCommand("16 pt.");

		size24 = new JRadioButton("24 pt.");
		size24.addActionListener(this);
		size24.setActionCommand("24 pt.");
		size24.setSelected(true);

		size32 = new JRadioButton("32 pt.");
		size32.addActionListener(this);
		size32.setActionCommand("32 pt.");

		size40 = new JRadioButton("40 pt.");
		size40.addActionListener(this);
		size40.setActionCommand("40 pt.");

		// Add radio buttons to button group
		ButtonGroup group = new ButtonGroup();
		group.add(size8);
		group.add(size16);
		group.add(size24);
		group.add(size32);
		group.add(size40);

		JPanel panelThree = new JPanel();
		panelThree.add(size8);
		panelThree.add(size16);
		panelThree.add(size24);
		panelThree.add(size32);
		panelThree.add(size40);
		panelThree.setBorder(new TitledBorder(new EtchedBorder(), "Size"));
		fontOptions.add(panelThree);

		mainPanel.add(fontOptions, BorderLayout.NORTH);
	}

	public static void main(String[] args) {
		new TextEditor();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		
		switch (command) {
		case "8 pt.": fontCommands.setFont((String) fonts.getSelectedItem(), italicCheckBox, boldCheckBox, 8); break;
		case "16 pt.": fontCommands.setFont((String) fonts.getSelectedItem(), italicCheckBox, boldCheckBox, 16); break;
		case "24 pt.": fontCommands.setFont((String) fonts.getSelectedItem(), italicCheckBox, boldCheckBox, 24); break;
		case "32 pt.": fontCommands.setFont((String) fonts.getSelectedItem(), italicCheckBox, boldCheckBox, 32); break;
		case "40 pt.": fontCommands.setFont((String) fonts.getSelectedItem(), italicCheckBox, boldCheckBox, 40); break;
		case "Font": String font = (String) fonts.getSelectedItem();
					 fontCommands.setFont(font, italicCheckBox, boldCheckBox, fontCommands.currentSize); break;
		case "Italic": fontCommands.setFont((String) fonts.getSelectedItem(), italicCheckBox, boldCheckBox, fontCommands.currentSize); break;
		case "Bold" : fontCommands.setFont((String) fonts.getSelectedItem(), italicCheckBox, boldCheckBox, fontCommands.currentSize); break;
		case "Clear": fontCommands.clear(); break;
		case "Undo": edit.undo(); break;
		case "Redo": edit.redo(); break;
		case "Exit": System.exit(0); break;
		default :
		}
	}
}