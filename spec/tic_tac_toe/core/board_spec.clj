(ns tic-tac-toe.core.board-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.core.board :refer :all]))

(describe "size"
          (it "returns size of board"
              (should= 3 (size [:_ :_ :_
                                :_ :_ :_
                                :_ :_ :_ ]))))

(describe "new-board"
          (it "returns an empty board"
              (should= [
                        :_ :_ :_
                        :_ :_ :_
                        :_ :_ :_ ] (new-board 3))))

(describe "put-marker"
          (it "returns a board with move applied"
              (should= [
                        :x :_ :_
                        :_ :_ :_
                        :_ :_ :_ ] (put-marker [:_ :_ :_
                                                :_ :_ :_
                                                :_ :_ :_ ] :x 1))))

(describe "get-marker"
          (it "returns marker if cell is occupied"
              (should= :x (get-marker 1 [:x :_ :_
                                         :_ :_ :_
                                         :_ :_ :_ ])))
          (it "returns nil if cell is empty"
              (should= nil (get-marker 1 [:_ :_ :_
                                          :_ :_ :_
                                          :_ :_ :_ ]))))

(describe "empty-spaces"
          (it "returns a vector of the empty cells"
              (should= [1 3 4 5] (empty-spaces [:_ :X :_
                                                :_ :_ :O
                                                :X :O :X ]))))

