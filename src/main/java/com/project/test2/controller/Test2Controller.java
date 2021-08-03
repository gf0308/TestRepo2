package com.project.test2.controller;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.project.test2.model.SecondPerson;


@Controller
@RequestMapping("/test2")
public class Test2Controller {
	
	private static final Logger log = LoggerFactory.getLogger(Test2Controller.class);
	
	// 객체내용 출력(output) 용
	@PostMapping("/writeObject")
	public void writeObject() {
		SecondPerson sp = new SecondPerson("Jongbin Kim", 29, "Developer");
		ObjectOutputStream out = null;
		try {
			// 적어놓은 리소스 주소로 파일이 저장되어지는 스트림을 생성하고, 그 스트림을 바이트배열내용을 받을 수 있는 스트림으로 만든다(ObjectOutputStream으로 투입내용 바이트배열 화)
			out = new ObjectOutputStream(new FileOutputStream("C:\\kjb\\test\\test2\\SecondPerson.txt"));
			out.writeObject(sp);
			log.info("[스트림으로 객체 out 저장 완료..................................]");
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch(IOException ie) {
				ie.printStackTrace();
			}
		}
	}
	
	// 객체내용 입력(input) 용
	@PostMapping("/readObject")
	public void readObject() {
		ObjectInputStream in = null;
		try {
			// 적어놓은 리소스 주소로 파일이 저장되어지는 스트림을 생성하고, 그 스트림을 바이트배열내용을 받을 수 있는 스트림으로 만든다(ObjectOutputStream으로 투입내용 바이트배열 화)
			in = new ObjectInputStream(new FileInputStream("C:\\kjb\\test\\test2\\SecondPerson.txt"));
			SecondPerson sp = (SecondPerson)in.readObject(); // 저장된객체를 다시 읽어들여올땐(역직렬화 시) Object로 반환되기 때문에, 원래대로 재캐스팅 해주어야 한다.
			log.info("[인풋스트림으로 객체 in 읽어들여오기 완료..................................]");
			sp.printFields();
		} catch(EOFException e) {
			e.printStackTrace();
			log.error("읽어들일 것이 더 남아있지 않습니다!");
		} catch(FileNotFoundException e) {
			e.printStackTrace();
			log.error("해당 파일이 없습니다!");
		} catch(IOException e) {
			e.printStackTrace();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch(IOException ie) {
				ie.printStackTrace();
			}
		}
	}
	
}
