package com.lzx.zcy.class15;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

// 打印 一个字符串的 的全排列
public class Code04_PrintAllPermutations {

	private List<String> result1;
	private HashSet<String> result2;
	private List<String> result3;

	// result1 为可以重复的解, result2 为不能重复的解
	public void permutation1(String s) {
		if (s.length() == 0){
			return;
		}
		result1 = new ArrayList<>();
		result2 = new HashSet<>();
		permutation1(s.toCharArray(), 0);
	}
	// 约定, index 之前的 字符已经确定好位置了
	private void permutation1(char[] chars, int index) {
		if (index == chars.length-1){
			result1.add(new String(chars));
			result2.add(new String(chars));
			return;
		}
		// index 位置的字符不变, 或者和后面的字符交换一次
		permutation1(chars, index+1);
		for (int i = index + 1; i < chars.length; i++) {
			swap(chars, i, index);
			permutation1(chars, index+1);
			swap(chars, i, index);
		}
	}

	private void swap(char[] chars, int i, int j) {
		char temp = chars[i];
		chars[i] = chars[j];
		chars[j] = temp;
	}



	// 不用 set 实现去重
	// result3 为不能重复的解
	public void permutation2(String s) {
		if (s.length() == 0){
			return;
		}
		result3 = new ArrayList<>();
		permutation2(s.toCharArray(), 0);
	}
	// 约定, index 之前的 字符已经确定好位置了
	private void permutation2(char[] chars, int index) {
		if (index == chars.length-1){
			result3.add(new String(chars));
			return;
		}
		// 规定字符串只有 26 个字母
		boolean[] visited = new boolean[26];
		permutation2(chars, index+1);
		visited[chars[index]-'a'] = true;
		for (int i = index + 1; i < chars.length; i++) {
			if (visited[chars[i]-'a']){
				continue;
			}
			visited[chars[i]-'a'] = true;
			swap(chars, i, index);
			permutation2(chars, index+1);
			swap(chars, i, index);
		}
	}

	@Test
	public void test_permutation() {

		String[] strings = {"abc", "aaa", "aac", "cddc"};
		for (String s : strings) {
			permutation1(s);
			permutation2(s);
			System.out.println("***** " + s + " : result1" + " *****");
			result1.forEach(System.out::println);
			System.out.println("***** " + s + " : result2" + " *****");
			ArrayList<String> s2 = new ArrayList<>(result2);
			s2.sort(String::compareTo);
			s2.forEach(System.out::println);
			System.out.println("***** " + s + " : result3" + " *****");
			result3.forEach(System.out::println);
		}
	}

}
