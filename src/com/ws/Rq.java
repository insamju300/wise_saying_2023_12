package com.ws;

import java.util.HashMap;
import java.util.Map;

// Rq == Request(요청)
public class Rq {
	private String actionCode;  //전체 명령어에서 쿼리문을 제외한 핵심 명령어를 담기 위한 변수
	private Map<String, String> params; //쿼리문의 파라미터 명과 벨류를 Map형식으로 담기 위한 변수

	public Rq(String cmd) { //Rq클래스의 생성자. 매개변수는 전체 명령어이다.
 		String[] cmdBits = cmd.split("\\?", 2); //명령어를 ?라는 글자 기준으로, 크기가 2 이하인 String 배열 객체로 분리한다. 

		actionCode = cmdBits[0]; //?라는 글자를 기준으로 앞쪽에 있는 글자를 문자열 타입 전역변수 actionCode에 담는다.

		params = new HashMap<>(); //맵 인터페이스에 hashMap구현체를 연결한다.

		if (cmdBits.length == 1) { //만약에 ?기준으로 나눠진 배열의 크기가 1이라면(쿼리문이 존재하지 않는다면)
			return; // 더이상 처리하지 않고 호출한 메소드로 돌아간다.
		}

		String[] paramBits = cmdBits[1].split("&"); //배열의 두번째 요소(쿼리문)을 문자열'&'기준으로 분리하여 문자열 배열 객체로 만든 후, paramBits 변수에 담는다.  

		for (String paramStr : paramBits) { //paramBits의 모든 요소를 순회하며, 각 요소를 paramStr변수에 담는다.
			String[] paramStrBits = paramStr.split("=", 2); //paramStr에 담긴 문자열을 '='기준으로 분리하여 문자열 배열을 만들어 paramStrBits라는 변수에 담는데, 배열 크기는 2이하이다.

			if (paramStrBits.length == 1) {//만약에 만들어진 배열에 요소가 하나라면('='을 기준으로 파라미터 명과 벨류가 쌍으로 입력된게 아니라면)
				continue;//이하 처리를 무시하고 반복문의 컨디션 체크로 돌아간다. 
			}

			String key = paramStrBits[0]; //paramStrBits의 첫번째 요소(파라미터 명)을 key라는 변수에 담는다.
			String value = paramStrBits[1]; //paramStrBits의 두번째 요소 (파라미터 벨류)를 value라는 변수에 담는다.
			params.put(key, value); // 키 벨류 쌍으로 맵타입 전역변수 params에 보존한다.
		}

	}

	public String getActionCode() { //문자열 타입의 결과값(핵심명령어)을 반환하는 메소드 선언부.
		return actionCode; //이 클래스의 생성자부분에서, 명령어로부터 분리해낸 핵심 명령어를 반환한다.
	}

	public String getParam(String name) {//문자열 타입의 결과값(쿼리의 파라미터 벨류)를 반환하는 메소드 선언부.
		return params.get(name); //매개변수로 건내진 파라미터 명에 대입되는 파라미터 벨류를 반환한다. 파라미터 명과 벨류는 생성자에서 처리되는 쿼리문에 의해 정의된다.
	}

	public int getIntParam(String name, int defaultValue) {//정수 타입의 결과값(쿼리의 파라미터 벨류)를 반환하는 메소드 선언부.
		try {//정수로 변환할 수 없는 문자열이 쿼리의 파라미터 벨류에 담겨왔을 때 예외처리 하기 위한 try catch 문
			return Integer.parseInt(getParam(name)); //만약에 매개변수로 건내진 파라미터 명에 대입되는 파라미터 벨류가 정수로 변환이 가능하다면, 정수로 변환하여 호출 장소로 돌려준다.
		} catch (NumberFormatException e) {//만약에 매개변수로 건내진 파라미터명에 대입되는 파라미터 벨류가 정수타입으로 변환이 불가능 하다면
          //캐치문 안에서는 별도 처리 없음
		}
		return defaultValue; //매개변수로 건내진 정수타입의 디폴트 값을 반환한다. 이 매개변수는 처리가 실패했을 때를 구분 하기 위한 값을 상정한다.
	}

}