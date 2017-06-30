(ns tic-tac-toe.validation-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.validation :refer :all]))


(describe "move"
          (it "returns error if move is not numeric"
              (should= (move-errors :not-numeric) (:error (move [] "5D"))))
          (it "returns error if move is not on board"
              (should= (move-errors :not-on-board) (:error (move [:x :x :o :o] "4"))))
          (it "returns error if space is not available"
              (should= (move-errors :not-available) (:error (move [:x :x :o :o] "1")))
              )
          (it "returns no error if move is valid"
              (should= nil (:error (move [:_ :_ :_ :_] "0")))))


(describe "marker"
          (it "returns error if marker is not single letter"
              (should= (marker-errors :not-single-letter) (:error (marker "BB"))))
          (it "returns marker if valid"
              (should= nil (:error (marker "S")))))
