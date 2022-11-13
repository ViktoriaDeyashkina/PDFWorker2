import com.itextpdf.text.DocumentException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Controller {
    private View view = new View(Controller.this);

    public static void main(String[] args) {
        Controller controller = new Controller();

    }

    public void cutFile () throws IOException, DocumentException {
        String path = view.getPath();
        if (path!=null) {
            PdfCutWorker pdfCutWorker = new PdfCutWorker(path);
            pdfCutWorker.start();
            view.notifyMessage();
        }
    }

    public void joinFiles () throws DocumentException, FileNotFoundException {
        ArrayList <String> list = view.getPathes();
        String fileName = view.saveFile();
        if (list!=null && fileName!=null) {
            PdfWorker pdfWorker = new PdfWorker(list, fileName);
            pdfWorker.start();
            view.notifyMessage();
        }
    }
}
