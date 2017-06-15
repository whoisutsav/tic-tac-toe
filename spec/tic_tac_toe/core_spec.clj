(ns tic-tac-toe.core-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.core :refer :all]))


(describe "prompt"
  (it "gets the user's input"
    (should= "9" (with-in-str
                   "9" (prompt "Enter your move: ")
    ))))

(describe "validate-move"
   (it "returns nil when move is not an long"
       (should= nil (validate-move [] "fridge"))
       )
   (it "returns nil when move is less than 0"
       (should= nil (validate-move [] -1))
       )
   (it "returns nil when move is greater than board size" 
       (should= nil (validate-move [1 2 3 4] 4))
       )
   (it "returns nil when space is occupied on board"
       (should= nil (validate-move [nil nil nil nil "X" nil nil nil nil] 4))
       )
   (it "returns move otherwise"
       (should= 5 (validate-move [nil nil nil nil nil nil nil nil nil] 5))
       )
)

(describe "apply-move"
   (it "returns a new board with the move applied"
       (should= [nil nil "X" nil nil nil nil nil] 
                (apply-move [nil nil nil nil nil nil nil nil] 2 "X"))
       ))

(describe "get-board-row"
          (it "returns the row of the board"
              (should= [1 2 3] (get-board-row 1 ["A" "B" "C" 1 2 3 "X" "Y" "Z"]))
              )
          )

(describe "get-board-column"
          (it "returns the column of the board"
              (should= ["B" 2 "Y"] (get-board-column 1 ["A" "B" "C" 1 2 3 "X" "Y" "Z"]))
              )
          )

(describe "get-board-diagonal"
          (it "returns the diagonal of the board"
              (should= ["C" 2 "X"] (get-board-diagonal -1 ["A" "B" "C" 1 2 3 "X" "Y" "Z"]))
              )
          )

(describe "all-markers-same?"
          (it "returns true if all the values match"
              (should= true (all-markers-same? ["X" "X" "X"]))
              )
          (it "returns false if not all the values match"
              (should= false (all-markers-same? ["A" "X" "X"]))
              )
          (it "returns false if all values are empty"
              (should= false (all-markers-same? [nil nil nil]))
              )
          )

(describe "axis-size"
          (it "returns the axis size of a given board"
              (should= 2 (axis-size [0 1 2 3]))
              )
          )

(describe "win?"
          (it "returns false if no row, column, or diagonal matches"
            (should= nil (win? [nil nil nil nil nil nil nil nil nil])))
        (it "returns true if a row matches"
            (should= true (win? [nil nil nil "X" "X" "X" nil nil nil])))
        (it "returns true if a column matches"
            (should= true (win? [nil "X" nil nil "X" nil nil "X" nil])))
        (it "returns true if a diagonal matches"
            (should= true (win? [nil nil "X" nil "X" nil "X" nil nil])))

          )

