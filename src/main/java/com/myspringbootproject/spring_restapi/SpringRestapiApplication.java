package com.myspringbootproject.spring_restapi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import com.myspringbootproject.spring_restapi.model.Trade;
import com.myspringbootproject.spring_restapi.model.User;
import com.myspringbootproject.spring_restapi.repository.TradeRepository;
import com.myspringbootproject.spring_restapi.repository.UserRepository;

@SpringBootApplication
public class SpringRestapiApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(SpringRestapiApplication.class, args);

		/* BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new FileWriter("E:\\workspace\\spring\\spring-restapi\\src\\main\\resources\\output.txt"));
		String team = bf.readLine();
		int year = Integer.parseInt(bf.readLine().trim());
		System.out.println("Team: "+team);
		System.out.println("Year: "+year);


		int result = Result.getTotalGoals(team, year);



		bw.write(String.valueOf(team + year));
		bw.newLine();
		bf.close();
		bw.close(); */


	}

	@Component
	class DemoRunner implements CommandLineRunner{

		@Autowired
		private UserRepository userReposity;

		@Autowired
		private TradeRepository tradeRepository;

		@Override
		public void run(String... args) throws Exception {
			User u1 = new User();
			u1.setName("Saikat Mitra");
			User u2 = new User();
			u2.setName("Rithes Pandey");

			userReposity.saveAll(Arrays.asList(u1, u2));

			Trade t1 = new Trade();Trade t2 = new Trade();Trade t3 = new Trade();Trade t4 = new Trade();Trade t5 = new Trade();Trade t6 = new Trade();
			t1.setType("buy");t1.setUser(u2);t1.setSymbol("B");t1.setShares(11);t1.setPrice((float)174.82);t1.setTimestamp(Timestamp.valueOf("2018-12-28 13:18:48"));
			t2.setType("buy");t2.setUser(u2);t2.setSymbol("AA");t2.setShares(11);t2.setPrice((float)174.82);t2.setTimestamp(Timestamp.valueOf("2018-12-29 09:47:43"));
			t3.setType("buy");t3.setUser(u1);t3.setSymbol("B");t3.setShares(20);t3.setPrice((float)155.32);t3.setTimestamp(Timestamp.valueOf("2019-01-01 11:19:01"));
			t4.setType("buy");t4.setUser(u2);t4.setSymbol("AA");t4.setShares(11);t4.setPrice((float)174.82);t4.setTimestamp(Timestamp.valueOf("2019-01-01 12:02:03"));
			t5.setType("buy");t5.setUser(u1);t5.setSymbol("AA");t5.setShares(11);t5.setPrice((float)174.82);t5.setTimestamp(Timestamp.valueOf("2019-01-02 15:31:49"));
			t6.setType("buy");t6.setUser(u1);t6.setSymbol("B");t6.setShares(20);t6.setPrice((float)174.82);t6.setTimestamp(Timestamp.valueOf("2019-01-02 15:42:18"));
			
			tradeRepository.saveAll(Arrays.asList(t1,t2,t3,t4,t5,t6));
			
			
		}

		
	} 

}
