package ru.job4j.map;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ProfilesTest {

    @Test
    public void whenProfileListConvertedToAddressList() {
        List<Profile> profiles = Arrays.asList(
                new Profile(new Address("Saint Petersburg", "Decabristov", 15, 1)),
                new Profile(new Address("Moskow", "Lenina", 25, 15)),
                new Profile(new Address("Tihvin", "Lesnaya", 10, 205)));
        List<Address> expected = Arrays.asList(
                new Address("Moskow", "Lenina", 25, 15),
                new Address("Saint Petersburg", "Decabristov", 15, 1),
                new Address("Tihvin", "Lesnaya", 10, 205));
        testProfiles(profiles, expected);
    }

    @Test
    public void whenAddressListOfContainsDuplicates() {
        List<Profile> profiles = Arrays.asList(
                new Profile(new Address("Saint Petersburg", "Decabristov", 15, 1)),
                new Profile(new Address("Moskow", "Lenina", 25, 15)),
                new Profile(new Address("Moskow", "Lenina", 25, 15)));
        List<Address> expected = Arrays.asList(
                new Address("Moskow", "Lenina", 25, 15),
                new Address("Saint Petersburg", "Decabristov", 15, 1));
        testProfiles(profiles, expected);
    }

    @Test
    public void whenAddressListIsSortedByCity() {
        List<Profile> profiles = Arrays.asList(
                new Profile(new Address("Saint Petersburg", "Decabristov", 15, 1)),
                new Profile(new Address("Arkhangelsk", "Lenina", 25, 15)),
                new Profile(new Address("Novosibirsk", "Popova", 25, 15)));
        List<Address> expected = Arrays.asList(
                new Address("Arkhangelsk", "Lenina", 25, 15),
                new Address("Novosibirsk", "Popova", 25, 15),
                new Address("Saint Petersburg", "Decabristov", 15, 1));
        testProfiles(profiles, expected);
    }

    public void testProfiles(List<Profile> profiles, List<Address> expected) {
        Profiles addressBook = new Profiles();
        List<Address> addressList = addressBook.collect(profiles);
        assertThat(addressList, is(expected));
    }
}