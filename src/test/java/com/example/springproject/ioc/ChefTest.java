package com.example.springproject.ioc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ChefTest {

    @Autowired
    IngredientFactory ingredientFactory;

    @Autowired
    Chef chef;

    @Test
    void 돈까스_요리하기() {
        String menu = "돈까스";

        String food = chef.cook(menu);

        String expected = "한돈 등심으로 만든 돈까스";

        assertEquals(expected, food);
        System.out.println(food);
    }

    @Test
    void 스테이크_요리하기() {
        String menu = "스테이크";

        String food = chef.cook(menu);

        String expected = "한우 꽃등심으로 만든 스테이크";

        assertEquals(expected, food);
        System.out.println(food);
    }

    @Test
    void 크리스피_치킨_요리하기() {
        String menu = "크리스피 치킨";

        String food = chef.cook(menu);

        String expected = "국내산 10호 닭으로 만든 크리스피 치킨";

        assertEquals(expected, food);
        System.out.println(food);
    }
}
