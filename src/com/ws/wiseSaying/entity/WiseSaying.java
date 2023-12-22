package com.ws.wiseSaying.entity;

public class WiseSaying {
	private int id;  //멤버필드 : id값. 유니크키
	private String content; // 멤버필드 : 명언
	private String author; // 멤버필드 : 작가

	public WiseSaying(int id, String content, String author) { // 생성자. id, 명언, 작가를 매개변수로 받는다.
		this.id = id; //매개변수의 id를 인스턴스의 id로 설정
		this.content = content; //매개변수의 명언을 인스턴스의 명언값으로 설정
		this.author = author;   // 매개변수의 작가를 인스턴스의 명언값으로 설정
	}

	public int getId() { //id 필드값 가져오기 위한 메서드
		return id;//인스턴스의 id값 반환
	}

	public void setId(int id) {//id 필드값에 값을 변경하기 위한 메서드
		this.id = id;//id 인스턴스의 필드값에 매개변수의 id 값을 할당
	}

	public String getContent() {//명언 필드값 가져오기 위한 메서드
		return content;//인스턴스의 명언값 반환
	}

	public void setContent(String content) { //명언 필드의 값을 변경하기 위한 메소드
		this.content = content;//명언 인스턴스의 필드값에 매개변수의 명언 값을 할당
	}

	public String getAuthor() {//작가 필드값 가져오기 위한 메서드
		return author;//인스턴스의 작가값 반환
	}

	public void setAuthor(String author) { //작가 필드의 값을 변경하기 위한 메소드
		this.author = author;//작가 인스턴스의 필드값에 매개변수의 작가 값을 할당
	}

}