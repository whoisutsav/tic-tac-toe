(ns tic-tac-toe.game
  (:require [tic-tac-toe.console-output :as console-output]
            [tic-tac-toe.turn :as turn]
            [tic-tac-toe.decision :as decision]))

(defn game-loop [turn]
  (let [{:keys [markers board]} turn] (console-output/print-board markers turn)
  (let [{new-board :board :as  finished-turn} (turn/take-turn turn)]
    (if-let [winner (decision/winner new-board)]
      (console-output/print-message (str winner " wins."))
      (if (decision/no-more-moves? new-board)
             (console-output/print-message "Cats game.")
             (recur finished-turn))))))


(defn new-game [board]
  (game-loop {
              :board board
              :markers {:x "X" :o "O"}
              :current-player :x}))
