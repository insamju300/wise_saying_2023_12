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

	public void remove(WiseSaying wiseSaying) { //명언 객체를 매
		wiseSayings.remove(wiseSaying);
	}

	public WiseSaying findById(int id) {
		for (WiseSaying wiseSaying : wiseSayings) {
			if (wiseSaying.getId() == id) {
				return wiseSaying;
			}
		}

		return null;
	}

	public void modify(WiseSaying wiseSaying, String content, String author) {
		wiseSaying.setContent(content);
		wiseSaying.setAuthor(author);
	}

	public int write(String content, String author) {
		int id = lastWiseSayingId + 1;

		WiseSaying wiseSaying = new WiseSaying(id, content, author);
		wiseSayings.add(wiseSaying);

		lastWiseSayingId = id; // 방금 전에 새 명언이 생겼으니, lastWiseSayingId의 값을 갱신

		return id;
	}

	public List<WiseSaying> findAll() {
		return wiseSayings;
	}

}