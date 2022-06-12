package guru_qa;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;


public class WildberrisTests {


   @ValueSource(strings = {"футболка женская", "платье летнее женское"})
    @ParameterizedTest (name ="При поиске на вайлдберис по запросу {0} в результатах отображается{0}}")
    void wildberrisTest(String testData){
        Selenide.open("https://www.wildberries.ru/");
        $("#searchInput").setValue(testData);
        $("#applySearchBtn").click();
        $$(".searching-results__title").find(text(testData)).shouldBe(visible);

    }

    @CsvSource(value = {
            "футболка женская, футболка",
            "платье летнее женское, платье"

    })
    @ParameterizedTest (name ="При поиске на вайлдберис по запросу {0} в результатах отображается{0}}")
    void  wildberrisTest1(String searchData, String  expectedResult){
        Selenide.open("https://www.wildberries.ru/");
        $("#searchInput").setValue(searchData);
        $("#applySearchBtn").click();
        $$(".searching-results__title").find(text(expectedResult)).shouldBe(visible);

    }


    static Stream<Arguments> wildberrisTest2(){
        return Stream.of(
                Arguments.of("футболка женская", List.of("футболка")),
                Arguments.of("платье летнее женское", List.of(" платье"))
        );


    }
    @MethodSource(value ="wildberrisTest2")

    @ParameterizedTest (name ="При поиске в яндексе по запросу {0} в результатах отображается текст {1}")
    void wildberrisTest2(String searchData, List <String>  expectedResult){
        Selenide.open("https://www.wildberries.ru/");
        $("#searchInput").setValue(searchData);
        $("#applySearchBtn").click();
        $$(".searching-results__title").shouldHave(CollectionCondition.texts(expectedResult));

    }

@EnumSource(Phone.class)
    @ParameterizedTest
    void wildberrisEnumTest(Phone phone){
        Selenide.open("https://www.wildberries.ru/");
        $("#searchInput").setValue(phone.desc);
        $("#applySearchBtn").click();
        $$("#mainContainer").find(text(phone.desc)).shouldBe(visible);

    }



}
