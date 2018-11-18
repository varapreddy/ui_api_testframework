package com.maximus.crm.api_step_definitions;

import com.google.common.collect.Ordering;
import com.google.gson.JsonParser;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.maximus.crm.utilities.APIClassUtil;
import com.maximus.crm.utilities.ApiTestDataUtil;
import com.maximus.crm.api_step_definitions.APIConsumerRestController;
import com.maximus.crm.api_step_definitions.APICaseRestController;
import com.maximus.crm.utilities.ConfigurationReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotSame;

public class APIContactRecordController {
    private APIClassUtil api = new APIClassUtil();
    private ApiTestDataUtil apitdu = new ApiTestDataUtil();
    private APIConsumerRestController consumerController = new APIConsumerRestController();
    private APICaseRestController caseController = new APICaseRestController();
    private String baseURI = ConfigurationReader.getProperty("apiContactRecordURI");
    private String contactRecordEndPoint = "app/crm/contactrecord";
    private String searchContactRecordsEndPoint = "app/crm/contactrecords";
    private String getContactRecordDetail = "app/crm/contactrecord/{contactId}";
    private String getCorrelationContactRecordDetail = "app/crm/correlation/contactrecord/{contactId}";
    private String contactRecordCustomersEndPoint = "app/crm/contactrecord/customers";

    private JsonObject requestParams;
    private String ConsumerTypes = "ConsumerType,Member,Non-Member,Authorized Representative";
    private String contactRecordOptions = "Inbound,Member,Outbound";
    private String consumerLanguageCodes = "English,Spanish";

    private String apiconsumerFirstName;
    private String apiconsumerLastName;
    private String apiconsumerCorrelationId;
    private String apiconsumerId;
    private String lastContactRecordId;
    private Integer contactRecordId;
    public String consumerParams;
    public JsonObject consumerObj;

    @Given("I initiated Create Contact Records API")
    public void i_initiated_create_contact_record_api() {
        api.setbaseUri(baseURI);
        api.setEndPoint(contactRecordEndPoint);
    }

    @Given("I initiated Contact Records Customers API")
    public void i_initiated_contact_record_customers_api() {
        api.setbaseUri(baseURI);
        api.setEndPoint(contactRecordCustomersEndPoint);
    }

    @And("I initiated specific contact Search {string}")
    public void i_initiated_specific_contact_search(String contactId){
        api.setbaseUri(baseURI);
        String getContactRecordDtl;
        if (contactId.isEmpty() || contactId.equals("")) {
            getContactRecordDtl = getContactRecordDetail.replace("{contactId}", contactRecordId.toString());
        } else {
            getContactRecordDtl = getContactRecordDetail.replace("{contactId}", contactId);
        }
        api.setEndPoint(getContactRecordDtl);
    }

    @And("I initiated correlation contact Search {string}")
    public void i_initiated_correlation_contact_search(String contactId){
        api.setbaseUri(baseURI);
        String getCorrelationContactRecordDtl;
        if (contactId.isEmpty() || contactId.equals("")) {
            getCorrelationContactRecordDtl = getCorrelationContactRecordDetail.replace("{contactId}", lastContactRecordId.toString());
        } else {
            getCorrelationContactRecordDtl = getCorrelationContactRecordDetail.replace("{contactId}", contactId);
        }
        api.setEndPoint(getCorrelationContactRecordDtl);
    }

    @Given("I initiated consumer search API for Contact")
    public void i_initiated_consumer_search_api_for_contact(){
        caseController.i_initiated_search_case_api();
    }

    @When("I can search consumer for contact by {string} with value {string}")
    public void i_can_search_consumer_for_contact(String item, String value) {
        caseController.i_can_search_case_by(item, value);
    }

    @And("I run the consumer search API for contact")
    public void i_run_the_consumer_search_api_for_contact(String item, String value) {
        caseController.i_run_the_case_search_api();
    }

    @When("I get correlationId id from consumer first name search {string}")
    public void i_get_correlation_id_from_consumer_search(String consumerFirstName){
        consumerController.get_the_consumer_correlationId_id_from_response(consumerFirstName);
        apiconsumerCorrelationId = consumerController.correlationId;
        System.out.println("Consumer Correlation ID : " + apiconsumerCorrelationId);
        consumerController.get_the_consumer_id_from_response(consumerFirstName);
        apiconsumerId = consumerController.apiconsumerId;
        System.out.println("Consumer Consumer ID : " + apiconsumerId);
        consumerParams = consumerController.consumerDetail;
        i_initiated_search_contact_records_api();
        i_can_search_contact_record_by("consumerFirstName", "");
        i_run_the_search_contact_records_api();
        get_last_contact_record_id_from_response();
    }

    @Given("I initiated search contact records API")
    public void i_initiated_search_contact_records_api() {
        api.setbaseUri(baseURI);
        api.setEndPoint(searchContactRecordsEndPoint);
    }

    private void search_contact_records_by(JsonObject requestParams, String item, String value) {
        // -- Make contact Records search by specific property. --
        switch (item) {
            case "consumerFirstName":
                requestParams.addProperty("consumerFirstName", value);
                break;
            case "consumerLastName":
                requestParams.addProperty("consumerLastName", value);
                break;
            case "updatedOn":
                requestParams.addProperty("updatedOn", value);
                break;
        }
    }

    @When("I can search Contact Record by {string} with value {string}")
    public void i_can_search_contact_record_by(String item, String value) {
        requestParams = new JsonObject();
        search_contact_records_by(requestParams, item, value);
        System.out.println(requestParams);
    }

    @And("I can search contact records for link by contactRecordId")
    public void i_can_search_contact_records_for_link_by_contact_record_id() {
        requestParams = new JsonObject();
        requestParams.addProperty("contactRecordId", contactRecordId);
        System.out.println(requestParams);
    }

    @And("I can set link consumer Id as reference Id")
    public void i_can_set_link_reference_id_as_contact_record_id() {
        requestParams = new JsonObject();
        requestParams.addProperty("linkRefType", "Consumer");
        requestParams.addProperty("linkRefId", apiconsumerId);
        System.out.println(requestParams);
    }

    @When("I can provide correlation id")
    public void i_can_provide_correlation_id() {
        requestParams.addProperty("correlationId", apiconsumerCorrelationId);
        requestParams.getAsJsonArray("contactRecordReasons").get(0).getAsJsonObject().addProperty("correlationId", apiconsumerCorrelationId);
        System.out.println(requestParams);
    }

    public void generate_contact_records_information_with(String consumerFirstName, String consumerLastName) {
        apitdu.getJsonFromFile("crm/contactrecords/apiCreateContactRecords.json");
        long currentDateTime = System.currentTimeMillis();
        apitdu.jsonElement.getAsJsonObject().addProperty("contactRecordActionTimeStamp", currentDateTime);
        apitdu.jsonElement.getAsJsonObject().addProperty("contactRecordStartTime", currentDateTime);
        apitdu.jsonElement.getAsJsonObject().addProperty("createdOn", currentDateTime);

        if (consumerFirstName.isEmpty() || consumerFirstName.equals("{random}")){
            apitdu.getRandomString(10);
            apiconsumerFirstName = apitdu.randomString;
            apitdu.jsonElement.getAsJsonObject().addProperty("consumerFirstName", apitdu.randomString);
        } else {
            apitdu.jsonElement.getAsJsonObject().addProperty("consumerFirstName", consumerFirstName);
        }

        if (consumerLastName.isEmpty() || consumerLastName.equals("{random}")){
            apitdu.getRandomString(10);
            apiconsumerLastName = apitdu.randomString;
            apitdu.jsonElement.getAsJsonObject().addProperty("consumerLastName", apitdu.randomString);
        } else {
            apitdu.jsonElement.getAsJsonObject().addProperty("consumerLastName", consumerLastName);
        }
    }

    @When("I can provide contact records information with {string} and {string}")
    public void i_can_provide_contact_records_information_with(String consumerFirstName, String consumerLastName) {
        generate_contact_records_information_with(consumerFirstName, consumerLastName);
        // Adding Consumer Type randomly
        apitdu.getARandomList(ConsumerTypes, true, "");
        apitdu.jsonElement.getAsJsonObject().addProperty("consumerType", apitdu.randomListValue);
        // Adding Contact Records Option randomly
        apitdu.getARandomList(contactRecordOptions, true, "");
        apitdu.jsonElement.getAsJsonObject().addProperty("contactRecordType", apitdu.randomListValue);
        // Adding Consumer Language Code Option randomly
        apitdu.getARandomList(consumerLanguageCodes, true, "");
        apitdu.jsonElement.getAsJsonObject().addProperty("consumerLanguageCode", apitdu.randomListValue);
        requestParams = apitdu.jsonElement.getAsJsonObject();
        System.out.println(requestParams);
    }

    @When("I can provide contact records with {string}, {string} and {string}")
    public void i_can_provide_contact_records_information_with_consumer_type(String consumerFirstName, String consumerLastName, String consumerType){
        generate_contact_records_information_with(consumerFirstName, consumerLastName);
        apitdu.jsonElement.getAsJsonObject().addProperty("consumerType", consumerType);
        // Adding Contact Records Option randomly
        apitdu.getARandomList(contactRecordOptions, true, "");
        apitdu.jsonElement.getAsJsonObject().addProperty("contactRecordType", apitdu.randomListValue);
        // Adding Consumer Language Code Option randomly
        apitdu.getARandomList(consumerLanguageCodes, true, "");
        apitdu.jsonElement.getAsJsonObject().addProperty("consumerLanguageCode", apitdu.randomListValue);
        requestParams = apitdu.jsonElement.getAsJsonObject();

        requestParams = apitdu.jsonElement.getAsJsonObject();
        System.out.println(requestParams);
    }

    @And("I can provide contact records information for link")
    public void i_can_provide_contact_records_info_for_link(){
        requestParams = new JsonObject();
        requestParams.addProperty("consumerAuthenticatedInd", true);
        requestParams.addProperty("contactRecordId", contactRecordId);
        requestParams.addProperty("linkRefId", Integer.valueOf(apiconsumerId));
        requestParams.addProperty("linkRefType", "consumer");
        System.out.println(requestParams);
    }

    @And("I can provide contact records information for unlink")
    public void i_can_provide_contact_records_info_for_unlink(){
        requestParams = new JsonObject();
        requestParams.addProperty("consumerAuthenticatedInd", true);
        requestParams.addProperty("contactRecordId", lastContactRecordId);
        requestParams.addProperty("linkRefId", "");
        requestParams.addProperty("linkRefType", "");
        System.out.println(requestParams);
    }

    @And("I can close contact records information")
    public void i_can_close_contact_records_info_for_link() {
        JsonParser jsonParser = new JsonParser();
        requestParams = (JsonObject) jsonParser.parse(consumerParams);
        requestParams.addProperty("contactRecordStatusType", "Dropped");
        System.out.println(requestParams);
    }

    @And("I run the post contact records API with Query parameters with {string},{string} and {string}")
    public void i_run_the_post_contact_records_api_with_query_parameters(String page, String size, String sort) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (!page.isEmpty()) {
            map.put("page", page);
        }
        if (!size.isEmpty()) {
            map.put("size", size);
        }
        if (!sort.isEmpty()) {
            map.put("sort", sort);
        }
        System.out.println(map);
        api.PostAPIWithParameterAndQuery(requestParams, map);
        System.out.println("API Response:");
        System.out.println(api.responseString);
        consumerParams = api.responseString;
    }

    @And("I run the search contact records API")
    public void i_run_the_search_contact_records_api() {
        api.PostAPIWithParameter(requestParams);
        System.out.println("API Response:");
        System.out.println(api.responseString);
        consumerParams = api.responseString;
        assertEquals(api.statusCode, 200);
        assertTrue(api.responseString.contains("success"));
        assertEquals(api.jsonPathEvaluator.get("status"), "success");
        System.out.println(api.responseString);
        consumerParams = api.responseString;
    }

    @And("I run Get contact records API")
    public void i_run_get_contact_records_api() {
        api.getAPI();
        System.out.println("API Response:");
        System.out.println(api.responseString);
        assertEquals(api.statusCode, 200);
        assertTrue(api.responseString.contains("success"));
        assertEquals(api.jsonPathEvaluator.get("status"), "success");
        System.out.println(api.responseString);
    }

    @And("I can run create contact records API")
    public void i_can_run_create_contact_records_api(){
        api.PutAPI(requestParams);
        System.out.println("API Response:");
        System.out.println(api.responseString);
        assertEquals(api.statusCode, 200);
        assertTrue(api.responseString.contains("success"));
        assertEquals(api.jsonPathEvaluator.get("status"), "success");
        System.out.println(api.responseString);
    }

    @And("I can run update contact records API")
    public void i_can_run_update_contact_records_api(){
        api.PutAPI(requestParams);
        System.out.println("API Response:");
        System.out.println(api.responseString);
        assertEquals(api.statusCode, 200);
        assertTrue(api.responseString.contains("success"));
        assertEquals(api.jsonPathEvaluator.get("status"), "success");
        System.out.println(api.responseString);
    }

    @And("I get the last contactRecordId from API response")
    public void get_last_contact_record_id_from_response() {
        Object contactRecordIdObj = api.jsonPathEvaluator.get("contactrecords.totalElements");
        System.out.println(contactRecordIdObj);
        lastContactRecordId = contactRecordIdObj.toString();
        System.out.println("Last Contact Record ID: "+lastContactRecordId);
        contactRecordId = Integer.valueOf(lastContactRecordId) + 3;
        System.out.println("Next Contact Record ID: "+contactRecordId);
    }

    @Then("I can verify contact records search response success is {string}")
    public void verify_contact_records_search_response_success_is(String success){
        if (Boolean.valueOf(success)== Boolean.TRUE) {
            assertEquals(api.jsonPathEvaluator.get("status"), "success");
        }else{
            assertEquals(api.jsonPathEvaluator.get("status"), "fail");
        }
    }

    @Then("I can verify consumer contact by consumerLastName with value {string} on API response")
    public void i_can_verify_the_consumer_contact_by_last_name_search_api(String value) {
        if (value.equals("{random}")) {
            value = apiconsumerLastName;
        }
        System.out.println("consumerLastName : " + value);
        System.out.println("Initiating contact Records Search API...");
        i_initiated_search_contact_records_api();
        System.out.println("Consumer Search API informaiton loading...");
        i_can_search_contact_record_by("consumerLastName", value);
        System.out.println("Consumer Contact Search API running...");
        i_run_the_search_contact_records_api();
        verify_contact_records_search_response_success_is("True");
    }

    @When("I get contact detail from consumer first name search {string}")
    public void i_get_contact_detail_from_consumer_search(String consumerFirstName){
        consumerController.get_the_consumer_correlationId_id_from_response(consumerFirstName);
        apiconsumerCorrelationId = consumerController.correlationId;
        System.out.println("Consumer Correlation ID : " + apiconsumerCorrelationId);
        consumerController.get_the_consumer_id_from_response(consumerFirstName);
        apiconsumerId = consumerController.apiconsumerId;
        System.out.println("Consumer Consumer ID : " + apiconsumerId);
        consumerParams = consumerController.consumerDetail;
        i_initiated_correlation_contact_search(apiconsumerCorrelationId);
        i_run_get_contact_records_api();
        i_initiated_search_contact_records_api();
        i_can_search_contact_record_by("consumerFirstName", "");
        i_run_the_search_contact_records_api();
        get_last_contact_record_id_from_response();
    }

    @Then("I can verify contact record Size is less than or equal of {string}")
    public void i_can_verify_contact_record(String size){
        String contactRecordTotal = api.jsonPathEvaluator.get("contactrecords.numberOfElements").toString();
        System.out.println(contactRecordTotal);
        assertTrue(Integer.parseInt(contactRecordTotal)<= Integer.parseInt(size));
    }

    @Then("I can verify contact record ID are descending")
    public void i_can_verify_contact_record_id_descding(){
        ArrayList<Integer> contactRecordIds = api.jsonPathEvaluator.get("contactrecords.content.contactRecordId");
        System.out.println(contactRecordIds);
        assertTrue(Ordering.natural().reverse().isOrdered(contactRecordIds));
    }
}