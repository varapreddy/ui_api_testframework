package com.maximus.crm.utilities;
    /*  (PLEASE DONOT REMOVE THESE LINES. These are advance level random data solution for Cucumber)
        ${} – everything inside will be parsed, strings are comma separated
        numerical value – create random alphanumeric string
        ! – use the string as it is
        N – random numbers
        S – random alphanumeric uppercase string
        s – random alphanumeric lowercase string

        So ${!0046,N8} will produce ‘0046’ followed by 8 random numbers. ${!test,4,@,8,!.com} will generate ‘test’ followed by 4 random alphanumeric letters, symbol ‘@’, 8 random alphanumeric letters and ‘.com’.
        Implementation will be like this:
        Given I create new user named "user_1" with the following data:
        |first_name |last_name |mobile      |email               |user_name  |pass     |
        |${6}       |${8}      |${!0046,N8} |${!test,4,@,8,!.com}|${!test,8} |Success  |

        Step Definition:
            temp_hash = {}
            data_table.hashes.each do |hash|
            temp_hash["first_name"] = parse_input(hash["first_name"])
            # Continue storing rest of data to temp_hash
            # ...
     */
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import com.google.gson.*;
import org.apache.commons.lang.RandomStringUtils;

public class ApiTestDataUtil {

    public JsonElement rootElement;
    public JsonElement jsonElement;
    public List states;
    public String state;
    public String randomString;
    public String randomNumber;
    public String randomListValue;


    public ApiTestDataUtil getRandomAlphaNumeric(int stringLength){
        boolean useLetters = true;
        boolean useNumbers = true;
        this.randomString = RandomStringUtils.random(stringLength, useLetters, useNumbers);
        return this;
    }

    public ApiTestDataUtil getRandomString(int stringLength){
        boolean useLetters = true;
        boolean useNumbers = false;
        this.randomString = RandomStringUtils.random(stringLength, useLetters, useNumbers);
        return this;
    }

    public ApiTestDataUtil getRandomNumber(int numberLength){
        boolean useLetters = false;
        boolean useNumbers = true;
        this.randomNumber = RandomStringUtils.random(numberLength, useLetters, useNumbers);
        return this;
    }

    public ApiTestDataUtil getJsonFromFile(String json_file_name) {
        JsonParser parser = new JsonParser();
        json_file_name = "testData/api/"+json_file_name;
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(json_file_name);
        Reader reader = new InputStreamReader(inputStream);
        this.rootElement = parser.parse(reader);
        this.jsonElement = rootElement.getAsJsonObject();
        return this;
    }

    public ApiTestDataUtil getArandomUSState(){
        String states = "AK,AL,AR,AS,AZ,CA,CO,CT,DC,DE,FL,FM,GA,GU,HI,IA,ID,IL,IN,KS,KY,LA,MA,MD,ME,MH,MI,MN,MO,MP,MS,MT,NC,ND,NE,NH,NJ,NM,NV,NY,OH,OK,OR,PA,PR,PW,RI,SC,SD,TN,TX,UT,VA,VI,VT,WA,WI,WV,WY";
        this.states = new ArrayList<String>(Arrays.asList(states.split(",")));
        Random randomizer = new Random();
        this.state = this.states.get(randomizer.nextInt(this.states.size())).toString();
        return this;
    }

    public ApiTestDataUtil getArandomUSStateName(){
        String states = "Alabama,Alaska,Arizona,Arkansas,California,Colorado,Connecticut,Delaware,Florida,Georgia,Hawaii,Idaho,Illinois,Indiana,Iowa,Kansas,Kentucky,Louisiana,Maine,Maryland,Massachusetts,Michigan,Minnesota,Mississippi,Missouri,Montana,Nebraska,Nevada,New Hampshire,New Jersey,New Mexico,New York,North Carolina,North Dakota,Ohio,Oklahoma,Oregon,Pennsylvania,Rhode Island,South Carolina,South Dakota,Tennessee,Texas,Utah,Vermont,Virginia,Washington,West Virginia,Wisconsin,Wyoming";
        this.states = new ArrayList<String>(Arrays.asList(states.split(",")));
        Random randomizer = new Random();
        this.state = this.states.get(randomizer.nextInt(this.states.size())).toString();
        return this;
    }

    public ApiTestDataUtil getARandomList(String stringList, Boolean isRandom, String expectedString){
        String provisioningString = "Active,Inactive,Pending";
        ArrayList aList = new ArrayList<String>(Arrays.asList(stringList.split(",")));
        if (isRandom) {
            Random randomizer = new Random();
            this.randomListValue = aList.get(randomizer.nextInt(aList.size())).toString();
        }else if(expectedString != null && !expectedString.isEmpty()){
            this.randomListValue = expectedString;
        } else {
            this.randomListValue = aList.get(0).toString();
        }
        return this;
    }

    public ApiTestDataUtil addProperty(JsonObject requestParams, String item, String value){
        requestParams.addProperty(item, value);
        return this;
    }
}

