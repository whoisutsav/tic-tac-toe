(ns tic-tac-toe.validation-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.validation :refer :all]))


(describe "move"
          (it "returns error if move is not numeric"
              (should= (error-messages :not-numeric) (:error (move [] "5D"))))
          (it "returns error if move is not on board"
              (should= (error-messages :not-on-board) (:error (move [:x :x :o :o] "5"))))
          (it "returns error if space is not available"
              (should= (error-messages :not-available) (:error (move [:x :x :o :o] "1")))
              )
          (it "returns no error if move is valid"
              (should= nil (:error (move [:_ :_ :_ :_] "0")))))
