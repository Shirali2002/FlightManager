package util;

import model.IdFIle;

import java.io.*;
import java.util.Optional;

public class IdUtil {
  private int id;
  private static final String dbFolderPath = "src/main/java/database/";
  private static final IdUtil idUtil = new IdUtil();

  private IdUtil() {
  }

  public static IdUtil getInstance() {
    return IdUtil.idUtil;
  }

  public static Optional<Integer> getNewId(IdFIle fileName) {
    try (BufferedReader br = new BufferedReader(new FileReader(dbFolderPath+fileName.getFileName()))) {
      int id = Integer.parseInt(br.readLine());
      getInstance().idIncrement(id, fileName);
      return Optional.of(id);
    } catch (IOException e) {
      return Optional.empty();
    }
  }

  private void idIncrement(int id, IdFIle fileName) {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(dbFolderPath+fileName.getFileName()))) {
      bw.write(id + 1);
    } catch (IOException e) {
      throw new RuntimeException("There is problem with id generator.");
    }
  }


}
