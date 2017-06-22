(ns tic-tac-toe.board-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.board :refer :all]))

(describe "axis-size"
          (it "returns the axis-size of the board"
              (should= 2 (axis-size [1 2 3 4]))))


(describe "generate-empty-board"
          (it "returns a board populated by nils with size axis-size*axis-size"
              (should= [:_ :_ :_ :_] (generate-empty-board 2))))

(describe "apply-move"
          (it "returns a board with move applied"
              (should= [mark1 :_ :_ :_] (apply-move [:_ :_ :_ :_] 0 1))))

(describe "player-at"
  (it "returns player at occupied cell"
      (should= 2 (player-at 1 [mark1 mark2 :_ :_]))))
