package com.shulipeng;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AutocodeApplicationTests {

	@Test
	public void contextLoads() {
		Random ran = new Random(100);
		System.out.println(ran.nextInt(27) );
	}

	private static final String randomString(int i){
		Random ran = new Random(i);
		StringBuilder stringBuilder = new StringBuilder();
		while (true){
			int k = ran.nextInt(27);
			if(k == 0){
				break;
			}
			stringBuilder.append((char)('`'+k));
		}
		return stringBuilder.toString();
	}

}
