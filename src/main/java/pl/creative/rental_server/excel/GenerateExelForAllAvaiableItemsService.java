package pl.creative.rental_server.excel;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import pl.creative.rental_server.entities.Item;
import pl.creative.rental_server.repository.ItemRepository;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
@Slf4j
public class GenerateExelForAllAvaiableItemsService {
    private final ItemRepository itemRepository;
    private final ExcelUtilities excelUtilities;
    private static final String[] headerRows = {
            "Rekord Przedmiotu", "Data stworzenia przedmiotu", "Id przedmiotu", "Nazwa",
            "Opis", "Wyporzyczony przez", "Status przedmiotu",
            "Id miejsca", "Nazwa miejsca", "Ilosc zdjec", "Ilosc wpisów w historii"};

    public File excelReportAboutSingleItem() throws IOException {
        List<Item> items = StreamSupport.stream(itemRepository.findAll().spliterator(), false).toList();
        Workbook workbook = new XSSFWorkbook();
        excelUtilities.setupCellStylesForCurrentWorkbook(workbook);
        Sheet sheet = workbook.createSheet("Wszystkie przedmioty");
        for (int rowIndex = 1; rowIndex - 1 < items.size(); rowIndex++) {
            StringBuilder stringBuilder = new StringBuilder();
            Item item = items.get(rowIndex - 1);
            stringBuilder
                    .append(item.getId())
                    .append(";")
                    .append(item.getName())
                    .append(";")
                    .append(item.getDescription())
                    .append(";")
                    .append(item.getBorrowedBy() == null ? "-" : item.getBorrowedBy().getEmail())
                    .append(";")
                    .append(item.getStatusOfItem())
                    .append(";")
                    .append(item.getPlace() == null ? "-" : item.getPlace().getId())
                    .append(";")
                    .append(item.getPlace() == null ? "-" : item.getPlace().getName())
                    .append(";")
                    .append(item.getImages().size())
                    .append(";")
                    .append(item.getHistory().size());

            excelUtilities.prepareHeader(sheet, headerRows, ExcelUtilities.YELLOW, ExcelUtilities.ORANGE, ExcelUtilities.GREEN);
            String[] cells = stringBuilder.toString().split(";");

            Row row = sheet.createRow(rowIndex);
            Cell cell = row.createCell(0);
            cell.setCellValue(rowIndex);
            cell.setCellStyle(ExcelUtilities.YELLOW);

            cell = row.createCell(1);
            cell.setCellValue(item.getDateOfCreation());
            cell.setCellStyle(ExcelUtilities.ORANGE_DATE);
            excelUtilities.buildMainData(row, 2, cells, ExcelUtilities.GREEN, ExcelUtilities.YELLOW, ExcelUtilities.ORANGE);

        }
        excelUtilities.autoSizeColumnsInRange(sheet, 0, headerRows.length);
        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        String fileLocation = path.substring(0, path.length() - 1) + "all_items_report.xlsx";

        FileOutputStream outputStream = new FileOutputStream(fileLocation);
        workbook.write(outputStream);
        workbook.close();
        return new File(fileLocation);
    }

}