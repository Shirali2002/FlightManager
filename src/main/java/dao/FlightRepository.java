package dao;

import database.DB;
import model.Flight;
import util.DatabaseUtil;

import java.util.HashMap;
import java.util.Optional;

public class FlightRepository implements DAO<Flight> {
  public static final FlightRepository flightRepo = new FlightRepository();

  private FlightRepository() {
  }

  public static FlightRepository getInstance() {
    return flightRepo;
  }

  @Override
  public Optional<HashMap<Integer, Flight>> getAll() {
    return DatabaseUtil.getAll(DB.FLIGHT_DB);
  }

  @Override
  public boolean save(HashMap<Integer, Flight> data) {
    return DatabaseUtil.save(data, DB.FLIGHT_DB);
  }

  @Override
  public Optional<Flight> getById(int id) {
    return DatabaseUtil.getById(id, DB.FLIGHT_DB);
  }

  @Override
  public boolean deleteById(int id) {
    return DatabaseUtil.deleteById(id, DB.FLIGHT_DB);
  }

  @Override
  public boolean add(int id, Flight value) {
    return DatabaseUtil.add(id, value, DB.FLIGHT_DB);
  }

  @Override
  public boolean updateById(int id, Flight value) {
    if (getById(id).isEmpty()){
      return false;
    }
    value.setId(id);
    return DatabaseUtil.updateById(id, value, DB.FLIGHT_DB);
  }
}
