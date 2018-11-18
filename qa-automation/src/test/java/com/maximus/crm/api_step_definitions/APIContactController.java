package com.maximus.crm.api_step_definitions;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.maximus.crm.utilities.APIClassUtil;
import com.maximus.crm.utilities.ApiTestDataUtil;
import com.maximus.crm.utilities.ConfigurationReader;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.path.json.JsonPath;
//import org.testng.Assert;
//import org.json.JSONObject;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertFalse;

public class APIContactController {
    private APIClassUtil api = new APIClassUtil();
    private ApiTestDataUtil apitdu = new ApiTestDataUtil();
    private APIConsumerRestController consumerController = new APIConsumerRestController();
    private String baseURI = ConfigurationReader.getProperty("apiContactsURI");
    private String createContactEndPoint = "mars/crm/contact";
    private String searchContactsByRefEndPoint = "mars/crm/contactsByRef";
    private String searchContactsByExtTypeEndPoint = "mars/crm/contacts/{externalType}/{externalRefid}";

    private JsonObject requestParams;
    private String apiConsumerFirstName;
    private String apiConsumerLastName;
    private JsonObject latestAddress;
    private String expectedAddress;
    private String apiPhoneNumber;
    private String apiConsumerAddressZip;
    private String apiConsumerCorrelationId;
    private String apiConsumerId;
    public String consumerParams;
    public String apiConsumeruiid;
    private String emailId;
   // public JSONObject consumerObj;

    @Given("I initiated consumer search API for Contacts")
    public void i_initiated_consumer_search_api_for_contact() {
        consumerController.i_initiated_consumer_search_api();
    }

    @When("I get uiid and correlationId id from contact first name search {string}")
    public void i_get_uuid_and_correlation_id_from_contact_search(String consumerFirstName) {
        if (consumerFirstName.isEmpty()) {
            consumerFirstName = apiConsumerFirstName;
        }
        consumerController.get_the_consumer_correlationId_id_from_response(consumerFirstName);
        consumerController.get_the_consumer_uiid_from_response(consumerFirstName);
        apiConsumerCorrelationId = consumerController.correlationId;
        apiConsumeruiid = consumerController.uiid;
        System.out.println("Consumer Correlation ID : " + apiConsumerCorrelationId);
        System.out.println("Consumer uiid : " + apiConsumeruiid);
        consumerController.get_the_consumer_id_from_response(consumerFirstName);
        apiConsumerId = consumerController.apiconsumerId;
        System.out.println("Consumer ID : " + apiConsumerId);
        consumerParams = consumerController.consumerDetail;
    }

    @Given("I initiated add new Contact using API")
    public void i_initiated_add_new_contact_using_api() {
        api.setbaseUri(baseURI);
        api.setEndPoint(createContactEndPoint);
    }

    @Given("I initiated Consumer Type Search vai API with {string} type and id {string}")
    public void i_initiated_consumer_type_search_api(String externalType, String externalRefid) {
        api.setbaseUri(baseURI);
        if (externalType.isEmpty()) {
            externalType = "Consumer";
        }
        if (externalRefid.isEmpty()) {
            externalRefid = apiConsumerId;
        }
        searchContactsByExtTypeEndPoint = searchContactsByExtTypeEndPoint.replace("{externalType}", externalType);
        searchContactsByExtTypeEndPoint = searchContactsByExtTypeEndPoint.replace("{externalRefid}", externalRefid);
        api.setEndPoint(searchContactsByExtTypeEndPoint);
    }

    @Given("I created a consumer for contact using API")
    public void i_created_a_consumer_for_contact_using_api() {
        consumerController.i_created_a_consumer_using_api();
    }

    @And("I collected some consumer related data from consumer Controller")
    public void i_collected_some_consumer_related_data_from_consumer_controller() {
        apiConsumerFirstName = consumerController.apiconsumerFirstName;
        System.out.println(" Consumer First Name: " + apiConsumerFirstName);
        apiConsumerLastName = consumerController.apiconsumerLastName;
        apiPhoneNumber = consumerController.apiphoneNumber;
        apiConsumerAddressZip = consumerController.apiaddressZip;
        apiConsumerCorrelationId = consumerController.correlationId;
        apiConsumerId = consumerController.apiconsumerId;
        System.out.println(" Consumer ID: " + apiConsumerId);
        apiConsumeruiid = consumerController.uiid;
    }

    private void add_email_information(String externalRefType, String associatedCaseMember, String emailAddress) {
        apitdu.getJsonFromFile("crm/contacts/apiAddNewEmail.json");
        apitdu.jsonElement.getAsJsonObject().addProperty("externalRefType", externalRefType);
        apitdu.jsonElement.getAsJsonObject().addProperty("externalRefId", apiConsumerId);
        if (associatedCaseMember.isEmpty() || associatedCaseMember.equals("{random}")) {
            associatedCaseMember = apiConsumerFirstName + " " + apiConsumerLastName;
            apitdu.jsonElement.getAsJsonObject().getAsJsonObject("email").addProperty("associatedCaseMember", associatedCaseMember);
        } else {
            apitdu.jsonElement.getAsJsonObject().getAsJsonObject("email").addProperty("associatedCaseMember", associatedCaseMember);
        }

        if (emailAddress.isEmpty() || emailAddress.equals("{random}")) {
            emailAddress = apiConsumerFirstName + "@" + apiConsumerLastName + ".com";
            apitdu.jsonElement.getAsJsonObject().getAsJsonObject("email").addProperty("emailAddress", emailAddress);
        } else {
            apitdu.jsonElement.getAsJsonObject().getAsJsonObject("email").addProperty("emailAddress", emailAddress);
        }
        apitdu.jsonElement.getAsJsonObject().getAsJsonObject("email").addProperty("emailType", "OFFICE");
        long curentDateTime = System.currentTimeMillis();
        apitdu.jsonElement.getAsJsonObject().getAsJsonObject("email").addProperty("createdOn", curentDateTime);
        apitdu.jsonElement.getAsJsonObject().addProperty("correlationId", apiConsumerCorrelationId);
        apitdu.jsonElement.getAsJsonObject().addProperty("uiid", apiConsumeruiid);

        requestParams = apitdu.jsonElement.getAsJsonObject();
        System.out.println(api.endPoint);
        System.out.println(requestParams);
    }

    private long set_date(String sDate, Integer add_days) {
        long setDate;
        if (sDate.isEmpty() || sDate == "") {
            setDate = System.currentTimeMillis();
        } else {
            if (sDate.contains("yesterday")) {
                System.out.println(sDate);
                if (add_days > 0) {
                    setDate = System.currentTimeMillis() - ((1 + add_days) * 24 * 60 * 60 * 1000);
                } else {
                    setDate = System.currentTimeMillis() - 1 * 24 * 60 * 60 * 1000;
                }
            } else if (sDate.contains("tomorrow")) {
                System.out.println(sDate);
                if (add_days > 0) {
                    setDate = System.currentTimeMillis() + ((1 + add_days) * 24 * 60 * 60 * 1000);
                } else {
                    setDate = System.currentTimeMillis() + 1 * 24 * 60 * 60 * 1000;
                }
            } else {
                System.out.println("Current Date : " + sDate);
                setDate = System.currentTimeMillis();
            }
        }
        return setDate;
    }

    @And("I added email information {string}, {string}, {string}, {string} and {string}")
    public void I_added_email_information(String externalRefType, String associatedCaseMember, String emailAddress, String startDate, String endDate) {
        add_email_information(externalRefType, associatedCaseMember, emailAddress);
        long startDateTime = set_date(startDate, 0);
        apitdu.jsonElement.getAsJsonObject().getAsJsonObject("email").addProperty("effectiveStartDate", startDateTime);
        if (endDate != "" && !endDate.isEmpty()) {
            long endDateTime = set_date(endDate, 0);
            apitdu.jsonElement.getAsJsonObject().getAsJsonObject("email").addProperty("effectiveEndDate", endDateTime);
        }
        requestParams = apitdu.jsonElement.getAsJsonObject();
        System.out.println(api.endPoint);
        System.out.println(requestParams);
    }

    @And("I added phone information {string}, {string}, {string}, {string}, {string}, {string} and {string}")
    public void I_added_phone_information(String externalRefType, String phoneNumber, String phoneType1, String phoneType2, String comments, String startDate, String endDate) {
        apitdu.getJsonFromFile("crm/contacts/apiAddNewPhone.json");
        apitdu.jsonElement.getAsJsonObject().addProperty("externalRefType", externalRefType);
        apitdu.jsonElement.getAsJsonObject().addProperty("externalRefId", apiConsumerId);
        long curentDateTime = System.currentTimeMillis();
        apitdu.jsonElement.getAsJsonObject().getAsJsonObject("phone").addProperty("createdOn", curentDateTime);
        long startDateTime = set_date(startDate, 0);
        apitdu.jsonElement.getAsJsonObject().getAsJsonObject("phone").addProperty("effectiveStartDate", startDateTime);
        if (endDate != "" && !endDate.isEmpty()) {
            long endDateTime = set_date(endDate, 0);
            apitdu.jsonElement.getAsJsonObject().getAsJsonObject("phone").addProperty("effectiveEndDate", endDateTime);
        }
        apitdu.jsonElement.getAsJsonObject().getAsJsonObject("phone").addProperty("phoneNumber", phoneNumber);
        apitdu.jsonElement.getAsJsonObject().getAsJsonObject("phone").addProperty("phoneType1", phoneType1);
        apitdu.jsonElement.getAsJsonObject().getAsJsonObject("phone").addProperty("phoneType2", phoneType2);
        apitdu.jsonElement.getAsJsonObject().getAsJsonObject("phone").addProperty("comments", comments);

        apitdu.jsonElement.getAsJsonObject().addProperty("correlationId", apiConsumerCorrelationId);
        apitdu.jsonElement.getAsJsonObject().addProperty("uiid", apiConsumerId);

        requestParams = apitdu.jsonElement.getAsJsonObject();
        System.out.println(requestParams);
    }

    @And("I added blank phone information")
    public void I_added_blank_phone_information() {
        apitdu.getJsonFromFile("crm/contacts/apiAddNewPhone.json");
        apitdu.jsonElement.getAsJsonObject().addProperty("externalRefId", apiConsumerId);
        apitdu.jsonElement.getAsJsonObject().addProperty("correlationId", apiConsumerCorrelationId);
        apitdu.jsonElement.getAsJsonObject().addProperty("uiid", apiConsumerId);
        long curentDateTime = System.currentTimeMillis();
        apitdu.jsonElement.getAsJsonObject().getAsJsonObject("phone").addProperty("createdOn", curentDateTime);
        requestParams = apitdu.jsonElement.getAsJsonObject();
        System.out.println(requestParams);
    }

    @And("I can run add contacts using API")
    public void i_can_run_add_contacts_api() {
        api.PutAPI(requestParams);
        System.out.println(api.responseString);
        assertEquals(api.statusCode, 200);
        assertTrue(api.responseString.contains("success"));
        assertEquals(api.jsonPathEvaluator.get("status"), "success");
        System.out.println(api.responseString);
    }

    @And("I initiated specific contacts detail {string}")
    public void i_initiated_specific_contacts_detail(String externalRefType) {
        api.setbaseUri(baseURI);
        String getContactsDetail = searchContactsByExtTypeEndPoint.replace("{externalType}", externalRefType);
        System.out.println("Consumer Id for initiate contact : " + apiConsumerId);
        getContactsDetail = getContactsDetail.replace("{externalRefid}", apiConsumerId);
        System.out.println(getContactsDetail);
        api.setEndPoint(getContactsDetail);
    }

    @And("I run get contacts detail using API")
    public void i_run_get_contacts_detail_api() {
        api.getAPI();
        System.out.println(api.responseString);
        assertEquals(api.statusCode, 200);
    }

    private JsonObject getlatestAddress()
    {
        //include validation, logic, logging or whatever you like here
        return this.latestAddress;
    }

    @And("I get latest address information of a the consumer")
    public void i_get_latest_address_information_of_the_consumer() {
        JsonObject consumer_object = api.apiJsonObject;
        System.out.println(consumer_object);
        JsonArray contact_array = consumer_object.getAsJsonArray("contacts");
        JsonObject address_object = contact_array.get(0).getAsJsonObject();
        System.out.println(address_object);
        JsonArray address_array = address_object.getAsJsonArray("addressess");
        latestAddress = address_array.get(0).getAsJsonObject();
        expectedAddress = getlatestAddress().toString();
        System.out.println("========Expected_Object=========");
        System.out.println(expectedAddress);
    }

    @Then("I verify {string} details using API")
    public void i_verify_status_details_using_api(String success) {
        if (Boolean.valueOf(success) == Boolean.TRUE) {
            assertEquals(api.jsonPathEvaluator.get("status"), "success");
            assertTrue(api.responseString.contains("success"));
        } else {
            assertEquals(api.jsonPathEvaluator.get("status"), "fail");
        }
    }

    @Then("I verify Email address Audit Trail")
    public void i_verify_email_address_audit_trail() {
        apitdu.jsonElement.getAsJsonObject().addProperty("externalRefType", "Consumer");
        apitdu.jsonElement.getAsJsonObject().addProperty("externalRefId", apiConsumerId);
        requestParams = apitdu.jsonElement.getAsJsonObject();
        api.GetAPIWithParameter(requestParams);
        System.out.println(api.responseString);
        assertEquals(api.statusCode, 200);
        //assertTrue(api.responseString.equalsIgnoreCase(status));
        ArrayList contacts = api.jsonPathEvaluator.get("contacts");
        HashMap email = (HashMap) contacts.get(0);
        // System.out.println(email);
        ArrayList emails = (ArrayList) email.get("emails");
        // System.out.println(emails);
        int count = 0;
        if (emails.size() > 1) {
            for (int i = 0; i < emails.size(); i++) {
                HashMap email_status = (HashMap) emails.get(i);
                String emailAddress = apiConsumerFirstName + "@" + apiConsumerLastName + ".com";
                if (emailAddress.equalsIgnoreCase(email_status.get("emailAddress").toString())) {
                    count = i;
                    break;
                }
            }
        }
        HashMap email_status = (HashMap) emails.get(count);
        String temp = email_status.get("createdOn").toString();
        System.out.println("Created On : " + temp);
        assertFalse(temp.equalsIgnoreCase("null"));
        // System.out.println(consumer_status.get("status"));
    }

    @Then("I can verify consumer phone detail {string} and count greater than zero using API")
    public void i_can_verify_consumer_phone_detail_success_and_count_greater_than_zero_using_API(String success) {
        i_verify_status_details_using_api(success);
        // -- AND --
        List<List> phoneLt = api.jsonPathEvaluator.getList("contacts.phones");
        System.out.println(phoneLt);
        List<List> phoneLst = phoneLt.get(0);
        System.out.println(phoneLst);
        Integer listCount = phoneLst.size();
        System.out.println("Total Phone records: " + listCount);
        assertTrue(listCount > 0);
    }

    @And("I added address information {string},{string},{string},{string},{string},{string},{string},{string},{string},{string} and {string}")
    public void I_added_address_information(String externalRefType, String address1, String address2,
                                            String city, String county, String state, String zip, String zipFour,
                                            String type, String startDate, String endDate) {
        apitdu.getJsonFromFile("crm/contacts/apiAddNewAddress.json");
        apitdu.jsonElement.getAsJsonObject().addProperty("externalRefType", externalRefType);
        apitdu.jsonElement.getAsJsonObject().addProperty("externalRefId", apiConsumerId);
        apitdu.jsonElement.getAsJsonObject().addProperty("correlationId", apiConsumerCorrelationId);
        apitdu.jsonElement.getAsJsonObject().addProperty("uiid", apiConsumeruiid);
        if (address1.isEmpty() || address1.equals("{random}")) {
            address1 = apitdu.getRandomNumber(4).randomNumber.toString() + " Test Ave.";
        }
        apitdu.jsonElement.getAsJsonObject().getAsJsonObject("address").addProperty("addressStreet1", address1);
        apitdu.jsonElement.getAsJsonObject().getAsJsonObject("address").addProperty("addressStreet2", address2);
        apitdu.jsonElement.getAsJsonObject().getAsJsonObject("address").addProperty("addressCity", city);
        apitdu.jsonElement.getAsJsonObject().getAsJsonObject("address").addProperty("addressCounty", county);
        apitdu.jsonElement.getAsJsonObject().getAsJsonObject("address").addProperty("addressState", state);
        if (zip.isEmpty() || zip.equals("{random}")) {
            zip = apitdu.getRandomNumber(5).randomNumber.toString();
        }
        apitdu.jsonElement.getAsJsonObject().getAsJsonObject("address").addProperty("addressZip", zip);
        if (zipFour.isEmpty() || zipFour.equals("{random}")) {
            zipFour = apitdu.getRandomNumber(4).randomNumber.toString();
        }
        apitdu.jsonElement.getAsJsonObject().getAsJsonObject("address").addProperty("addressZipFour", zipFour);
        apitdu.jsonElement.getAsJsonObject().getAsJsonObject("address").addProperty("addressType", type);
        long startDateTime = set_date(startDate, 0);
        apitdu.jsonElement.getAsJsonObject().getAsJsonObject("address").addProperty("effectiveStartDate", startDateTime);
        if (!endDate.isEmpty()) {
            long endDateTime = set_date(endDate, 0);
            apitdu.jsonElement.getAsJsonObject().getAsJsonObject("address").addProperty("effectiveEndDate", endDateTime);
        }

        requestParams = apitdu.jsonElement.getAsJsonObject();
        System.out.println(api.endPoint);
        System.out.println(requestParams);
    }

    private String get_random_string(String param, Integer length) {
        if (param.isEmpty() || param.equals("{random}")) {
            param = apitdu.getRandomAlphaNumeric(length).randomString;
        }
        return param;
    }

    private String get_random_value(String param, Integer length) {
        if (param.isEmpty() || param.equals("{random}")) {
            param = apitdu.getRandomNumber(length).randomNumber.toString();
        }
        return param;
    }

    @And("I updated existing address information {string},{string},{string},{string},{string},{string},{string},{string},{string} and {string}")
    public void I_updated_existing_address_information(String address1, String address2, String city, String county,
                                                       String state, String zip, String zipFour, String type,
                                                       String startDate, String endDate) {
        requestParams = new JsonObject();
        requestParams.add("address", latestAddress);
        requestParams.getAsJsonObject("address").addProperty("addressStreet1", get_random_value(address1, 4) + " Test Ave.");
        requestParams.getAsJsonObject("address").addProperty("addressStreet2", "APT#" + get_random_value(address1, 4));
        requestParams.getAsJsonObject("address").addProperty("addressCity", get_random_string(city, 10));
        requestParams.getAsJsonObject("address").addProperty("addressCounty", get_random_string(county, 10));
        requestParams.getAsJsonObject("address").addProperty("addressState", apitdu.getArandomUSStateName().state);
        if (zip.isEmpty() || zip.equals("{random}")) {
            zip = apitdu.getRandomNumber(5).randomNumber.toString();
        }
        requestParams.getAsJsonObject("address").addProperty("addressZip", get_random_value(zip, 5));
        requestParams.getAsJsonObject("address").addProperty("addressZipFour", get_random_value(zipFour, 4));
        requestParams.getAsJsonObject("address").addProperty("addressType", apitdu.getARandomList("Mailing,Physical", true, null).randomListValue);
        long startDateTime = set_date(startDate, 0);
        requestParams.getAsJsonObject("address").addProperty("effectiveStartDate", startDateTime);
        if (!endDate.isEmpty()) {
            long endDateTime = set_date(endDate, 0);
            requestParams.getAsJsonObject("address").addProperty("effectiveEndDate", endDateTime);
        }
        requestParams.addProperty("externalRefType", "Consumer");
        requestParams.addProperty("externalRefId", apiConsumerId);
        System.out.println(requestParams);
    }

    @Then("I verify Address Audit Trail")
    public void i_verify_Address_Audit_Trail() {
        apitdu.jsonElement.getAsJsonObject().addProperty("externalRefType", "Consumer");
        apitdu.jsonElement.getAsJsonObject().addProperty("externalRefId", apiConsumerId);
        requestParams = apitdu.jsonElement.getAsJsonObject();
        api.GetAPIWithParameter(requestParams);
        System.out.println(api.responseString);
        assertEquals(api.statusCode, 200);

        ArrayList contacts = api.jsonPathEvaluator.get("contacts");
        HashMap address = (HashMap) contacts.get(0);
        ArrayList addresses = (ArrayList) address.get("address");
        System.out.println(addresses);

        HashMap address_status = (HashMap) addresses.get(0);
        String temp = address_status.get("createdOn").toString();
        System.out.println("Created On : "+temp);
        assertFalse(temp.equalsIgnoreCase("null"));
    }

    @Then("I can verify Address Updates")
    public void i_can_verify_Address_updates() {
        JsonObject actualAddress = requestParams.get("address").getAsJsonObject();
        System.out.println(actualAddress);
        JsonParser parser = new JsonParser();
        JsonObject expectedAddressObject =parser.parse(expectedAddress).getAsJsonObject();
        System.out.println(expectedAddressObject);
        assertEquals(actualAddress.get("addressId"), expectedAddressObject.get("addressId"));
        assertNotEquals(actualAddress.get("addressStreet1"), expectedAddressObject.get("addressStreet1"));
        assertNotEquals(actualAddress.get("addressStreet2"), expectedAddressObject.get("addressStreet2"));
        assertNotEquals(actualAddress.get("addressCity"), expectedAddressObject.get("addressCity"));
        assertNotEquals(actualAddress.get("addressState"), expectedAddressObject.get("addressState"));
        assertNotEquals(actualAddress.get("addressZip"), expectedAddressObject.get("addressZip"));
        assertNotEquals(actualAddress.get("addressZipFour"), expectedAddressObject.get("addressZipFour"));
        assertNotEquals(actualAddress.get("addressCounty"), expectedAddressObject.get("addressCounty"));
     }
    
	@Then("I verify active emails fetched first followed by inactive emails")
    public void i_verify_active_emails_fetched_first_followed_by_inactive_emails() {
        apitdu.jsonElement.getAsJsonObject().addProperty("externalRefType", "Consumer");
        apitdu.jsonElement.getAsJsonObject().addProperty("externalRefId", apiConsumerId);
        requestParams = apitdu.jsonElement.getAsJsonObject();
        api.GetAPIWithParameter(requestParams);
        System.out.println("Debug :" + api.responseString);
        assertEquals(api.statusCode, 200);
        //assertTrue(api.responseString.equalsIgnoreCase(status));
        JsonPath jsonPathEvaluator = api.jsonPathEvaluator;
        ArrayList contacts = jsonPathEvaluator.get("contacts");
        HashMap email = (HashMap) contacts.get(0);
        // System.out.println(email);
        ArrayList emails = (ArrayList) email.get("emails");
        // System.out.println(emails);
        boolean flag = true;
        boolean isInactive = false;
        if (emails.size() > 1) {
            for ( int i = 0; i < emails.size(); i++ ) {
                HashMap email_status = (HashMap) emails.get(i);
                if (email_status.get("status").toString().equalsIgnoreCase("Active")) {
                    if (isInactive) {
                        flag = false;
                        break;
                    }
                } else {
                    isInactive = true;
                }
            }
        }
        assertTrue(flag);
    }
		
    @Then("I verify Updated on field is populated")
    public void i_verify_Updated_on_field_is_populated() {
        apitdu.jsonElement.getAsJsonObject().addProperty("externalRefType", "Consumer");
        apitdu.jsonElement.getAsJsonObject().addProperty("externalRefId", apiConsumerId);
        requestParams = apitdu.jsonElement.getAsJsonObject();
        api.GetAPIWithParameter(requestParams);
        System.out.println("Debug :"+api.responseString);
        assertEquals(api.statusCode, 200);
        //assertTrue(api.responseString.equalsIgnoreCase(status));
        JsonPath jsonPathEvaluator = api.jsonPathEvaluator;
        ArrayList contacts = jsonPathEvaluator.get("contacts");
        HashMap email = (HashMap) contacts.get(0);
        // System.out.println(email);
        ArrayList emails = (ArrayList) email.get("emails");
        // System.out.println(emails);
        int count=0;
        if(emails.size()>1){
            for(int i=0;i<emails.size();i++){
                HashMap email_status = (HashMap) emails.get(i);
                String  emailAddress=apiConsumerFirstName+"@"+apiConsumerLastName+".com";
                if(emailAddress.equalsIgnoreCase(email_status.get("emailAddress").toString())) {
                    count = i;
                    break;
                }
            }
        }
        HashMap email_status = (HashMap) emails.get(count);
        String temp = email_status.get("createdOn").toString();
        System.out.println("Updated On : "+temp);
        assertFalse(temp.equalsIgnoreCase("null"));
        // System.out.println(consumer_status.get("status"));
    }
    
	@When("I updated email information {string}")
    public void i_updated_email_information(String emailAddress) {
        apitdu.jsonElement.getAsJsonObject().getAsJsonObject("email").addProperty("emailId", emailId);
        apitdu.jsonElement.getAsJsonObject().getAsJsonObject("email").addProperty("emailAddress", emailAddress);

        requestParams = apitdu.jsonElement.getAsJsonObject();
        System.out.println("Debug "+api.endPoint);
        System.out.println("Debug "+requestParams);
    }
    @Then("I verify updated Email address detail using API {string}")
    public void i_verify_updated_Email_address_detail_using_API(String emailAddress) {
        apitdu.jsonElement.getAsJsonObject().addProperty("externalRefType", "Consumer");
        apitdu.jsonElement.getAsJsonObject().addProperty("externalRefId", apiConsumerId);
        requestParams = apitdu.jsonElement.getAsJsonObject();
        System.out.println("Debug :"+requestParams);
        api.GetAPIWithParameter(requestParams);
        System.out.println(api.responseString);
        assertEquals(api.statusCode, 200);
        assertTrue(api.responseString.contains(emailAddress));
    }
}

