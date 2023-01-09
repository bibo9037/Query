package com.example.Query;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.Query.entity.QueryDeposit;
import com.example.Query.entity.QueryManager;
import com.example.Query.repository.QueryDepositDao;
import com.example.Query.repository.QueryManagerDao;

@SpringBootTest
class QueryApplicationTests {

	@Autowired
	private QueryDepositDao queryDepositDao;
	
	@Autowired
	private QueryManagerDao queryManagerDao;

	@Test
	void findByCaptionAndQuestion() {
		List<QueryDeposit> queryDepositList = queryDepositDao.findByCaptionAndQuestion("性別大調查", "你的性向");
		double size = (double) queryDepositList.size();
		System.out.println(size);

		int boy = 0;
		int girl = 0;
		int bisexual = 0;
		int gay = 0;

		for (QueryDeposit queryDeposit : queryDepositList) {
			switch (queryDeposit.getAns()) {
			case "男生":
				boy += 1;
				break;
			case "女生":
				girl += 1;
				break;
			case "雙性":
				bisexual += 1;
				break;
			case "同性":
				gay += 1;
				break;
			default:
				break;
			}
		}
		System.out.println("boy:" + boy);
		System.out.println("girl:" + girl);
		System.out.println("bisexual:" + bisexual);
		System.out.println("gay:" + gay);

		double ans1 = boy / size * 100;
		double ans2 = girl / size * 100;
		double ans3 = bisexual / size * 100;
		double ans4 = gay / size * 100;

		System.out.println("統計出 : " + "男生為 : " + ans1 + " % , " + "女生為 : " + ans2 + " % , " + "同性為 : " + ans3 + " % , "
				+ "雙性為 : " + ans4 + " % ");
	}
	
	@Test
	public void findByStartDateBetweenEndDate() throws ParseException {
//		Date start = new Date(2022,12,01);
		DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
		Date start = dateFormat1.parse("2022-12-05");
//		Date end = dateFormat1.parse("2022-12-31");
		List<QueryManager> queryAddList = queryManagerDao.findByStartDateGreaterThanEqual(start);
		for(QueryManager q:queryAddList) {
			System.out.println(q.getCaption());
		}
		
	}

}
