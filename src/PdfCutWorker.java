import java.io.FileOutputStream;
import java.io.IOException;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

public class PdfCutWorker {
    private String path;

    public PdfCutWorker (String path){
        this.path = path;
    }

    public void start () throws IOException, DocumentException {
        PdfReader pdfReader = new PdfReader(path);
        int pages = pdfReader.getNumberOfPages();
        for (int i=1; i<=pages; i++) {
            PdfReader pdfReader2 = new PdfReader(pdfReader);
            pdfReader2.selectPages(String.valueOf(i));
            PdfStamper pdfStamper = new PdfStamper(pdfReader2, new FileOutputStream(path + "(" + i + ")" + ".pdf"));
            pdfStamper.close();
        }
        pdfReader.close();
    }
}
