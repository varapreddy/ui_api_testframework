package com.maximus.crm.api_step_definitions;

import com.google.gson.JsonObject;
import com.maximus.crm.utilities.APIClassUtil;
import com.maximus.crm.utilities.ApiTestDataUtil;
import com.maximus.crm.utilities.ConfigurationReader;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;
import java.util.ListIterator;
import java.util.regex.Pattern;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class APITMProjectRoleRestController {
    private APIClassUtil api = new APIClassUtil();
    private ApiTestDataUtil apitdu = new ApiTestDataUtil();
    private JsonObject requestParams;
    private String projectId;
    private String roleName;
    private String baseURI = ConfigurationReader.getProperty("apiTMURI");
    private String createProjectRoleEndPoint = "mars/tm/project/role";
    private String searchProjectsRolesEndPoint = "mars/tm/getRoles/{projectId}";
    private String searchProjectsRoleByProjectIdEndPoint = "mars/tm/project/role/{projectId}";
    private String searchAssignedRoleEndPoint = "mars/tm/getRoleDescription/{projectId}/{roleName}";
    private String provisioningString = "Active,Inactive,Pending";

    @Given("I initiated Create Project Role API")
    public void i_initiated_create_project_role_api() {
        api.setbaseUri(baseURI);
        api.setEndPoint(createProjectRoleEndPoint);
    }

    @When("I can provide role details with {string} {string} {string}")
    public void i_can_search_project_by(String projectId, String roleName,String roleDesc) {
        requestParams = new JsonObject();
        if (projectId.isEmpty() || projectId.equals("{random}")) {
            apitdu.getRandomString(10);
            apitdu.getRandomNumber(1);
            projectId = apitdu.randomNumber;
            }
            requestParams.addProperty("projectId", projectId);

        if (roleName.isEmpty() || roleName.equals("{random}")) {
            apitdu.getRandomString(5);
            apitdu.getRandomNumber(1);
            roleName = "Role"+apitdu.randomString;
             }
            requestParams.addProperty("roleName", roleName);

        if (roleDesc.isEmpty() || roleDesc.equals("{random}")) {
            apitdu.getRandomString(10);
            apitdu.getRandomNumber(1);
            roleDesc = "Description"+apitdu.randomString;
        }
        requestParams.addProperty("roleDesc", roleDesc);

        long curentDateTime = System.currentTimeMillis();
        requestParams.addProperty("startDate", curentDateTime);
    }

    @And("I run the create project role API")
    public void i_run_the_create_project_role_api() {
        System.out.println(requestParams);
        api.PutAPI(requestParams);
        System.out.println(api.responseString);
        assertEquals(api.statusCode, 200);
        System.out.println(api.responseString);
        projectId = requestParams.get("projectId").toString().replace("\"", "");
        roleName = requestParams.get("roleName").toString().replace("\"", "");
    }

    @Then("I can search the project role by role name to validate is {string}")
    public void i_can_verify_api_response_will_be_success(String success) {
        assertTrue(api.responseString.contains("success"));
        assertEquals(api.jsonPathEvaluator.get("status"), "success");
        System.out.println("Role Name : "+roleName);
//        projectId = "1";
        i_initiated_project_role_by_project_id_via_api(projectId);
        i_can_get_the_assign_role_of_a_project_via_api();
        i_can_verify_the_project_role_field_search_api("roleName", roleName, success);
    }

    @Given("I initiated Project Roles Search By Project ID via API")
    public void i_initiated_project_roles_search_by_project_id_via_api(String project_id) {
        api.setbaseUri(baseURI);
        searchProjectsRolesEndPoint = searchProjectsRolesEndPoint.replace("{projectId}", project_id);
        System.out.println(searchProjectsRolesEndPoint);
        api.setEndPoint(searchProjectsRolesEndPoint);
    }

    @Given("I initiated Project Role By Project ID via API")
    public void i_initiated_project_role_by_project_id_via_api(String project_id) {
        api.setbaseUri(baseURI);
        searchProjectsRoleByProjectIdEndPoint = searchProjectsRoleByProjectIdEndPoint.replace("{projectId}", project_id);
        System.out.println(searchProjectsRoleByProjectIdEndPoint);
        api.setEndPoint(searchProjectsRoleByProjectIdEndPoint);
    }

    @Given("I initiated Assigned Role Search By {string} and {string} via API")
    public void i_initiated_assigned_role_search_by_project_id_and_role_name_via_api(String project_id, String role_name) {
        api.setbaseUri(baseURI);
        searchAssignedRoleEndPoint = searchAssignedRoleEndPoint.replace("{projectId}", project_id).replace("{roleName}", role_name);
        System.out.println(searchAssignedRoleEndPoint);
        api.setEndPoint(searchAssignedRoleEndPoint);
    }

    @When("I can Search Project Role by {string} with value {string}")
    public void i_can_search_project_role_by(String item, String value) {
        requestParams = new JsonObject();
        search_project_role_by(requestParams, item, value);
    }

    private void search_project_role_by(JsonObject requestParams, String item, String value) {
        // -- Make consumer search by specific property. --
        switch (item) {
            case "roleName":
                requestParams.addProperty("roleName", value);
                break;
            case "roleDesc":
                requestParams.addProperty("roleDesc", value);
                break;
        }
    }

    @And("I run get roles of a project via API")
    public void i_run_get_roles_of_a_project_via_api() {
        api.PostAPIWithParameter(requestParams);
        if (api.statusCode !=200){
            System.out.println("API Response:");
            System.out.println(requestParams);
            System.out.println(api.responseString);
        }
        assertEquals(api.statusCode, 200);
        System.out.println(api.responseString);
        assertTrue(api.responseString.contains("success"));
        assertEquals(api.jsonPathEvaluator.get("status"), "success");
    }

    @Then("I can verify on project role search API response will be {string}")
    public void i_can_verify_the_project_role_search_api_response(String success) {
        if (Boolean.valueOf(success)== Boolean.TRUE) {
            assertEquals(api.jsonPathEvaluator.get("status"), "success");
        }else{
            assertEquals(api.jsonPathEvaluator.get("status"), "fail");
        }
    }

    @Then("I can verify the duplicate role error message on API response")
    public void i_can_verify_the_project_role_search_api_response() {
        assertEquals(api.jsonPathEvaluator.get("status"), "fail");
    }

    @And("I can get the assign role of a project via API")
    public void i_can_get_the_assign_role_of_a_project_via_api() {
        api.getAPI();
        if (api.statusCode !=200){
            System.out.println("API Request:");
            System.out.println(requestParams);
            System.out.println("API Response:");
            System.out.println(api.responseString);
        }
        assertEquals(api.statusCode, 200);
        System.out.println(api.responseString);
        assertTrue(api.responseString.contains("success"));
        assertEquals(api.jsonPathEvaluator.get("status"), "success");
    }

    @Then("I can verify {string} with value {string} on project role API response will be {string}")
    public void i_can_verify_the_project_role_field_search_api(String field, String value, String success) {
        i_can_verify_the_project_role_search_api_response(success);
        if (Boolean.valueOf(success) == Boolean.TRUE){
            switch (field) {
                case "roleName":
                    List<List> projectRoleLst = api.jsonPathEvaluator.getList("list.projectRoles.roleName");
                    System.out.println(projectRoleLst);
                    List<String> orjectRoleObj = api.jsonPathEvaluator.get("projectRoles.roleName");
                    System.out.println(orjectRoleObj);
                    assert orjectRoleObj.contains(value);
                    break;
            }
        }
    }
}
