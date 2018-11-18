package com.maximus.crm.api_step_definitions;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.JsonElement;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import com.google.gson.JsonObject;
import com.maximus.crm.utilities.APIClassUtil;
import com.maximus.crm.utilities.ConfigurationReader;
import com.maximus.crm.utilities.ApiTestDataUtil;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.regex.Pattern;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertEquals;


public class APITMProjectRestController {
    private APIClassUtil api = new APIClassUtil();
    private ApiTestDataUtil apitdu = new ApiTestDataUtil();
    private JsonObject requestParams;
    private String projectId;
    private String baseURI = ConfigurationReader.getProperty("apiTMURI");
    private String createProjectsEndPoint = "mars/tm/project";
    private String searchProjectsEndPoint = "mars/tm/projects";
    private String getTenantRecordByIdEndPoint = "mars/tm/project/{projectId}";
    private String getProjectApproverByIdEndPoint = "mars/tm/project/approver/{projectId}";

    private String provisioningString = "Active,Inactive,Pending";

    @Given("I initiated Create Project API")
    public void i_initiated_create_project_api() {
        api.setbaseUri(baseURI);
        api.setEndPoint(createProjectsEndPoint);
    }

    @Given("I initiated Search Project API")
    public void i_initiated_project_search_api() {
        api.setbaseUri(baseURI);
        api.setEndPoint(searchProjectsEndPoint);
    }

    @Given("I initiated Get Project List API")
    public void i_initiated_project_list_api() {
        api.setbaseUri(baseURI);
        api.setEndPoint(searchProjectsEndPoint);
    }

    @Given("I initiated Search Project API By Project ID {string}")
    public void i_initiated_project_search_api_by_id(String projectID) {
        if (projectID != null && !projectID.isEmpty()) {
            projectId = projectID;
        }
        api.setbaseUri(baseURI);
        getTenantRecordByIdEndPoint = getTenantRecordByIdEndPoint.replace("{projectId}", projectId);
        api.setEndPoint(getTenantRecordByIdEndPoint);
    }

    @Given("I initiated project approver API by project Id {string}")
    public void i_initiated_project_approver_api_by_id(String projectID) {
        if (projectID != null && !projectID.isEmpty()) {
            projectId = projectID;
        }
        api.setbaseUri(baseURI);
        getProjectApproverByIdEndPoint = getProjectApproverByIdEndPoint.replace("{projectId}", projectId);
        api.setEndPoint(getProjectApproverByIdEndPoint);
    }

    @When("I initiated project approver API by project name {string}")
    public void i_initiated_project_approver_api_by_name(String projectName) {
        JsonObject project_detail = get_project_detail_by_project_name(projectName);
        api.setbaseUri(baseURI);
        getProjectApproverByIdEndPoint = getProjectApproverByIdEndPoint.replace("{projectId}", projectId);
        api.setEndPoint(getProjectApproverByIdEndPoint);
    }


    @When("I can provide project information to create a {string} project on {string}")
    public void i_can_provide_project_information_to_create_project(String provisioning, String state) {
        System.out.println("Project is initiated on '"+state+"' state.");
        apitdu.getJsonFromFile("tenantmanager/apiCreateProject.json");
        apitdu.jsonElement.getAsJsonObject().addProperty("state", state);
        apitdu.jsonElement.getAsJsonObject().addProperty("country", "USA");
        apitdu.getRandomNumber(6);
        apitdu.jsonElement.getAsJsonObject().addProperty("contractId", (apitdu.randomNumber).toString());
        long curentDateTime = System.currentTimeMillis();
        apitdu.jsonElement.getAsJsonObject().addProperty("contractStartDate", curentDateTime);
        apitdu.getRandomString(8);
        apitdu.jsonElement.getAsJsonObject().addProperty("projectName", apitdu.randomString);
        apitdu.getRandomString(8);
        apitdu.jsonElement.getAsJsonObject().addProperty("programName", apitdu.randomString);
        apitdu.getRandomString(8);
        apitdu.jsonElement.getAsJsonObject().addProperty("stateAgencyName", apitdu.randomString);
        apitdu.jsonElement.getAsJsonObject().addProperty("productId", 1);
        apitdu.getARandomList(provisioningString, false, provisioning);
        apitdu.jsonElement.getAsJsonObject().getAsJsonObject("projectStatus").addProperty("provisioningStatus", apitdu.randomListValue);
        apitdu.jsonElement.getAsJsonObject().getAsJsonObject("projectStatus").addProperty("effectiveStartDate", curentDateTime);
        requestParams = apitdu.jsonElement.getAsJsonObject();
        // System.out.println(apitdu.jsonElement.toString());
    }

    @When("I can create a {string} project on {string} with contract number more than 6 digits")
    public void i_can_provide_project_information_to_create_project_with_contract_number_more_than_six(String provisioning, String state) {
        System.out.println("Project is initiated on '"+state+"' state.");
        apitdu.getJsonFromFile("tenantmanager/apiCreateProject.json");
        apitdu.jsonElement.getAsJsonObject().addProperty("state", state);
        apitdu.jsonElement.getAsJsonObject().addProperty("country", "USA");
        apitdu.getRandomString(6);
        apitdu.jsonElement.getAsJsonObject().addProperty("contractId", apitdu.randomString);
        long curentDateTime = System.currentTimeMillis();
        apitdu.jsonElement.getAsJsonObject().addProperty("contractStartDate", curentDateTime);
        apitdu.getRandomString(8);
        apitdu.jsonElement.getAsJsonObject().addProperty("projectName", apitdu.randomString);
        apitdu.getRandomString(8);
        apitdu.jsonElement.getAsJsonObject().addProperty("programName", apitdu.randomString);
        apitdu.getRandomString(8);
        apitdu.jsonElement.getAsJsonObject().addProperty("stateAgencyName", apitdu.randomString);
        apitdu.jsonElement.getAsJsonObject().addProperty("productId", 1);
        apitdu.getARandomList(provisioningString, false, provisioning);
        apitdu.jsonElement.getAsJsonObject().getAsJsonObject("projectStatus").addProperty("provisioningStatus", apitdu.randomListValue);
        apitdu.jsonElement.getAsJsonObject().getAsJsonObject("projectStatus").addProperty("effectiveStartDate", curentDateTime);
        requestParams = apitdu.jsonElement.getAsJsonObject();
        // System.out.println(apitdu.jsonElement.toString());
    }

    @When("I can provide project information to create a {string} project on {string} with contract start date as Yesterday date")
    public void i_can_provide_project_information_to_create_project_with_start_date_as_yesterday_date(String provisioning, String state) {
        System.out.println("Project is initiated on '"+state+"' state.");
        apitdu.getJsonFromFile("tenantmanager/apiCreateProject.json");
        apitdu.jsonElement.getAsJsonObject().addProperty("state", state);
        apitdu.jsonElement.getAsJsonObject().addProperty("country", "USA");
        apitdu.getRandomString(6);
        apitdu.jsonElement.getAsJsonObject().addProperty("contractId", apitdu.randomString);
        long yesterdayDateTime = System.currentTimeMillis() - 1 * 24 * 60 * 60 * 1000;
        apitdu.jsonElement.getAsJsonObject().addProperty("contractStartDate", yesterdayDateTime);
        apitdu.getRandomString(8);
        apitdu.jsonElement.getAsJsonObject().addProperty("projectName", apitdu.randomString);
        apitdu.getRandomString(8);
        apitdu.jsonElement.getAsJsonObject().addProperty("programName", apitdu.randomString);
        apitdu.getRandomString(8);
        apitdu.jsonElement.getAsJsonObject().addProperty("stateAgencyName", apitdu.randomString);
        apitdu.jsonElement.getAsJsonObject().addProperty("productId", 1);
        apitdu.getARandomList(provisioningString, false, provisioning);
        apitdu.jsonElement.getAsJsonObject().getAsJsonObject("projectStatus").addProperty("provisioningStatus", apitdu.randomListValue);
        apitdu.jsonElement.getAsJsonObject().getAsJsonObject("projectStatus").addProperty("effectiveStartDate", yesterdayDateTime);
        requestParams = apitdu.jsonElement.getAsJsonObject();
        // System.out.println(apitdu.jsonElement.toString());
    }

    @When("I can provide project information to create a {string} project on {string} with end date greater than today")
    public void i_can_provide_project_information_to_create_project_with_end_date_greater_than_today(String provisioning, String state) {
        System.out.println("Project is initiated on '"+state+"' state.");
        apitdu.getJsonFromFile("tenantmanager/apiCreateProject.json");
        apitdu.jsonElement.getAsJsonObject().addProperty("state", state);
        apitdu.jsonElement.getAsJsonObject().addProperty("country", "USA");
        apitdu.getRandomString(6);
        apitdu.jsonElement.getAsJsonObject().addProperty("contractId", apitdu.randomString);
        long tomorrowDateTime = System.currentTimeMillis() + 1 * 24 * 60 * 60 * 1000;
        apitdu.jsonElement.getAsJsonObject().addProperty("contractStartDate", tomorrowDateTime);
        apitdu.getRandomString(8);
        apitdu.jsonElement.getAsJsonObject().addProperty("projectName", apitdu.randomString);
        apitdu.getRandomString(8);
        apitdu.jsonElement.getAsJsonObject().addProperty("programName", apitdu.randomString);
        apitdu.getRandomString(8);
        apitdu.jsonElement.getAsJsonObject().addProperty("stateAgencyName", apitdu.randomString);
        apitdu.jsonElement.getAsJsonObject().addProperty("productId", 1);
        apitdu.getARandomList(provisioningString, false, provisioning);
        apitdu.jsonElement.getAsJsonObject().getAsJsonObject("projectStatus").addProperty("provisioningStatus", apitdu.randomListValue);
        apitdu.jsonElement.getAsJsonObject().getAsJsonObject("projectStatus").addProperty("effectiveStartDate", tomorrowDateTime);
        requestParams = apitdu.jsonElement.getAsJsonObject();
        // System.out.println(apitdu.jsonElement.toString());
    }

    private void search_project_by(JsonObject requestParams, String item, String value) {
        // -- Make consumer search by specific property. --
        switch (item) {
            case "state":
                requestParams.addProperty("state", value);
                break;
            case "programName":
                requestParams.addProperty("programName", value);
                break;
            case "projectName":
                requestParams.addProperty("projectName", value);
                break;
            case "stateAgencyName":
                requestParams.addProperty("stateAgencyName", value);
                break;
        }
    }

    @When("I can provide all blank project information")
    public void i_can_provide_al_blank_project_information() {
        requestParams = new JsonObject();
        apitdu.getJsonFromFile("tenantmanager/apiCreateProject.json");
        System.out.println(requestParams);
    }

    @When("I can Search Project by {string} with value {string}")
    public void i_can_search_project_by(String item, String value) {
        requestParams = new JsonObject();
        search_project_by(requestParams, item, value);
    }

    @When("I can Search Project by {string} with value {string}, {string} with value {string}, {string} with value {string} and {string} with value {string}")
    public void i_can_search_project_by_node_and_value(String node1, String value1, String node2, String value2, String node3, String value3, String node4, String value4) {
        requestParams = new JsonObject();
        if (node1 != null && !node1.isEmpty()) {
            search_project_by(requestParams, node1, value1);
        }
        if (node2 != null && !node2.isEmpty()) {
            search_project_by(requestParams, node2, value2);
        }
        if (node3 != null && !node3.isEmpty()) {
            search_project_by(requestParams, node3, value3);
        }
        if (node4 != null && !node4.isEmpty()) {
            search_project_by(requestParams, node4, value4);
        }
    }

    @And("I run the search project API")
    public void i_run_the_search_project_api() {
        api.PostAPIWithParameter(requestParams);
        if (api.statusCode !=200){
            System.out.println("API Request:");
            System.out.println(requestParams);
            System.out.println("API Response:");
            System.out.println(api.responseString);
        }
        assertEquals(api.statusCode, 200);
        assertTrue(api.responseString.contains("success"));
        assertEquals(api.jsonPathEvaluator.get("status"), "success");
    }

    @And("I run the get project list API")
    public void i_run_the_get_project_list_api() {
        api.GetAPIWithParameter(requestParams);
        if (api.statusCode !=200){
            System.out.println("API Request:");
            System.out.println(requestParams);
            System.out.println("API Response:");
            System.out.println(api.responseString);
        }
        assertEquals(api.statusCode, 200);
        assertTrue(api.responseString.contains("success"));
        System.out.println(api.responseString);
    }


    @And("I run the search project API By ProjectID")
    public void i_run_the_search_project_api_by_project_id() {
        api.getAPI();
        if (api.statusCode !=200){
            System.out.println("API Request:");
            System.out.println(requestParams);
            System.out.println("API Response:");
            System.out.println(api.responseString);
        }
        assertEquals(api.statusCode, 200);
        assertTrue(api.responseString.contains("success"));
        assertEquals(api.jsonPathEvaluator.get("status"), "success");
        System.out.println(api.responseString);
    }

    @And("I can get the project approver detail")
    public void i_can_get_the_project_detail() {
        api.getAPI();
        if (api.statusCode !=200){
            System.out.println("API Request:");
            System.out.println(requestParams);
            System.out.println("API Response:");
            System.out.println(api.responseString);
        }
        assertEquals(api.statusCode, 200);
        assertTrue(api.responseString.contains("success"));
        assertEquals(api.jsonPathEvaluator.get("status"), "success");
        System.out.println(api.responseString);
    }

    @And("I run the create project API")
    public void i_run_the_creat_project_api() {
        api.PutAPI(requestParams);
        System.out.println(api.responseString);
        assertEquals(api.statusCode, 200);
        assertTrue(api.responseString.contains("success"));
        assertEquals(api.jsonPathEvaluator.get("status"), "success");
        System.out.println(api.responseString);
    }

    @And("I run the create project API again")
    public void i_run_the_creat_project_api_again() {
        api.PutAPI(requestParams);
        System.out.println(api.responseString);
        assertEquals(api.statusCode, 200);
        assertTrue(api.responseString.contains("errorResponse"));
    }

    @And("I run the create empty project API")
    public void i_run_the_creat_empty_project_api() {
        api.PutAPI(requestParams);
        System.out.println(api.responseString);
    }

    @Then("I can search the project by project name to validate is {string}")
    public void i_can_verify_api_response_will_be_success(String success) {
        JsonObject rp = requestParams;
        String projectName = rp.get("projectName").toString().replace("\"", "");
        System.out.println("Project Name : "+projectName);
        i_initiated_project_search_api();
        i_can_search_project_by("projectName", projectName);
        i_run_the_search_project_api();
        i_can_verify_the_project_search_api_response(success);
    }

    @Then("I can verify on project search API response will be {string}")
    public void i_can_verify_the_project_search_api_response(String success) {
        if (Boolean.valueOf(success)== Boolean.TRUE) {
            assertEquals(api.jsonPathEvaluator.get("status"), "success");
        }else{
            assertEquals(api.jsonPathEvaluator.get("status"), "fail");
        }
    }

    @Then("I can verify the project error message on API response")
    public void i_can_verify_the_duplicate_project_error_message_api_response() {
        System.out.println(api.responseString);
        assertEquals(api.jsonPathEvaluator.get("status"), "fail");
    }

    @Then("I can search the project by project name to validate is success")
    public void i_can_verify_the_project_search_api_response() {
        assertEquals(api.statusCode, 200);
    }

    @Then("I can verify Project ID on project search API response will be {string}")
    public void i_can_verify_project_id_on_project_search_api_response(String success) {
        i_can_verify_the_project_search_api_response(success);
        List<String> projectIdLst = api.jsonPathEvaluator.getList("projectResponse.projectRequest.projectContact.projectId");
        System.out.println(projectIdLst);
        Object projId = projectIdLst.get(0);
        assertEquals(projId.toString(), projectId);
    }

    @Then("I can verify get user approver detail API response will be {string}")
    public void i_can_verify_get_user_approver_detail_api_response(String success) {
        if (Boolean.valueOf(success)== Boolean.TRUE) {
            assertEquals(api.jsonPathEvaluator.get("status"), "success");
        }else{
            assertEquals(api.jsonPathEvaluator.get("status"), "fail");
        }
    }

    @Then("I can verify {string} with value {string} on project API response will be {string}")
    public void i_can_verify_the_project_search_api(String field, String value, String success) {
        i_can_verify_the_project_search_api_response(success);
        if (Boolean.valueOf(success) == Boolean.TRUE){
            switch (field) {
                case "state":
                    List<List> stateLst = api.jsonPathEvaluator.getList("list.projectRequest.state");
                    for (ListIterator<List> iter = stateLst.listIterator(); iter.hasNext(); ) {
                        Object element = iter.next();
                        assertTrue(Pattern.compile(Pattern.quote(value), Pattern.CASE_INSENSITIVE).matcher(element.toString()).find());
                    }
                    break;
                case "programName":
                    List<List> programLst = api.jsonPathEvaluator.getList("list.projectRequest.programName");
                    for (ListIterator<List> iter = programLst.listIterator(); iter.hasNext(); ) {
                        Object element = iter.next();
                        assertTrue(Pattern.compile(Pattern.quote(value), Pattern.CASE_INSENSITIVE).matcher(element.toString()).find());
                    }
                    break;
                case "projectName":
                    List<List> projectLst = api.jsonPathEvaluator.getList("list.projectRequest.projectName");
                    for (ListIterator<List> iter = projectLst.listIterator(); iter.hasNext(); ) {
                        Object element = iter.next();
                        assertTrue(Pattern.compile(Pattern.quote(value), Pattern.CASE_INSENSITIVE).matcher(element.toString()).find());
                    }
                    break;
                case "stateAgencyName":
                    List<List> stateAgencyNameLst = api.jsonPathEvaluator.getList("list.projectRequest.stateAgencyName");
                    for (ListIterator<List> iter = stateAgencyNameLst.listIterator(); iter.hasNext(); ) {
                        Object element = iter.next();
                        assertTrue(Pattern.compile(Pattern.quote(value), Pattern.CASE_INSENSITIVE).matcher(element.toString()).find());
                    }
                    break;
            }
        }
    }

    @And("I can get ProjectID")
    public void i_can_get_project_id() {
        i_can_verify_the_project_search_api_response("True");
        List<String> projectIdLst = api.jsonPathEvaluator.getList("list.projectRequest.projectStatus.projectId");
        // System.out.println(projectIdLst);
        Object projId = projectIdLst.get(0);
        projectId = projId.toString();
        System.out.println("Project ID : " + projectId);
    }

    public void get_project_id_by_project_name(String projectName) {
        /*This function will set Project Id based on prject Name */
        i_initiated_project_search_api();
        i_can_search_project_by("projectName", projectName);
        i_run_the_search_project_api();
        i_can_get_project_id();
    }

    public JsonObject get_project_detail_by_id(String projectID){
        /*This function will return Project Detail response in a JsonObject*/
        if (projectID != null && !projectID.isEmpty()) {
            projectId = projectID;
        }
        i_initiated_project_search_api_by_id(projectID);
        i_run_the_search_project_api_by_project_id();
        i_can_verify_project_id_on_project_search_api_response("True");
        return api.apiJsonObject;
    }


    public JsonObject get_project_detail_by_project_name(String projectName){
        /*This function will return Project Detail response in a JsonObject*/
        get_project_id_by_project_name(projectName);
        return get_project_detail_by_id("");
    }


}
