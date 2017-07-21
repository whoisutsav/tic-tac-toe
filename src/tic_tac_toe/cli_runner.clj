(ns tic-tac-toe.cli-runner
  (:require [tic-tac-toe.console :as console]
            [tic-tac-toe.player :as player]
            [tic-tac-toe.board :as board]
            [tic-tac-toe.decision :as decision]))

(defn- switch-player [player]
  (case player
    :x :o
    :o :x))

(defn- take-turn [board markers current-player]
  (-> board
      (player/get-move (current-player markers) markers)
      (board/apply-move board current-player)))

(defn- complete-game [markers board]
  (if-let [winner (decision/winner board)]
    (console/declare-winner (winner markers))
    (console/declare-draw)))

(defn run [{:keys [board current-player markers]}]
  (if (decision/over? board)
      (complete-game markers board)
      (recur {
              :board (take-turn board markers current-player)
              :current-player (switch-player current-player)
              :markers markers})))


