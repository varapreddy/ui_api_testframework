package com.maximus.crm.api_step_definitions;

import com.google.gson.JsonObject;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import com.google.gson.JsonElement;
import com.maximus.crm.utilities.APIClassUtil;
import com.maximus.crm.utilities.ApiTestDataUtil;
import com.maximus.crm.utilities.ConfigurationReader;
import gherkin.deps.com.google.gson.JsonArray;
import org.junit.Assert;

import java.text.NumberFormat;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertFalse;


public class APICaseRestController {
    private String baseURI = ConfigurationReader.getProperty("apiCaseConsumerURI");
    private String caseEndPoint = "app/crm/case";
    private String searchCaseEndPoint = "app/crm/case/consumers";
    private JsonObject requestParams;
    private APIClassUtil api = new APIClassUtil();
    private ApiTestDataUtil apitdu = new ApiTestDataUtil();

    private String apiconsumerId;

    @Given("I initiated create case API")
    public void i_initiated_create_case_api() {
        api.setbaseUri(baseURI);
        api.setEndPoint(caseEndPoint);
    }

    @Given("I initiated Search Case API")
    public void i_initiated_search_case_api() {
        api.setbaseUri(baseURI);
        api.setEndPoint(searchCaseEndPoint);
    }


    @When("I can input {string}, {string}, {string} and {string}")
    public void i_can_search_by(String caseFirstName, String caseLastName, String phoneNumber, String addressZip) {
         apitdu.getJsonFromFile("crm/case/apiCreateCase.json");
         apitdu.jsonElement.getAsJsonObject().get("caseConsumers").getAsJsonArray().get(0).getAsJsonObject()
                 .getAsJsonObject("caserequest").addProperty("caseFirstName", caseFirstName);
         apitdu.jsonElement.getAsJsonObject().get("caseConsumers").getAsJsonArray().get(0).getAsJsonObject()
                .getAsJsonObject("caserequest").addProperty("caseLastName", caseLastName);
         apitdu.jsonElement.getAsJsonObject().get("caseConsumers").getAsJsonArray().get(0).getAsJsonObject().getAsJsonObject("caserequest")
                .getAsJsonObject("contacts").getAsJsonObject("phone").addProperty("phoneNumber", phoneNumber);
         apitdu.jsonElement.getAsJsonObject().get("caseConsumers").getAsJsonArray().get(0).getAsJsonObject().getAsJsonObject("caserequest")
                .getAsJsonObject("contacts").getAsJsonObject("address").addProperty("addressZip", addressZip);

         requestParams = apitdu.jsonElement.getAsJsonObject();
         System.out.println(apitdu.jsonElement.toString());
    }

    @And("I can input more {string} and {string}")
    public void i_can_input_more(String consumerDateOfBirth, String consumerSSN) {
        try {
            apitdu.jsonElement.getAsJsonObject().get("caseConsumers").getAsJsonArray().get(0).getAsJsonObject()
                    .getAsJsonObject("caserequest").addProperty("consumerDateOfBirth", NumberFormat.getInstance().parse(consumerDateOfBirth));
        }catch (java.text.ParseException e) {
            System.out.println("Date of Birth is not a string input.");
        }
        apitdu.jsonElement.getAsJsonObject().get("caseConsumers").getAsJsonArray().get(0).getAsJsonObject()
                .getAsJsonObject("caserequest").addProperty("consumerSSN", consumerSSN);

        requestParams = apitdu.jsonElement.getAsJsonObject();
        System.out.println(apitdu.jsonElement.toString());
    }

    @And("I can input additional {string}")
    public void i_can_input_additional(String consumerIdentificationNo) {
        apitdu.jsonElement.getAsJsonObject().get("caseConsumers").getAsJsonArray().get(0).getAsJsonObject()
                .getAsJsonObject("caserequest").addProperty("consumerIdentificationNo", consumerIdentificationNo);

        requestParams = apitdu.jsonElement.getAsJsonObject();
        System.out.println(apitdu.jsonElement.toString());
    }

    @And("I can run create case API")
    public void i_can_run_create_case_api(){
        api.PutAPI(requestParams);
        assertEquals(api.statusCode, 200);
        Assert.assertTrue(api.responseString.contains("success"));
        assertEquals(api.jsonPathEvaluator.get("status"), "success");
        }

    @Then("I can verify case exists by {string} is {string} on API response")
    public void i_can_verify_case_submission_case_api(String caseLastName, String Success){
        api.setbaseUri(baseURI);
        api.setEndPoint(searchCaseEndPoint);
        i_can_search_case_by("consumerLastName", caseLastName);
        i_run_the_case_search_api();
//        if (Success == "True"):
//            Assert.assertTrue(api.responseString.contains("success"));

        System.out.println("I can verify case submission {string} on API response");
    }

    @When("I can search case by {string} with value {string}")
    public void i_can_search_case_by(String item, String value) {
        requestParams = new JsonObject();
        // -- Make consumer search by specific property. --
        switch (item) {
            case "consumerDateOfBirth":
                try {
                    requestParams.addProperty("consumerDateOfBirth", NumberFormat.getInstance().parse(value));
                    //System.out.println(requestParams);
                }catch (java.text.ParseException e) {
                    System.out.println("Date of Birth is not a string input.");
                }
                break;
            case "consumerFirstName":
                requestParams.addProperty("consumerFirstName", value);
                break;
            case "consumerIdentificationNo":
                requestParams.addProperty("consumerIdentificationNo", value);
                break;
            case "consumerLastName":
                requestParams.addProperty("consumerLastName", value);
                break;
            case "consumerMiddleName":
                requestParams.addProperty("consumerMiddleName", value);
                break;
            case "consumerSSN":
                requestParams.addProperty("consumerSSN", value);
                break;
        }
        System.out.println(requestParams);
    }

    private void search_case_by(JsonObject requestParams, String item, String value) {
        // -- Make consumer search by specific property. --
        switch (item) {
            case "consumerFirstName":
                requestParams.addProperty("consumerFirstName", value);
                break;
            case "consumerIdentificationNo":
                requestParams.addProperty("consumerIdentificationNo", value);
                break;
            case "consumerLastName":
                requestParams.addProperty("consumerLastName", value);
                break;
            case "consumerMiddleName":
                requestParams.addProperty("consumerMiddleName", value);
                break;
            case "consumerSSN":
                requestParams.addProperty("consumerSSN", value);
                break;
            case "consumerDateOfBirth":
                try {
                    requestParams.addProperty("consumerDateOfBirth", NumberFormat.getInstance().parse(value));
                    //System.out.println(requestParams);
                }catch (java.text.ParseException e) {
                    System.out.println("Date of Birth is not a string input.");
                }
                break;
        }
    }

    @When("I can Search Case by {string} with value {string}, {string} with value {string}, {string} with value {string}, {string} with value {string} and {string} with value {string}")
    public void i_can_search_case_by_node_and_value(String node1, String value1, String node2, String value2, String node3, String value3, String node4, String value4, String node5, String value5) {
        requestParams = new JsonObject();
        if (node1 != null && !node1.isEmpty()) {
            search_case_by(requestParams, node1, value1);
        }
        if (node2 != null && !node2.isEmpty()) {
            search_case_by(requestParams, node2, value2);
        }
        if (node3 != null && !node3.isEmpty()) {
            search_case_by(requestParams, node3, value3);
        }
        if (node4 != null && !node4.isEmpty()) {
            search_case_by(requestParams, node4, value4);
        }
        if (node5 != null && !node5.isEmpty()) {
            search_case_by(requestParams, node5, value5);
        }
        System.out.println(requestParams);
    }


    @And("I run the case search API")
    public void i_run_the_case_search_api() {
        api.PostAPIWithParameter(requestParams);
        assertEquals(api.statusCode, 200);
        Assert.assertTrue(api.responseString.contains("success"));
        assertEquals(api.jsonPathEvaluator.get("status"), "success");
        System.out.println(api.responseString);
    }

    private boolean check_record_exists(List<List> consumers, String field, String value){
        Boolean recordFound = false;
        List<String> consumersFirstList = consumers.get(0);
        System.out.println(consumersFirstList);
        for (String item : consumersFirstList) {
            if (item.toString().equalsIgnoreCase(value)) {
                System.out.println("Consumer "+field+" : " + item.toString());
                recordFound = true;
                break;
            }
        }
        return recordFound;
    }

    @Then("I can verify case {string} with value {string} on API response")
    public void i_can_verify_the_consumer_search_api(String field, String value) {
        List<List> consumerList = api.jsonPathEvaluator.getList("object.result.consumers");
        System.out.println(consumerList);
        int consumerListCount = ((List) consumerList).size();
        assertTrue(consumerListCount > 0, "Consumers list is not greater than zero(0).");
        Boolean consumerFound = false;
        switch (field) {
            case "consumerDateOfBirth":
                List<List> consumerListDOBs = api.jsonPathEvaluator.getList("object.result.consumers.consumerDateOfBirth");
                //System.out.println(consumerListDOBs);
                List consumerListDOB = consumerListDOBs.get(0);
                assert consumerListDOB.contains(value);
                break;
            case "consumerFirstName":
                List<List> consumerListFN = api.jsonPathEvaluator.getList("object.result.consumers.consumerFirstName");
                consumerFound = check_record_exists(consumerListFN, field, value);
                assertTrue(consumerFound);
                break;
            case "consumerLastName":
                List<List> consumerListLN = api.jsonPathEvaluator.getList("object.result.consumers.consumerLastName");
                consumerFound = check_record_exists(consumerListLN, field, value);
                assertTrue(consumerFound);
                break;
            case "consumerMiddleName":
                List<List> consumerListMN = api.jsonPathEvaluator.getList("object.result.consumers.consumerMiddleName");
                consumerFound = check_record_exists(consumerListMN, field, value);
                assertTrue(consumerFound);
                break;
            case "consumerSSN":
                List<List> consumerListSSN = api.jsonPathEvaluator.getList("object.result.consumers.consumerSSN");
                consumerFound = check_record_exists(consumerListSSN, field, value);
                assertTrue(consumerFound);
                break;
            case "consumerId":
                List<List> consumerListID = api.jsonPathEvaluator.getList("object.result.consumers.consumerId");
                consumerFound = check_record_exists(consumerListID, field, value);
                assertTrue(consumerFound);
                break;
        }
    }

    private void verify_case_search_response(String success){
        Object caseList = api.jsonPathEvaluator.getList("object.result.consumers");
        // System.out.println(caseList);
        int caseListCount = ((List) caseList).size();
        // System.out.println(caseListCount);
        if (caseListCount == 0) {
            assertEquals(Boolean.valueOf(success), Boolean.FALSE);
        } else {
            assertEquals(Boolean.valueOf(success), Boolean.TRUE);
            // System.out.println(api_step_definitions.responseString);
        }
    }

    @Then("I can verify on case search API response will be {string}")
    public void i_can_verify_the_case_search_api_response(String success) {
        verify_case_search_response(success);
    }
}
