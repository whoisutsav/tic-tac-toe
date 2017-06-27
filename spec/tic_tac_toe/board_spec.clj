(ns tic-tac-toe.board-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.board :refer :all]))

(describe "new-board"
          (it "returns an empty board"
              (should= [
                        :_ :_ :_
                        :_ :_ :_
                        :_ :_ :_ ] (new-board))))

(describe "apply-move"
          (it "returns a board with move applied"
              (should= [
                        :x :_ :_
                        :_ :_ :_
                        :_ :_ :_ ] (apply-move 0 
                                               [:_ :_ :_
                                                :_ :_ :_
                                                :_ :_ :_ ] :x))))

(describe "empty-space?"
          (it "returns false if cell is occupied"
              (should= false (empty-space? 0 [:x :_ :_
                                              :_ :_ :_
                                              :_ :_ :_ ])))
          (it "returns true if cell is not occupied"
              (should= true (empty-space? 0 [:_ :_ :_
                                             :_ :_ :_
                                             :_ :_ :_ ]))))


