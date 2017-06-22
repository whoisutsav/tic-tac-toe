(ns tic-tac-toe.view-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.view :refer :all]))

(describe "str-format-row"
          (it "returns string of elements in row vector separated by tabs"
              (should= "1\t2" (str-format-row [1 2]))))

(describe "str-format-board"
          (it "returns string formatted rows separated by new lines"
              (should= "1\t2\n3\t4\n" (str-format-board [1 2 3 4]))))

(describe "print-board"
          (it "prints the result of str-format-board"
              (with-redefs [str-format-board (constantly "board")] (should-contain "board" (with-out-str (print-board []))))))
