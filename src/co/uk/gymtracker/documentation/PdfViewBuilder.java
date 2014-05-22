package co.uk.gymtracker.documentation;

import co.uk.gymtracker.model.GymLogData;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Description Here
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 17/04/14
 * @project GymTrackerApp
 */
public class PdfViewBuilder extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(Map model, Document document, PdfWriter pdfWriter, HttpServletRequest request, HttpServletResponse httpServletResponse) throws Exception {

        List<GymLogData> gymLogData = (List<GymLogData>) model.get("gymData");

        for(GymLogData data : gymLogData) {
            document.add(new Paragraph(data.toString()));
        }

    }

}

