(ns tic-tac-toe.validation-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.validation :refer :all]))


(describe "move"
          (it "returns error if move is not numeric"
              (should= "Move must be a number" (:error (move [] "5D"))))
          (it "returns error if move is not on board"
              (should= "Move must be between 1 and size of board" (:error (move [:x :x :o :o] "0"))))
          (it "returns error if space is not available"
              (should= "Space is occupied" (:error (move [:x :x :o :o] "1")))
              )
          (it "returns no error if move is valid"
              (should= nil (:error (move [:_ :_ :_ :_] "1")))))


(describe "marker"
          (it "returns error if marker is not single letter"
              (should= "Marker must be a single letter" (:error (marker "BB"))))
          (it "returns marker if valid"
              (should= nil (:error (marker "S")))))
