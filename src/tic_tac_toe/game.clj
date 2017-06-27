(ns tic-tac-toe.game
  (:require [tic-tac-toe.console-output :as console-output]
            [tic-tac-toe.turn :as turn]
            [tic-tac-toe.decision :as decision]))

(defn game-loop [turn]
  (console-output/print-board (:markers turn) (:board turn))
  (let [finished-turn (turn/take-turn turn)]
    (if-let [winner (decision/winner (:board finished-turn))]
      (console-output/print-message (str winner " wins."))
      (if (decision/no-more-moves? (:board finished-turn))
             (console-output/print-message "Cats game.")
             (recur finished-turn)))))


(defn new-game [board]
  (game-loop {
              :board board
              :markers {:x "X" :o "O"}
              :current-player :x}))
