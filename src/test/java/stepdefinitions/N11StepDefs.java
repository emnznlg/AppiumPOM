package stepdefinitions;

import io.cucumber.java.en.*;
import pages.N11Page;
import utilities.Driver;

public class N11StepDefs {

    N11Page n11 = new N11Page();

    @When("Kategorilere tiklanir")
    public void kategorilere_tiklanir() {
        n11.kategorilerBtn.click();
        n11.sepetimBtn.click();
    }

}
