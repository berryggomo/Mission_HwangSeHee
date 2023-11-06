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
    void run(){

        System.out.println("== 명언 앱 ==");

        while(true) {
            System.out.print("명령) ");
            String cmd = scanner.nextLine();

            if(cmd.equals("종료"))
                break;
            else if(cmd.equals("등록"))
                등록();
        }
    }
    void 등록() {
        System.out.print("명언 : ");
        String content = scanner.nextLine();

        System.out.print("작가 : ");
        String author = scanner.nextLine();

        lastId++;
        int id = lastId;

        Quotation quotation = new Quotation(id, content, author);
        quotations.add(quotation);

        System.out.println(lastId+ "번 명언이 등록되었습니다.");
    }
}