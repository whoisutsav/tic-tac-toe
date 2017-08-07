(ns tic-tac-toe.board-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.board :refer :all]))

(describe "size"
          (it "returns size of board"
              (should= 9 (size [:_ :_ :_
                                :_ :_ :_
                                :_ :_ :_ ]))))

(describe "new-board"
          (it "returns an empty board"
              (should= [
                        :_ :_ :_
                        :_ :_ :_
                        :_ :_ :_ ] (new-board))))

(describe "put-marker"
          (it "returns a board with move applied"
              (should= [
                        :x :_ :_
                        :_ :_ :_
                        :_ :_ :_ ] (put-marker [:_ :_ :_
                                                :_ :_ :_
                                                :_ :_ :_ ] 1 :x))))

(describe "get-marker"
          (it "returns marker if cell is occupied"
              (should= :x (get-marker 1 [:x :_ :_
                                         :_ :_ :_
                                         :_ :_ :_ ])))
          (it "returns nil if cell is empty"
              (should= nil (get-marker 1 [:_ :_ :_
                                          :_ :_ :_
                                          :_ :_ :_ ]))))

(describe "get-empty-spaces"
          (it "returns a vector of the empty cells"
              (should= [1 3 4 5] (get-empty-spaces [:_ :X :_
                                                :_ :_ :O
                                                :X :O :X ]))))

