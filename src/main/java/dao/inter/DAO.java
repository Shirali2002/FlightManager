package dao.inter;

import java.util.HashMap;
import java.util.Optional;

public interface DAO<A> {
  Optional<HashMap<Integer, A>> getAll();
  Optional<A> getById(int id);
  boolean save(HashMap<Integer, A> data);
  boolean deleteById(int id);
  boolean add(int id, A value);
  boolean updateById(int id, A value);
}
