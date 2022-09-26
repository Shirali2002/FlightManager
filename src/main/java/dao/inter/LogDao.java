package dao.inter;

import console.Console;
import model.Log;

public interface LogDao {
  boolean save(String message, Log log, Console console);
}
