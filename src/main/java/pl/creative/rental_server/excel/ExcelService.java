package pl.creative.rental_server.excel;

import lombok.RequiredArgsConstructor;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Service
@RequiredArgsConstructor
public class ExcelService {

    private final GenerateExcelForSingleItemService generateExcelForSingleItemService;
    private final GenerateExelForAllAvaiableItemsService allAvailableItemsService;

    public String reportWithHistoryForSingleItem(String itemId) throws IOException {
        return encodeFileToBase64File(generateExcelForSingleItemService.excelReportAboutSingleItem(itemId));
    }

    public String reportWithAllItems() throws IOException {
        return encodeFileToBase64File(allAvailableItemsService.excelReportAboutSingleItem());
    }

    private String encodeFileToBase64File(File file) {
        try {
            return new String(Base64.encode(Files.readAllBytes(file.toPath())));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
