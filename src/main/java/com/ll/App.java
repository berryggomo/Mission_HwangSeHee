package com.ll;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    Scanner scanner;
    List<Quotation> quotations;
    int lastId;

    App() {
        scanner = new Scanner(System.in);
        quotations = new ArrayList<>();
        lastId = 0;
    }

    void run() {

        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명령) ");
            String cmd = scanner.nextLine();

            if (cmd.equals("종료"))
                break;
            else if (cmd.equals("등록"))
                actionRegist();
            else if (cmd.equals("목록"))
                actionList();
            else if (cmd.startsWith("삭제?"))
                actionRemove(cmd);
        }
    }

    void actionRegist() {
        System.out.print("명언 : ");
        String content = scanner.nextLine();

        System.out.print("작가 : ");
        String author = scanner.nextLine();

        lastId++;
        int id = lastId;

        Quotation quotation = new Quotation(id, content, author);
        quotations.add(quotation);

        System.out.println(lastId + "번 명언이 등록되었습니다.");
    }

    void actionList() {
        if (quotations.isEmpty())
            System.out.println("등록된 명언이 없습니다.");
        else {
            System.out.println("번호 / 작가 / 명언");
            System.out.println("----------------------");
            for (int i = quotations.size() - 1; i >= 0; i--) {
                Quotation quotation = quotations.get(i);
                System.out.printf("%d / %s / %s\n", quotation.id, quotation.author, quotation.content);
            }
        }
    }

    void actionRemove(String cmd) {
        int id = getId(cmd, "id", 0);
        if (id == 0) {
            System.out.println("id를 다시 입력해주세요.");
            return;
        }
        int index = getIndexOfQuotationById(id);    // id를 매개변수로 하는 메서드 실행

        if (index == -1) {                  // 명언이 존재하지 않을 경우
            System.out.printf("%d번 명언은 존재하지 않습니다.\n", id);
            return;
        }
        quotations.remove(index);
        System.out.printf("%d번 명언이 삭제되었습니다.\n", id);
    }

    int getIndexOfQuotationById(int id) {                   // id를 매개변수로 받음
        for (int i = 0; i < quotations.size(); i++) {       // for문을 이용해서 리스트 0번부터 마지막까지 탐색
            Quotation quotation = quotations.get(i);        // 리스트에서 i번 값을 가져옴
            if (quotation.id == id) {                       // 가져온 i번 값의 id가 매개변수 id와 같으면
                return i;                                   // 인덱스 값 리턴      여기서 i는 인덱스 id는 내가 보는 명언 번호 (i==id-1과 같음)
            }
        }
        return -1;      // 해당 안될 경우 -1 리턴
    }

    int getId(String cmd, String paramName, int defaultValue) {
        //String[] cmdArray = cmd.split("\\?", 2); //삭제?id=1를 ? 기준 두덩이로 잘라 배열에 저장
        //String idString = cmdArray[1];    //배열[1]에 들어있는 id=1을 queryString에 저장
        //String[] idStringArray = idString.split("&");  //idString 값 idStringArray에 저장

        String[] idStringArray = cmd.split("\\?")[1].split("&");    //cmd를 ? 기준으로 나누고 1번 배열에 있는 값을 idStringArray에 저장
        //cmd를 ?기준으로 나눈 값을 바로 배열에 저장할 수 없음 ->  split("&")를 이용해서 1번 배열에 있는 값을 그대로 추출한 다음 저장해줌

        for (int i = 0; i < idStringArray.length; i++) {
            String queryParamStr = idStringArray[i];  //queryParamStr에 id=1을 저장
            String[] queryParamStrBits = queryParamStr.split("=", 2);   //id=1를 = 기준 두덩이로 잘라 배열에 저장
            String _paramName = queryParamStrBits[0];   //_paramName에 id 저장
            String paramValue = queryParamStrBits[1];   //paramValue에 1 저장
            if (_paramName.equals(paramName)) {     //_paramName 값이 id와 같으면 if문 실행
                try {
                    // 문제가 없을 경우
                    return Integer.parseInt(paramValue);    // String으로 저장되어있던 1을(숫자를) int형태로 변환하여 리턴
                } catch (NumberFormatException e) {
                    // 문제가 생긴 경우
                    return defaultValue;        //문제 발생 -> 0리턴
                }
            }
        }
        return defaultValue;
    }
}