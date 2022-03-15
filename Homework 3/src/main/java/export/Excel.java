package export;

import goods.Good;
import org.apache.poi.hssf.usermodel.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class Excel{

    private String path;
    private String listName;

    public Excel(String listName, String path)
    {
        this.listName = listName;
        this.path = path;
    }

    public String getListName() {
        return listName;
    }

    public String getPath() {
        return path;
    }

    public void newExcel(List<Good> goods) throws Exception {

        HSSFWorkbook workbook = new HSSFWorkbook(); // Создание книги.
        HSSFSheet sheet = workbook.createSheet(getListName()); // Создание листа с названием
        HSSFRow row = sheet.createRow((int) 0);
        HSSFCell cell = row.createCell((short) 0);

        addColumnExcel(cell, row, sheet); // Добавление колон.
        fillAndWriteExcel(goods, row, sheet, workbook); // Заполнение таблицы и сохраниние.
    }

    private static void addColumnExcel(HSSFCell cell, HSSFRow row, HSSFSheet sheet)
    {
        cell = row.createCell((short) 0);
        cell.setCellValue ("Название товара");
        sheet.setColumnWidth(0,4000);
        cell = row.createCell((short) 1);
        cell.setCellValue ("Кол-во товара");
        sheet.setColumnWidth(1,4000);
    }

    private void fillAndWriteExcel(List<Good> goods, HSSFRow row, HSSFSheet sheet, HSSFWorkbook workbook) throws IOException {

        if (goods.size() == 0)
        {
            System.out.println("На складе нет ни одного товара, таблица не может быть создана.");
        }
        else {
            try {
            for (int i = 0; i < goods.size(); i++) {
                row = sheet.createRow((int) i + 1);
                row.createCell(0).setCellValue(goods.get(i).getName());
                row.createCell(1).setCellValue(goods.get(i).getQuantity());
            }
            workbook.write(new FileOutputStream(getPath()));
            System.out.println("Excel файл успешно создан!");
            }
            catch (Exception e)
            {
                System.out.println("Упс, скорее всего вы неправильно указали путь для файла(");
            }
        }
    }
}
