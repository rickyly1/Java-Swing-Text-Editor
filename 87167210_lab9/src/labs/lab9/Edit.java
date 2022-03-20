package labs.lab9;

import java.util.Stack;

public class Edit {
	
	TextEditor textEditor;
	
	Stack<String[]> undoStack = new Stack<String[]>();
	Stack<String[]> redoStack = new Stack<String[]>();
	
	public Edit(TextEditor t) {
		this.textEditor = t;
	}
	
	public void addUndo(String[] action) {
		if (!undoStack.isEmpty()) {
			String[] topStack = undoStack.peek();
			if (!topStack.equals(action)) {
				undoStack.push(action);
			}
		} else {
			undoStack.push(action);
		}
	}
	
	public void undo() {
		//textEditor.undoManager.undo();
		textEditor.edit.pushRedo();
		
		if (!undoStack.isEmpty()) {
			String[] x = {textEditor.textArea.getText(), 
					  Boolean.toString(textEditor.fontCommands.italic), 
					  Boolean.toString(textEditor.fontCommands.bold),
					  textEditor.fontCommands.currentFont, 
					  Integer.toString(textEditor.fontCommands.currentSize)};
			
			String[] topStack = undoStack.pop();
			if (topStack.equals(x)) {
				while (topStack.equals(x)) {
					topStack = undoStack.pop();
				}
			}
			
			if (!topStack[0].equals(textEditor.textArea.getText())) {
				textEditor.textArea.setText(topStack[0]);
				
			} else if (!topStack[1].equals(Boolean.toString(textEditor.italicCheckBox.isSelected()))) {
				if (textEditor.italicCheckBox.isSelected() == false) {
					textEditor.italicCheckBox.setSelected(true);
				} else {
					textEditor.italicCheckBox.setSelected(false);
				}
				textEditor.fontCommands.setFontNoPush((String) textEditor.fonts.getSelectedItem(), 
												textEditor.italicCheckBox, 
												textEditor.boldCheckBox, 
												textEditor.fontCommands.currentSize);
				
			} else if (!topStack[2].equals(Boolean.toString(textEditor.boldCheckBox.isSelected()))) {
				if (textEditor.boldCheckBox.isSelected() == false) {
					textEditor.boldCheckBox.setSelected(true);
				} else {
					textEditor.boldCheckBox.setSelected(false);
				}
				textEditor.fontCommands.setFontNoPush((String) textEditor.fonts.getSelectedItem(), 
												textEditor.italicCheckBox, 
												textEditor.boldCheckBox, 
												textEditor.fontCommands.currentSize);
				
			} else if (!topStack[3].equals((String) textEditor.fonts.getSelectedItem())) {
				textEditor.fontCommands.setFontNoPush(topStack[3],textEditor.italicCheckBox, 
												textEditor.boldCheckBox, 
												textEditor.fontCommands.currentSize);
				textEditor.fonts.setSelectedItem(topStack[3]);
				
			} else if (!topStack[4].equals(Integer.toString(textEditor.fontCommands.currentSize))) {
				if (topStack[4].equals("8")) {
					textEditor.size8.setSelected(true);
					textEditor.fontCommands.setFontNoPush((String) textEditor.fonts.getSelectedItem(), 
													textEditor.italicCheckBox, 
													textEditor.boldCheckBox, 8);
					
				} else if (topStack[4].equals("16")) {
					textEditor.size16.setSelected(true);
					textEditor.fontCommands.setFontNoPush((String) textEditor.fonts.getSelectedItem(), 
													textEditor.italicCheckBox, 
													textEditor.boldCheckBox, 16);
					
				} else if (topStack[4].equals("24")) {
					textEditor.size24.setSelected(true);
					textEditor.fontCommands.setFontNoPush((String) textEditor.fonts.getSelectedItem(), 
													textEditor.italicCheckBox, 
													textEditor.boldCheckBox, 24);
					
				} else if (topStack[4].equals("32")) {
					textEditor.size32.setSelected(true);
					textEditor.fontCommands.setFontNoPush((String) textEditor.fonts.getSelectedItem(), 
													textEditor.italicCheckBox, 
													textEditor.boldCheckBox, 32);
					
				} else if (topStack[4].equals("40")) {
					textEditor.size40.setSelected(true);
					textEditor.fontCommands.setFontNoPush((String) textEditor.fonts.getSelectedItem(), 
													textEditor.italicCheckBox, 
													textEditor.boldCheckBox, 40);
					
				}
			} else {
				undo();
			}
			
			//redoStack.push(topStack);
		}
	}
	
	public void redo() {
		//textEditor.undoManager.redo();
		textEditor.fontCommands.pushUndo();
		
		if (!redoStack.isEmpty()) {
			String[] x = {textEditor.textArea.getText(), 
					  Boolean.toString(textEditor.fontCommands.italic), 
					  Boolean.toString(textEditor.fontCommands.bold),
					  textEditor.fontCommands.currentFont, 
					  Integer.toString(textEditor.fontCommands.currentSize)};
			
			String[] topStack = redoStack.pop();
			if (topStack.equals(x)) {
				while (topStack.equals(x)) {
					topStack = redoStack.pop();
				}
			}
			//String[] topStack = redoStack.pop();
			
			if (!topStack[0].equals(textEditor.textArea.getText())) {
				textEditor.textArea.setText(topStack[0]);
				
			} else if (!topStack[1].equals(Boolean.toString(textEditor.italicCheckBox.isSelected()))) {
				if (textEditor.italicCheckBox.isSelected() == false) {
					textEditor.italicCheckBox.setSelected(true);
				} else {
					textEditor.italicCheckBox.setSelected(false);
				}
				textEditor.fontCommands.setFont((String) textEditor.fonts.getSelectedItem(), 
												textEditor.italicCheckBox, 
												textEditor.boldCheckBox, 
												textEditor.fontCommands.currentSize);
				
			} else if (!topStack[2].equals(Boolean.toString(textEditor.boldCheckBox.isSelected()))) {
				if (textEditor.boldCheckBox.isSelected() == false) {
					textEditor.boldCheckBox.setSelected(true);
				} else {
					textEditor.boldCheckBox.setSelected(false);
				}
				textEditor.fontCommands.setFont((String) textEditor.fonts.getSelectedItem(), 
												textEditor.italicCheckBox, 
												textEditor.boldCheckBox, 
												textEditor.fontCommands.currentSize);
				
			} else if (!topStack[3].equals((String) textEditor.fonts.getSelectedItem())) {
				textEditor.fontCommands.setFont(topStack[3],textEditor.italicCheckBox, 
												textEditor.boldCheckBox, 
												textEditor.fontCommands.currentSize);
				textEditor.fonts.setSelectedItem(topStack[3]);
				
			} else if (!topStack[4].equals(Integer.toString(textEditor.fontCommands.currentSize))) {
				if (topStack[4].equals("8")) {
					textEditor.size8.setSelected(true);
					textEditor.fontCommands.setFont((String) textEditor.fonts.getSelectedItem(), 
													textEditor.italicCheckBox, 
													textEditor.boldCheckBox, 8);
					
				} else if (topStack[4].equals("16")) {
					textEditor.size16.setSelected(true);
					textEditor.fontCommands.setFont((String) textEditor.fonts.getSelectedItem(), 
													textEditor.italicCheckBox, 
													textEditor.boldCheckBox, 16);
					
				} else if (topStack[4].equals("24")) {
					textEditor.size24.setSelected(true);
					textEditor.fontCommands.setFont((String) textEditor.fonts.getSelectedItem(), 
													textEditor.italicCheckBox, 
													textEditor.boldCheckBox, 24);
					
				} else if (topStack[4].equals("32")) {
					textEditor.size32.setSelected(true);
					textEditor.fontCommands.setFont((String) textEditor.fonts.getSelectedItem(), 
													textEditor.italicCheckBox, 
													textEditor.boldCheckBox, 32);
					
				} else if (topStack[4].equals("40")) {
					textEditor.size40.setSelected(true);
					textEditor.fontCommands.setFont((String) textEditor.fonts.getSelectedItem(), 
													textEditor.italicCheckBox, 
													textEditor.boldCheckBox, 40);
					
				}
				
			} else {
				redo();
			}
			
			//undoStack.push(topStack);
		}
	}
	
	public void pushRedo() {
		String[] x = {textEditor.textArea.getText(), 
					  Boolean.toString(textEditor.fontCommands.italic), 
					  Boolean.toString(textEditor.fontCommands.bold),
					  textEditor.fontCommands.currentFont, 
					  Integer.toString(textEditor.fontCommands.currentSize)};
		
		if (!redoStack.isEmpty()) {
			String[] topStack = redoStack.peek();
			if (!topStack.equals(x)) {
				redoStack.push(x);
			}
		} else {
			redoStack.push(x);
		}
	}
}
