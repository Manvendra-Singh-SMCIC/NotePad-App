package Notepad;

import javax.swing.*;
import javax.swing.event.UndoableEditEvent;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.*;

public final class GUI implements ActionListener {

    JFrame window;
    JTextArea textArea;
    Container container;
    JScrollPane scrollPane;
    JMenuBar menuBar;
    JMenu menuFile, menuEdit, menuFormat, menuColor, menuHelp;
    JMenuItem iNew, iOpen, iSave, iSaveAs, iPrint, iExit;
    JMenuItem iWrap;
    JMenu menuFont, menuFontSize;
    JMenuItem iFontArial, iFontCSMS, iFontTNR;
    JMenuItem iFontSize4, iFontSize6, iFontSize8, iFontSize10, iFontSize12, iFontSize14, iFontSize16, iFontSize18,
            iFontSize20, iFontSize22, iFontSize24, iFontSize26, iFontSize28, iFontSize30;
    JMenuItem iColor1, iColor2, iColor3, iColor4, iColor5, iColor6;
    JMenuItem iCut, iCopy, iPaste, iSelectAll, iUndo, iRedo;
    JMenuItem iAbout;

    boolean wordWrapOn = false;

    Function_File file = new Function_File(this);
    Function_Format format = new Function_Format(this);
    Function_Color color = new Function_Color(this);
    Function_Edit edit = new Function_Edit(this);
    KeyHandler kHandler = new KeyHandler(this);
    About about = new About(this);

    UndoManager um = new UndoManager();

    public GUI() throws ClassNotFoundException, InstantiationException, IllegalAccessException,
            UnsupportedLookAndFeelException {
        createWindow();
        createTextArea();
        createMenuBar();
        createFileMenu();
        createFormatMenu();
        createColorMenu();
        createEditMenu();
        createHelpMenu();
        createShortCut();

        format.selectedFont = "Arial";
        format.createFont(16);
        format.wordWrap();

        color.changeColor("White");

        window.setVisible(true);
    }

    @SuppressWarnings("all")
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, UnsupportedLookAndFeelException {
        new GUI();
    }

    public void createWindow() throws ClassNotFoundException, InstantiationException, IllegalAccessException,
            UnsupportedLookAndFeelException {
        window = new JFrame("Notepad");
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        container = window.getContentPane();

        ImageIcon icon = new ImageIcon(getClass().getResource("Notepad.png"));
        window.setIconImage(icon.getImage());

        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    }

    public void createTextArea() {
        textArea = new JTextArea();
        textArea.setFont(format.arial);

        textArea.addKeyListener(kHandler);

        textArea.getDocument().addUndoableEditListener((UndoableEditEvent e) -> {
            um.addEdit(e.getEdit());
        });

        scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        container.add(scrollPane);
    }

    public void createMenuBar() {
        menuBar = new JMenuBar();
        window.setJMenuBar(menuBar);

        menuFile = new JMenu("File");
        menuEdit = new JMenu("Edit");
        menuFormat = new JMenu("Format");
        menuColor = new JMenu("Color");
        menuHelp = new JMenu("Help");

        menuBar.add(menuFile);
        menuBar.add(menuEdit);
        menuBar.add(menuFormat);
        menuBar.add(menuColor);
        menuBar.add(menuHelp);
    }

    public void createFileMenu() {
        iNew = new JMenuItem("New");
        iOpen = new JMenuItem("Open");
        iSave = new JMenuItem("Save");
        iSaveAs = new JMenuItem("Save As");
        iExit = new JMenuItem("Exit");
        iPrint = new JMenuItem("Print");

        menuFile.add(iNew);
        menuFile.add(iOpen);
        menuFile.add(iSave);
        menuFile.add(iSaveAs);
        menuFile.add(iPrint);
        menuFile.add(iExit);

        iNew.addActionListener(this);
        iNew.setActionCommand("New");

        iOpen.addActionListener(this);
        iOpen.setActionCommand("Open");

        iSave.addActionListener(this);
        iSave.setActionCommand("Save");

        iSaveAs.addActionListener(this);
        iSaveAs.setActionCommand("SaveAs");

        iPrint.addActionListener(this);
        iPrint.setActionCommand("Print");

        iExit.addActionListener(this);
        iExit.setActionCommand("Exit");
    }

    public void createFormatMenu() {
        iWrap = new JMenuItem("Word Wrap: Off");

        menuFont = new JMenu("Font");
        menuFontSize = new JMenu("Font Size");

        iFontArial = new JMenuItem("Arial");
        iFontCSMS = new JMenuItem("Comic Sans MS");
        iFontTNR = new JMenuItem("Times New Roman");

        iFontSize4 = new JMenuItem("4");
        iFontSize6 = new JMenuItem("6");
        iFontSize8 = new JMenuItem("8");
        iFontSize10 = new JMenuItem("10");
        iFontSize12 = new JMenuItem("12");
        iFontSize14 = new JMenuItem("14");
        iFontSize16 = new JMenuItem("16");
        iFontSize18 = new JMenuItem("18");
        iFontSize20 = new JMenuItem("20");
        iFontSize22 = new JMenuItem("22");
        iFontSize24 = new JMenuItem("24");
        iFontSize26 = new JMenuItem("26");
        iFontSize28 = new JMenuItem("28");
        iFontSize30 = new JMenuItem("30");

        menuFormat.add(iWrap);
        menuFormat.add(menuFont);
        menuFormat.add(menuFontSize);

        menuFont.add(iFontArial);
        menuFont.add(iFontCSMS);
        menuFont.add(iFontTNR);

        menuFontSize.add(iFontSize10);
        menuFontSize.add(iFontSize12);
        menuFontSize.add(iFontSize14);
        menuFontSize.add(iFontSize16);
        menuFontSize.add(iFontSize18);
        menuFontSize.add(iFontSize20);
        menuFontSize.add(iFontSize22);
        menuFontSize.add(iFontSize24);
        menuFontSize.add(iFontSize26);
        menuFontSize.add(iFontSize28);
        menuFontSize.add(iFontSize30);

        iWrap.addActionListener(this);
        iWrap.setActionCommand("Word Wrap");

        iFontArial.addActionListener(this);
        iFontArial.setActionCommand("Arial");

        iFontCSMS.addActionListener(this);
        iFontCSMS.setActionCommand("Comic Sans MS");

        iFontTNR.addActionListener(this);
        iFontTNR.setActionCommand("Times New Roman");

        iFontSize4.addActionListener(this);
        iFontSize4.setActionCommand("Size4");

        iFontSize6.addActionListener(this);
        iFontSize6.setActionCommand("Size6");

        iFontSize8.addActionListener(this);
        iFontSize8.setActionCommand("Size8");

        iFontSize10.addActionListener(this);
        iFontSize10.setActionCommand("Size10");

        iFontSize12.addActionListener(this);
        iFontSize12.setActionCommand("Size12");

        iFontSize14.addActionListener(this);
        iFontSize14.setActionCommand("Size14");

        iFontSize16.addActionListener(this);
        iFontSize16.setActionCommand("Size16");

        iFontSize18.addActionListener(this);
        iFontSize18.setActionCommand("Size18");

        iFontSize20.addActionListener(this);
        iFontSize20.setActionCommand("Size20");

        iFontSize22.addActionListener(this);
        iFontSize22.setActionCommand("Size22");

        iFontSize24.addActionListener(this);
        iFontSize24.setActionCommand("Size24");

        iFontSize26.addActionListener(this);
        iFontSize26.setActionCommand("Size26");

        iFontSize28.addActionListener(this);
        iFontSize28.setActionCommand("Size28");

        iFontSize30.addActionListener(this);
        iFontSize30.setActionCommand("Size30");
    }

    public void createColorMenu() {
        iColor1 = new JMenuItem("White");
        iColor1.addActionListener(this);
        iColor1.setActionCommand("White");
        menuColor.add(iColor1);

        iColor2 = new JMenuItem("Red");
        iColor2.addActionListener(this);
        iColor2.setActionCommand("Red");
        menuColor.add(iColor2);

        iColor3 = new JMenuItem("Blue");
        iColor3.addActionListener(this);
        iColor3.setActionCommand("Blue");
        menuColor.add(iColor3);

        iColor4 = new JMenuItem("Green");
        iColor4.addActionListener(this);
        iColor4.setActionCommand("Green");
        menuColor.add(iColor4);

        iColor5 = new JMenuItem("Yellow");
        iColor5.addActionListener(this);
        iColor5.setActionCommand("Yellow");
        menuColor.add(iColor5);

        iColor6 = new JMenuItem("Black");
        iColor6.addActionListener(this);
        iColor6.setActionCommand("Black");
        menuColor.add(iColor6);
    }

    public void createEditMenu() {
        iCut = new JMenuItem("Cut");
        iCut.addActionListener(this);
        iCut.setActionCommand("Cut");
        menuEdit.add(iCut);

        iCopy = new JMenuItem("Copy");
        iCopy.addActionListener(this);
        iCopy.setActionCommand("Copy");
        menuEdit.add(iCopy);

        iPaste = new JMenuItem("Paste");
        iPaste.addActionListener(this);
        iPaste.setActionCommand("Paste");
        menuEdit.add(iPaste);

        iSelectAll = new JMenuItem("Select All");
        iSelectAll.addActionListener(this);
        iSelectAll.setActionCommand("Select All");
        menuEdit.add(iSelectAll);

        iUndo = new JMenuItem("Undo");
        iUndo.addActionListener(this);
        iUndo.setActionCommand("Undo");
        menuEdit.add(iUndo);

        iRedo = new JMenuItem("Redo");
        iRedo.addActionListener(this);
        iRedo.setActionCommand("Redo");
        menuEdit.add(iRedo);
    }

    public void createHelpMenu() {
        iAbout = new JMenuItem("About");
        iAbout.addActionListener(this);
        iAbout.setActionCommand("About");
        menuHelp.add(iAbout);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "New" -> file.newFile();
            case "Open" -> file.open();
            case "Save" -> file.save();
            case "SaveAs" -> file.saveAs();
            case "Print" -> file.print();
            case "Exit" -> file.exit();
            case "Word Wrap" -> format.wordWrap();
            case "Arial" -> format.setFont(command);
            case "Comic Sans MS" -> format.setFont(command);
            case "Times New Roman" -> format.setFont(command);
            case "Size4" -> format.createFont(4);
            case "Size6" -> format.createFont(6);
            case "Size8" -> format.createFont(8);
            case "Size10" -> format.createFont(10);
            case "Size12" -> format.createFont(12);
            case "Size14" -> format.createFont(14);
            case "Size16" -> format.createFont(16);
            case "Size18" -> format.createFont(18);
            case "Size20" -> format.createFont(20);
            case "Size22" -> format.createFont(22);
            case "Size24" -> format.createFont(24);
            case "Size26" -> format.createFont(26);
            case "Size28" -> format.createFont(28);
            case "Size30" -> format.createFont(30);
            case "White" -> color.changeColor(command);
            case "Red" -> color.changeColor(command);
            case "Blue" -> color.changeColor(command);
            case "Green" -> color.changeColor(command);
            case "Yellow" -> color.changeColor(command);
            case "Black" -> color.changeColor(command);
            case "Cut" -> edit.cut();
            case "Copy" -> edit.copy();
            case "Paste" -> edit.paste();
            case "Select All" -> edit.selectAll();
            case "Undo" -> edit.undo();
            case "Redo" -> edit.redo();
            case "About" -> about.showAbout();
        }
    }

    public void createShortCut() {
        iNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
        iOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
        iSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
        iSaveAs.setAccelerator(KeyStroke.getKeyStroke("F12"));
        iPrint.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK));
        iExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, KeyEvent.ALT_DOWN_MASK));
        iCut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK));
        iCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK));
        iPaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_DOWN_MASK));
        iSelectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK));
        iUndo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_DOWN_MASK));
        iRedo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, KeyEvent.CTRL_DOWN_MASK));
        iAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, KeyEvent.CTRL_DOWN_MASK));
    }
}
