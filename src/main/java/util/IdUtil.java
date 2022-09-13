package util;

import java.io.*;
import java.util.Optional;

public class IdUtil {
  private int id;
  private final String idFilePath = "src/main/java/database/UserIdFile.txt";
  private static final IdUtil idUtil = new IdUtil();

  private IdUtil() {
  }

  public static IdUtil getInstance() {
    return IdUtil.idUtil;
  }

  public Optional<Integer> getNewId() {
    try (BufferedReader br = new BufferedReader(new FileReader(idFilePath))) {
      int id = Integer.parseInt(br.readLine());
      idIncrement(id);
      return Optional.of(id);
    } catch (IOException e) {
      return Optional.empty();
    }
  }

  private void idIncrement(int id) {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(idFilePath))) {
      bw.write(id + 1);
    } catch (IOException e) {
      throw new RuntimeException("There is problem with id generator.");
    }
  }


}
