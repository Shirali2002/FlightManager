package dao.impl;


import console.Console;
import dao.inter.LogDao;
import database.DB;
import model.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogRepository implements LogDao {
  private static final LogRepository logRepository = new LogRepository();
  private final String dbFolderPath = "src/main/java/database";

  private LogRepository() {
  }

  public static LogRepository getInstance(){
    return logRepository;
  }

  @Override
  public boolean save(String message, Log log, Console console) {
    File f = new File(dbFolderPath + DB.LOG_DB.getFileName());
    if (!f.exists()){
      try {
        if(!f.createNewFile()){
          throw new IOException();
        }
      } catch (IOException e) {
        console.printLine("Can not created Log file.");
        return false;
      }
    }

    try (BufferedWriter bw = new BufferedWriter(new FileWriter(f, true))){
      String formattedDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
      bw.append(String.format("%s [%s] --- %s\n", formattedDateTime, log.name(), message));
      return true;
    } catch (IOException e) {
      console.printLine("There is problem with File writer.");
      return false;
    }
  }
}
