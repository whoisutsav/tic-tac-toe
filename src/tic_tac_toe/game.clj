(ns tic-tac-toe.game
  (:require [tic-tac-toe.console-output :as console-output]
            [tic-tac-toe.turn :as turn]
            [tic-tac-toe.decision :as decision]))


(defn- complete-game [turn]
  (if-let [winner (decision/winner (:board turn))]
    (console-output/print-winner winner)
    (console-output/print-draw)))

(defn game-loop [turn]
  (console-output/print-board (:markers turn) (:board turn))
  (let [{new-board :board :as  finished-turn} (turn/take-turn turn)]
    (if (decision/over? new-board)
      (complete-game finished-turn)
      (recur finished-turn))))


(defn new-game [board]
  (game-loop {
              :board board
              :markers {:x "X" :o "O"}
              :current-player :x}))
