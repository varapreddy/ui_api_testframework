package com.maximus.crm.api_step_definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import com.google.gson.JsonObject;
import com.maximus.crm.utilities.APIClassUtil;
import com.maximus.crm.utilities.ApiTestDataUtil;
import com.maximus.crm.utilities.ConfigurationReader;
import com.maximus.crm.api_step_definitions.APIConsumerRestController;
import io.cucumber.datatable.DataTable;
import io.restassured.path.json.JsonPath;
import java.text.NumberFormat;
import java.util.Collection;
import java.util.Iterator;
import java.util.*;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;


public class APIConsumerRestController {
    private String baseConsumerURI = ConfigurationReader.getProperty("apiCaseConsumerURI");
    private String createConsumerEndPoint = "app/crm/consumer";
    private String searchConsumerEndPoint = "app/crm/consumers";
    private String createCaseMemberEndPoint = "app/crm/casemember";
    private String getCaseMemberEndPoint = "app/crm/casemember/{caseId}";
    private JsonObject consumerRequestParams;
    private JsonObject caseMemberRequestParams;
    private APIClassUtil api = new APIClassUtil();
    private ApiTestDataUtil apitdu = new ApiTestDataUtil();
    public String apiconsumerFirstName;
    public String apiconsumerLastName;
    public String apiphoneNumber;
    public String apiaddressZip;
    public String apiconsumerId;
    public String correlationId;
    public String consumerDetail;
    public String uiid;

    private List<HashMap<String, String>> caseMembersData = new ArrayList <HashMap<String, String>>();

    @Given("I initiated Consumer Search API")
    public void i_initiated_consumer_search_api() {
        api.setbaseUri(baseConsumerURI);
        api.setEndPoint(searchConsumerEndPoint);
    }

    @Given("I initiated Create Consumer API")
    public void i_initiated_create_consumer_api() {
        api.setbaseUri(baseConsumerURI);
        api.setEndPoint(createConsumerEndPoint);
    }

    @Given("I initiated Create case member API")
    public void i_initiated_create_case_member_api() {
        api.setbaseUri(baseConsumerURI);
        api.setEndPoint(createCaseMemberEndPoint);
    }

    @Given("I initiated get case member API for case {string}")
    public void i_initiated_get_case_member_api(String caseId) {
        api.setbaseUri(baseConsumerURI);
        api.setEndPoint(getCaseMemberEndPoint.replace("{caseId}", caseId));
    }

    @When("I can provide consumer information with {string} {string} {string} and {string}")
    public void i_can_provide_consumer_information_with(String consumerFirstName, String consumerLastName, String phoneNumber, String addressZip) {
        apitdu.getJsonFromFile("crm/consumer/apiCreateConsumer.json");
        long currentDateTime = System.currentTimeMillis();
        apitdu.jsonElement.getAsJsonObject().addProperty("createdOn", currentDateTime);
        if (consumerFirstName.isEmpty() || consumerFirstName.equals("{random}")) {
            apitdu.getRandomString(10);
            apiconsumerFirstName = apitdu.randomString;
            apitdu.jsonElement.getAsJsonObject().addProperty("consumerFirstName", apitdu.randomString);
        } else {
            apitdu.jsonElement.getAsJsonObject().addProperty("consumerFirstName", consumerFirstName);
        }

        if (consumerLastName.isEmpty() || consumerLastName.equals("{random}")) {
            apitdu.getRandomString(10);
            apiconsumerLastName = apitdu.randomString;
            apitdu.jsonElement.getAsJsonObject().addProperty("consumerLastName", apitdu.randomString);
        } else {
            apitdu.jsonElement.getAsJsonObject().addProperty("consumerLastName", consumerLastName);
        }

        if (phoneNumber.isEmpty() || phoneNumber.equals("{random}")) {
            apitdu.getRandomNumber(10);
            apiphoneNumber = apitdu.randomNumber;
            apitdu.jsonElement.getAsJsonObject().getAsJsonObject("contacts").getAsJsonObject("phone").addProperty("phoneNumber", (apitdu.randomNumber).toString());
        } else {
            apitdu.jsonElement.getAsJsonObject().getAsJsonObject("contacts").getAsJsonObject("phone").addProperty("phoneNumber", phoneNumber);
        }

        if (addressZip.isEmpty() || addressZip.equals("{random}")) {
            apitdu.getRandomNumber(5);
            addressZip = apitdu.randomNumber;
            apitdu.jsonElement.getAsJsonObject().getAsJsonObject("contacts").getAsJsonObject("address").addProperty("addressZip", (apitdu.randomNumber).toString());
        } else {
            apitdu.jsonElement.getAsJsonObject().getAsJsonObject("contacts").getAsJsonObject("address").addProperty("addressZip", addressZip);
        }
        consumerRequestParams = apitdu.jsonElement.getAsJsonObject();
        System.out.println(consumerRequestParams);
    }

    @When("I can provide consumer's information randomly")
    public void i_can_provide_consumers_information_randomly() {
        apitdu.getJsonFromFile("crm/consumer/apiCreateConsumer.json");
        long currentDateTime = System.currentTimeMillis();
        apitdu.jsonElement.getAsJsonObject().addProperty("createdOn", currentDateTime);
        // Adding Consumer First Name randomly
        apitdu.getRandomString(10);
        apiconsumerFirstName = apitdu.randomString;
        apitdu.jsonElement.getAsJsonObject().addProperty("consumerFirstName", apitdu.randomString);
        // Adding Consumer Last Name randomly
        apitdu.getRandomString(10);
        apiconsumerLastName = apitdu.randomString;
        apitdu.jsonElement.getAsJsonObject().addProperty("consumerLastName", apitdu.randomString);
        // Adding Consumer phone number randomly
        apitdu.getRandomNumber(10);
        apiphoneNumber = apitdu.randomNumber;
        apitdu.jsonElement.getAsJsonObject().getAsJsonObject("contacts").getAsJsonObject("phone").addProperty("phoneNumber", (apitdu.randomNumber).toString());
        // Adding Consumer address Zip code randomly
        apitdu.getRandomNumber(5);
        apiaddressZip = apitdu.randomNumber;
        apitdu.jsonElement.getAsJsonObject().getAsJsonObject("contacts").getAsJsonObject("address").addProperty("addressZip", (apitdu.randomNumber).toString());
        consumerRequestParams = apitdu.jsonElement.getAsJsonObject();
        System.out.println(consumerRequestParams);
    }

    @When("I can provide same consumer information that is created earlier")
    public void i_can_provide_same_consumer_information_that_is_created_earlier() {
        apitdu.getJsonFromFile("crm/consumer/apiCreateConsumer.json");
        long currentDateTime = System.currentTimeMillis();
        apitdu.jsonElement.getAsJsonObject().addProperty("createdOn", currentDateTime);
        // Adding Consumer First Name
        apitdu.jsonElement.getAsJsonObject().addProperty("consumerFirstName", apiconsumerFirstName);
        // Adding Consumer Last Name
        apitdu.jsonElement.getAsJsonObject().addProperty("consumerLastName", apiconsumerLastName);
        // Adding Consumer phone number
        apitdu.jsonElement.getAsJsonObject().getAsJsonObject("contacts").getAsJsonObject("phone").addProperty("phoneNumber", (apiphoneNumber).toString());
        // Adding Consumer address Zip code
        apitdu.jsonElement.getAsJsonObject().getAsJsonObject("contacts").getAsJsonObject("address").addProperty("addressZip", (apiaddressZip).toString());

        consumerRequestParams = apitdu.jsonElement.getAsJsonObject();
        System.out.println(consumerRequestParams);
    }

    @When("I can provide consumer address State with {string}")
    public void i_can_provide_consumer_address_state_with(String addressState) {
        i_can_provide_consumers_information_randomly();
        // Adding Consumer address state code randomly
        apitdu.jsonElement.getAsJsonObject().getAsJsonObject("contacts").getAsJsonObject("address").addProperty("addressState", (addressState).toString());

        consumerRequestParams = apitdu.jsonElement.getAsJsonObject();
        System.out.println(consumerRequestParams);
    }

    @When("I can search consumer by {string} with value {string}")
    public void i_can_search_consumer_by(String item, String value) {
        consumerRequestParams = new JsonObject();
        // -- Make consumer search by specific property. --
        switch (item) {
            case "consumerDateOfBirth":
                try {
                    consumerRequestParams.addProperty("consumerDateOfBirth", NumberFormat.getInstance().parse(value));
                    break;
                } catch (java.text.ParseException e) {
                    System.out.println("Date of Birth is not string input.");
                }
            case "consumerFirstName":
                consumerRequestParams.addProperty("consumerFirstName", value);
                break;
            case "consumerLastName":
                consumerRequestParams.addProperty("consumerLastName", value);
                break;
            case "consumerMiddleName":
                consumerRequestParams.addProperty("consumerMiddleName", value);
                break;
            case "consumerSSN":
                consumerRequestParams.addProperty("consumerSSN", value);
                break;
            case "consumerIdentificationNo":
                consumerRequestParams.addProperty("consumerIdentificationNo", value);
                break;
        }
        System.out.println(consumerRequestParams);
    }

    @And("I run the consumer search API")
    public void i_run_the_consumer_search_api() {
        System.out.println(consumerRequestParams);
        api.PostAPIWithParameter(consumerRequestParams);
        System.out.println(api.responseString);
        assertEquals(api.statusCode, 200);
        assertTrue(api.responseString.contains("success"));
        assertEquals(api.jsonPathEvaluator.get("status"), "success");
        //System.out.println(api.responseString);
    }

    @And("I can run create consumer API")
    public void i_can_run_create_consumer_api() {
        api.PutAPI(consumerRequestParams);
        System.out.println(api.responseString);
        assertEquals(api.statusCode, 200);
        assertTrue(api.responseString.contains("success"));
        assertEquals(api.jsonPathEvaluator.get("status"), "success");
    }

    @And("I can run try to create duplicate consumer via API")
    public void i_can_try_to_create_duplicate_consumer_api() {
        api.PutAPI(consumerRequestParams);
        System.out.println(api.responseString);
        assertEquals(api.statusCode, 200);
    }

    @Then("I can verify consumer consumerLastName with value {string} on API response")
    public void i_can_verify_the_consumer_last_name_search_api(String value) {
        if (value.equals("{random}")) {
            value = apiconsumerLastName;
        }
        System.out.println("consumerLastName : " + value);
        System.out.println("Initiating Consumer Search API...");
        i_initiated_consumer_search_api();
        System.out.println("Consumer Search API informaiton loading...");
        i_can_search_consumer_by("consumerLastName", value);
        System.out.println("Consumer Search API running...");
        i_run_the_consumer_search_api();
        System.out.println(api.responseString);
        i_can_verify_the_consumer_search_api("consumerLastName", value);
    }

    @And("I get the consumerId from API response")
    public void get_the_consumer_id() {
        List<List> consumerListID = api.jsonPathEvaluator.getList("object.result.consumers.consumerId");
        //System.out.println(consumerListID);
        for (List consumerId : consumerListID) {
            apiconsumerId = consumerId.toString();
            break;
        }

    }

    @Then("I can verify consumer consumerId on API response")
    public void i_can_verify_consumer_unique_id() {
        i_can_verify_the_consumer_search_api("consumerId", "");
    }

    @Then("I can verify consumer {string} with value {string} on API response")
    public void i_can_verify_the_consumer_search_api(String field, String value) {
        Object consumerDoBList = api.jsonPathEvaluator.getList("consumersList.content.consumerDateOfBirth");
        System.out.println(consumerDoBList);
        int consumerListCount = ((List) consumerDoBList).size();
        // System.out.println(consumerListCount);
        assertEquals(consumerListCount, 1);
        // System.out.println(api_step_definitions.responseString);
        switch (field) {
            case "consumerDateOfBirth":
                List<List> consumerListDOB = api.jsonPathEvaluator.getList("consumersList.content.consumerDateOfBirth");
                //System.out.println(consumerListDOB);
                Iterator iterdob = consumerListDOB.iterator();
                Object dob = iterdob.next();
                assertTrue(dob.equals(value));
                break;
            case "consumerFirstName":
                List<List> consumerListFN = api.jsonPathEvaluator.getList("consumersList.content.consumerFirstName");
                //System.out.println(consumerListFN);
                Iterator iterfn = consumerListFN.iterator();
                Object fn = iterfn.next();
                String firstname = (fn.toString()).toLowerCase();
                System.out.println(value.toLowerCase());
                assertTrue(firstname.contains(value.toLowerCase()));
                break;
            case "consumerLastName":
                List<List> consumerListLN = api.jsonPathEvaluator.getList("consumersList.content.consumerLastName");
                Iterator iterln = consumerListLN.iterator();
                Object ln = iterln.next();
                String lastname = (ln.toString()).toLowerCase();
                System.out.println(value.toLowerCase());
                assertTrue(lastname.contains(value.toLowerCase()));
                break;
            case "consumerMiddleName":
                List<List> consumerListMN = api.jsonPathEvaluator.getList("consumersList.content.consumerMiddleName");
                //System.out.println(consumerListMN);
                Iterator itermn = consumerListMN.iterator();
                Object mn = itermn.next();
                String middlename = (mn.toString()).toLowerCase();
                System.out.println(value.toLowerCase());
                assertTrue(middlename.contains(value.toLowerCase()));
                break;
            case "consumerSSN":
                List<List> consumerListSSN = api.jsonPathEvaluator.getList("consumersList.content.consumerSSN");
                //System.out.println(consumerListSSN);
                Iterator iterssn = consumerListSSN.iterator();
                Object ssn = iterssn.next();
                String ssnumber = (ssn.toString()).toLowerCase();
                System.out.println(value.toLowerCase());
                assertEquals(ssnumber, value);
                break;
            case "consumerId":
                List<List> consumerListID = api.jsonPathEvaluator.getList("consumersList.content.consumerId");
                // System.out.println(consumerListID);
                Iterator consumerIdIter = consumerListID.iterator();
                Object consumerIdObj = consumerIdIter.next();
                apiconsumerId = consumerIdObj.toString();
                String consumerId = (consumerIdObj.toString()).toLowerCase();
                System.out.println("consumerId : " + consumerId);
                assertFalse(consumerId.isEmpty());
                break;
        }
    }

    @Given("I created a consumer using API")
    public void i_created_a_consumer_using_api() {
        i_initiated_create_consumer_api();
        i_can_provide_consumers_information_randomly();
        i_can_run_create_consumer_api();
        i_can_verify_the_case_search_api_response();
        get_the_consumer_id_from_response(apiconsumerFirstName);
        get_the_consumer_correlationId_id_from_response(apiconsumerFirstName);
    }

    @Then("I can verify on consumer search API response")
    public void i_can_verify_the_case_search_api_response() {
        i_initiated_consumer_search_api();
        i_can_search_consumer_by("consumerFirstName", apiconsumerFirstName);
        i_run_the_consumer_search_api();
        i_can_verify_the_consumer_search_api("consumerFirstName", apiconsumerFirstName);
    }

    public String get_the_consumer_correlationId_id_from_response(String consumerFirstName) {
        i_initiated_consumer_search_api();
        i_can_search_consumer_by("consumerFirstName", consumerFirstName);
        i_run_the_consumer_search_api();
        List<List> correlationIdList = api.jsonPathEvaluator.getList("consumersList.content.correlationId");
        // System.out.println(correlationIdList);
        Iterator   iterfn = correlationIdList.iterator();
        Object correlationIdObj = iterfn.next();
        correlationId = (correlationIdObj.toString());
        System.out.println(correlationId);
        return correlationId;
    }

    public String get_the_consumer_uiid_from_response(String consumerFirstName) {
        i_can_search_consumer_by("consumerFirstName", consumerFirstName);
        i_run_the_consumer_search_api();
        List<List> uiidList = api.jsonPathEvaluator.getList("consumersList.content.uiid");
        // System.out.println(uiidList);
        Iterator   uiidIter = uiidList.iterator();
        Object uiidObj = uiidIter.next();
        uiid = (uiidObj.toString());
        System.out.println(uiid);
        return uiid;
    }

    public String get_the_consumer_id_from_response(String consumerFirstName) {
        i_can_search_consumer_by("consumerFirstName", consumerFirstName);
        i_run_the_consumer_search_api();
        List<List> consumerIdList = api.jsonPathEvaluator.getList("consumersList.content.consumerId");
        System.out.println(consumerIdList);
        Iterator  iterId = consumerIdList.iterator();
        Object consumerIdObj = iterId.next();
        apiconsumerId = (consumerIdObj.toString());
        System.out.println(apiconsumerId);
        consumerDetail = api.responseString;
        return apiconsumerId;
    }

    @When("I can provide case member information")
    public void i_can_provide_case_member_information_with(DataTable caseMember) {

        for (Map<Object, Object> data : caseMember.asMaps(String.class, String.class)) {
            apitdu.getJsonFromFile("crm/case/apiCreateCaseMember.json");
            HashMap<String, String> memberData = new HashMap<String, String>();
           if( data.containsKey("consumerFirstName")){
                String firstName = data.get("consumerFirstName").toString();
                if (firstName.isEmpty() || firstName.equals("{random}")) {
                    apitdu.getRandomString(10);
                    apiconsumerFirstName = apitdu.randomString;
                    apitdu.jsonElement.getAsJsonObject().addProperty("consumerFirstName", apitdu.randomString);
                } else {
                    apitdu.jsonElement.getAsJsonObject().addProperty("consumerFirstName", firstName);
                }
               memberData.put("consumerFirstName", apiconsumerFirstName);
            }

            if( data.containsKey("consumerLastName")){
                String lastName = data.get("consumerLastName").toString();
                if (lastName.isEmpty() || lastName.equals("{random}")) {
                    apitdu.getRandomString(10);
                    apiconsumerLastName = apitdu.randomString;
                    apitdu.jsonElement.getAsJsonObject().addProperty("consumerLastName", apitdu.randomString);
                } else {
                    apitdu.jsonElement.getAsJsonObject().addProperty("consumerLastName", lastName);
                }
                memberData.put("consumerLastName", apiconsumerLastName);
            }

            if( data.containsKey("consumerDateOfBirth")){
                apitdu.jsonElement.getAsJsonObject().addProperty("consumerDateOfBirth", getDateInMilli(data.get("consumerDateOfBirth").toString()));
            }

            if( data.containsKey("consumerSSN")){
                String ssn = data.get("consumerSSN").toString();
                if(ssn.equals("{random}") || ssn.isEmpty()){
                    apitdu.getRandomNumber(9);
                    apitdu.jsonElement.getAsJsonObject().addProperty("consumerSSN", apitdu.randomNumber);
                }else{
                    apitdu.jsonElement.getAsJsonObject().addProperty("consumerSSN", ssn);
                }
            }

            if( data.containsKey("genderCode")){
                apitdu.jsonElement.getAsJsonObject().addProperty("genderCode", data.get("genderCode").toString());
            }

            if( data.containsKey("effectiveStartDate")){
                apitdu.jsonElement.getAsJsonObject().addProperty("effectiveStartDate", getDateInMilli(data.get("effectiveStartDate").toString()));
            }

            if( data.containsKey("effectiveEndDate")){
                apitdu.jsonElement.getAsJsonObject().addProperty("effectiveEndDate", getDateInMilli(data.get("effectiveEndDate").toString()));
            }

            if( data.containsKey("caseId")){
                apitdu.jsonElement.getAsJsonObject().addProperty("caseId", data.get("caseId").toString());
            }

            if( data.containsKey("consumerType")){
                apitdu.jsonElement.getAsJsonObject().addProperty("consumerType", data.get("consumerType").toString());
            }

            if( data.containsKey("relationShip")){
                apitdu.jsonElement.getAsJsonObject().addProperty("relationShip", data.get("relationShip").toString());
                memberData.put("relationShip", data.get("relationShip").toString());
            }

            if( data.containsKey("consumerStatus")){
                memberData.put("consumerStatus", data.get("consumerStatus").toString());
            }

            caseMemberRequestParams = apitdu.jsonElement.getAsJsonObject();
            System.out.println(caseMemberRequestParams);
            api.PutAPI(caseMemberRequestParams);
            System.out.println(api.responseString);
            assertEquals(api.statusCode, 200);
            caseMembersData.add(memberData);
        }



    }
    @When("I can provide case member information with {string} {string} {string} {string} {string} {string}and {string}")
    public void i_can_provide_case_member_information_with_and(String consumerFirstName, String consumerLastName, String consumerDateOfBirth, String consumerSSN, String genderCode, String effectiveStartDate,String effectiveEndDate) {
        apitdu.getJsonFromFile("crm/case/apiCreateCaseMember.json");
        long currentDateTime = System.currentTimeMillis();

        if (consumerFirstName.isEmpty() || consumerFirstName.equals("{random}")) {
            apitdu.getRandomString(10);
            apiconsumerFirstName = apitdu.randomString;
            apitdu.jsonElement.getAsJsonObject().addProperty("consumerFirstName", apitdu.randomString);
        } else {
            apitdu.jsonElement.getAsJsonObject().addProperty("consumerFirstName", consumerFirstName);
        }

        if (consumerLastName.isEmpty() || consumerLastName.equals("{random}")) {
            apitdu.getRandomString(10);
            apiconsumerLastName = apitdu.randomString;
            apitdu.jsonElement.getAsJsonObject().addProperty("consumerLastName", apitdu.randomString);
        } else {
            apitdu.jsonElement.getAsJsonObject().addProperty("consumerLastName", consumerLastName);
        }

        apitdu.jsonElement.getAsJsonObject().addProperty("consumerDateOfBirth", getDateInMilli(consumerDateOfBirth));
        if(consumerSSN.equals("{random}") || consumerSSN.isEmpty()){
            apitdu.getRandomNumber(9);
            apitdu.jsonElement.getAsJsonObject().addProperty("consumerSSN", apitdu.randomNumber);
        }else{
            apitdu.jsonElement.getAsJsonObject().addProperty("consumerSSN", consumerSSN);
        }

        apitdu.jsonElement.getAsJsonObject().addProperty("genderCode", genderCode);
        apitdu.jsonElement.getAsJsonObject().addProperty("effectiveStartDate", getDateInMilli(effectiveStartDate));
        apitdu.jsonElement.getAsJsonObject().addProperty("effectiveEndDate", getDateInMilli(effectiveEndDate));

        caseMemberRequestParams = apitdu.jsonElement.getAsJsonObject();
        System.out.println(caseMemberRequestParams);
    }

    @And("I can run create case member API")
    public void i_can_run_create_case_member_api() {
        api.PutAPI(caseMemberRequestParams);
        System.out.println(api.responseString);
        assertEquals(api.statusCode, 200);
        assertTrue(api.responseString.contains("success"));
        assertEquals(api.jsonPathEvaluator.get("status"), "success");
    }
    @Then("I can run create case member API and validate the status is success")
    public void i_can_run_create_case_member_API_and_validate_the_status_is_success() {
                    api.PutAPI(caseMemberRequestParams);
            System.out.println(api.responseString);
            assertEquals(api.statusCode, 200);
            assertTrue(api.responseString.contains("success"));
            assertEquals(api.jsonPathEvaluator.get("status"), "success");
    }
    @Then("I can verify case member is created on API response")
    public void i_can_verify_case_member_is_created() {
        api.getAPI();
        ArrayList results = api.jsonPathEvaluator.get("result");
        System.out.println(results);
        System.out.println("========");
        HashMap consumerType = (HashMap) results.get(1);
        ArrayList consumers = (ArrayList) consumerType.get("consumers");

        boolean memberFound = false;
        if (consumers.size() > 1) {
            for (int i = 0; i < consumers.size(); i++) {
                HashMap consumer = (HashMap) consumers.get(i);
                if(consumer.get("consumerFirstName").equals(apiconsumerFirstName)
                && consumer.get("consumerLastName").equals(apiconsumerLastName)){
                    memberFound = true;
                    break;
                }
            }
        }

        assertTrue(memberFound);
    }

    @Then("I verify the case member details using API")
    public void i_can__verify_case_member_details(DataTable dataTable) {
        api.getAPI();
        System.out.println(api.responseString);
        ArrayList results = api.jsonPathEvaluator.get("result");

        for (Map<Object, Object> data : dataTable.asMaps(String.class, String.class)) {
            HashMap consumerType = (HashMap) results.get(1);
            for(int i=0;i<results.size();i++){
                consumerType = (HashMap) results.get(i);
                if(consumerType.get("consumerType").toString().equalsIgnoreCase(data.get("consumerType").toString()))
                    break;
            }

            String elementToBeValidated = "";
            if(data.containsKey("relationShip")){
                elementToBeValidated = "relationShip";
            }else if(data.containsKey("consumerStatus")){
                elementToBeValidated = "consumerStatus";
            }
            String expValue = data.get(elementToBeValidated).toString();
            ArrayList consumers = (ArrayList) consumerType.get("consumers");

            boolean memberFound = false;
            if (consumers.size() > 1) {

                for(HashMap<String, String> member:caseMembersData){
                    for (int i = 0; i < consumers.size(); i++) {
                        HashMap consumer = (HashMap) consumers.get(i);
                        if(consumer.get("consumerFirstName").toString().equalsIgnoreCase((member.get("consumerFirstName")))
                                && consumer.get("consumerLastName").toString().equalsIgnoreCase((member.get("consumerLastName")))
                                && consumer.get(elementToBeValidated).toString().equalsIgnoreCase((member.get(elementToBeValidated)))){
                            memberFound = true;
                            break;
                        }
                    }
                }
            }
            assertTrue(memberFound, "member not found for the "+elementToBeValidated+": "+expValue);
        }

    }

    /**
     * <Description> This method valdate the order of status for given consumer type</>
     * @param consumerType
     */
    @Then("I verify active case members fetched followed by inactive for consumerType {string} using API")
    public void i_can__verify_case_member_order(String consumerType) {
        api.getAPI();
        System.out.println(api.responseString);
        ArrayList results = api.jsonPathEvaluator.get("result");

        HashMap dataConsumerType = (HashMap) results.get(0);
        for(int i=0;i<results.size();i++){
            dataConsumerType = (HashMap) results.get(i);
            if(dataConsumerType.get("consumerType").toString().equalsIgnoreCase(consumerType))
                break;
        }
        ArrayList consumers = (ArrayList) dataConsumerType.get("consumers");

        boolean flag = true;
        boolean isInactive = false;
        if (consumers.size() > 1) {
            for (int i = 0; i < consumers.size(); i++) {
                HashMap consumer = (HashMap) consumers.get(i);
                if(consumer.get("consumerStatus").toString().equalsIgnoreCase("Active")){
                    if(isInactive){
                        flag = false;
                        break;
                    }
                }else{
                    isInactive = true;
                }
            }
        }
        assertTrue(flag, "Records not fetched Active first followed by inactive");

    }

    @Then("I verify the case member details not fetched using API")
    public void i_can__verify_case_member_details_not_fetched(DataTable dataTable) {
        api.getAPI();
        System.out.println(api.responseString);
        ArrayList results = api.jsonPathEvaluator.get("result");

        for (Map<Object, Object> data : dataTable.asMaps(String.class, String.class)) {
            HashMap consumerType = (HashMap) results.get(1);
            for(int i=0;i<results.size();i++){
                consumerType = (HashMap) results.get(i);
                if(consumerType.get("consumerType").toString().equalsIgnoreCase(data.get("consumerType").toString()))
                    break;
            }

            String elementToBeValidated = "";
            if(data.containsKey("relationShip")){
                elementToBeValidated = "relationShip";
            }else if(data.containsKey("consumerStatus")){
                elementToBeValidated = "consumerStatus";
            }
            String expValue = data.get(elementToBeValidated).toString();
            ArrayList consumers = (ArrayList) consumerType.get("consumers");

            boolean memberFound = false;
            if (consumers.size() > 1) {

                for(HashMap<String, String> member:caseMembersData){
                    for (int i = 0; i < consumers.size(); i++) {
                        HashMap consumer = (HashMap) consumers.get(i);
                        if(consumer.get("consumerFirstName").toString().equalsIgnoreCase((member.get("consumerFirstName")))
                                && consumer.get("consumerLastName").toString().equalsIgnoreCase((member.get("consumerLastName")))
                                && consumer.get(elementToBeValidated).toString().equalsIgnoreCase((member.get(elementToBeValidated)))){
                            memberFound = true;
                            break;
                        }
                    }
                }
            }
            System.out.println(memberFound);
            System.out.println("member found for the "+elementToBeValidated+": "+expValue);
            assertTrue(memberFound, "member found for the "+elementToBeValidated+": "+expValue);
        }

    }

    /**
     * <Description> This method valdate the order of status for given consumer type</>
     * @param consumerType
     */
    @Then("I verify case members fetched in the order of their start date for consumerType {string} using API")
    public void i_can__verify_case_member_order_latest_start_date_first(String consumerType) {
        api.getAPI();
        System.out.println(api.responseString);
        ArrayList results = api.jsonPathEvaluator.get("result");

        HashMap dataConsumerType = (HashMap) results.get(0);
        for(int i=0;i<results.size();i++){
            dataConsumerType = (HashMap) results.get(i);
            if(dataConsumerType.get("consumerType").toString().equalsIgnoreCase(consumerType))
                break;
        }
        ArrayList consumers = (ArrayList) dataConsumerType.get("consumers");

        boolean flag = true;
        long previousCaseMemberStartDate = 0;
        if (consumers.size() > 1) {
            for (int i = 0; i < consumers.size(); i++) {
                HashMap consumer = (HashMap) consumers.get(i);

                System.out.println(consumer.get("effectiveStartDate").toString());
                if(i==0){
                    previousCaseMemberStartDate = Long.parseLong(consumer.get("effectiveStartDate").toString());
                    continue;
                }
                long presentCaseMemberStartDate = Long.parseLong(consumer.get("effectiveStartDate").toString());
                if(presentCaseMemberStartDate<=previousCaseMemberStartDate){
                    flag = true;
                    previousCaseMemberStartDate = presentCaseMemberStartDate;
                }else{
                    flag = false;
                    break;
                }
            }
        }
        assertTrue(flag, "Case member records not fetched in the order of their start date");

    }
    /**
     * <Description> returns date in milli with added/subtracted number of days to current date</>
     * @param date
     * @return
     */
    private long getDateInMilli(String date){
        long dateValueToReturn = 0;
        int days = 0;
        boolean isDateFuture = false;
        if(date.contains("+")){
            days = Integer.parseInt(date.split("\\+")[1]);
            date = date.split("\\+")[0];
            isDateFuture = true;
        }else if(date.contains("-")){
            days = Integer.parseInt(date.split("-")[1]);
            date = date.split("-")[0];
        }

        if(isDateFuture){
            dateValueToReturn = futureDates(days);
        }else{
            dateValueToReturn = pastDates(days);
        }

        return dateValueToReturn;
    }

    public static long pastDates(int i) {
        Date myDate = new Date(System.currentTimeMillis());
        Calendar cal = Calendar.getInstance();
        cal.setTime(myDate);
        System.out.print(cal.getTime()+" ==> ");
        cal.add(Calendar.DATE, -i);
        System.out.print(cal.getTime()+" ==> ");
        long temp = cal.getTimeInMillis();
        return temp;
    }

    public static long futureDates(int i) {
        Date myDate = new Date(System.currentTimeMillis());
        Calendar cal = Calendar.getInstance();
        cal.setTime(myDate);
        System.out.print(cal.getTime()+" ==> ");
        cal.add(Calendar.DATE,+i);
        System.out.print(cal.getTime()+" ==> ");
        long temp = cal.getTimeInMillis();
        return temp;
    }

}