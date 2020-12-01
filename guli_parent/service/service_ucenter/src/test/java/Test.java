import com.lbvguli.commonutils.JwtUtils;

public class Test {
    @org.junit.Test
    public void test() {
        String lbw = JwtUtils.getJwtToken("1111", "lbw");
        System.out.println(lbw);
    }
}
