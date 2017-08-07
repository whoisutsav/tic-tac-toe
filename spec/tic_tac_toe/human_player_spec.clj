(ns tic-tac-toe.human-player-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.player :refer :all]
            [tic-tac-toe.human-player :refer :all]))

; TODO is there a way not to require both player and human-player

(describe "get-move human"
          (it "prints error if move is invalid" 
              (should-contain "Move must be a number" (with-out-str (with-in-str "Z\n1" (get-move [:_ :_ :_ :_] {:type :human})))))

          (it "keeps getting user input until move is valid" (with-out-str (should= 2 (with-in-str "Z\n-1\n8\n0\n    2  \t " (get-move [:x :_ :_ :_] {:type :human}))))))


;TODO move somewhere else
(describe "get-marker"
          (around [it] (with-out-str (it)))
          (it "prompts the user"
              (should-contain "Player 2, please enter marker:"
                              (with-out-str (with-in-str "A\n"
                                              (get-marker :opponent)))))
          (it "gets the player's marker until it's valid"
              (should= :Z (with-in-str "0\nBB\nZ\n" (get-marker 1)))))
