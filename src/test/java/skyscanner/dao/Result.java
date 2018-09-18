package skyscanner.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Result {
	String from,to,price,hourgo,hourback,url,datego,dateback;

	
	
	public Result(String from, String to, String price, String datego, String dateback, String url) {
		super();
		this.from = from;
		this.to = to;
		this.price = price;
		this.datego = datego;
		this.dateback = dateback;
		this.url = url;
	}



	@Override
	public String toString() {
		return "from=" + from + ", to=" + to + ", price=" + price + ", hourgo=" + hourgo + ", hourback="
				+ hourback + ", url=" + url;
	}
	
	

	public String getDatego() {
		return datego;
	}

	public void setDatego(String datego) {
		this.datego = datego;
	}

	public String getDateback() {
		return dateback;
	}

	public void setDateback(String dateback) {
		this.dateback = dateback;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getHourgo() {
		return hourgo;
	}

	public void setHourgo(String hourgo) {
		this.hourgo = hourgo;
	}

	public String getHourback() {
		return hourback;
	}

	public void setHourback(String hourback) {
		this.hourback = hourback;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public void updateGo(Date d) {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		hourgo = dateFormat.format(d) + hourgo;
	}
	
	public void updateBack(Date d) {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		hourback = dateFormat.format(d) + hourback;
	}
	
	

	
	
	
	
}
