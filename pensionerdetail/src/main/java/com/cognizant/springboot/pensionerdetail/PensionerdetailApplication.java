package com.cognizant.springboot.pensionerdetail;

import com.cognizant.springboot.pensionerdetail.entity.AadhaarCard;
import com.cognizant.springboot.pensionerdetail.entity.BankDetail;
import com.cognizant.springboot.pensionerdetail.entity.PensionerDetail;
import com.cognizant.springboot.pensionerdetail.helper.CSVHelper;
import com.cognizant.springboot.pensionerdetail.repository.AadhaarCardRepository;
import com.cognizant.springboot.pensionerdetail.repository.BankDeatilsRepository;
import com.cognizant.springboot.pensionerdetail.repository.PensionerDetailsRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaRepositories
@ComponentScan("com.cognizant.springboot.pensionerdetail")
public class PensionerdetailApplication implements CommandLineRunner {

	@Autowired
	private CSVHelper csvHelper;
	@Autowired
	private PensionerDetailsRespository pensionerDetailsRespository;
	@Autowired
	private BankDeatilsRepository bankDeatilsRepository;
	@Autowired
	private AadhaarCardRepository aadhaarCardRepository;

	public static void main(String[] args) {
		SpringApplication.run(PensionerdetailApplication.class, args);
		System.out.println("Pensionerdetail Application has Started..!!");
	}

	@Override
	public void run(String... args) throws Exception {
		InputStream is = new ClassPathResource("/pensionerDetails.txt").getInputStream();
		List<PensionerDetail> pensionerDetailList = csvHelper.csvToPensionerDetails(is);
		System.out.println(pensionerDetailList);
		BankDetail bankDetail;
		List<BankDetail> bankDetails = pensionerDetailList
				.stream()
				.map(pensionerDetail -> {
					return pensionerDetail.getBankDetail();
				})
				.collect(Collectors.toList());
		bankDeatilsRepository.saveAll(bankDetails);
		pensionerDetailsRespository.saveAll(pensionerDetailList);
		List aadhaarCardList = new ArrayList<>();
		aadhaarCardList.add(new AadhaarCard("123456789","CSID40YF7"));
		aadhaarCardList.add(new AadhaarCard("987654321","FTRY57RM"));
		aadhaarCardList.add(new AadhaarCard("123454321","DVSZ76IT"));
		aadhaarCardRepository.saveAll(aadhaarCardList);
	}
}
