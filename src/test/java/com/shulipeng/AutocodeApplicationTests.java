package com.shulipeng;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AutocodeApplicationTests {

	@Test
	public void contextLoads() {
		List<String> vector = new ArrayList<>(3);
		vector.add("1");
		vector.add("2");
		vector.add("3");
		vector.add("4");
		vector.add("5");
		vector.add("6");
		Iterator<String> iterator = vector.iterator();
		while (iterator.hasNext()){
			System.out.println(iterator.next());
		}
	}

}
