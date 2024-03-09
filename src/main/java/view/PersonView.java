package view;

import model.Person;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.util.List;

public class PersonView {
    public void menu() {
        Table table = new Table(1, BorderStyle.UNICODE_BOX_HEAVY_BORDER, ShownBorders.SURROUND_HEADER_AND_COLUMNS);
        CellStyle cellStyle = new CellStyle(CellStyle.HorizontalAlign.CENTER);
        table.setColumnWidth(0, 40, 40);
        table.addCell("MENU",cellStyle);
        table.addCell("1. Add New Person");
        table.addCell("2. Show All Person");
        table.addCell("3. Delete Person");
        table.addCell("4. Exit!!!");
        table.addCell("Enter Option: ");
        System.out.println(table.render());
    }
    public void renderPerson(List<Person> personList) {
        Table table = new Table(5, BorderStyle.UNICODE_BOX_HEAVY_BORDER,
                ShownBorders.SURROUND_HEADER_FOOTER_AND_COLUMNS);
        CellStyle cellStyle = new CellStyle(CellStyle.HorizontalAlign.CENTER);
        CellStyle cellStyleRight = new CellStyle(CellStyle.HorizontalAlign.RIGHT);
        CellStyle cellStyleLeft = new CellStyle(CellStyle.HorizontalAlign.LEFT);

        table.setColumnWidth(0, 10, 10);
        table.setColumnWidth(1, 20, 20);
        table.setColumnWidth(2, 10, 10);
        table.setColumnWidth(3, 40, 40);
        table.setColumnWidth(4, 60, 60);

        // Add headers
        table.addCell("ID", cellStyle);
        table.addCell("Full Name", cellStyle);
        table.addCell("Gender", cellStyle);
        table.addCell("Email", cellStyle);
        table.addCell("Address", cellStyle);

        for(Person person : personList) {
            table.addCell(String.valueOf(person.getId()));
            table.addCell(person.getFullName());
            table.addCell(person.getGender());
            table.addCell(person.getEmail());
            table.addCell(person.getAddress());
        }

        System.out.println(table.render());
    }
    public void showEachPerson(String msg, Person person) {
        Table table = new Table(2, BorderStyle.UNICODE_BOX_HEAVY_BORDER, ShownBorders.SURROUND_HEADER_AND_COLUMNS);
        CellStyle cellStyle = new CellStyle(CellStyle.HorizontalAlign.CENTER);

        table.setColumnWidth(0, 30, 30);
        table.setColumnWidth(1, 30, 30);

        table.addCell(msg, cellStyle, 2);
        table.addCell("Code");
        table.addCell(String.valueOf(person.getId()));
        table.addCell("Name");
        table.addCell(person.getFullName());
        table.addCell("Gender");
        table.addCell(person.getGender());
        table.addCell("Email");
        table.addCell(person.getEmail());
        table.addCell("Address");
        table.addCell(person.getAddress());
        table.addCell("");
        System.out.println(table.render());
    }
}
