package com.ws;

import com.ws.system.controller.SystemController;
import com.ws.wiseSaying.controller.WiseSayingController;

public class App {

	private byte system_status = 1; //명령어의 반복 입력 루프를 빠져나가기 위한 기준

	public App() {

	}

	public void run() {
		System.out.println("== 명언 앱 실행 ==");

		SystemController systemController = new SystemController();//시스템 콘트롤러 인스턴스 생성
		WiseSayingController wiseSayingController = new WiseSayingController();//와이즈세잉 콘트롤러 인스턴스 생성

		while (system_status == 1) {//명령어를 반복 입력 받아, 처리하기 위한 반복문
			System.out.print("명령어 ) "); //유저가 어떤 정보를 입력해야할지 알려주기 위한 안내
			String cmd = Container.getScanner().nextLine().trim();//유저한테 명령어를 입력받아, 양 끝 공백을 지워준다. 
			Rq rq = new Rq(cmd); //파싱 처리를 위한 리퀘스트 인스턴스를 생성한다.

			switch (rq.getActionCode()) { //명령어에서 쿼리를 제외한 핵심 명령어만 분리하여, 핵심 명령어에 따라 분기처리를 한다.
			case "종료": //만약 핵심 명령어가 "종료"라면
				systemController.exit(); //시스템 컨트롤러에서 종료 메소드를 호출한다.
				system_status = 0; //반복문을 빠져나오기 위한 status 설정.
				break; //제어문에서 나머지 조건문 처리를 실행하지 않도록 빠져나온다.
			case "등록": // 만약 핵심 명령어가 "등록"이라면
				wiseSayingController.write(); //wiseSayingController에서 등록 메소드를 호출한다.
				break;//제어문에서 나머지 조건문 처리를 실행하지 않도록 빠져나온다.
			case "목록": // 만약 핵심 명령어가 "목록"이라면
				wiseSayingController.list(); //wiseSayingController에서 목록 메소드를 호출한다.
				break;//제어문에서 나머지 조건문 처리를 실행하지 않도록 빠져나온다.
			case "삭제": // 만약 핵심 명령어가 "삭제"라면
				wiseSayingController.remove(rq);//wiseSayingController에서 삭제 메소드를 호출하는데 이 때 명령어 파싱처리를 담당하는 객체를 인자값으로 보낸다. 
				break;//제어문에서 나머지 조건문 처리를 실행하지 않도록 빠져나온다.
			case "수정":// 만약 핵심 명령어가 "수정"이라면
				wiseSayingController.modify(rq);//wiseSayingController에서 수정 메소드를 호출하는데 이 때 명령어 파싱처리를 담당하는 객체를 인자값으로 보낸다.
				break;//제어문에서 나머지 조건문 처리를 실행하지 않도록 빠져나온다.
			default: //만약에 상기 핵심명령어를 제외한 명령어가 입력될 경우
				System.out.println("존재하지 않는 명령어입니다"); //유저에게 안내 메세지를 표시한다.
				break; //별 의미 없다.
			}
		}

	}
}