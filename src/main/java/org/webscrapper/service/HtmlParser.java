package org.webscrapper.service;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.webscrapper.model.Record;

public class HtmlParser {

  private static final String loginUrl = "https://firstbd.ltd/work/index.php";
  private final Scanner scanner = new Scanner(System.in);

  public Response login() throws IOException {
    final Map<String, String> cookies =
        Jsoup.connect(loginUrl)
            .followRedirects(true)
            .execute()
            .cookies();
    return Jsoup.connect(loginUrl)
        .data("username", scanUsername())
        .data("password", scanPassword())
        .cookies(cookies)
        .followRedirects(true)
        .method(Method.POST)
        .execute();
  }

  public Element getLoginForm(Response response) throws IOException {
    final Elements elements = response.parse().getElementsByAttributeValue("action", "insert.php");
    if (elements.isEmpty()) {
      throw new RuntimeException("Unable to login");
    }
    return elements.first();
  }

  private String scanUsername() {
    System.out.print("Username: ");
    return scanner.next();
  }

  private String scanPassword() {
    System.out.print("Password: ");
    return scanner.next();
  }

  public void setFormData(Record record, Element form) {
    form.select("[name=record_no]").val(record.getRecordNo());
    form.select("[name=c]").val(record.getPolicyDate());
    form.select("[name=policy_no]").val(record.getPolicyNo());
    form.select("[name=medical_card_no]").val(record.getMedicalCardNo());
    form.select("[name=first_name]").val(record.getFirstName());
    form.select("[name=last_name]").val(record.getLastName());
    form.select("[name=city]").val(record.getCity());
    form.select("[name=state]").val(record.getState());
    form.select("[name=phone]").val(record.getPolicyNo());
    form.select("[name=martial_status]").val(record.getMartialStatus());
    form.select("[name=general_practitioner_code]").val(record.getGpCode());
    form.select("[name=hospital_claim_days]").val(record.getHospitalClaimDays());
    form.select("[name=paid_amount]").val(record.getPaidAmount());
    form.select("[name=net_amount]").val(record.getNetAmount());
  }
}
