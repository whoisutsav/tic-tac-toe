(ns tic-tac-toe.cli-runner
  (:require [tic-tac-toe.console :as console]
            [tic-tac-toe.player :as player]
            [tic-tac-toe.board :as board]
            [tic-tac-toe.decision :as decision]))

(defn- switch-player [player]
  (case player
    :x :o
    :o :x))


(defn- take-turn [board players current-player]
  (console/print-board players board)
  (let [move (player/get-move board (current-player players))]
    (board/apply-move move board current-player)))

(defn- complete-game [players board]
  (if-let [winner (decision/winner board)]
    (console/declare-winner (winner players))
    (console/declare-draw)))

(defn run [{:keys [board current-player players]}]
  (if (decision/over? board)
      (complete-game players board)
      (recur {
              :board (take-turn board players current-player)
              :current-player (switch-player current-player)
              :players players})))


