package util;

import database.DB;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Optional;

public class DatabaseUtil {
  private static final String dbFolderPath = "src/main/java/database/db/";

  public static <B> Optional<HashMap<Integer, B>> getAll(DB db) {
    try (ObjectInputStream oi = new ObjectInputStream(new FileInputStream(db.getFileName()))) {
      return Optional.of((HashMap<Integer, B>) oi.readObject());
    } catch (IOException | ClassNotFoundException e) {
      return Optional.empty();
    }

  }

  public static <B> boolean save(HashMap<Integer, B> data, DB db) {
    try (ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream(db.getFileName()))) {
      oo.writeObject(data);
      return true;
    } catch (IOException e) {
      return false;
    }
  }

  public static <B> Optional<B> getById(int id, DB db){
    return DatabaseUtil.<B>getAll(db)
        .map(integerBHashMap -> integerBHashMap.get(id));
  }

  public static <B> boolean deleteById(int id, DB db){
    try {
      HashMap<Integer, B> data = DatabaseUtil.<B>getAll(db).orElse(new HashMap<>());
      data.remove(id);
      DatabaseUtil.<B>save(data, db);
      return true;
    } catch (Exception e){
      return false;
    }
  }

  public static <B> boolean updateById(int id, B value, DB db){
    try {
      HashMap<Integer, B> data = DatabaseUtil.<B>getAll(db).orElse(new HashMap<>());
      data.put(id, value);
      DatabaseUtil.<B>save(data, db);
      return true;
    } catch (Exception e){
      return false;
    }
  }
}
