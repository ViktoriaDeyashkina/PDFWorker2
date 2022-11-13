import com.itextpdf.text.DocumentException;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;


public class View {
    private Controller controller;

    public View(Controller controller) {
        this.controller = controller;
        initView();
    }

    private JFrame frame = new JFrame("PDFWorker");
    private JButton button1 = new JButton("Разрезать файл");
    private JButton button2 = new JButton("Объединить файлы");


    private void initView() {
        frame.setBounds(500, 150, 300, 300);
        frame.setVisible(true);
        Box box = Box.createVerticalBox();
        box.add(button1);
        box.add(button2);
        frame.getContentPane().add(box);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controller.cutFile();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controller.joinFiles();
                } catch (DocumentException ex) {
                    ex.printStackTrace();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public String getPath () {
        String path = null;
            JFileChooser jFileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "PDF", "pdf");
        jFileChooser.setFileFilter(filter);
            int returnValue = jFileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = jFileChooser.getSelectedFile();
                path = selectedFile.getAbsolutePath();
            }
        return path;
    }


    public String saveFile (){
        String path = null;
        JFileChooser jFileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "PDF", "pdf");
        jFileChooser.setFileFilter(filter);
        int returnValue = jFileChooser.showSaveDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION){
            File selectedFile = jFileChooser.getSelectedFile();
            path = selectedFile.getAbsolutePath();
        }
        return path;
    }

    public ArrayList<String> getPathes (){
        ArrayList<String> list = new ArrayList<>();
        while (true) {
            JFileChooser jFileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "PDF", "pdf");
            jFileChooser.setFileFilter(filter);
            jFileChooser.setMultiSelectionEnabled(true);
            int returnValue = jFileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File[] files = jFileChooser.getSelectedFiles();
                for (int i = 0; i < files.length; i++) {
                    list.add(files[i].getAbsolutePath());
                }
            } else if (returnValue == JFileChooser.CANCEL_OPTION){
                break;
            }
        }
        return list;
    }

    public void notifyMessage (){
        JOptionPane.showMessageDialog(
                new JFrame("Result"),
                "Done",
                "Info",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
