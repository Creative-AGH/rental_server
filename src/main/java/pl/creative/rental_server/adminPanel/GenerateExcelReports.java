package pl.creative.rental_server.adminPanel;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.creative.rental_server.excel.ExcelService;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class GenerateExcelReports {
    private final ExcelService excelService;

    @PostMapping("/admin/generateSingleExcelReport/{itemId}")
    public String encodedBase64ExcelReport(@PathVariable String itemId) throws IOException {
        return excelService.reportWithHistoryForSingleItem(itemId);

    }

    @PostMapping("/admin/generateMultipleExcelReport/")
    public String generateReport() throws IOException {
        return excelService.reportWithAllItems();
    }
}
