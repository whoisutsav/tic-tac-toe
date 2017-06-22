(ns tic-tac-toe.game-spec
  (require [speclj.core :refer :all])
  (require [tic-tac-toe.game :refer :all]))

(describe "get-marker"
          (it "gets the user's marker" (should= "D" (with-in-str "D" (get-marker 1))))
          (it "gets marker again if user's marker is more than one character" (should= "B" (with-in-str "ZZ\nB" (get-marker 1)))))


;(describe "new-game"
;          (with-stubs)
;         (it "calls game-turn with a new board"
;            (with-redefs [generate-empty-board (constantly "empty")
;                          game-turn (constantly "turn")
;                          get-marker (constantly "Z")
;                          ]  
;                            (should= "turn" (game-turn 3 "Z" "Z")))))

;(describe "game-turn"
;          (it "prints out board if game is over"
;              (with-redefs [str-format-board (constantly "board")
;                            win? (constantly true) ]
;                (should-contain "board" (with-out-str (game-turn [] "X" "Z")))))
;          (it "prints out win message if game is over"
;              (with-redefs [str-format-board (constantly "board")
;                            win? (constantly true) ]
;                (should-contain "Z WINS!" (with-out-str (game-turn [] "X" "Z"))))))
