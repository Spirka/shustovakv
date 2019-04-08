package ru.job4j.map;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class Profiles.
 *
 * @author shustovakv
 * @since 01.04.2019
 */
public class Profiles {

    public List<Address> collect(List<Profile> profiles) {
        return profiles.stream()
                .map(Profile::address)
                .sorted((Comparator.comparing(Address::city)))
                .distinct()
                .collect(Collectors.toList());
    }
}
