package com.bruno.javafx.gui.util;

import com.bruno.javafx.model.dao.MilkDao;
import com.bruno.javafx.model.entities.Milk;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Utils {
    public static Stage currentStage(ActionEvent actionEvent) {
        return (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
    }


    public static <T> void formatTableColumnDate(TableColumn<T, Date> tableColumn, String format) {
        tableColumn.setCellFactory(column -> {
            TableCell<T, Date> cell = new TableCell<T, Date>() {
                private SimpleDateFormat sdf = new SimpleDateFormat(format);

                @Override
                protected void updateItem(Date item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText(null);
                    } else {
                        setText(sdf.format(item));
                    }
                }
            };
            return cell;
        });
    }


    public static void formatDatePicker(DatePicker datePicker, String format) {
        datePicker.setConverter(new StringConverter<LocalDate>() {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(format);

            {
                datePicker.setPromptText(format.toLowerCase());
            }

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        });
    }

    public static void autoInsertMonthsAndReadFilesToSplitQuantity(Path pathFile, MilkDao milkDao, Integer quantityDaysInMonth) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        List<Date> dates = new ArrayList<>();
        List<Milk> milksList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(pathFile.toFile()))) {
            String fields[] = br.readLine().split(" ");
            for (int i = 1; i <= quantityDaysInMonth; i++) {
                dates.add((sdf.parse(i + "/04/2022")));
            }
            for (int i = 0; i < dates.size(); i++) {
                milksList.add(new Milk(dates.get(i), Double.parseDouble(fields[i])));
            }
            for (int i = 0; i < milksList.size(); i++) {
                milkDao.insert(milksList.get(i));
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
