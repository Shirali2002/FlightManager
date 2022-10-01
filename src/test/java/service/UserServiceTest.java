package service;

import app.FlightManager;
import dao.impl.UserRepository;
import exception.NoSuchUserException;
import model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import java.util.HashMap;
import java.util.Map;
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
    public void testRegisterUser_Success() {
        Optional<User> result = registerTestUser(testUser);
        Assertions.assertEquals(testUser.getUsername(), result.get().getUsername());
    }

    private Optional<User> registerTestUser(User testUser) {
        userService.register(testUser.getName(), testUser.getSurname(), testUser.getUsername(), testUser.getPassword());
        return userService.getAllUser().values()
                .stream().filter(u -> u.getName().equals(testUser.getName())
                        && u.getSurname().equals(testUser.getSurname())
                        && u.getUsername().equals(testUser.getUsername())
                        && u.getPassword().equals(testUser.getPassword())
                ).findFirst();
    }

    @Test
    public void testLoginUser_Success() {
        Optional<User> result = registerTestUser(testUser);
        userService.login(testUser.getUsername(), testUser.getPassword());
        Assertions.assertEquals(result.get().getId(), FlightManager.getLoggedInUserId());
    }

    @Test
    public void testLoginUser_WhenUserNotFound() {
        userService.login(testUser.getUsername(), testUser.getPassword());
        Assertions.assertEquals(-1, FlightManager.getLoggedInUserId());
    }


    @Test
    public void testGetUserById_Success() {
        var expected = registerTestUser(testUser);
        var actual = userService.getUserById(expected.get().getId());
        Assertions.assertEquals(expected.get(), actual);
    }

    @Test
    public void testGetUserById_WhenUserNotFound_ThrowNoSuchUserException() {
        Assertions.assertThrows(NoSuchUserException.class,
                () -> userService.getUserById(testUser.getId()));
    }

    @Test
    public void testGetAllUser_Success() {
        Optional<User> user = registerTestUser(testUser);
        var expected = Map.of(user.get().getId(), user.get());
        var actual = userService.getAllUser();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testDeleteUserById_Success() {
        Optional<User> user = registerTestUser(testUser);
        var expected = new HashMap<>();
        userService.deleteUserById(user.get().getId());
        var actual = userService.getAllUser();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testDeleteUserById_WhenUserNotFound_ThrowNoSuchUserException() {
        Assertions.assertThrows(NoSuchUserException.class, () -> userService.deleteUserById(1));
    }
}
