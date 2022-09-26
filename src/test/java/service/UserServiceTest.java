package service;

import app.FlightManager;
import dao.impl.UserRepository;
import model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import java.util.HashMap;
import java.util.Optional;


public class UserServiceTest {
  UserService userService = UserService.getInstance();
  UserRepository userDao = UserRepository.getInstance();
  HashMap<Integer, User> realData;
  User testUser;


  @BeforeEach
  public void setTestEnv() {
    realData = userService.getAllUser();
    userDao.save(new HashMap<>());
    testUser = new User("Shirali", "Alihummatov", "shirali02", "SH1234sh");
  }

  @AfterEach
  public void endTestEnv() {
    userDao.save(realData);
  }

  @Test
  public void testRegisterUser() {
    Optional<User> result = registerTestUser(testUser);
    Assertions.assertEquals(testUser.getUsername(), result.get().getUsername());
  }

  @Test
  public void testLoginUser() {
    Optional<User> result = registerTestUser(testUser);
    userService.login(testUser.getUsername(), testUser.getPassword());
    Assertions.assertEquals(result.get().getId(), FlightManager.getLoggedInUserId());
  }

  private Optional<User> registerTestUser(User testUser){
    userService.register(testUser.getName(), testUser.getSurname(), testUser.getUsername(), testUser.getPassword());
     return userService.getAllUser().values()
        .stream().filter(u ->
            u.getName().equals(testUser.getName())
                && u.getSurname().equals(testUser.getSurname())
                && u.getUsername().equals(testUser.getUsername())
                && u.getPassword().equals(testUser.getPassword())
        ).findFirst();

  }
}
