package br.com.jhonata;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.jhonata.exceptions.UnsupportedMathOperationException;

@RestController
public class MathController {
	

	private static final AtomicLong counter = new AtomicLong();
	
	@RequestMapping(value = "/sum/{numberOne}/{numberTwo}/{operation}",
			method=RequestMethod.GET )
			
	public Double calc(
			@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo,
			@PathVariable(value = "operation") String operation
			) throws Exception { 
		
		if(!isNumeric(numberOne) || !isNumeric(numberTwo))  {
			throw new UnsupportedMathOperationException("Please set a numeric value!"); 
			}
		
		switch (operation) {
		case "SOMA": {
			return convertToDouble(numberOne) + convertToDouble(numberTwo);
		}
		case "SUB": {
			return convertToDouble(numberOne) - convertToDouble(numberTwo);
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + operation);
		}
	
	}
	
	private Double convertToDouble(String strNumber) {
		if (strNumber == null) return 0D;
		String number = strNumber.replaceAll(",", ".");
		if (isNumeric(number)) return Double.parseDouble(number);
		return 0D;
	}

	private boolean isNumeric(String strNumber) {
		if (strNumber == null) return false;
		String number = strNumber.replaceAll(",", ".");
		return number.matches("[-+]?[0-9]*\\.?[0-9]+");
	}

}