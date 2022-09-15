package util;

import database.DB;

import java.io.*;
import java.util.Optional;

public class IdUtil {
  private static final String dbFolderPath = "src/main/java/database/id/";

  public static Optional<Integer> getNewId(DB db) {
    try (BufferedReader br = new BufferedReader(new FileReader(dbFolderPath+db.getFileName()))) {
      int id = Integer.parseInt(br.readLine());
      idIncrement(id, db);
      return Optional.of(id);
    } catch (IOException e) {
      return Optional.empty();
    } catch (NumberFormatException nfe){
      // burda log olacaq
      return Optional.empty();
    }
  }

  private static void idIncrement(int id, DB db) {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(dbFolderPath+db.getFileName()))) {
      bw.write(String.valueOf(++id));
    } catch (IOException e) {
      throw new RuntimeException("There is problem with id generator.");
    }
  }


}
