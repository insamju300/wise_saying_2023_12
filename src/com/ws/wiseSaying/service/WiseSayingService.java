package com.ws.wiseSaying.service;

import java.util.List;

import com.ws.wiseSaying.entity.WiseSaying;
import com.ws.wiseSaying.repository.WiseSayingRepository;

public class WiseSayingService {

	private WiseSayingRepository wiseSayingRepository; //데이터의 보관 및 직접적인 제어를 담당하는 리포지터리 타입의 변수 선언

	public WiseSayingService() { //생성자

		wiseSayingRepository = new WiseSayingRepository();//리포지터리 객체 생성
	}

	public List<WiseSaying> findAll() { //명언의 컬랙션을 가져오기 위한 메소드 선언부
		return wiseSayingRepository.findAll(); //레포지토리 객체에서 findAll메소드 호출
	}

	public int write(String content, String author) { //명언을 등록하기 위한 메소드 선언부. 매개변수는 문자열타입의 명언과 작가.
		return wiseSayingRepository.write(content, author);//레포지토리 객체에서 write메소드 호출, 인자로는 명언과 작가를 넘긴다.
	}

	public WiseSaying findById(int id) {//명언 객체 하나를 받아오기 위한 메소드 선언부. 매개변수는 int형의 id
		return wiseSayingRepository.findById(id); //레포지토리 객체에서 findById메소드 호출, 인자로는 정수형의 id를 넘긴다.
	}

	public void remove(WiseSaying wiseSaying) { //명언 객체 하나를 컬렉션에서 지우기 위한 메소드 선언부. 매개변수는 명언 객체
		wiseSayingRepository.remove(wiseSaying);//레포지토리 객체에서 remove메소드 호출, 인자로는 명언 객체를 넘긴다.
	}

	public void modify(WiseSaying wiseSaying, String content, String author) { //명언 객체 하나를 수정하기 위한 메소드 선언부. 매개변수는 명언 객체, 문자열인 명언과 작가
		wiseSayingRepository.modify(wiseSaying, content, author);//레포지토리 객체에서 modify메소드 호출, 매개변수는 명언 객체, 문자열인 명언과 작가

	}

}