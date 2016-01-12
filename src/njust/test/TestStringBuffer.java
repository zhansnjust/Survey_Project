package njust.test;


import org.junit.Test;

public class TestStringBuffer {

	

	@Test
	public void test() {
		StringBuffer sb=new StringBuffer("abc");
		System.out.println(sb.substring(0,sb.length()-1));
	}

}
