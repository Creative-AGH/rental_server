package pl.creative.rental_server.excel;

import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;

@Service
public class ExcelUtilities {

    public static CellStyle YELLOW;
    public static CellStyle ORANGE;
    public static CellStyle GREEN;
    public static CellStyle GREEN_DATE;
    public static CellStyle YELLOW_DATE;
    public static CellStyle ORANGE_DATE;

    public void setupCellStylesForCurrentWorkbook(Workbook workbook) {
        YELLOW = workbook.createCellStyle();
        YELLOW.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
        YELLOW.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        YELLOW.setWrapText(true);
        ORANGE = workbook.createCellStyle();
        ORANGE.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.getIndex());
        ORANGE.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        ORANGE.setWrapText(true);
        GREEN = workbook.createCellStyle();
        GREEN.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
        GREEN.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        GREEN.setWrapText(true);
        CreationHelper createHelper = workbook.getCreationHelper();
        GREEN_DATE = workbook.createCellStyle();
        GREEN_DATE.setDataFormat(createHelper.createDataFormat().getFormat("dd/MMM/yyyy hh:mm:ss"));
        GREEN_DATE.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
        GREEN_DATE.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        GREEN_DATE.setWrapText(true);
        YELLOW_DATE = workbook.createCellStyle();
        YELLOW_DATE.setDataFormat(createHelper.createDataFormat().getFormat("dd/MMM/yyyy hh:mm:ss"));
        YELLOW_DATE.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
        YELLOW_DATE.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        YELLOW_DATE.setWrapText(true);
        ORANGE_DATE = workbook.createCellStyle();
        ORANGE_DATE.setDataFormat(createHelper.createDataFormat().getFormat("dd/MMM/yyyy hh:mm:ss"));
        ORANGE_DATE.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.getIndex());
        ORANGE_DATE.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        ORANGE_DATE.setWrapText(true);
    }


    public void prepareHeader(Sheet sheet, String[] values, CellStyle... headerStyles) {
        autoSizeColumnsInRange(sheet, 0, values.length);
        Row header = sheet.createRow(0);
        for (int i = 0; i < values.length; i++) {
            Cell headerCell = header.createCell(i);
            headerCell.setCellValue(values[i]);
            headerCell.setCellStyle(headerStyles[i % headerStyles.length]);
        }
        autoSizeColumnsInRange(sheet, 1, values.length);
    }

    public void buildMainData(Row row, int cellIndex, String[] values, CellStyle... rowStyles) {

        for (int i = 0; i < values.length; i++) {
            Cell headerCell = row.createCell(i + cellIndex);
            headerCell.setCellValue(values[i]);
            headerCell.setCellStyle(rowStyles[i % rowStyles.length]);
        }
    }

    public void autoSizeColumnsInRange(Sheet sheet, int begin, int end) {
        for (int i = begin; i < end; i++) {
            sheet.autoSizeColumn(i);
        }
    }
}
