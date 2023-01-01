package com.lzx.zcy.class15;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Code03_PrintAllSubSequences {

	// result11 为有重复的结果
	public List<String> result11;
	// result12 为无重复的结果
	public Set<String> result12;

	// 打印一个字串的所有子序列
	public void subs1(String s) {
		if (s.length() == 0){
			return;
		}
		result11 = new ArrayList<>();
		result12 = new HashSet<>();
		StringBuilder s1 = new StringBuilder();
		StringBuilder s2 = new StringBuilder(s);
		subs1(s1, s2);
	}
	private void subs1(StringBuilder s1, StringBuilder s2) {
		if (s2.length() == 0){
			if (s1.length() > 0){
				result11.add(s1.toString());
				result12.add(s1.toString());
			}
			return;
		}
		StringBuilder s11 = new StringBuilder(s1);
		StringBuilder s21 = new StringBuilder(s2.subSequence(1, s2.length()));
		StringBuilder s12 = new StringBuilder(s1.append(s2.subSequence(0,1)));
		StringBuilder s22 = new StringBuilder(s2.subSequence(1, s2.length()));

		subs1(s11, s21);
		subs1(s12, s22);
	}

	@Test
	public void test_subs() {
	    String[] strings = {"abc", "aaa"};
		for (String s : strings) {
			subs1(s);
			System.out.println("***** " + s + " : result11" + " *****");
			result11.forEach(System.out::println);
			System.out.println("***** " + s + " : result12" + " *****");
			result12.forEach(System.out::println);
		}
	}
}
