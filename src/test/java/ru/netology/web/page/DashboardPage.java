package ru.netology.web.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";
    private final SelenideElement heading = $("[data-test-id=dashboard]");
    private final ElementsCollection cards = $$(".list__item div");

    public void verifyIsDashboardPage() {
        heading.shouldBe(visible);
    }

    public int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }
    public int getFirstCardBalance() {
        val text = cards.first().text();
        int balance = extractBalance(text);
        return balance;
    }
    public int getSecondCardBalance() {
        val text = cards.get(1).text();
        int balance = extractBalance(text);
        return balance;
    }
    public TransferPage depositFirstCard() {
        $$("[data-test-id=action-deposit]").get(0).click();
        return new TransferPage();
    }
    public TransferPage depositSecondCard() {
        $$("[data-test-id=action-deposit]").get(1).click();
        return new TransferPage();
    }
}
