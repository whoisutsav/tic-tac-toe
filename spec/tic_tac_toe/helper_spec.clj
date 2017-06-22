(ns tic-tac-toe.helper-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.helper :refer :all]))

(describe "prompt"
  (it "gets the user's input"
    (should= "9" (with-in-str
                   "9" (prompt "Enter your move: ")))))

(describe "axis-size"
          (it "returns the axis-size of the board"
              (should= 2 (axis-size [1 2 3 4]))))
