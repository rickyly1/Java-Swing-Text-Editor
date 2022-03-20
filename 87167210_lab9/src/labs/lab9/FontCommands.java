package labs.lab9;

import java.awt.Font;

import javax.swing.JCheckBox;

public class FontCommands {
	TextEditor textEditor;
	Font arial, comicSansMS, timesNewRoman;
	String currentFont = "Arial";
	int currentSize = 24;
	int currentStyle;
	boolean italic = false;
	boolean bold = false;
	
	public FontCommands(TextEditor t) {
		this.textEditor = t;
	}
	
//	public void changeFontSize(int fontSize) {
//		currentSize = fontSize;
//		arial = new Font("Arial", currentStyle, fontSize);
//		comicSansMS = new Font("Comic Sans MS", currentStyle, fontSize);
//		timesNewRoman = new Font("Times New Roman", currentStyle, fontSize);
//		
//		
//		//setFont(currentFont);
//	}
	
	public void setFont(String font, JCheckBox italic, JCheckBox bold, int fontSize) {
		textEditor.fontCommands.pushUndo();
		
		currentStyle = 0;
		
		if (italic.isSelected()) {
			currentStyle += Font.ITALIC;
			this.italic = true;
		} else {
			this.italic = false;
		}
		
		if (bold.isSelected()) {
			currentStyle += Font.BOLD;
			this.bold = true;
		} else {
			this.bold = false;
		}
		
		currentSize = fontSize;
		arial = new Font("Arial", currentStyle, fontSize);
		comicSansMS = new Font("Comic Sans MS", currentStyle, fontSize);
		timesNewRoman = new Font("Times New Roman", currentStyle, fontSize);
		
		if (font.equals("Arial")) {
			textEditor.textArea.setFont(arial);
			currentFont = "Arial";
			
		} else if (font.equals("Comic Sans MS")) {
			textEditor.textArea.setFont(comicSansMS);
			currentFont = "Comic Sans MS";
			
		} else if (font.equals("Times New Roman")) {
			textEditor.textArea.setFont(timesNewRoman);
			currentFont = "Times New Roman";
			
		}
		
	}

	public void setFontNoPush(String font, JCheckBox italic, JCheckBox bold, int fontSize) {
		currentStyle = 0;
		
		if (italic.isSelected()) {
			currentStyle += Font.ITALIC;
			this.italic = true;
		} else {
			this.italic = false;
		}
		
		if (bold.isSelected()) {
			currentStyle += Font.BOLD;
			this.bold = true;
		} else {
			this.bold = false;
		}
		
		currentSize = fontSize;
		arial = new Font("Arial", currentStyle, fontSize);
		comicSansMS = new Font("Comic Sans MS", currentStyle, fontSize);
		timesNewRoman = new Font("Times New Roman", currentStyle, fontSize);
		
		if (font.equals("Arial")) {
			textEditor.textArea.setFont(arial);
			currentFont = "Arial";
			
		} else if (font.equals("Comic Sans MS")) {
			textEditor.textArea.setFont(comicSansMS);
			currentFont = "Comic Sans MS";
			
		} else if (font.equals("Times New Roman")) {
			textEditor.textArea.setFont(timesNewRoman);
			currentFont = "Times New Roman";
			
		}
		
	}
//	public void changeStyle(JCheckBox italic, JCheckBox bold) {
//		currentStyle = 0;
//		
//		if (italic.isSelected()) {
//			currentStyle += Font.ITALIC;
//		}
//		
//		if (bold.isSelected()) {
//			currentStyle += Font.BOLD;
//		}
//		
//		changeFontSize(currentSize);
//
//	}
	
	public void clear() {
		textEditor.fontCommands.pushUndo();
		textEditor.textArea.setText("");
		textEditor.fontCommands.pushUndo();
	}
	
	public void pushUndo() {
//		String[] x = {textEditor.textArea.getText(),
//	              Boolean.toString(textEditor.italicCheckBox.isSelected()),
//	              Boolean.toString(textEditor.boldCheckBox.isSelected()),
//	              (String) textEditor.fonts.getSelectedItem(),
//	              Integer.toString(currentSize)};
		
		String[] x = {textEditor.textArea.getText(), Boolean.toString(italic), Boolean.toString(bold),
					  currentFont, Integer.toString(currentSize)};
		//Checks for major text changes/pushes undo
		
		textEditor.edit.addUndo(x);
	}
}
