(ns tic-tac-toe.validation-helper-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.board :as board]
            [tic-tac-toe.validation-helper :refer :all]))

(describe "numeric?"
          (it "returns true if string is numeric"
              (should= true (numeric? "123")))
          (it "returns false otherwise"
              (should= false (numeric? "ABC"))))

(describe "on-board?"
          (it "returns true if move is on board"
              (should= true (on-board? (board/new-board) 2)))
          (it "returns false otherwise"
              (should= false (on-board? (board/new-board) 99))))

(describe "available?"
          (it "returns true if space is empty"
              (should= true (available? (board/new-board) 3)))
          (it "returns false otherwise"
              (should= false (available? [:x :x :x :x] 2))))

(describe "single-letter?"
          (it "returns true if string is a single letter"
              (should= true (single-letter? "A")))
          (it "returns false otherwise"
              (should= false (single-letter? "BB"))))
