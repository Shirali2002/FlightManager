package dao;

import database.DB;
import model.Flight;
import model.Ticket;
import util.DatabaseUtil;

import java.util.HashMap;
import java.util.Optional;

public class TicketRepository implements DAO<Ticket> {
  public static final TicketRepository ticketRepo = new TicketRepository();

  private TicketRepository() {
  }

  public static TicketRepository getInstance() {
    return ticketRepo;
  }

  @Override
  public Optional<HashMap<Integer, Ticket>> getAll() {
    return DatabaseUtil.getAll(DB.TICKET_DB);
  }

  @Override
  public boolean save(HashMap<Integer, Ticket> data) {
    return DatabaseUtil.save(data, DB.TICKET_DB);
  }

  @Override
  public Optional<Ticket> getById(int id) {
    return DatabaseUtil.getById(id, DB.TICKET_DB);
  }

  @Override
  public boolean deleteById(int id) {
    return DatabaseUtil.deleteById(id, DB.TICKET_DB);
  }

  @Override
  public boolean add(int id, Ticket value) {
    return DatabaseUtil.add(id, value, DB.TICKET_DB);
  }

  @Override
  public boolean updateById(int id, Ticket value) {
    if (getById(id).isEmpty()){
      return false;
    }
    value.setId(id);
    return DatabaseUtil.updateById(id, value, DB.TICKET_DB);
  }
}
