package ru.job4j.sort;

import java.util.*;

/**
 * Class DepartmentSort.
 * @author  shustovakv
 * @since 26.05.2018
 */
public class DepartmentSort {

    /**
     * Field unsorted codes.
     */
    private Set<Org> unsortedCodes = new HashSet<>();

    /**
     * Constructor
     * @param departments list of incomplete departments.
     */
    public DepartmentSort(String[] departments) {
        for (String departmentCode : departments) {
            parse(departmentCode);
        }
    }

    static class Org {

        private List<String> codes;

        public Org(List<String> codes) {
            this.codes = new ArrayList<>(codes);
        }

        @Override
        public String toString() {
            StringJoiner stringJoiner = new StringJoiner("/");
            for (String code : this.codes) {
                stringJoiner.add(code);
            }
            return stringJoiner.toString();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Org org = (Org) o;
            return Objects.equals(codes, org.codes);
        }

        @Override
        public int hashCode() {

            return Objects.hash(codes);
        }
    }

    /**
     * Method parse
     * Разделяет строку на отдельные коды департаментов,
     * затем соединяет с учетом вышестоящих департаментов.
     * @param departmentCode коды департаментов.
     */
    private  void parse(String departmentCode) {
        String[] splitCode = departmentCode.split("/");
        List<String> codeOrg = new ArrayList<>();
        for (String code : splitCode) {
            codeOrg.add(code);
            this.unsortedCodes.add(new Org(codeOrg));
        }
    }

    /**
     * Method sort.
     * @return result
     */
    private String[] sort(Comparator<Org> order) {
        List<Org> departments = new ArrayList<>(this.unsortedCodes);
        departments.sort(order);
        String[] result = new String[departments.size()];
        Iterator<Org> it = departments.iterator();
        for (int i = 0; i < result.length; i++) {
            result[i] = it.next().toString();
        }
        return result;
    }

    /**
     * Передает в метод sort компаратор для сортировки кодов по порядку
     * @return comparator ascend to sort
     */
    public String[] departmentSortAscend() {

        Comparator<Org> comparator = (o1, o2) -> {
            int result = 0;
            Iterator<String> it1 = o1.codes.iterator();
            Iterator<String> it2 = o2.codes.iterator();

            while (it1.hasNext() && it2.hasNext() && result == 0) {
                result = it1.next().compareTo(it2.next());
            }
            if (result == 0) {
                result = Integer.compare(o1.codes.size(), o2.codes.size());
            }
            return result;
        };
        return sort(comparator);
    }

    /**
     * Передает в метод sort компаратор для сортировки кодов в порядке убывания
     * @return comparator descend to sort
     */
    public String[] departmentSortDescend() {

        Comparator<Org> comparator = (o1, o2) -> {
            Iterator<String> it1 = o1.codes.iterator();
            Iterator<String> it2 = o2.codes.iterator();
            int result = 0;
            while (it1.hasNext() && it2.hasNext() && result == 0) {
                result = -1 * it1.next().compareTo(it2.next());
            }
            if (result == 0) {
                result = Integer.compare(o1.codes.size(), o2.codes.size());
            }
            return result;
        };
        return sort(comparator);
    }
}