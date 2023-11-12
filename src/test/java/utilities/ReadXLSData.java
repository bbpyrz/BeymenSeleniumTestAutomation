package utilities;

import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ReadXLSData {
    public String getData(String excelSheetName,int index)  {
        File excelFile=new File(System.getProperty("user.dir")+"/src/test/resources/testData/testData.xlsx");
        FileInputStream fileInputStream= null;
        try {
            fileInputStream = new FileInputStream(excelFile);
            Workbook workbook= WorkbookFactory.create(fileInputStream);
            Sheet sheet=workbook.getSheet(excelSheetName);
            int totalRow=sheet.getLastRowNum()+1;
            Row rowCells= sheet.getRow(0);
            int totalCols=rowCells.getLastCellNum();

            DataFormatter format=new DataFormatter();
            String testData[]=new String[totalRow];

            for(int i=0;i<totalRow;i++){
                testData[i]=format.formatCellValue(sheet.getRow(i).getCell(0));
            }
            return testData[index-1];
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
