package com.ws;

public class Main {
	public static void main(String[] args) {
		
		Container.init(); //공용 자원 클래스에서 정적인 스캐너 객체를 생성한다.
		
		new App().run(); //어플리케이션 클래스의 런 메소드를 호출한다.

		Container.close(); // 공용 자원 클래스에서 스캐너 객체의 리소스를 반납한다.
	}
}