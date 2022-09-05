package pl.creative.rental_server.excel;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import pl.creative.rental_server.entities.Item;
import pl.creative.rental_server.entities.ItemHistory;
import pl.creative.rental_server.exception.notFound.NotFoundException;
import pl.creative.rental_server.repository.ItemRepository;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class GenerateExcelForSingleItemService {
    private final ItemRepository itemRepository;
    private final ExcelUtilities excelUtilities;
    private static final String[] headerRows = {"Rekord Historii", "Co sie wydarzylo", "Kiedy sie wydarzylo",
            "Id przedmiotu", "Opis przedmiotu", "Status przedmiotu",
            "Id miejsca", "Nazwa miejsca", "Opis miejsca", "Id kategorii", "Nazwy kategorii przedmiotu"};

    public File excelReportAboutSingleItem(String itemId) throws IOException {
        Optional<Item> itemOptional = itemRepository.findById(itemId);
        if (itemOptional.isEmpty()) {
            throw new NotFoundException("NOT FOUND ITEM");
        } else {
            Item item = itemOptional.get();
            List<ItemHistory> history = item.getHistory();
            List<String> detailsBeforeEvent = history
                    .stream()
                    .map(ItemHistory::getDetailsOfItemBeforeEvent)
                    .toList();
            List<String[]> rows = detailsBeforeEvent
                    .stream()
                    .map(s -> s.split(";"))
                    .toList();
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet(item.getName());
            excelUtilities.setupCellStylesForCurrentWorkbook(workbook);
            excelUtilities.prepareHeader(sheet, headerRows, ExcelUtilities.YELLOW, ExcelUtilities.ORANGE, ExcelUtilities.GREEN);
            int rowIndex = 1;
            for (ItemHistory itemHistory : history) {

                Row row = sheet.createRow(rowIndex);
                Cell cell = row.createCell(0);
                cell.setCellValue(rowIndex);
                cell.setCellStyle(ExcelUtilities.YELLOW);

                cell = row.createCell(1);
                cell.setCellValue(itemHistory.getTypeOfEvent());
                cell.setCellStyle(ExcelUtilities.ORANGE);

                Cell cell2 = row.createCell(2);
                cell2.setCellStyle(ExcelUtilities.GREEN_DATE);
                cell2.setCellValue(itemHistory.getTimeOfEvent());
                excelUtilities.buildMainData(row, 3, rows.get(rowIndex - 1), ExcelUtilities.YELLOW, ExcelUtilities.ORANGE, ExcelUtilities.GREEN);
                rowIndex++;
            }
            excelUtilities.autoSizeColumnsInRange(sheet, 1, headerRows.length);
            File currDir = new File(".");
            String path = currDir.getAbsolutePath();
            String fileLocation = path.substring(0, path.length() - 1) + "temp.xlsx";

            FileOutputStream outputStream = new FileOutputStream(fileLocation);
            workbook.write(outputStream);
            workbook.close();
            return new File(fileLocation);
        }
    }
}
