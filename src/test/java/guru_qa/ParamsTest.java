package guru_qa;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;


public class ParamsTest {


//    @ValueSource(strings = {"JUnit 5", "TestNG"})
//    @ParameterizedTest (name ="При поиске в яндексе по запросу {TEST DATA} в результатах отображается текст  ")
//    void yaTestCommon(String testData){
//        Selenide.open("https://ya.ru");
//        $("#text").setValue(testData);
//        $("button[type='submit']").click();
//        $$("li.serp-item").find(text(testData)).shouldBe(visible);
//
//    }

    @CsvSource(value = {
            "JUnit 5, team's statement on the war Ukraine ",
            "TestNG, is a testing framework "

    })
    @ParameterizedTest (name ="При поиске в яндексе по запросу {0} в результатах отображается текст {1}")
    void yaTestComplex(String searchData, String  expectedResult){
        Selenide.open("https://ya.ru");
        $("#text").setValue(searchData);
        $("button[type='submit']").click();
        $$("li.serp-item").find(text(expectedResult)).shouldBe(visible);

    }


    static Stream<Arguments> yaTestVeryComplexDataProvider(){
        return Stream.of(
                Arguments.of("JUnit 5", List.of("JUnit 5", "framework")),
                Arguments.of("TestNG", List.of("JUnit 5", "framework"))
        );


    }
    @MethodSource(value ="yaTestVeryComplexDataProvider")

    @ParameterizedTest (name ="При поиске в яндексе по запросу {0} в результатах отображается текст {1}")
    void yaTestVeryComplex(String searchData, List <String>  expectedResult){
        Selenide.open("https://ya.ru");
        $("#text").setValue(searchData);
        $("button[type='submit']").click();
        $$("li.serp-item").shouldHave(CollectionCondition.texts(expectedResult));

    }



}
