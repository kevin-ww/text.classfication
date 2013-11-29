import junit.framework.TestCase;

public class testDouble extends TestCase {

  protected void setUp() throws Exception {
    super.setUp();
  }

  protected void tearDown() throws Exception {
    super.tearDown();
  }

  public static void testCase1() throws Exception {

    double nc = 1.0;

    String x = String.format("double double %f", nc);

    System.out.println(x);
  }

}
