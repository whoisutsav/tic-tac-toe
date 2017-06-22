(ns tic-tac-toe.game
  (:require [tic-tac-toe.helper :as helper]
            [tic-tac-toe.board :as board]
            [tic-tac-toe.view :as view]
            [tic-tac-toe.move :as move]))

(defn alter-board [board]
  (-> board
       (move/get-move)
       (board/apply-move)))

(defn turn [marker1 marker2 current-player board]
  (view/draw marker1 marker2 board)
  (if-let [winner (game/winner board)]
    (println (str winner " wins."))
    (if (board/filled? board)
      (println "Cats game.")
      (recur marker1 marker2 (switch-player current-player) (alter-board board)))))

(defn new-game [size]
  (game-turn (board/generate-empty-board size) (get-marker 1) (get-marker 2)))
