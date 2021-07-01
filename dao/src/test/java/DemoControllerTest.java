import com.flamexander.spring.security.cookbook.dao.controllers.DemoController;
import com.flamexander.spring.security.cookbook.dao.entities.User;
import com.flamexander.spring.security.cookbook.dao.services.TestService;
import com.flamexander.spring.security.cookbook.dao.services.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.ParameterizedTest;
import org.mockito.Mockito;

import java.security.Principal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DemoControllerTest {
    private DemoController demoController;
    private final User user = new User(
            "user",
            "$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i",
            "user@mail.com"
    );
    private final Principal principal = Mockito.mock(Principal.class);

    @BeforeAll
    public void setUp() {
        UserService userService = mock(UserService.class);
        TestService testService = mock(TestService.class);
        demoController = new DemoController(userService, testService);

        when(userService.findByUsername(user.getUsername())).thenReturn(Optional.of(user));
        when(principal.getName()).thenReturn("user");
    }

    @Test
    public void getUserTest() {
        User userActual = demoController.getUser(principal);
        assertNotNull(userActual);
        assertTrue(userActual.getUsername().equalsIgnoreCase(user.getUsername()));
    }

    @CsvSource({"1, 1, 2", "2, 3, 5"})
    @ParameterizedTest
    public void calcDataTest(int a, int b, int result) {
        assertEquals(result, demoController.calcData(a, b));
    }
}


