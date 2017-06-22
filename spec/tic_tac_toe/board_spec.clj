(ns tic-tac-toe.board-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.board :refer :all]))


(describe "generate-empty-board"
          (it "returns a board populated by nils with size axis-size*axis-size"
              (should= [nil nil nil nil] (generate-empty-board 2))))

(describe "get-board-row"
          (it "returns the row of the board"
              (should= [1 2 3] (get-board-row 1 ["A" "B" "C" 1 2 3 "X" "Y" "Z"]))))

(describe "get-board-column"
          (it "returns the column of the board"
              (should= ["B" 2 "Y"] (get-board-column 1 ["A" "B" "C" 1 2 3 "X" "Y" "Z"]))))

(describe "get-board-diagonal"
          (it "returns the diagonal of the board"
              (should= ["C" 2 "X"] (get-board-diagonal -1 ["A" "B" "C" 1 2 3 "X" "Y" "Z"]))))

(describe "apply-move"
   (it "returns a new board with the move applied"
       (should= [nil nil "X" nil nil nil nil nil] 
                (apply-move [nil nil nil nil nil nil nil nil] 2 "X"))))

(describe "all-markers-same?"
          (it "returns true if all the values match"
              (should= true (all-markers-same? ["X" "X" "X"])))
          (it "returns false if not all the values match"
              (should= false (all-markers-same? ["A" "X" "X"])))
          (it "returns false if all values are empty"
              (should= false (all-markers-same? [nil nil nil]))))


