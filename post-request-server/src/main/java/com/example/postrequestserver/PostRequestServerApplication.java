package com.example.postrequestserver;

import com.example.postrequestserver.mart.MartTnRecdPrd;
import com.example.postrequestserver.utill.GetRequest;
import com.example.postrequestserver.utill.UrlGetter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@Slf4j
public class PostRequestServerApplication {

	@Autowired
	private MartTnRecdPrd beanObject;
	public static MartTnRecdPrd martTnRecdPrd;

	@PostConstruct
	private void init() {
		this.martTnRecdPrd = beanObject;
	}

	private static boolean checkOption(String str) {
		return str == null || str.trim().isEmpty();
	}


	private static void startLoop(Map<String, Integer> option) {

		GetRequest getRequest = new GetRequest();
		Gson gson = new Gson();
		Type type =	new TypeToken<List<MartTnRecdPrd>>() {}.getType();

		int start = option.get("-start");
		int end = option.get("-end");
		int ap = option.get("-ap");

		UrlGetter testUrl = new UrlGetter("localhost","8080","test");
		testUrl.setStart(start);
		testUrl.setEnd(end);
		testUrl.setAp(ap);

		long fullRequestTime = 0;
		long fullDaoTime = 0;
		long fullTime = 0;
		boolean loop = true;


		while (loop) {
			// 요청 시작 시점
			Long requestStartTime = System.currentTimeMillis();
			String jsonString = getRequest.getTestData(testUrl.getFullURL());
			// 요청 종료 시점
			Long requestEndTime = System.currentTimeMillis();

			List<MartTnRecdPrd> tnRecdPrds = gson.fromJson(jsonString, type);




			if(CollectionUtils.isEmpty(tnRecdPrds)) {
				fullTime = fullRequestTime +  fullDaoTime;
				System.out.println("------------------------------");
				log.info(String.format("%d %d %d", start, end, ap));
				log.info("oracle get time = " + fullRequestTime);
				log.info("maraia insert time =" + fullDaoTime);
				log.info("full time = " + fullTime);
				System.out.println("------------------------------");
				break;
			} else {

				fullRequestTime += requestEndTime-requestStartTime;

				Long daoStartTime = System.currentTimeMillis();
				martTnRecdPrd.insertMartTnRecdPrd(tnRecdPrds);
				Long daoEndTime = System.currentTimeMillis();

				fullDaoTime += daoEndTime-daoStartTime;
				testUrl.addOffset();
			}
		}

	}

	public static Map<String, Integer> argCheck(Map<String, Integer> optionMap) {

		if (optionMap.get("-start") == null){
			int defalutStart = 0;
			optionMap.put("-start", defalutStart);
		}
		if (optionMap.get("-end") == null){
			int defalutEnd = 10000;
			optionMap.put("-end", defalutEnd);
		}
		if (optionMap.get("-ap") == null){
			int defalutAP = 10000;
			optionMap.put("-ap", defalutAP);
		}

		System.out.println(optionMap);
		return optionMap;
	}

	/**
	 * option은 -start, -end -ap
	 * -start : 첫시작 rownum
	 * -end : 마지막 rownum
	 * -ap : 요청 데이트 수
	 * @param args
	 */
	public static void main(String[] args) {

		SpringApplication.run(PostRequestServerApplication.class, args);
		Map<String, Integer> argMap = new HashMap();

		if (args.length > 0 ) {
			for (String arg : args) {
				if (arg.contains("=")) {
					int splitIndex = arg.indexOf("=");
					String key = arg.substring(0, splitIndex);
					Integer value = Integer.valueOf(arg.substring(splitIndex + 1));
					argMap.put(key, value);
				}
			}
			argMap = argCheck(argMap);
		} else {
			log.info("-start, -end, -ap option 필요");
			argMap = argCheck(argMap);
		}

		startLoop(argMap);
	}

}
