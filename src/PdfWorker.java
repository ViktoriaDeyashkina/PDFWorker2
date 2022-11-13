import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.*;
import java.util.ArrayList;

public class PdfWorker {
    ArrayList<String> path;
    String fileName;

    public PdfWorker(ArrayList<String> path, String fileName){
        this.path = path;
        this.fileName = fileName;
    }

    public void start () throws FileNotFoundException, DocumentException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(fileName+".pdf"));
        document.open();
        PdfContentByte cb = writer.getDirectContent();
        for (int j=0; j< path.size(); j++){
            try {
                PdfReader reader = new PdfReader(path.get(j));
                int pages = reader.getNumberOfPages();
                for (int i = 1; i <= pages; i++) {
                    PdfImportedPage page = writer.getImportedPage(reader, i);
                    document.newPage();
                    cb.addTemplate(page, 0, 0);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
       document.close();
    }
}
