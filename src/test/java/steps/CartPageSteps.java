package steps;

import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import pages.invitro.AnalyzesForDoctorsPage;
import pages.lk3_invitro.CartPage;

import static io.qameta.allure.Allure.label;
import static io.qameta.allure.Allure.step;

public class CartPageSteps {
    private final ThreadLocal<CartPage> cartPage = ThreadLocal.withInitial(CartPage::new);

    @Then("цена в корзине должна совпадать с сохраненной ценой")
    public void theCartPriceShouldBeComparedWithTheSavedPrice() {
        label("owner", "A.Alekseev");
        String cartPrice = cartPage.get().getCartPrice();
        int cartPriceValue = Integer.parseInt(cartPrice.replaceAll("[^0-9]", ""));
        int savedPriceValue = Integer.parseInt(AnalyzesForDoctorsPage.getSavedPrice().replaceAll("[^0-9]", ""));
        step("Cart Price Value = " + cartPriceValue + " compare to Saved Price Value = " + savedPriceValue, () -> {
            if (savedPriceValue == 10000) {
                throw new AssertionError("Price is exactly 10000");
            } else if (savedPriceValue > 10000) {
                System.out.println("Saved price is greater than 10000");
            } else {
                System.out.println("Saved price is less than 10000");
            }
        Assertions.assertEquals(savedPriceValue, cartPriceValue);
        });

    }
}

