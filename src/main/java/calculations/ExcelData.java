package calculations;


import org.apache.commons.math3.util.Precision;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelData {

    public ExcelData(Mortgage mortgage) throws IOException {



        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Data");

        int cellWidth = 7000;
        sheet.setColumnWidth(0, cellWidth);
        sheet.setColumnWidth(1, cellWidth);
        sheet.setColumnWidth(2, cellWidth);
        sheet.setColumnWidth(3, cellWidth);


        // Headers
        Row header = sheet.createRow(0);

        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFFont font = workbook.createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 16);
        font.setBold(true);
        headerStyle.setFont(font);

        Cell headerCell = header.createCell(0);
        headerCell.setCellValue("Monthly payment");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(1);
        headerCell.setCellValue("Loan part(%)");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(2);
        headerCell.setCellValue("Interest part(%)");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(3);
        headerCell.setCellValue("Loan balance");
        headerCell.setCellStyle(headerStyle);


        // Data
        CellStyle style = workbook.createCellStyle();
        style.setWrapText(true);

        boolean annuity = mortgage.getIsAnnuity();

        for(int i=0;i<(mortgage.getLoanTerm());i++)
        {
            Row row = sheet.createRow(i+1);

            Cell cell = row.createCell(0);
            if(annuity)
                cell.setCellValue(Double.toString(Precision.round(mortgage.getMonthlyPaymentAnnuity()[i], 2)));
            else
                cell.setCellValue(Double.toString(Precision.round(mortgage.getMonthlyPaymentLinear()[i], 2)));
            cell.setCellStyle(style);

            cell = row.createCell(1);
            cell.setCellValue(Double.toString(Precision.round(mortgage.getLoanPart()[i],2)));
            cell.setCellStyle(style);

            cell = row.createCell(2);
            cell.setCellValue(Double.toString(Precision.round(mortgage.getInterestPart()[i],2)));
            cell.setCellStyle(style);

            cell = row.createCell(3);
            cell.setCellValue(Double.toString(Precision.round(mortgage.getMonthlyBalance()[i],2)));
            cell.setCellStyle(style);
        }


        // Save to file
        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        String fileLocation = path.substring(0, path.length() - 1) + "output/report.xlsx";


        FileOutputStream outputStream = new FileOutputStream(fileLocation);

        workbook.write(outputStream);
        workbook.close();
    }


}
