package pl.rafal.testing.account;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThan;

class AddressTest {

     @ParameterizedTest
     @CsvSource({"Fabryczna,10","Armia,15","'Romka,Atomka',16"})
     void givenAddressesShouldNotBeEmptyAndHavePropperNames(String street, String number){
            assertThat(street, notNullValue());
            assertThat(street, containsString("a"));
            assertThat(number, notNullValue());
            assertThat(number.length(), lessThan(8));
        }

    @ParameterizedTest
    @CsvFileSource(resources = "/addresses.csv")
    void givenAddressesFromCsvFileShouldNotBeEmptyAndHavePropperNames(String street, String number){
        assertThat(street, notNullValue());
        assertThat(street, containsString("a"));
        assertThat(number, notNullValue());
        assertThat(number.length(), lessThan(8));
    }
}