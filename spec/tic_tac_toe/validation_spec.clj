(ns tic-tac-toe.validation-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.validation :refer :all]))


(describe "move"
          (it "validates move is numeric"
              (should= {:error "Move must be a number"} (move [] "5D")))
          (it "validates move is on board"
              (should= {:error "Move must be on board"} (move [:_ :_ :_ 
                                                               :_ :_ :_
                                                               :_ :_ :_] "0")))
          (it "validates space is available"
              (should= {:error "Space is occupied"} (move [:x :_ :_ 
                                                           :_ :_ :_
                                                           :_ :_ :_] "1")))
          (it "returns empty error hash if move is valid"
              (should= {:error nil} (move [:_ :_ :_ 
                                           :_ :_ :_
                                           :_ :_ :_] "1"))))


(describe "marker"
          (it "validates marker is a single letter"
              (should= {:error "Marker must be a single letter"} (marker "BB" nil)))
          (it "validates marker is not taken"
              (should= {:error "Marker taken. Please choose a different marker"} (marker "B" "B")))
          (it "returns empty error hash if marker is valid"
              (should= {:error nil} (marker "S" nil))))
