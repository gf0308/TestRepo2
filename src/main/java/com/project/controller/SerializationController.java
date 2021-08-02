package com.project.controller;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import com.project.model.Person;

/**
 * 실행결과는 Postman으로 api 호출해 확인
 * - 직렬화해 write 작업 : ok
 * - 역직렬화해 read 작업: ok
 * */

@Controller
public class SerializationController {
	
	@PostMapping("/writeObject")
	public void writeSaveObjectOut() {
		System.out.println("[SerializationController] 진입");
		// 밖으로 객체를 내보내기위한 아웃풋스트림과 직렬화할 스트림
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(new FileOutputStream("C:\\kjb\\test\\Person.txt"));
			Person p = new Person("Jongbin", 29, "kevin"); 
			out.writeObject(p);
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@PostMapping("/readObject")
	public void readObjectIn() {
		System.out.println("[SerializationController] 진입");
		// 밖으로 객체를 내보내기위한 아웃풋스트림과 직렬화할 스트림
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(new FileInputStream("C:\\kjb\\test\\Person.txt"));
			Person recoveredP = (Person)in.readObject();
			recoveredP.printPersonInfo();
		} catch(EOFException e) {
			e.printStackTrace();
			System.out.println("더 읽을 내용이 없습니다");
		} catch(IOException e) {
			e.printStackTrace();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}

}
