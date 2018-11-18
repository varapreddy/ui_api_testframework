package com.maximus.crm.api_step_definitions;

import com.google.gson.JsonElement;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import com.maximus.crm.utilities.APIClassUtil;
import com.maximus.crm.utilities.ApiTestDataUtil;
import com.maximus.crm.utilities.ConfigurationReader;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class APITMCommonRestController {
    private APIClassUtil api = new APIClassUtil();
    private ApiTestDataUtil apitdu = new ApiTestDataUtil();
    private JsonObject requestParams;
    private String maxId;
    private String baseURI = ConfigurationReader.getProperty("apiTMURI");
    private String getMaxIDEndPoint = "mars/tm/common/activedirectory/{maxId}";

    @Given("I initiated get employee detail by MaxID {string}")
    public void i_initiated_get_employee_detail_by_maxid(String maximusID) {
        if (maximusID != null && !maximusID.isEmpty()) {
            maxId = maximusID;
        }
        api.setbaseUri(baseURI);
        getMaxIDEndPoint = getMaxIDEndPoint.replace("{maxId}", maxId);
        api.setEndPoint(getMaxIDEndPoint);
    }

    @When("I run the employee detail API by MaxID")
    public void i_run_the_employee_detail_api_by_max_id() {
        String url = baseURI+"/"+getMaxIDEndPoint;
        System.out.println(url);
        api.getAPI();
        if (api.statusCode !=200){
            System.out.println("API Request:");
            System.out.println("API Response:");
            System.out.println(api.responseString);
        }
        assertEquals(api.statusCode, 200);
        System.out.println(api.responseString);
//        assertTrue(api.responseString.contains("success"));
//        assertEquals(api.jsonPathEvaluator.get("status"), "success");
    }

    @Then("I can verify ge user approver detail API response will be {string}")
    public void i_can_verify_get_employee_detail_api_response(String success) {
        if (Boolean.valueOf(success)== Boolean.TRUE) {
            assertEquals(api.jsonPathEvaluator.get("status"), "success");
        }else{
            assertEquals(api.jsonPathEvaluator.get("status"), "fail");
        }
    }
}
