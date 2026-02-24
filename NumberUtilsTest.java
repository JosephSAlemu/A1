package org.example;
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
    [[],  null]  -> null
    [null,  []]  -> null
    [null, null] -> null
     */
    @Test
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
    void addWhenInputisEmptyList() {
        assertThat(NumberUtils.add(List.of(), List.of(0))).isEqualTo(List.of(0));
        assertThat(NumberUtils.add(List.of(9), List.of())).isEqualTo(List.of(9));
        assertThat(NumberUtils.add(List.of(), List.of())).isEqualTo(List.of());
    }

    /*
    interaction:
    -  Left !Right
    - !Left  Right
    -  Left  Right
    - !Left !Right


    Test:
    [[1],   [7,3]]  -> [7,4]
    [[8,6,5], [4]]  -> [8,6,9]
    [[0],     [0]]  -> [0]
    [[1,2], [2,1]]  -> [3,3]
    */
    @Test
    void addWhenInputLengthGreaterOrEqualToOneNoCarry() {
        assertThat(NumberUtils.add(List.of(1), new ArrayList<>(List.of(7,3)))).isEqualTo(List.of(7,4));
        assertThat(NumberUtils.add(new ArrayList<>(List.of(8,6,5)), List.of(4))).isEqualTo(List.of(8,6,9));
        assertThat(NumberUtils.add(List.of(0), List.of(0))).isEqualTo(List.of(0));
        assertThat(NumberUtils.add(new ArrayList<>(List.of(1,2)), new ArrayList<>(List.of(2,1)))).isEqualTo(List.of(3,3));
    }

    /*
    interaction:
    -  Left !Right
    - !Left  Right
    -  Left  Right
    - !Left !Right

    Test:
    [[9],           [9,1]]  -> [1,0,0]
    [[9,9,4],         [6]]  -> [1,0,0,0]
    [[7],             [3]]  -> [1,0]
    [[5,4,3,2], [4,5,6,8]]  -> [1,0,0,0,0]
     */
    @Test
    void addWhenInputLengthGreaterOrEqualToOneCarry() {
        assertThat(NumberUtils.add(List.of(9), new ArrayList<>(List.of(9,1)))).isEqualTo(List.of(1,0,0));
        assertThat(NumberUtils.add(new ArrayList<>(List.of(9,9,4)), List.of(6))).isEqualTo(List.of(1,0,0,0));
        assertThat(NumberUtils.add(List.of(7), List.of(3))).isEqualTo(List.of(1,0));
        assertThat(NumberUtils.add(new ArrayList<>(List.of(5,4,3,2)), new ArrayList<>(List.of(4,5,6,8)))).isEqualTo(List.of(1,0,0,0,0));
    }

}