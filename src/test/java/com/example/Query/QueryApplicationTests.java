package com.example.Query;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.Query.entity.QueryAdd;
import com.example.Query.entity.QueryDeposit;
import com.example.Query.entity.QueryManager;
import com.example.Query.repository.QueryAddDao;
import com.example.Query.repository.QueryDepositDao;
import com.example.Query.repository.QueryManagerDao;

@SpringBootTest
class QueryApplicationTests {

	@Autowired
	private QueryDepositDao queryDepositDao;

	@Autowired
	private QueryManagerDao queryManagerDao;
	@Autowired
	private QueryAddDao queryAddDao;

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
		for (QueryManager q : queryAddList) {
			System.out.println(q.getCaption());
		}

	}
	@Test
	// ====kai
	void count() {
		List<QueryAdd> q = queryAddDao.findByCaption("性別大調查");
		List<QueryDeposit> u = queryDepositDao.findByCaption("性別大調查");
		Map<String,Integer> sMap = new HashMap<>();
		Map<String,Map<String,Integer>> bMap = new HashMap<>();
		for (var qItem : q) {
			int x =0;
			for (var uItem : u) {
				
				if (qItem.getQuestion().equals(uItem.getQuestion())) {
					String[] str = qItem.getOpt().split(";");
					{
						for(var strItem :str) {
							String ssss=strItem.trim();
							sMap.put(ssss, 0);
						}
						for(var sMapItem :sMap.entrySet()) {
							if(sMapItem.getKey().equals(uItem.getAns())) {
								sMap.put(sMapItem.getKey(), x++);
							}
						}
					}
				}
			}
			bMap.put(qItem.getQuestion(), sMap);
	}
		
		
		Map<String,Integer> sMap2 = new HashMap<>();
		for(var bI:bMap.entrySet()) {
			System.out.println(bI.getKey()+" 問題 ");
			sMap2 = bI.getValue();
			for(var sI:sMap2.entrySet()) {
				System.out.println(" 選項 "+sI.getKey()+" 次數: "+sI.getValue());
			}
		}
		
		}

}
