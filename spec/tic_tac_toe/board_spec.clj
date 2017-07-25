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
                        :_ :_ :_ ] (apply-move 1 
                                               [:_ :_ :_
                                                :_ :_ :_
                                                :_ :_ :_ ] :x))))

(describe "bget"
          (it "returns marker if cell is occupied"
              (should= :x (bget 1 [:x :_ :_
                                   :_ :_ :_
                                   :_ :_ :_ ])))
          (it "returns nil if cell is empty"
              (should= nil (bget 1 [:_ :_ :_
                                    :_ :_ :_
                                    :_ :_ :_ ]))))


