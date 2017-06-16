(ns tic-tac-toe.core-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.core :refer :all]
            [clojure.string :as str]
            ))

(describe "prompt"
  (it "gets the user's input"
    (should= "9" (with-in-str
                   "9" (prompt "Enter your move: ")
    ))))

(describe "other-marker"
          (it "returns other marker"
             (should= "X" (other-marker "O")) 
              )
          )

(describe "axis-size"
          (it "returns the axis-size of the board"
              (should= 2 (axis-size [1 2 3 4]))
              )
          )
(describe "generate-empty-board"
          (it "returns a board populated by nils with size axis-size*axis-size"
              (should= [nil nil nil nil] (generate-empty-board 2))
              )
          )

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

(describe "get-move"
          (it "prints board"
             (with-redefs [str-format-board (constantly "formatted-board")
                          validate-move (constantly "validated-move")
]
                (should-contain "formatted-board" (with-in-str "0"  (with-out-str (get-move [] "X"))))
              ))
          (it "validates move"
             (with-redefs [str-format-board (constantly "formatted-board")
                          validate-move (constantly "validated-move")
]
                (should= "validated-move" (with-in-str "0"  (get-move [] "X")))
              ))
          (it "recurs if move is invalid"
             (with-redefs [str-format-board (constantly "formatted-board")
                          validate-move (fn [board move] (if (= "first-move" (str move)) nil (str move))) 
]
                (should= "second-move" (with-in-str "first-move\nsecond-move"  (get-move [] "X")))
              ))
          
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

(describe "str-format-row"
          (it "returns string of elements in row vector separated by tabs"
              (should= "1\t2" (str-format-row [1 2]))
              )
          )

(describe "str-format-board"
          (it "returns string formatted rows separated by new lines"
              (should= "1\t2\n3\t4\n" (str-format-board [1 2 3 4]))
              )
          )

(describe "axis-size"
          (it "returns the axis size of a given board"
              (should= 2 (axis-size [0 1 2 3]))
              )
          )

(describe "win?"
          (it "returns false if no row, column, or diagonal matches"
            (with-redefs [all-markers-same? (constantly false)] (should= nil (win? []))))
        (it "returns true if a row matches"
            (with-redefs [all-markers-same? identity
                          get-board-rows (constantly [true])
                          get-board-columns (constantly [false])
                          get-board-diagonals (constantly [false])
                          ] (should= true (win? []))))
        (it "returns true if a column matches"
            (with-redefs [all-markers-same? identity
                          get-board-rows (constantly [false])
                          get-board-columns (constantly [true])
                          get-board-diagonals (constantly [false])
                          ] (should= true (win? []))))
        (it "returns true if a diagonal matches"
            (with-redefs [all-markers-same? identity
                          get-board-rows (constantly [false])
                          get-board-columns (constantly [false])
                          get-board-diagonals (constantly [true])
                          ] (should= true (win? []))))
          )

(describe "new-game"
          (with-stubs)
         (it "returns a game-turn with a new board"
            (with-redefs [generate-empty-board (constantly "empty")
                          game-turn (stub :game-stub (:return "game-turn"))
                          ]  
                          (and 
                            (should= "game-tun" (new-game 3))))
             ) 
          )

