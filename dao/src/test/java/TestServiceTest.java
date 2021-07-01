import com.flamexander.spring.security.cookbook.dao.services.TestService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestServiceTest {
    private TestService testService;

    @BeforeAll
    public void setUp(){
        this.testService = new TestService();
    }

    @Test
    public void testSecuredByRoleMethod() {
        String s = testService.SecuredByRoleMethod();
        assertEquals("role admin", s);
    }

    @Test
    @Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
    @Disabled
    public void testSecuredByAuthorityMethod() {
        String s = testService.SecuredByAuthorityMethod();
        assertEquals("read all messages", s);
    }

    @ParameterizedTest
    @MethodSource("dataForAddOperation")
    public void testAddOperation(int a, int b, int result) {
        assertEquals(result, testService.calcData(a, b));
    }

    public static Stream<Arguments> dataForAddOperation() {
        List<Arguments> out = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            int a = (int)(Math.random() * 1000);
            int b = (int)(Math.random() * 1000);
            int result = a + b;
            out.add(Arguments.arguments(a, b, result));
        }
        return out.stream();
    }
}


