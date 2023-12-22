package com.ws;

import java.util.Scanner;

public class Container {
	private static Scanner sc; //입력 처리에 공동으로 사용하기 위한 변수. 인스턴스 생성없이 돌려쓸거라 static

	// 공통적으로 사용되는 자원들을 모아두는 공간 초기화
	public static void init() { // 스캐너 변수의 초기화를 위한 매서드
		sc = new Scanner(System.in); // 스캐너 변수의 초기화 실시
	}

	// 공통적으로 사용되는 자원들을 모아두는 공간 자원 해제
	public static void close() { // 스캐너 객체에 할당된 리소스를 해제하는 메소드
		sc.close(); // 스캐너 객체의 리소스 해제
	}

	public static Scanner getScanner() { //스캐너 객체를 제공하기 위한 메소드
		return sc; //스캐너 객체를 반환한다.
	}
}