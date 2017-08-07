(ns tic-tac-toe.computer-player-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.player :refer :all]
            [tic-tac-toe.computer-player :refer :all])) 

;TODO is there a way to not require both player and computer-player?

(describe "get-move computer"
          (it "finds an empty space on the board"
              (with-out-str (should= 4 (get-move [:x :o :x :_] {:type :computer})))))
