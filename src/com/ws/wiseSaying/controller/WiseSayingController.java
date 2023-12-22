package com.ws.wiseSaying.controller;

import java.util.List;

import com.ws.Container;
import com.ws.Rq;
import com.ws.wiseSaying.entity.WiseSaying;
import com.ws.wiseSaying.service.WiseSayingService;

public class WiseSayingController {

	private WiseSayingService wiseSayingService; // 비즈니스 로직을 처리할 서비스 객체를 담을 변수를 선언한다.

	public WiseSayingController() {// 생성자 메소드
		wiseSayingService = new WiseSayingService(); // 비지니스 로직을 처리할 서비스 객체를 생성한다.
	}

	public void write() { // 등록 처리를 위한 입출력과, 서비스 메서드 호출을 담당하는 메소드
		System.out.print("명언 : "); // 유저 안내 문구를 출력한다.
		String content = Container.getScanner().nextLine().trim(); // 입력 받은 명언에서 공백을 제거하여 변수명 content에 담는다.
		System.out.print("작가 : "); // 유저 안내 문구를 출력한다.
		String author = Container.getScanner().nextLine().trim(); // 입력받은 작가에서 공백을 제거하여 변수명 author에 담는다.

		int id = wiseSayingService.write(content, author); // 서비스 객체에서 write메소드를 호출하는데, 인자로 입력받은 명언과, 작가를 넘긴다. 반환타입은
															// 정수형이다.

		System.out.printf("%d번 명언이 등록되었습니다.\n", id); // 반환받은 정수형(등록된 명언 객체의 번호)를 안내문구로 출력한다.
	}

	public void list() {// 등록한 명언 목록을 서비스 객체에서 받아와 출력하기 위한 메소드
		List<WiseSaying> wiseSayings = wiseSayingService.findAll(); // 서비스 객체에서 findAll메소드를 호출하여, 반환된 WiseSaying타입만 보관할
																	// 수 있는 리스트 컬렉션을 wiseSayings라는 변수에 담는다.

		System.out.println("번호  /  작가  /  명언  "); // 반환된 명언 리스트 출력을 위해 필드에 대한 설명을 출력한다.
		System.out.println("=".repeat(30)); // 경계선을 출력한다.

		for (int i = wiseSayings.size() - 1; i >= 0; i--) { // 리스트의 크기만큼 순회하며 아래 처리를 반복한다.
			WiseSaying ws = wiseSayings.get(i); // i에 저장된 인덱스값의 명언을 가져온다.

			System.out.printf("%d  /  %s  /  %s\n", ws.getId(), ws.getAuthor(), ws.getContent()); // 가져온 명언을 출력한다.
		}
	}

	public void remove(Rq rq) { // 등록된 명언을 제거하기 위한 메소드. 매개변수로 파싱처리를 담당하는 객체를 담는다.

		int id = rq.getIntParam("id", -1); // rq클래스에서 getIntParam메소드를 호출하는데, 인자로 쿼리의 파람명과, 만약에 파람벨류의 유효성 검증에 실패할 경우 반환할
											// 정수값을 넘긴다.
		// 처리 결과를 정수형의 id라는 변수에 담는다.

		if (id == -1) {// 만약에 처리 결과인 정수값이 파람벨류의 유효성 검증에 실패할 경우 반환되는 정수값과 동일하다면
			System.out.println("id(정수)를 제대로 입력해주세요"); // 유저에게 보여줄 경고 문구를 출력한다.
			return; // 이하의 처리를 생략하고 호출한 곳으로 돌아간다.
		}
		// 입력된 id와 일치하는 명언 객체 찾기
		WiseSaying wiseSaying = wiseSayingService.findById(id); // 서비스 객체의 findById메소드를 호출하는데 매개변수로, getIntParam메소드에서
																// 받아온 정수값을 넘겨주고 반환값은 명언 타입으로 받아서 wiseSaying변수에 담는다.

		if (wiseSaying == null) { // 만약 id에 해당하는 wiseSaying 객체를 findById메소드에서 반환받지 못했다면
			System.out.printf("%d번 명언은 존재하지 않습니다.\n", id); // 해당 번호는 존재하지 않는다는 경고문구를 출력한다.
			return; // 이하 처리를 생략하고 호출한 곳으로 돌아간다.
		}

		// 찾은 명언 객체를 object기반으로 삭제
		wiseSayingService.remove(wiseSaying); // 서비스 객체의 remove 메소드를 호출 하는데 매개 변수는, findById로 찾아온 명언 객체이고, 반환타입은 없다.

		System.out.printf("%d번 명언이 삭제되었습니다.\n", id); // getIntParam에서 받아온 id와 명언이 삭제되엇다는 안내 문구를 같이 출력해준다.

	}

	public void modify(Rq rq) { // 등록된 명언을 수정하기 위한 메서드, 매개변수로 문자열 파싱을 위한 객체를 받는다.
		int id = rq.getIntParam("id", -1); // rq클래스에서 getIntParam메소드를 호출하는데, 인자로 쿼리의 파람명과, 만약에 파람벨류의 유효성 검증에 실패할 경우 반환할
											// 정수값을 넘긴다.
		// 처리 결과를 정수형의 id라는 변수에 담는다.

		if (id == -1) {
			{// 만약에 처리 결과인 정수값이 파람벨류의 유효성 검증에 실패할 경우 반환되는 정수값과 동일하다면
				System.out.println("id(정수)를 제대로 입력해주세요"); // 유저에게 보여줄 경고 문구를 출력한다.
				return; // 이하의 처리를 생략하고 호출한 곳으로 돌아간다.
			}
		}
		// 입력된 id와 일치하는 명언 객체 찾기

		WiseSaying wiseSaying = wiseSayingService.findById(id); // 서비스 객체의 findById메소드를 호출하는데 매개변수로,
		// getIntParam메소드에서 받아온 정수값을 넘겨주고 반환값은 명언 타입으로 받아서
		// wiseSaying변수에 담는다.

		if (wiseSaying == null) {// 만약 id에 해당하는 wiseSaying 객체를 findById메소드에서 반환받지 못했다면
			System.out.printf("%d번 명언은 존재하지 않습니다.\n", id);// 해당 번호는 존재하지 않는다는 경고문구를 출력한다.
			return; // 이하 처리를 생략하고 호출한 곳으로 돌아간다.
		}

		// 찾은 명언 객체를 object기반으로 수정
		System.out.println("명언(기존) :" + wiseSaying.getContent()); // findById메소드로 얻어온 객체에서 명언 필드를 출력한다.
		System.out.println("작가(기존) :" + wiseSaying.getAuthor()); // findById메소드로 얻어온 객체에서 작가 필드를 출력한다.

		System.out.print("명언 : "); // 유저가 입력하기 쉽도록 안내 문구 출력
		String content = Container.getScanner().nextLine().trim(); // 유저한테 문자열을 입력받아 공백을 제거해서 content라는 변수에 담는다.
		System.out.print("작가 : "); // 유저가 입력하기 쉽도록 안내 문구 출력
		String author = Container.getScanner().nextLine().trim(); // 유저한테 문자열을 입력받아 공백을 제거해서 author이라는 변수에 담는다.

		wiseSayingService.modify(wiseSaying, content, author); // 서비스에서 modify메소드를 실행하는데 매개변수는, findById로 받아온 명언 객체,
																// 입력받은 수정할 명언 내용, 입력받은 수정할 작가명이다.

		System.out.printf("%d번 명언이 수정되었습니다.\n", id); // getIntParam에서 받아온 id와 수정되었다는 안내문구를 출력한다.

	}

}
