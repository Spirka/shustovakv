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
    private Set<String> unsortedCodes = new HashSet<>();

    /**
     * Constructor
     * @param departments list of incomplete departments.
     */
    public DepartmentSort(String[] departments) {
        for (String departmentCode : departments) {
            parse(departmentCode);
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
        StringJoiner stringJoiner = new StringJoiner("/");
        for (String code : splitCode) {
            stringJoiner.add(code);
            this.unsortedCodes.add(stringJoiner.toString());
        }

    }

    /**
     * Method sort.
     * @param order порядок сортировки.
     * @return result
     */
    private String[] sort(Comparator<String> order) {
        List<String> departmentSorted = new ArrayList<>(this.unsortedCodes);
        departmentSorted.sort(order);
        String[] result = new String[departmentSorted.size()];
        departmentSorted.toArray(result);
        return result;
    }

    /**
     * Method department sort ascend.
     * Передает в метод sort порядок сортировки по возрастанию.
     * @return отсортированный по возрастанию список департаментов.
     */
    public String[] departmentSortAscend() {
        Comparator<String> comparator = Comparator.naturalOrder();
        return sort(comparator);
    }

    /**
     * Method department sort descend.
     * ередает в метод sort порядок сортировки по убыванию.
     * @return отсортированный по убыванию список департаментов.
     */
    public String[] departmentSortDescend() {
        Comparator<String> comparator = (o1, o2) -> {
            if (o1.compareTo(o2) == 0) {
                return 0;
            }
            char[] array1 = o1.toCharArray();
            char[] array2 = o2.toCharArray();
            for (int i = 0; i < array1.length; i++) {
                if (i >= array2.length) {
                    return 1;
                }
                int indexCompare = Character.compare(array1[i], array2[i]);
                if (indexCompare != 0) {
                    return indexCompare * -1;
                }
            }
            return array2.length > array1.length ? -1 : 1;
        };
        return sort(comparator);
    }
}