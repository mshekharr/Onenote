import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class OneNote implements ActionListener {
    JFrame frame;
    JMenuBar menuBar;
    JMenu file,edit;
    JMenuItem newFile,openFile,saveFile;
    JMenuItem cut,copy,paste,selectAll,close;
    JTextArea textArea;
    OneNote(){
        frame = new JFrame();

        textArea = new JTextArea();

        menuBar = new JMenuBar();

        //Initialize and add menu
        file = new JMenu("File");
        edit = new JMenu("Edit");
        menuBar.add(file);
        menuBar.add(edit);

        //Initialize  menuitems for file menu
        newFile = new JMenuItem("New");
        openFile = new JMenuItem("Open");
        saveFile = new JMenuItem("Save");

        //add ActionListener to file menu items
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

        //add menuitems for file menu
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        //Initialize menuitems to Edit menu items
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("selectAll");
        close = new JMenuItem("Close");

        //add ActionListen to Edit menu items
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

        //add menuitems to Edit menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        //Add menubar to frame
        frame.setJMenuBar(menuBar);

        //Add textArea to frame
        frame.add(textArea);

        frame.setBounds(100,100,800,500);
        frame.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent){
        if(actionEvent.getSource()==cut){
            textArea.cut();
        }
        if(actionEvent.getSource()==copy){
            textArea.copy();
        }
        if(actionEvent.getSource()==paste){
            textArea.paste();
        }
        if(actionEvent.getSource()==selectAll){
            textArea.selectAll();
        }
        if(actionEvent.getSource()==close){
            System.exit(0);
        }

        // File menu items
        // if source in NEW
        if(actionEvent.getSource()==newFile){
            OneNote newWindow = new OneNote();
        }
        //if source is open file
        if(actionEvent.getSource()==openFile){
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption = fileChooser.showOpenDialog(null);
            if(chooseOption == JFileChooser.APPROVE_OPTION){
                File file = fileChooser.getSelectedFile();
                String filePath = file.getPath();
                try{
                    //get conten in output
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
                    String line = "", output = "";
                    while((line = bufferedReader.readLine()) != null){
                        output += line + "\n";
                    }
                    //set output text Area
                    textArea.setText(output);
                }catch (Exception exception){
                    exception.printStackTrace();
                }
            }
        }
        // save file
        if(actionEvent.getSource()==saveFile){
            //create a file chooser
            JFileChooser fileChooser = new JFileChooser("C:");
            //get choose option from file chooser
            int chooseOption = fileChooser.showSaveDialog(null);
            //if choosen option is approved
            if(chooseOption==JFileChooser.APPROVE_OPTION){
                //create a file object with selected path
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                try{
                    //create buffer writer to write content into file
                    BufferedWriter outfile = new BufferedWriter(new FileWriter(file));
                    //get content from text area to outfile
                    textArea.write(outfile);
                    outfile.close();
                }catch (Exception exception){
                    exception.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        OneNote oneNote = new OneNote();
    }
}