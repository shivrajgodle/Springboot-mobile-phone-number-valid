package com.mobileno.phone;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
public class PhoneApplication {
	private String brazilShortCode = "BR";
	private String columnbiaShortCode = "CO";
	private String AmericanShortCode = "US";



	private PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
	Logger logger = LoggerFactory.getLogger(PhoneApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(PhoneApplication.class, args);

		PhoneApplication p = new PhoneApplication();
		p.get();
	}

	public void get(){
//		List<String> brazilPhoneNumbers = Arrays.asList(
//				"+5521976923932",
//				"5561991474841",
//				"055 719 9174 8722",
//				"719 9174 8722",
//				"55 (719) (9174) (8722)"
//		);
//
//		List<String> columbiaPhoneNumbers = Arrays.asList(
//				"573146339375",
//				"3137145988",
//				"+573154417054",
//				"316537219731323123",
//				"3013755140",
//				"+57 31 5441 7054",
//				"+57 (31) (8393) (1081)"
//		);

//		------------------------------------------US Code-------------------------------------

		List<String> USPhoneNumbers = Arrays.asList(

				"+5521976923932",
				"(691) 010-7514",
				"1-(773) 971-2036",
				"(600) 328-7633",
				"1-(791) 540-4577",
				"1-(338) 478-6464",
				"(260) 964-7918",
				"(749) 244-0582"
		);


		phoneUtil = PhoneNumberUtil.getInstance();

		for (String inputPhoneNumber : USPhoneNumbers) {
			validateAndFormatPhoneNumber(inputPhoneNumber, AmericanShortCode);
		}



//		for (String inputPhoneNumber : brazilPhoneNumbers) {
//			validateAndFormatPhoneNumber(inputPhoneNumber, brazilShortCode);
//		}

//		for (String inputPhoneNumber : columbiaPhoneNumbers) {
//			validateAndFormatPhoneNumber(inputPhoneNumber, columnbiaShortCode);
//		}

		logger.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		logger.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		logger.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		logger.info("Interchanging country code, all numbers should be invalid now.");

		for (String inputPhoneNumber : USPhoneNumbers) {
			validateAndFormatPhoneNumber(inputPhoneNumber, brazilShortCode);
		}


//		for (String inputPhoneNumber : brazilPhoneNumbers) {
//			validateAndFormatPhoneNumber(inputPhoneNumber, columnbiaShortCode);
//		}
//
//		for (String inputPhoneNumber : columbiaPhoneNumbers) {
//			validateAndFormatPhoneNumber(inputPhoneNumber, brazilShortCode);
//		}

	}



	private void validateAndFormatPhoneNumber(String inputPhoneNumber, String shortCode) {

		logger.info("Processing phone number: " + inputPhoneNumber + " with short code: " + shortCode);

		Phonenumber.PhoneNumber phoneNumberProto = null;

		try {
			phoneNumberProto = phoneUtil.parse(inputPhoneNumber, shortCode);

			logger.info("phoneNumberProto: " + phoneNumberProto);

			boolean isValid = phoneUtil.isValidNumber(phoneNumberProto);

			logger.info("Is phone number valid: " + isValid);

			if (phoneNumberProto.hasCountryCode()) {
				logger.info("Country code is present: " + phoneNumberProto.getCountryCode());
			} else {
				logger.info("Country code is not present.");
			}

			if (phoneNumberProto.hasNationalNumber()) {
				logger.info("National number is present: " + phoneNumberProto.getNationalNumber());
			} else {
				logger.info("National number is not present.");
			}

			String formattedPhoneNumber = phoneUtil.format(phoneNumberProto, PhoneNumberUtil.PhoneNumberFormat.E164);

			if (formattedPhoneNumber.startsWith("+")) {
				logger.info("Removing leading + from phone number");
				formattedPhoneNumber = formattedPhoneNumber.replace("+", "");
			}

			logger.info("Formatted phone number: " + formattedPhoneNumber);

		} catch (NumberParseException e) {
			logger.error(e.getMessage());
		}

		logger.info("==================================");
	}

}
