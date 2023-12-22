package com.ws.wiseSaying.repository;

import java.util.ArrayList;
import java.util.List;

import com.ws.wiseSaying.entity.WiseSaying;

public class WiseSayingRepository { //데이터의 보관 및 제어를 위한 클래스

	private int lastWiseSayingId; //시퀀스
	private List<WiseSaying> wiseSayings; //명언 객체를 담기위한 컬렉션 

	public WiseSayingRepository() { //생성자

		lastWiseSayingId = 0; //시퀀스 초기값은 0으로 초기화
		wiseSayings = new ArrayList<>(); //wiseSayings변수에 ArrayList 객체 생성하여 할당
	}

	public void remove(WiseSaying wiseSaying) { //매개변수로 입력받은 명언 객체를 컬렉션에서 삭제하기 위한 메소드. 
		wiseSayings.remove(wiseSaying); //매개변수로 입력받은 명언 객체를 컬렉션에서 조회해 삭제한다.
	}

	public WiseSaying findById(int id) { //매개변수로 입력받은 id값과 일치하년 명언 객체를 컬렉션에서 찾아오는 매개변수
		for (WiseSaying wiseSaying : wiseSayings) {//컬렉션 wiseSayings의 요소를 순회하며, 명언 객체를 변수 wiseSayting에 담는다.
			if (wiseSaying.getId() == id) { //만약에 객체의 id가 매개변수로 입력받은 id와 일치하면
				return wiseSaying; //객체를 반환한다.
			}
		}

		return null; //id에 해당하는 객체가 없을 경우 null을 반환한다.
	}

	public void modify(WiseSaying wiseSaying, String content, String author) {//명언 객체, 명언, 작가를 입력받아 객체를 수정하기 위한 매소드
		wiseSaying.setContent(content);// 매개변수로 입력받은 명언 객체의 명언을, 문자열 매개변수로 받은 명언으로 변경한다.
		wiseSaying.setAuthor(author);// 매개변수로 입력받은 명언 객체의 작가를, 문자열 매개변수로 받은 작가로 변경한다.
	}

	public int write(String content, String author) { //명언과 작가를 입력받아 객체를 생성하여 컬렉션에 추가하기 위한 메소드
		int id = lastWiseSayingId + 1; //id변수에 시퀀스로 쓸 lastWiseSayingId에 1을 더한 숫자를 등록한다.

		WiseSaying wiseSaying = new WiseSaying(id, content, author);//위에서 설정한 id변수, 매개변수로 받은 명언과 작가를 매개변수로 명언 객체를 생성해 wiseSaying변수에 담는다.
		wiseSayings.add(wiseSaying); //컬랙션에 위에서 생성한 명언 객체를 추가한다.

		lastWiseSayingId = id; // 방금 전에 새 명언이 생겼으니, lastWiseSayingId의 값을 갱신

		return id; //id변수값을 반환한다.
	}

	public List<WiseSaying> findAll() {//현재상태의 명언 컬렉션을 반환하기 위한 메소드 
		return wiseSayings;//현재 상태의 명언 컬렉션을 반환한다.
	}

}