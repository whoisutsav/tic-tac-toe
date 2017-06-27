(ns tic-tac-toe.console-input-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.console-input :refer :all]))

(describe "prompt-for-move"
  (it "gets and trims the user's input"
    (should= "9" (with-in-str
                   " 9\t" (prompt-for-move "X")))))

