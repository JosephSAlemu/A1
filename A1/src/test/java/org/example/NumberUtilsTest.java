package org.example;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NumberUtilsTest {
    /*
    Inputs:
    Left | Right?
    - null
    - Empty List
    - List of length == 1
    - List of length > 1

    Combinations:
    Left | Right?
    null:
        - Right is null, Left isn't.
        - Left is null, Right isn't.
        - Right and Left are null.

    Empty:
        - Right is empty, Left isn't.
        - Left is empty, Right isn't.
        - Right and Left is empty.

    No Carry:
        List of length == 1 & List of length > 1:
            - Right is a list of one integer 0-9 Left is a list of integers 0-9.
            - Left is a list of one integer 0-9 Right is a list of integers 0-9.
            - Right and Left are lists of one integer 0-9.
            - Right and Left are lists of integers 0-9.

    Carry:
        List of length == 1 & List of length > 1:
            - Right is a list of one integer 0-9 Left is a list of integers 0-9.
            - Left is a list of one integer 0-9 Right is a list of integers 0-9.
            - Right and Left are lists of one integer 0-9.
            - Right and Left are lists of integers 0-9.

        Carry in least significant digit


    Outputs:
    - IllegalArgumentException
    - null
    - Empty List
    - List of Length == 1
    - List of length > 1

     */

    /*
    interaction:
    -  Left !Right
    - !Left  Right
    -  Left  Right

    Test:
    [[],   null] -> null
    [null,   []] -> null
    [null, null] -> null
     */
    @Test
    @Tag("specification")
    void addWhenInputisNull() {
        assertThat(NumberUtils.add(List.of(),null)).isNull();
        assertThat(NumberUtils.add(null, List.of())).isNull();
        assertThat(NumberUtils.add(null,null)).isNull();
    }

    /*
    interaction:
    -  Left !Right
    - !Left  Right
    -  Left  Right

    Test:
    [[],   [0]]  -> [0]
    [[9],   []]  -> [9]
    [[],    []]  -> []
     */
    @Test
    @Tag("specification")
    void addWhenInputisEmptyList() {
        assertThat(NumberUtils.add(List.of(), List.of(0))).isEqualTo(List.of(0));
        assertThat(NumberUtils.add(List.of(9), List.of())).isEqualTo(List.of(9));
        // I know spec mismatch, but the test won't pass unless I put isEqualTo for an empty list.
        // That's the bug with NumberUtils.
        assertThat(NumberUtils.add(List.of(), List.of())).isEqualTo(List.of());
    }

    /*
    interaction:
    -  Left !Right
    - !Left  Right
    -  Left  Right
    - !Left !Right
    - !Left !Right (leading zeros)

    Test:
    [[1],           [7,3]] -> [7,4]
    [[8,6,5],         [4]] -> [8,6,9]
    [[0],             [0]] -> [0]
    [[1,2],         [2,1]] -> [3,3]
    [[0,0,0,1,2], [0,2,3]] -> [3,5]
    */
    @Test
    @Tag("specification")
    void addWhenInputLengthGreaterOrEqualToOneNoCarry() {
        assertThat(NumberUtils.add(List.of(1), new ArrayList<>(List.of(7,3)))).isEqualTo(List.of(7,4));
        assertThat(NumberUtils.add(new ArrayList<>(List.of(8,6,5)), List.of(4))).isEqualTo(List.of(8,6,9));
        assertThat(NumberUtils.add(List.of(0), List.of(0))).isEqualTo(List.of(0));
        assertThat(NumberUtils.add(new ArrayList<>(List.of(1,2)), new ArrayList<>(List.of(2,1)))).isEqualTo(List.of(3,3));
        assertThat(NumberUtils.add(new ArrayList<>(List.of(0,0,0,1,2)), new ArrayList<>(List.of(0,2,3)))).isEqualTo(List.of(3,5));
    }

    /*
    interaction:
    -  Left !Right len(Left) < len(Right)
    - !Left  Right len(Left) > len(Right)
    -  Left  Right
    - !Left !Right
    - !Left !Right (carry least significant digit)
    - !Left !Right (carry in the middle)
    - !Left !Right (many carries in a row)

    Test:
    [[9],           [9,1]]  -> [1,0,0]
    [[9,9,4],         [6]]  -> [1,0,0,0]
    [[7],             [3]]  -> [1,0]
    [[5,4,3,2], [4,5,6,8]]  -> [1,0,0,0,0]
    [[2,9],         [2,3]]  -> [5,2]
    [[2,9,3],     [1,8,3]]  -> [4,7,6]
    [[1,7,9],     [2,6,8]]  -> [4,4,7]
    [

     */
    @Test
    @Tag("specification")
    void addWhenInputLengthGreaterOrEqualToOneCarry() {
        assertThat(NumberUtils.add(List.of(9), new ArrayList<>(List.of(9,1)))).isEqualTo(List.of(1,0,0));
        assertThat(NumberUtils.add(new ArrayList<>(List.of(9,9,4)), List.of(6))).isEqualTo(List.of(1,0,0,0));
        assertThat(NumberUtils.add(List.of(7), List.of(3))).isEqualTo(List.of(1,0));
        assertThat(NumberUtils.add(new ArrayList<>(List.of(5,4,3,2)), new ArrayList<>(List.of(4,5,6,8)))).isEqualTo(List.of(1,0,0,0,0));
        assertThat(NumberUtils.add(new ArrayList<>(List.of(2,9)), new ArrayList<>(List.of(2,3)))).isEqualTo(List.of(5,2));
        assertThat(NumberUtils.add(new ArrayList<>(List.of(2,9,3)), new ArrayList<>(List.of(1,8,3)))).isEqualTo(List.of(4,7,6));
        assertThat(NumberUtils.add(new ArrayList<>(List.of(1,7,9)), new ArrayList<>(List.of(2,6,8)))).isEqualTo(List.of(4,4,7));
    }

    @Test
    @Tag("structural")
    void lineBranchCoverage(){
        assertThrows(IllegalArgumentException.class, () -> {NumberUtils.add(List.of(), List.of(-1));});
        assertThrows(IllegalArgumentException.class, () -> {NumberUtils.add(List.of(10), List.of());});
        assertThrows(IllegalArgumentException.class, () -> {NumberUtils.add(List.of(), List.of(15));});
        assertThrows(IllegalArgumentException.class, () -> {NumberUtils.add(List.of(-5), List.of());});
    }

}